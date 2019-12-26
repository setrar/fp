package paragraph74

trait HtmlWriter[A] {
  def write(in: A): String
}

object HtmlWriter {
  def apply[A](implicit writer: HtmlWriter[A]): HtmlWriter[A] = writer
}

