ifEven f x = if even x
             then f x
             else x

-- closure
genIfXEven x = (\f -> ifEven f x)

-- ghci> genIfXEven 4 (\x -> x + 5)


add4 a b c d = a + b + c + d

-- partial application
-- ghci> mystery = add4 3
-- ghci> mystery 2 3 4

