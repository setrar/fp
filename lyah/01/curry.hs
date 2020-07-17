f :: (Num a) => a -> a
f x = x + 1

f' :: (Num a) => a -> a -> a 
f' a b = a + b


f'' :: (Num a) => a -> ( a -> a )
f'' a b = a + b

-- https://wiki.haskell.org/Partial_application

add :: Int -> Int -> Int
add x y = x + y

-- addOne = add 1

add' :: Int -> (Int -> Int)
add' x y = x + y

-- https://en.wikipedia.org/wiki/Operator_associativity (left or right)

-- https://en.wikipedia.org/wiki/Function_application is left associative
