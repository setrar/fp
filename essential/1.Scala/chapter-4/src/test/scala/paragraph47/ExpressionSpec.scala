package paragraph47

import org.scalatest._

class ExpressionSpec extends FlatSpec with Matchers {
  "The Number" should "be 2.0" in {
    Number(2.0).eval shouldEqual Success(2.0)
  }
  "The addition" should "be 0" in {
    Addition(Number(1.0),Number(1.0)).eval shouldEqual Success(2.0)
  }
  "The division" should "be 3.0" in {
    Division(Number(6.0),Number(2.0)).eval shouldEqual Success(3.0)
  }
  "The division" should "Fail" in {
    Division(Number(0),Number(0)).eval shouldBe Failure("Divide by Zero")
  }
  "The Square Root" should "be 2.0" in {
    SquareRoot(Number(16.0)).eval shouldBe Success(4.0)
  }
  "The Square Root" should "Fail" in {
    SquareRoot(Number(-16.0)).eval shouldBe Failure("Square root of negative number")
  }
}
