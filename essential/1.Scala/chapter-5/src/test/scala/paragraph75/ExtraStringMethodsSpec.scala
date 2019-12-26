package paragraph75

import org.scalatest._

class ExtraStringMethodsSpec extends FlatSpec with Matchers {
  "The quick brown fox string" should "return 5" in {
    import ExtraStringMethods._
    "the quick brown fox".numberOfVowels shouldEqual 5
  }
}
