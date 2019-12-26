package chapter1

import org.scalatest._

class ModuloSpec extends FlatSpec with Matchers {
  "The ModuloSpec" should "not fail" in {
    // https://www.youtube.com/watch?v=2rbeCUMBYgk negative numbers
    Figure_1_3.modulo(51, modulus = 10) shouldEqual 1
//    Figure_1_3.modulo(-51, modulus = 10) shouldEqual 9
    Figure_1_3.modulo(253, modulus = 60) shouldEqual 13
//    Figure_1_3.modulo(59, modulus = 60) shouldEqual 59
    Figure_1_3.modulo(-59, modulus = 60) shouldEqual 1
//    Figure_1_3.modulo(-61, modulus = 60) shouldEqual 59
    Figure_1_3.modulo(-9, modulus = 25) shouldEqual 16
//    Figure_1_3.modulo(7, modulus = 120) shouldEqual 103
  }

}
