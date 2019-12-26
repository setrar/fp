package chapter1

import org.scalatest._

class OddSpec extends FlatSpec with Matchers {
  "The isOddSpec" should "not fail" in {
    Figure_1_3.isOdd(1,60) shouldBe true
    Figure_1_3.isOdd(-1,60) shouldBe true
    Figure_1_3.isOdd(-3,2) shouldBe true
  }
}
