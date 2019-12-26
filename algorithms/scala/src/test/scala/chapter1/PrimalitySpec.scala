package chapter1

import org.scalatest._

class PrimalitySpec extends FlatSpec with Matchers {
  "The PrimalitySpec" should "should not fail" in {
    Figure_1_7.primality(7) shouldBe true
    intercept[AssertionError] {
      Figure_1_7.primality(-77) shouldBe true
    }
//    Figure_1_7.primality(6) shouldBe false
    Figure_1_7.primality(341) shouldBe false
  }
}
