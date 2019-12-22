
# [List](https://en.wikipedia.org/wiki/List_(abstract_data_type))

```
By definition, Haskell is a lazy language. To mimic the same feature, I will be using Scala LazyList to match Haskell lazy feature.
```

:bulb: To force the evaluation, i.e. to see the results, use `force`: i.e. `LazyList(1,2) force` [<sup>**postfixOps</sup>](#postfixOps) 

:bookmark: Haskell notation seems inverted compare to Scala's. 
    For example, This Haskell's script `[x * 2 \| x <- [1..10]]`    is translated to `1 to 10 to(LazyList) map ( x => x * 2)` in Scala


| Feature         | Haskell <sup><img src="../images/602px-Haskell-Logo.svg.png" width=37 height=26><img></sup> | Scala <img src="../images/Scala_logo.png" width=72px height=50px><img> |
|-----------------|-----------------------------------------|--------------------------------------------------------|
|  [Cons]()truct  | `:`                                     | `#::` `and` `::` [<sup>**strict</sup>](#strict)           |
|                 | [ ]                                     | LazyList() `or` Nil  `and` `List()` [<sup>**strict</sup>](#strict)|
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
|                 | [0.1, 0.3 .. 1]        | BigDecimal("0.1") to BigDecimal("0.3") by BigDecimal("1") to(LazyList) :bangbang:|
| Cycle           |                                         |                                                       |
|                 | take 10 (cycle [1,2,3])                 | cycle(1 #:: 2 #:: 3 #:: LazyList.empty) take 10 [<sup>**cycle</sup>](#cycle) |
|                 | take 10 (repeat 5)                      | LazyList.fill(10)(5) :warning: not ideal                       |
| Comprehension   |                                         |                                                                |
|                 | [x * 2 \| x <- [1..10]]                 | 1 to 10 to(LazyList) collect ( x => x * 2)                     |
|                 | [x * 2 \| x <- [1..10], x * 2 >= 12]    | 1 to 10 to(LazyList) collect { case x if x * 2 >= 12 => x * 2 }|
|                 | [x\| x <- [50..100], x%7==3 >= x]       | 50 to 100 to(LazyList) collect {case x if x % 7 == 3 => x}     |




<img src="http://www.sciweavers.org/tex2img.php?eq=%20S%20%3D%5Cbig%5C%7B%202%20.%20x%20%7C%20x%20%20%5Cin%20%20%5Caleph%20%2C%20x%20%3C%3D%2010%5Cbig%5C%7D%20%0A&bc=White&fc=Black&im=jpg&fs=12&ff=arev&edit=0" align="center" border="0" alt=" S =\big\{ 2 . x | x  \in  \aleph , x <= 10\big\} " width="204" height="21" />

![equation](http://www.sciweavers.org/tex2img.php?eq=%20S%20%3D%5Cbig%5C%7B%202%20.%20x%20%7C%20x%20%20%5Cin%20%20%5Caleph%20%2C%20x%20%3C%3D%2010%5Cbig%5C%7D%20%0A&bc=White&fc=Black&im=jpg&fs=12&ff=arev&edit=0)

#### :m: Haskell 

##### **Infix: 
    By default all functions are prefixing the instruction
    Adding ` (backticks) between functions allows infixing
    4 `div` 2

#### :m: Scala - Required Imports and missing code

##### **Ordering: 
    Add the following Implicit Import.
    import Ordering.Implicits._
    
##### **postfixOps
    Add the following Implicit Import.
    import scala.language.postfixOps

##### **strict: 
    Strict evaluation (i.e. non lazy), Scala uses the non-strict construct like `List` by default
    
##### **cycle: 
    def cycle[T](seq: Seq[T]) = LazyList.from(0).flatten(_ => seq)
