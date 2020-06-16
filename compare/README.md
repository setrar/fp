# Comparison

#### :m: Compare Haskell vs Scala REPL `based on `[LYAH book](http://learnyouahaskell.com/)

[Version](Version.md)


#### :m: API

| Data Structure  | Description                                                                          | Comments |
|-----------------|--------------------------------------------------------------------------------------|----------|
| [List](List.md) | [List (abstract data type)](https://en.wikipedia.org/wiki/List_(abstract_data_type)) |          |
| [Range](Range.md) | [Set Builder Notation](https://en.wikipedia.org/wiki/Set-builder_notation) |  Set, For   |
| [Comprehension](Comprehension.md) | [Set Builder Notation](https://en.wikipedia.org/wiki/Set-builder_notation) |  Set, For         |

### Functional Programming Concepts <sup><img src="../images/602px-Haskell-Logo.svg.png" width=37 height=26><img></sup>

|  sign  | Type Class |  Type Definition                               | method |
|:------:|---------|------------------------------------------------|-------|
| `.`    | Compose | `(.) :: (b -> c) -> (a -> b) -> a -> c`        |       |
| `<$>`  | Functor | `(<$>) :: Functor f => (a -> b) -> f a -> f b` | fmap  |
