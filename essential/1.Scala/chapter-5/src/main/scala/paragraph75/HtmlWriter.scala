package paragraph75

trait HtmlWriter[A] {
  def write(in: A): String
}
