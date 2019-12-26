package paragraph546

import org.scalatest._

class MayBeSpec extends FlatSpec with Matchers {
  "The Full addition" should "be 124" in {
    Full(123).fold(0)(_ + 1) shouldEqual 124
  }
  "The Empty " should "be empty" in {
//    Empty().fold(Empty())() shouldBe Empty()
  }

}
