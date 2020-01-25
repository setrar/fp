# Set Comprehensions

![equation](http://www.sciweavers.org/tex2img.php?eq=1%2Bsin%28mc%5E2%29&bc=White&fc=Black&im=jpg&fs=12&ff=arev&edit=)


| Math Feature      | Haskell <sup><img src="../images/602px-Haskell-Logo.svg.png" width=37 height=26><img></sup> | Scala <img src="../images/Scala_logo.png" width=72px height=50px><img> |
|-------------------|-----------------------------------------|--------------------------------------------------------|
| Set Comprehension | List Comprehension                      | For Comprehension                                      |
|                   | [x * 2 \| x <- [1..10]]                 | for (x <- 1 to 10 to(LazyList)) yield x * 2                |
|                   | [x * 2 \| x <- [1..10], x * 2 >= 12]    | for (x <- 1 to 10 to(LazyList) if x * 2 >= 12) yield x * 2 |
|                   | [x\|x<-[50..100],x\`mod\`7==3]          | for (x <- 1 to 10 to(LazyList) if x % 7 == 3) yield x      |


References: 

https://medium.com/@joshuapaulrobin/set-comprehension-in-python3-for-beginners-80561a9b4007

https://brilliant.org/wiki/list-comprehension/#set-builders

https://en.wikipedia.org/wiki/Comparison_of_programming_languages_(list_comprehension)
