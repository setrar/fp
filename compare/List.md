# [List](https://en.wikipedia.org/wiki/List_(abstract_data_type))

| Feature         | Haskell <sup><img src="../images/602px-Haskell-Logo.svg.png" width=37 height=26><img></sup>                               | Scala <img src="../images/Scala_logo.png" width=72px height=50px><img>                                                 |
|-----------------|---------------------------------------|-------------------------------------------------------|
|  [Cons]()truct  | `:`                                   | `::`                                                  |
|                 | [ ]                                   | List() `or` Nil                                       |
|                 | [1,2]                                 | List(1,2)                                             |
|                 | 1 : 2 : [ ]                           | 1 :: 2 :: Nil                                         |
| Concatenation   |  `++`                                 | `++`                                                  |
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
|                 | take 3 [5,4,3,2,1]                    | List(5,4,3,2,1) take 3                                |
|                 | drop 3 [8,4,2,1,5,6]                  | List(8,4,2,1,5,6) drop 3                              |
|                 | maximum [1,9,2,3,4]                   | List(1,9,2,3,4) max                                   |
|                 | sum [1,9,2,3,4]                       | List(1,9,2,3,4) sum                                   |
|                 | product [1,9,2,3,4]                   | List(1,9,2,3,4) product                               |
|                 | 5 \`elem\` [5,4,3,2,1]                | List(5,4,3,2,1).size >= 5                             |


#### :m: Haskell 

##### **Infix: 
    By default all functions are prefixing the instruction
    Adding \` (backticks) between functions allows infixing
    \`div\`

#### :m: Scala - Required Imports

##### **Ordering: 
    Add the following Implicit Import.
    import Ordering.Implicits._
    
##### **postfixOps
    Add the following Implicit Import.
    import scala.language.postfixOps

