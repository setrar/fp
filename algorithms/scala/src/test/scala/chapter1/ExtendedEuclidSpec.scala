package chapter1

import org.scalatest._

import Figure_1_6.extendedEuclid

class ExtendedEuclidSpec extends FlatSpec with Matchers {
  "The ExtendedEuclidSpec" should "should not fail" in {


    /**
      * Example from the book which went too far in the resolution by considering 0 has remainder
      * Figure_1_6.extendedEuclid(25, 11) shouldEqual (15, -34, 1)
      * below is the right solution when not considering 0 as remainder
      */
    extendedEuclid(25, 11) shouldEqual (4, -9, 1)

    // https://www.youtube.com/watch?v=6KmhCKxFWOs
    extendedEuclid(56, 15) shouldEqual (-4, 15, 1)

    // https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm#Example
    extendedEuclid(240, 46) shouldEqual (-9, 47, 2)

    // https://www.youtube.com/watch?v=hB34-GSDT3k
    extendedEuclid(888, 54) shouldEqual (-2, 33, 6)

    // https://brilliant.org/wiki/extended-euclidean-algorithm/
    extendedEuclid(123211, 1432) shouldEqual (267, -22973, 1)

    extendedEuclid(35, 15) shouldEqual (1, -2, 5)
    extendedEuclid(13, 4) shouldEqual (1, -3, 1)
    extendedEuclid(1035, 759) shouldEqual (3,-4,69)

    // https://www.youtube.com/watch?v=fz1vxq5ts5I
    // x = 17^-1 mod 43 Inverse Modulo
    val (_,invMod,_) = extendedEuclid(43, 17) //shouldEqual (2,-5,1)
    invMod shouldEqual -5

    /**
      * Fix extended euclidean for negative results ?
      */
    // 1.4.2 RSA p. 33
    val (p,q) = (5,11)
    val e = 3
    // the totient:  n ϕ (n) =(p−1)x(q−1)
    val totient = (p - 1) * (q - 1)

    totient shouldEqual 40

    //    d = 3^-1 mod 40 Inverse Modulo
    val (_,d,_) = extendedEuclid(totient, e)
    (totient + d) shouldEqual 27

    /**
      * Modular Multiplicative Inverse
      * https://planetcalc.com/3311/ 7
      */
    val (p1,q1) = (11,13)
    val e1 = 7
    val totient1 = (p1 - 1) * (q1 - 1)

    totient1 shouldEqual 120

    //    d1 = 7^-1 mod 120 (totient) Inverse Modulo
    val (_,d1,_) = extendedEuclid(totient1, e1) // shouldEqual (2,-5,1)
    (totient1 + d1) shouldEqual 103

  }

}
