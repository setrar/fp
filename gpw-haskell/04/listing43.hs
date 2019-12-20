ifEven myFunction x = if even x
                      then myFunction x
                      else x

inc n = n + 1
double n = n*2
square n = n^2

ifEvenInc n = ifEven inc n
ifEvenDouble n = ifEven double n
ifEvenSquare n = ifEven square n


