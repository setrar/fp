package paragraph73

import java.time.{Instant, LocalDateTime, ZoneId}

import org.scalatest._

class HtmlWriterSpec extends FlatSpec with Matchers {
  "The Person class" should "say html spews" in {
    PersonWriter.write(Person("John","john@example.com")) shouldEqual "<span>John &lt;john@example.com&gt;</span>"
  }
  "The Date class" should "say html spews" in {
    val longValue: Long = 1555549200000L
    val date: LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneId.systemDefault())
    DateTimeWriter.write(date) shouldEqual "<span>2019-04-17T21:00</span>"
  }
  "The Obfuscated Person class" should "say html spews" in {
    ObfuscatedPersonWriter.write(Person("John","john@example.com")) shouldEqual "<span>John (john at example.com)</span>"
  }
}
