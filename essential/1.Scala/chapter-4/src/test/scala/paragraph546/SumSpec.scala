package paragraph546

import org.scalatest._

class SumSpec extends FlatSpec with Matchers {
  "The Left addition" should "be 127" in {
    Left(123).fold(_ + 4, (x: String) => x + "!") shouldEqual 127
  }
  "The Right concatenation" should "be 127" in {
    Right("Joe").fold( (_: Int) => "": String, _ + "!") shouldEqual "Joe!"
  }
}
