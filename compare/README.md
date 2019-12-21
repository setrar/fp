# Comparison

#### :m: Compare Haskell vs Scala REPL `based on `[LYAH book](http://learnyouahaskell.com/)

| Feature         | Haskell                               | Scala                                                 |
|-----------------|---------------------------------------|-------------------------------------------------------|
| [List](https://en.wikipedia.org/wiki/List_(abstract_data_type)) Construct  |              |                     |
|                 | [ ]                                   | List() `or` Nil                                         |
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
| Comparisons     |                                       | :bulb: [<sup>**Ordering</sup>](#Ordering)             |
|                 | [3,2,1] > [2,1,0]                     | List(3,2,1) > List(2,1,0)                             | 
| Operations      |                                       | :bulb: [<sup>**postfixOps</sup>](#postfixOps)         |
|                 | head [5,4,3,2,1]                      | List(5,4,3,2,1) head                                  |
|                 | tail [5,4,3,2,1]                      | List(5,4,3,2,1) tail                                  |
|                 | init [5,4,3,2,1]                      | List(5,4,3,2,1) init                                  |
|                 | last [5,4,3,2,1]                      | List(5,4,3,2,1) last                                  |
|                 | null []                               | List() isEmpty `or` List() == Nil                     |
|                 | length [5,4,3,2,1]                    | List(5,4,3,2,1) length                                |
|                 | reverse [5,4,3,2,1]                   | List(5,4,3,2,1) reverse                               |


#### :m: Required Imports in Scala

##### **Ordering: 
    Add the following Implicit Import.
    import Ordering.Implicits._
    
##### **postfixOps
    Add the following Implicit Import.
    import scala.language.postfixOps



