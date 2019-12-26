package paragraph74

import org.scalatest._

class HtmlUtilSpec extends FlatSpec with Matchers {
  "The htmlify Writer" should "demonstrate the explicit Parameter Lists" in {
    HtmlUtil.htmlify(Person("John","john@example.com"))(PersonWriter) shouldEqual "<span>John &lt;john@example.com&gt;</span>"
    }
  "The htmlify Writer" should "demonstrate the implicit Parameter Lists and scope" in {
    implicit object ApproximationWriter extends HtmlWriter[Int] {
      override def write(in: Int): String =
        s"It's definitely less than ${((in / 10) + 1) /10}"
    }
    HtmlUtil.htmlify(2)/*(...)*/ shouldEqual "It's definitely less than 0"
  }
}
