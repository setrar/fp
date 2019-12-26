package chapter1

import org.scalatest._

class ModDivSpec extends FlatSpec with Matchers {
  "The ModDivSpec" should "should not fail" in {

    // https://planetcalc.com/3311/

    val a = 25
    val b = 11
    val (_,y,_) = Figure_1_6.extendedEuclid(a, b) // shouldEqual (4, -9, 1)
    Figure_1_5.euclid(y, a) shouldEqual 1

    /**
      * Modular Multiplicative Inverse
      * https://planetcalc.com/3311/ 7
      */
    val (p,q) = (11,13)
    val e = 7
    // the totient:  n ϕ (n) =(p−1)x(q−1)
    val totient = (p - 1) * (q - 1)

    totient shouldEqual 120

    val (_,s,_) = Figure_1_6.extendedEuclid(totient, e) // shouldEqual (1,-17,1)
//    Figure_1_5.euclid(s, totient) shouldEqual 103
//    Figure_1_5.euclid(totient, e) shouldEqual 103
//    eInverse shouldEqual 103

  }
}
