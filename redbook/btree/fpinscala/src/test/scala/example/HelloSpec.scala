package example

import org.scalatest.{FlatSpec, Matchers}

class HelloSpec extends FlatSpec with Matchers {

  "The Hello object" should "say hello" in {
    Hello.firstDuplicate(List(-1,1)) shouldBe 0
    Hello.firstDuplicate(List(3,3,4,-4)) shouldBe 10
  }

}
