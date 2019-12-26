package chapter1

import org.scalatest._

class ModExpSpec extends FlatSpec with Matchers {
  "The ModExpSpec" should "should not fail" in {
    Figure_1_4.modexp(3,25,2) shouldEqual 1
    Figure_1_4.modexp(2,2,2) shouldEqual 0
    Figure_1_4.modexp(4,13,497) shouldEqual 445
    Figure_1_4.modexp(9,7,143) shouldEqual 48
  }
}
