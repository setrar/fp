-- BR

import Data.List

myLast :: [a] -> a
myLast = head . reverse

myMin :: Ord a => [a] -> a
myMin = head . sort

myMax :: Ord a => [a] -> a
myMax = myLast . sort

myAll :: (a -> Bool) -> [a] -> Bool
myAll testFunc = (foldr (&&) True) . (map testFunc)

myAny :: (a -> Bool) -> [a] -> Bool
myAny testFunc = (foldr (||) False) . (map testFunc)

main = do
   print (myLast [1,2,4])
   print (myAny (\a -> a `mod` 2 == 0) [1,5,8,4,10])
   print (myMin [5,7,8,3,9,0,14])
   print (myAll (>=5) [46,57,8,5])
