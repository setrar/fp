-- BR

import Data.List
import Data.Semigroup

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


instance Semigroup Integer where
   (<>) x y = x + y

data Color = Red |
   Yellow |
   Blue |
   Green |
   Purple |
   Orange |
   Brown deriving (Show,Eq)

instance Semigroup Color where
   (<>) Red Blue = Purple
   (<>) Blue Red = Purple
   (<>) Yellow Blue = Green
   (<>) Blue Yellow = Green
   (<>) Yellow Red = Orange
   (<>) Red Yellow = Orange
   (<>) a b = if a == b
              then a
              else Brown

main = do
   print (myLast [1,2,4])
   print (myAny (\a -> a `mod` 2 == 0) [1,5,8,4,10])
   print (myMin [5,7,8,3,9,0,14])
   print (myAll (>=5) [46,57,8,5])
   print (Red <> Yellow)
