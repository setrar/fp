package paragraph453

import org.scalatest._

class FelineSpec extends FlatSpec with Matchers {
  "The Lion's dinner" should "be an Antelope" in {
    Dinner.dinner(Lion()) shouldEqual Antelope
  }

  "The Lion" should "eat an Antelope" in {
    Lion().dinner shouldEqual Antelope
  }
}
