


### Functional Programming Concepts <sup><img src="../images/602px-Haskell-Logo.svg.png" width=37 height=26><img></sup>

|  operator   |   Haskell Definition <sup><img src="../images/602px-Haskell-Logo.svg.png" width=37 height=26><img></sup>   |  Elm <sup><img src="../images/elm-logo.png" width=28px height=28px><img></sup> |
|:-----------:|-------------------------------------------------------|------------|
| `.`         |  `(.) :: (b -> c) -> (a -> b) -> a -> c`              | `(<\|) <fn> : (a -> b) -> a -> b` |
| `.`         |  `(.) :: (b -> c) -> (a -> b) -> a -> c`              | `(>>) <fn> : (b -> c) -> (a -> b) -> a -> c` |
| `<>`        |  `(<>) :: Semigroup a => a -> a -> a`                 |          |
