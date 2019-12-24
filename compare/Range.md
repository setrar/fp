# Ranges


| Feature         | Haskell <sup><img src="../images/602px-Haskell-Logo.svg.png" width=37 height=26><img></sup> | Scala <img src="../images/Scala_logo.png" width=72px height=50px><img> |
|-----------------|-----------------------------------------|--------------------------------------------------------|
| Ranges          |                                         |                                                       |
|                 | [1..20]                                 | 1 to 20 to(LazyList)                                        |
|                 | ['a'..'z']                              | 'a' to 'z' mkString                                   |
|                 | ['K'..'Z']                              | 'K' to 'Z' mkString                                   |
|                 | [2,4..20]  :warning: `first included`   | 2 to 20 by 2 to(LazyList)                                   |
|                 | [3,6..20]  :warning: `first included`   | 3 to 20 by 3 to(LazyList)                                   |
|                 | [0.1, 0.3 .. 1]        | BigDecimal("0.1") to BigDecimal("0.3") by BigDecimal("1") to(LazyList) :bangbang:|
| Cycle           |                                         |                                                       |
|                 | take 10 (cycle [1,2,3])                 | cycle(1 #:: 2 #:: 3 #:: LazyList.empty) take 10 [<sup>**cycle</sup>](#cycle) |
|                 | take 10 (repeat 5)                      | LazyList.fill(10)(5) :warning: not ideal                   |



#### :m: Scala - Required Imports and missing code
    
##### **cycle: 
    def cycle[T](seq: Seq[T]) = LazyList.from(0).flatten(_ => seq)
