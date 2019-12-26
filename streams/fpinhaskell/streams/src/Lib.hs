module Lib ( someFunc, factorial, factorial', factorial'' ) where

someFunc :: IO ()
someFunc = putStrLn "dude ain't got nothing"

factorial :: Int -> Int
factorial 0 = 1
factorial n = n * factorial (n-1)

factorial' :: Int -> Int
factorial' n = go n 1
  where go 0 acc = acc
        go n acc = go (n-1) (n * acc)

factorial'' :: Int -> Int
factorial'' n =
  let go 0 acc = acc
      go n acc = go (n-1) (n * acc)
  in go n 1

