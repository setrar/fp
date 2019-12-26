package chapter1

import org.scalatest._

class Primality2Spec extends FlatSpec with Matchers {
  "The Primality2Spec" should "should not fail" in {
    Figure_1_8.primality2(7) shouldBe true
    intercept[AssertionError] {
      Figure_1_8.primality2(-77) shouldBe true
    }
    Figure_1_8.primality2(6) shouldBe false
    Figure_1_8.primality2(341) shouldBe false
  }
}
