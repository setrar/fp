package paragraph73

import org.scalatest._

class HtmlWritableSpec extends FlatSpec with Matchers {
  "The Person class" should "say html spews" in {
    PersonWritable("John","john@example.com").toHtml shouldEqual "<span>John &lt;john@example.com&gt;</span>"
  }
}
