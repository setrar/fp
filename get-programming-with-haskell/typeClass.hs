data ShirtSize = S | M | L      -- Here ShirtSize is a enum data-type

class MyEq a where
  (==) :: a -> a -> Bool
instance MyEq ShirtSize where   -- Here ShirtSize is an instance of the MyEq type-class
  S == S = True
  M == M = True
  L == L = True
  _ == _ = False


