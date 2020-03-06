# Set Comprehensions

:pushpin: Mathematical Notation

![equation](http://www.sciweavers.org/tex2img.php?eq=S%3D%5Cbig%5C%7B2.x%5Cmid%20x%5Cin%20N%2Cx%5Cleq10%5Cbig%5C%7D%20&bc=White&fc=Black&im=jpg&fs=12&ff=arev&edit=0)

    The part before the pipe is called the output function, 
    x is the variable, N is the input set and x <= 10 is the predicate. 
    That means that the set contains the doubles of all natural numbers that satisfy the predicate.

| Math Feature      | Haskell <sup><img src="../images/602px-Haskell-Logo.svg.png" width=37 height=26><img></sup> | Scala <img src="../images/Scala_logo.png" width=72px height=50px><img> |
|-------------------|-----------------------------------------|--------------------------------------------------------|
| Set Comprehension | List Comprehension                      | For Comprehension  (Conv. add `to(LazyList)` to `1 to 10` ) |
|                   | [x * 2 \| x <- [1..10]]                 | for (x <- 1 to 10 ) yield x * 2                |
|                   | [x * 2 \| x <- [1..10], x * 2 >= 12]    | for (x <- 1 to 10 if x * 2 >= 12) yield x * 2 |
|                   | [x\|x<-[50..100],x\`mod\`7==3]          | for (x <- 1 to 10 if x % 7 == 3) yield x      |


References: 

https://medium.com/@joshuapaulrobin/set-comprehension-in-python3-for-beginners-80561a9b4007

https://brilliant.org/wiki/list-comprehension/#set-builders

https://en.wikipedia.org/wiki/Comparison_of_programming_languages_(list_comprehension)
