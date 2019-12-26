package paragraph74

object PersonWriter extends HtmlWriter[Person] {
  def write(person: Person): String = s"<span>${person.name} &lt;${person.email}&gt;</span>"
}
