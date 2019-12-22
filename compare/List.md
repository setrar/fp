
# [List](https://en.wikipedia.org/wiki/List_(abstract_data_type))

```
By definition, Haskell is a lazy language. To mimic the same feature, I will be using Scala LazyList to match Haskell lazy feature.
```

:bulb: To force the evaluation, to see the results, use `force`: i.e. `LazyList(1,2) force`


| Feature         | Haskell <sup><img src="../images/602px-Haskell-Logo.svg.png" width=37 height=26><img></sup> | Scala <img src="../images/Scala_logo.png" width=72px height=50px><img> |
|-----------------|-----------------------------------------|--------------------------------------------------------|
|  [Cons]()truct  | `:`                                     | `#::` `=>` `::` [<sup>**strict</sup>](#strict)           |
|                 | [ ]                                     | LazyList() `or` Nil  `=>` `List()` [<sup>**strict</sup>](#strict)|
|                 | [1,2]                                   | LazyList(1,2)                                          |
|                 | 1 : 2 : [ ]                             | 1 #:: 2 #:: LazyList.empty                             |
| Concatenation   |  `++`                                   | `++`                                                   |
|                 | 'A' : " SMALL CAT"                      | 'A' :: " SMALL CAT" :: Nil  mkString                   |
|                 | 5 : [1,2,3,4,5]                         | 5 :: LazyList(1,2,3,4,5)                               |
|                 | [1,2,3,4] ++ [5]                        | LazyList(1,2,3,4) ++ LazyList(5)                       |
|                 | [[1,2],[3,4],[5,6]]                     | LazyList(LazyList(1,2),LazyList(3,4),LazyList(5,6))    |
| Access          |                                         |                                                        |
|                 | "Steve Buscemi" !! 6                    | "Steve Buscemi" (6)                                    |
|                 | [1,2,3,4] !! 0                          | LazyList(1,2,3,4) (0)                                  |
|                 | [[1,2],[3,4],[5,6]] !! 0                | LazyList(LazyList(1,2),LazyList(3,4),LazyList(5,6))(0) |
|                 | `let` b = [[1,2,3,4],[5,3,3,3],[1,2,3]] | `val` b = LazyList(LazyList(1,2,3,4),LazyList(5,3,3,3),LazyList(1,2,3)) |
|                 | b ++ [[1,1,1,1]]                        | b ++ LazyList(LazyList(1,1,1,1))                              |
| Comparisons     |                                         | :bulb: [<sup>**Ordering</sup>](#Ordering)             | 
|                 | [3,2,1] > [2,1,0]                       | LazyList(3,2,1) > LazyList(2,1,0)                             |
| Operations      |                                         | :bulb: [<sup>**postfixOps</sup>](#postfixOps)         |
|                 | null []                                 | LazyList() isEmpty `or` LazyList() == Nil                     |
|                 | 5 \`elem\` [5,4,3,2,1] :bulb: [<sup>**Infix</sup>](#Infix) | LazyList(5,4,3,2,1) contains 5         |
| `{head,tail}`   | head [5,4,3,2,1]                        | LazyList(5,4,3,2,1) head                                  |
| `{init,last}`   | init [5,4,3,2,1]                        | LazyList(5,4,3,2,1) init                                  |
| `{take,drop}`   | take 3 [5,4,3,2,1]                      | LazyList(5,4,3,2,1) take 3                                |
|                 | length [5,4,3,2,1]                      | LazyList(5,4,3,2,1) length                                |
|                 | reverse [5,4,3,2,1]                     | LazyList(5,4,3,2,1) reverse                               |
|                 | maximum [1,9,2,3,4]                     | LazyList(1,9,2,3,4) max                                   |
|                 | sum [1,9,2,3,4]                         | LazyList(1,9,2,3,4) sum                                   |
|                 | product [1,9,2,3,4]                     | LazyList(1,9,2,3,4) product                               |
| Ranges          |                                         |                                                       |
|                 | [1..20]                                 | 1 to 20 to(LazyList)                                        |
|                 | ['a'..'z']                              | 'a' to 'z' mkString                                   |
|                 | ['K'..'Z']                              | 'K' to 'Z' mkString                                   |
|                 | [2,4..20]  :warning: `first included`   | 2 to 20 by 2 to(LazyList)                                   |
|                 | [3,6..20]  :warning: `first included`   | 3 to 20 by 3 to(LazyList)                                   |


#### :m: Haskell 

##### **Infix: 
    By default all functions are prefixing the instruction
    Adding ` (backticks) between functions allows infixing
    4 `div` 2

#### :m: Scala - Required Imports

##### **Ordering: 
    Add the following Implicit Import.
    import Ordering.Implicits._
    
##### **postfixOps
    Add the following Implicit Import.
    import scala.language.postfixOps

##### **strict: 
    Strick evaluation (i.e. non lazy)
