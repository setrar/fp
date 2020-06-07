-- https://www.tutorialspoint.com/haskell/haskell_function_composition.htm

eveno :: Int -> Bool 
eveno x = if x `rem` 2 == 0 
          then True 
          else False 

noto  :: Bool -> String 
noto x = if x == True 
         then "This is an even Number" 
         else "This is an ODD number" 

main = do 
   putStrLn "Example of Haskell Function composition" 
   print ((noto.eveno)(16))
