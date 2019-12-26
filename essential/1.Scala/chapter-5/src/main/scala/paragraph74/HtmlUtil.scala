package paragraph74

object HtmlUtil {
  def htmlify[A](data: A)(implicit writer: HtmlWriter[A]): String = writer.write(data)
}