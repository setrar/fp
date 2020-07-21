module Main where

import Data.List ( sort, sortBy )
import Data.Ord (comparing)
import Data.Maybe
import Control.Monad (msum)
import System.IO
import System.Random
import Options.Applicative

data Player = P1 | P2 deriving (Show, Eq)

otherPlayer:: Player -> Player
otherPlayer P1 = P2
otherPlayer P2 = P1

data Result = Pl1 | Draw | Pl2 deriving (Show, Eq, Ord)

data Grid = Grid [Maybe Player]

type PickMove = (Grid -> Player -> IO Grid)

newGrid = Grid (replicate 9 Nothing)

instance Show Grid where
    show (Grid g) = tableify 0 $ map toSym g
        where toSym (Just P1) = 'X'
              toSym (Just P2) = 'O'
              toSym Nothing = '.'

tableify :: Int -> String -> String
tableify _ [] = "\n"
tableify ix (x:xs) = 
    let shw =  x:' ':tableify (ix + 1) xs in
        case ix `mod` 3 of
          0 -> '\n':shw
          _ -> shw


move :: Grid -> Player -> Int -> Maybe Grid
move _ _ ix | ix > 9 = Nothing
move (Grid (Nothing:rest)) p 0 = Just $ Grid (Just p:rest)
move _ _ 0  = Nothing
move (Grid (x:xs)) p ix =
    move (Grid xs) p (ix - 1) >>= \(Grid g) -> Just $ Grid (x:g)


chkTriplet :: Maybe Player -> Maybe Player -> Maybe Player -> Maybe Result
chkTriplet v1 v2 v3 | isJust v1 && v1 == v2 && v2 == v3 = Just $ winner v1
                    | otherwise = Nothing

chkDraw :: Grid -> Maybe Result
chkDraw (Grid (Nothing:xs)) = Nothing
chkDraw (Grid (x:xs)) = chkDraw (Grid xs)
chkDraw (Grid []) = Just Draw

chkRow :: Grid -> Int -> Maybe Result
chkRow (Grid g) ix = chkTriplet v1 v2 v3
   where ix3 = 3 * ix
         v1 = g !! ix3
         v2 = g !! (ix3 + 1)
         v3 = g !! (ix3 + 2)

chkCol :: Grid -> Int -> Maybe Result
chkCol (Grid g) ix = chkTriplet v1 v2 v3
   where v1 = g !! ix
         v2 = g !! (ix + 3)
         v3 = g !! (ix + 6)

chkDiags :: Grid -> [Maybe Result]
chkDiags (Grid g) = [ulbr, urbl]
    where ulbr = chkTriplet (g !! 0) (g !! 4) (g !! 8)
          urbl = chkTriplet (g !! 2) (g !! 4) (g !! 6)

chkWin :: Grid -> Maybe Result
chkWin g = msum [rowWin, colWin, diagWin, chkDraw g]
    where rowWin = msum $ map (chkRow g) [0..2]
          colWin = msum $ map (chkCol g) [0..2]
          diagWin = msum $ chkDiags g

winner :: Maybe Player -> Result
winner (Just P1) = Pl1
winner (Just P2) = Pl2


readSafe :: (Read a) => String -> IO a
readSafe prompt = do
    putStrLn prompt
    rd <- getLine 
    case readMaybe rd of
      Just x -> return x
      Nothing -> do
          putStrLn "Invalid input"
          readSafe prompt


randomMove :: PickMove
randomMove grid p = do 
    let choices = possGrids grid p
    mvix <- randomRIO (0, length choices - 1)
    return $ choices !! mvix

minimaxMove :: PickMove
minimaxMove g p = do
    mvix <- randomRIO (0, length choices - 1)
    return . snd $ choices !! mvix
    where outcomes = gridOutcomes g p
          sorted = sortBy (comparing fst) outcomes
          bestf = if p == P1 then head else last
          bestres = fst . bestf $ sorted
          choices = filter (\(s, g) -> s == bestres) sorted


-- returns the result of a grid assuming perfect play from both players
evalGrid :: Grid -> Player -> Result
evalGrid g p = case p of
        P1 ->  minimum outcomes
        P2 ->  maximum outcomes
    where outcomes = map fst $ gridOutcomes g p

gridOutcomes :: Grid -> Player -> [(Result, Grid)]
gridOutcomes grid p = map (\g -> resolve (chkWin g) g) (possGrids grid p)
    where resolve (Just res) g = (res, g)
          resolve Nothing g = (evalGrid g (otherPlayer p), g)

possGrids :: Grid -> Player -> [Grid]
possGrids g p = mapMaybe (move g p) [0..8]

userMove :: PickMove
userMove g p = do
    mv <- subtract 1 <$> readSafe "Make your move!"
    case move g p mv of
      Just g -> return g
      Nothing -> do 
          putStrLn "Invalid move"
          print g
          userMove g p


readMaybe :: (Read a) => String -> Maybe a
readMaybe s = case reads s of
                [(x, "")] -> Just x
                _ -> Nothing


gameLoop :: (PickMove, PickMove) -> Grid -> Player -> IO (Grid, Result)
gameLoop (pm1, pm2) g p = do
    print g
    g <- case p of
      P1 -> pm1 g p
      P2 -> pm2 g p
    case chkWin g of
      Just res -> return (g, res)
      Nothing -> gameLoop (pm1, pm2) g $ otherPlayer p


-- CLI gubbins --

data AI = User | Random | Minimax deriving (Read, Show)

selectAI :: AI -> PickMove
selectAI User = userMove
selectAI Random = randomMove
selectAI Minimax = minimaxMove

data Game = Game AI AI deriving Show

players :: Parser Game
players = Game
  <$> argument auto (metavar "P1")
  <*> argument auto (metavar "P2")

main :: IO ()
main = do
    Game p1 p2 <- execParser opts
    let pms = (selectAI p1, selectAI p2)
    (g, winner) <- gameLoop pms newGrid P1
    print g
    case winner of
      Draw -> putStrLn "Draw!"
      _ -> putStrLn $ "Winnar! Congratulations " ++ show winner
  where
    opts = info (helper <*> players)
      ( fullDesc
     <> progDesc "Play Tic-Tac-Toe"
     <> header "noughts - a tic-tac-toe game in haskell")
