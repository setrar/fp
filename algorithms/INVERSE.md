# Modular Inverse 



### `Example p. 23:` Modular Inverse 

To compute 11^-1 mod 25 using the same Extended Euclid's algorithm example: 

[Inverse Modulo](https://www.youtube.com/watch?v=fz1vxq5ts5I)

```scala
    /**
      * Inverse Modulo
      * d = e^-1 mod totient using extended euclidean algorithm
      */
    val (_,y,_) = egcd(totient, e)
    // Returning positive value ???
    val d = if (y >= 0) y else totient + y
```


### References:

| Link                                        | Comments                                           | Author           |
|---------------------------------------------|----------------------------------------------------|------------------|
| https://www.youtube.com/watch?v=fz1vxq5ts5I | Inverse Modulo using Ext. Euclidean Algo. Tutorial | Emily Jane       |
| https://rosettacode.org/wiki/Modular_inverse#Scala | Attempted to use brute force                 |  |

