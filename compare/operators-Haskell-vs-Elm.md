


### Functional Programming Concepts <sup><img src="../images/602px-Haskell-Logo.svg.png" width=37 height=26><img></sup>

|  operator   | Type Class  |  Haskell Definition <sup><img src="../images/602px-Haskell-Logo.svg.png" width=37 height=26><img></sup> | Scala <img src="../images/Scala_logo.png" width=72px height=50px><img>                              | function | Comments        |
|:-----------:|-------------|------------------------------------------------------|----------|-----------------|
| `.`         | Compose     | `(.) :: (b -> c) -> (a -> b) -> a -> c`              |          |                 |
| `<>`        | [Semigroup](https://wiki.haskell.org/Typeclassopedia#Semigroup)    | `(<>) :: Semigroup a => a -> a -> a`                 |          | alias for `mappend` |
