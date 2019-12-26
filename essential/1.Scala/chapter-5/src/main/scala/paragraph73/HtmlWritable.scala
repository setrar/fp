package paragraph73

trait HtmlWritable {
  def toHtml: String
}

final case class PersonWritable(name: String, email: String) extends HtmlWritable {
  def toHtml: String = s"<span>$name &lt;$email&gt;</span>"
}

