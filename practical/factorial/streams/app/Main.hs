module Main where

import Lib
import Adt.List
import Data.List

main :: IO ()
main = do {
    someFunc;
    print (factorial 4);
    print (factorial' 5);
    print (factorial'' 6);
    putStrLn "Hello world!!";
--    print (intersperse ',' "abcde")
--    n <- readLn ; print (n^2)
  }
