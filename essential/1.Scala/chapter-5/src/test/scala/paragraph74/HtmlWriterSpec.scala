package paragraph74

import org.scalatest._

class HtmlWriterSpec extends FlatSpec with Matchers {
  "The HTML Writer apply" should "demonstrate the Interfaces using explicit Parameters" in {
    HtmlWriter.apply(PersonWriter).write(Person("John","john@example.com"))
  }
  "The HTML Writer companion object" should "demonstrate the Interfaces using implicit Parameters" in {
    implicit object ObfuscatedPersonWriter extends HtmlWriter[Person] {
      def write(person: Person) = s"<span>${person.name} (${person.email.replaceAll("@", " at ")})</span>"
    }
    HtmlWriter[Person].write(Person("John","john@example.com")) shouldEqual "<span>John (john at example.com)</span>"
  }
}
