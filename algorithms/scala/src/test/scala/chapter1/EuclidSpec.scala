package chapter1

import org.scalatest._

class EuclidSpec extends FlatSpec with Matchers {
  "The EuclidSpec" should "should not fail" in {
    Figure_1_5.euclid(3, 2) shouldEqual 1
    Figure_1_5.euclid(4, 2) shouldEqual 2
/*
    intercept[AssertionError] {
      Figure_1_5.euclid(-4, 2) shouldEqual Int.MaxValue
    }
*/
    Figure_1_5.euclid(253, 60) shouldEqual 1
    Figure_1_5.euclid(256, 128) shouldEqual 128
    Figure_1_5.euclid(1035, 759) shouldEqual 69
    Figure_1_5.euclid(120, 7) shouldEqual 1
    Figure_1_5.euclid(25, 11) shouldEqual 1
  }
}
