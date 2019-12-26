package chapter1

import org.scalatest._

class DivideSpec extends FlatSpec with Matchers {
  "The Mutable DivideSpec" should "should not fail" in {
    Figure_1_2.mutableDivide(6,2) shouldEqual (3,0)
  }

  "The DivideSpec" should "should not fail" in {
    Figure_1_2.divide(6,2) shouldEqual (3,0)
    Figure_1_2.divide(Int.MaxValue,Int.MaxValue / 2) shouldEqual (3,1073741825)
    Figure_1_2.divide(Int.MaxValue,Int.MaxValue) shouldEqual (0,-2)
  }

}
