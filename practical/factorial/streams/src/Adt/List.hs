module Adt.List ( headOption ) where

{-  see below syntax sugar
data List a = Nil | Cons a (List a)
head (Cons h _) = h
tail (Cons _ t) = t
-}

data List a = Nil | Cons { head :: a, tail :: List a }

data Option a
  = None
  | Some a

-- ****************       List combinators   *************************

headOption :: List a -> Option a
headOption Nil = None
headOption (Cons h t) = Some h

