# Algorithms in Scala `2.13`:grey_exclamation:
Study of the book written by [Dasgupta](http://cseweb.ucsd.edu/~dasgupta/book/index.html), Papadimitriou and Vazirani

## Source Codes by Chapters

| Chp. |  Fig.   | Theme                                                     |  Comments                                     |
|-----:|:--------|-----------------------------------------------------------|-----------------------------------------------|
|:one: | :one:   | [Multiply](scala/src/main/scala/chapter1/Figure_1_1.scala)| [:bulb:](https://en.wikipedia.org/w/index.php?title=Exponential_function&section=2#Formal_definition)             |
|      | :two:   | [Divide](scala/src/main/scala/chapter1/Figure_1_2.scala)|   |
|      | :three: | [Modular Arithmetic](scala/src/main/scala/chapter1/Figure_1_3.scala)| [:bulb: Two's Complement](https://en.wikipedia.org/wiki/Two's_complement) |
|      | :four:  | [Modular Exponentiation](scala/src/main/scala/chapter1/Figure_1_4.scala)| [:bulb: Modular Exponentiation](https://en.wikipedia.org/wiki/Modular_exponentiation) |
|      | :five:  | [Euclid](scala/src/main/scala/chapter1/Figure_1_5.scala)| [:bulb: greatest common divisor ](https://en.wikipedia.org/wiki/Greatest_common_divisor#Using_Euclid's_algorithm)   ![alt tag](https://wikimedia.org/api/rest_v1/media/math/render/svg/50dcf31162a0922f36a606e0b0a95b2744039b0a) |
|      | :six:   | [Extended Euclid](scala/src/main/scala/chapter1/Figure_1_6.scala)| [:bulb: Extended Euclidian](https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm) [:x: Side Note on mindless algorithm](XGCD.md)|
|      | :seven: | [Primality](scala/src/main/scala/chapter1/Figure_1_7.scala)| [:bulb: Fermat's Primality Test](https://en.wikipedia.org/wiki/Fermat_primality_test) ![alt tag](https://wikimedia.org/api/rest_v1/media/math/render/svg/58a9e1a77254c598a3bbd20ee75962c540381c54) |
|      | :eight: | [Primality2](scala/src/main/scala/chapter1/Figure_1_8.scala)| [:bulb: Fermat's Primality Test 2](https://en.wikipedia.org/wiki/Fermat%27s_little_theorem#Pseudoprimes) ![alt tag](https://wikimedia.org/api/rest_v1/media/math/render/svg/9725156b187128ff35b4897885cb6bee6266c77b) |
|      | :nine:  | [RSA](scala/src/main/scala/chapter1/Figure_1_9.scala)| [:bulb: RSA](https://searchsecurity.techtarget.com/definition/RSA) ![alt tag](https://wikimedia.org/api/rest_v1/media/math/render/svg/fbfc70524a1ad983e6f3aac51226b9ca92fefb10) <+> ![alt tag](https://wikimedia.org/api/rest_v1/media/math/render/svg/10227461ee5f4784484f082d744ba5b8c468668c)|
|      | :keycap_ten: | [Modulo Inverse](scala/src/main/scala/chapter1/Paragraph_1_2_5.scala) | [:bulb: Modulo Inverse's Note](INVERSE.md)|
|:two: | :one:   | [Multiply](scala/src/main/scala/chapter2/Figure_2_1.scala)| [:bulb: Karatsuba's Algorithm](https://en.wikipedia.org/wiki/Karatsuba_algorithm)             |
|      | :four:   | [MergeSort](scala/src/main/scala/chapter2/Figure_2_4.scala)| [:bulb: Wikipedia's Algorithm](https://en.wikipedia.org/wiki/Merge_sort) :warning: `Review` Failed Iterative Algorithm            |

## Chapters

| Chapter                                                                      |Link                                         |
|------------------------------------------------------------------------------|:--------------------------------------------|
|[:zero:](https://people.eecs.berkeley.edu/~vazirani/algorithms/chap0.pdf)     | Prologue                                    |
|[:one:](https://people.eecs.berkeley.edu/~vazirani/algorithms/chap1.pdf)      | Algorithms with numbers                     |
|[:two:](https://people.eecs.berkeley.edu/~vazirani/algorithms/chap2.pdf)      | Divide and conquer algorithms               |


## Errata

| Chp. | Fig. |  Link                                                               |  Comments                           |
|------|-----:|:--------------------------------------------------------------------|-------------------------------------|
|      |      | [Errata Dasgupta](http://cseweb.ucsd.edu/~dasgupta/book/errata.pdf) | Entire book                         |
| :two:| :one:| [* Vazirani](https://people.eecs.berkeley.edu/~vazirani/algorithms/chap2.pdf) | Missing n = Max(size of x, sizeOf y)    |
|      | :one:| [* Dasgupta](http://cseweb.ucsd.edu/~dasgupta/book/errata.pdf)      | return P1 × 2 2⌊n/2⌋ + (P3 − P1 − P2) × 2 ⌊n/2⌋ + P2 |


## References
| Theme           | Link                                                                |
|-----------------|---------------------------------------------------------------------|
| Complex Numbers | https://www.javacodegeeks.com/2013/02/complex-numbers-in-scala.html | 
| Algo Archive :clap: | https://www.algorithm-archive.org |
