# Comparison

#### :m: Compare Haskell vs Scala REPL `based on `[LYAH book](http://learnyouahaskell.com/)

| Feature         | Haskell                               | Scala                                                 |
|-----------------|---------------------------------------|-------------------------------------------------------|
| [List](https://en.wikipedia.org/wiki/List_(abstract_data_type)) Construct  |              |                     |
|                 | [ ]                                   | List() or Nil                                         |
|                 | [1,2]                                 | List(1,2)                                             |
|                 | 1 : 2 : [ ]                           | 1 :: 2 :: Nil                                         |
| Concatenation   |                                       |                                                       |
|                 | 'A' : "SMALL CAT"                     | ❓                                                    |
|                 | 5 : [1,2,3,4,5]                       | 5 :: List(1,2,3,4,5)                                  | 
|                 | [1,2,3,4] ++ [5]                      | List(1,2,3,4) ++ List(5)                              | 
|                 | [[1,2],[3,4],[5,6]]                   | List(List(1,2),List(3,4),List(5,6))                   |
| Access          |                                       |                                                       |
|                 | "Steve Buscemi" !! 6                  | "Steve Buscemi" (6)                                   |
|                 | [1,2,3,4] !! 0                        | List(1,2,3,4) (0)                                     | 
|                 | [[1,2],[3,4],[5,6]] !! 0              | List(List(1,2),List(3,4),List(5,6)) (0)               |
|                 | let b = [[1,2,3,4],[5,3,3,3],[1,2,3]] | val b = List(List(1,2,3,4),List(5,3,3,3),List(1,2,3)) |
|                 | b ++ [[1,1,1,1]]                      | b ++ List(List(1,1,1,1))                              | 
| Comparisons     |                                       |                                                       |
|                 | [1,2,3] <= [1,2,3]                    | List(1,2,3) <= List(1,2,3) [<sup>**Ordering</sup>](##Ordering) | 
| Operations      |                                       |                                                       |
|                 | head [5,4,3,2,1]                      | List(5,4,3,2,1).head                                  |
|                 | tail [5,4,3,2,1]                      | List(5,4,3,2,1).tail                                  |


## **Ordering: 
    Add the following Implicit Ordering Import.
    
    import Ordering.Implicits._



