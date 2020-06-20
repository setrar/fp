


### Functional Programming Concepts <sup><img src="../images/602px-Haskell-Logo.svg.png" width=37 height=26><img></sup>

|  operator   | Type Class  |  Haskell Definition <sup><img src="../images/602px-Haskell-Logo.svg.png" width=37 height=26><img></sup>   |  Elm <sup><img src="../images/elm-logo.png" width=28px height=28px><img></sup> |
|:-----------:|-------------|------------------------------------------------------|------------|
| `.`         | Compose     | `(.) :: (b -> c) -> (a -> b) -> a -> c`              | `(<\|) <function> : (a -> b) -> a -> b` |
| `.`         | Compose     | `(.) :: (b -> c) -> (a -> b) -> a -> c`              | `(>>)<function> : (a -> b) -> (b -> c) -> a -> c`|
| `<>`        | [Semigroup](https://wiki.haskell.org/Typeclassopedia#Semigroup)    | `(<>) :: Semigroup a => a -> a -> a`                 |          |
