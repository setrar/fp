ifEven myFunction x = if even x
                      then myFunction x
                      else x

inc n = n + 1
double n = n*2
square n = n^2

-- Closure on f
genIfEven f = (\x -> ifEven f x)

ifEvenInc = genIfEven inc
ifEvenDouble = genIfEven double
ifEvenSquare = genIfEven square

-- Prelude> ifEvenInc 4
-- Prelude> ifEvenDouble 9


