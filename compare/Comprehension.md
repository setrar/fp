# Set Comprehensions


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
|                 | [x\|x<-[50..100],x\`mod\`7==3]     | 50 to 100 to(LazyList) collect {case x if x % 7 == 3 => x}     |




References: 

https://medium.com/@joshuapaulrobin/set-comprehension-in-python3-for-beginners-80561a9b4007

https://brilliant.org/wiki/list-comprehension/#set-builders
