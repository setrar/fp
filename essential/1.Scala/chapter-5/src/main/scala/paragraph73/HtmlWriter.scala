package paragraph73

import java.time.LocalDateTime

trait HtmlWriter[A] {
  def write(in: A): String
}

object PersonWriter extends HtmlWriter[Person] {
  def write(person: Person ) = s"<span>${person.name} &lt;${person.email}&gt;</span>"
}

object DateTimeWriter extends HtmlWriter[java.time.LocalDateTime] {
  def write(dateTime: LocalDateTime) = s"<span>${dateTime.toString}</span>"
}

object ObfuscatedPersonWriter extends HtmlWriter[Person] {
  def write(person: Person) = s"<span>${person.name} (${person.email.replaceAll("@", " at ")})</span>"
}
