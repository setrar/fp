# Comparison

#### :m: Compare Haskell vs Scala REPL `based on `[LYAH book](http://learnyouahaskell.com/)

| Feature         | Haskell      | Scala         |
|-----------------|--------------|---------------|
| [List](https://en.wikipedia.org/wiki/List_(abstract_data_type)) Construct  |              |               |
|                 | [ ]          | List() or Nil |
|                 | [1,2]        | List(1,2)     |
|                 | 1 : 2 : [ ]  | 1 :: 2 :: Nil |
| Concatenation   |              |               |
|                 | 'A' : "SMALL CAT" | ❓              |
|                 | 5 : [1,2,3,4,5]    | 5 :: List(1,2,3,4,5) | 
|                 | [1,2,3,4] ++ [5]   | List(1,2,3,4) ++ List(5) | 
|                 | [[1,2],[3,4],[5,6]]|List(List(1,2),List(3,4),List(5,6))|
| Access          |              |               |
|                 | [1,2,3,4]!!0 | List(1,2,3,4)(0) | 
|                 | [[1,2],[3,4],[5,6]]!!0 | List(List(1,2),List(3,4),List(5,6))(0) |


