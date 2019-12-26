package africa.valiha.ttt.state

import scalaz.State.{get, modify, put}
import scalaz.{Applicative, State}
import scalaz.std.list._

// https://softwarecorner.wordpress.com/2013/08/29/scalaz-state-monad/

object wordCounts {


  val m1 = State { s: String => (s, s.length) }

  def repeat(num: Int): State[String, Unit] = State { s: String => (s * num, ()) }

  def words(str: String): List[String] = List(str,str)

  def wordCounts(str: String) = modify[Map[String, Int]]{ currMap =>
    words(str).foldLeft(currMap) { (map, word) =>
      val count = map.getOrElse(word, 0) + 1
      map + (word -> count)
    }
  }

  def wordCountsForArticle(article: Article) = for {
    _ <- wordCounts(article.headline)
    _ <- wordCounts(article.abstr)
    _ <- wordCounts(article.body)
  } yield ()

  class Article(val headline: String, val abstr: String, val body: String)

  def main(args: Array[String]): Unit = {

    assert(m1.run("hello")==("hello", 5))
    assert(repeat(3).run("abc")==("abcabcabc", ()))
    assert(m1.flatMap(repeat).run("hello")==("hellohellohellohellohello", ()))
    assert(m1.flatMap(repeat).flatMap({ _ => m1 }).run("hello")==("hellohellohellohellohello", 25))
    assert(get[String]
      .flatMap({ s0 => put[String](s0 * s0.length) })
      .flatMap({ _ => get[String]})
      .map({ s1 => s1.length })
      .run("hello")==("hellohellohellohellohello", 25)
    )
    assert((for {
        s0 <- get[String]
        _  <- put[String](s0 * s0.length)
        s1 <- get[String]
      } yield s1.length)
      .run("hello")==("hellohellohellohellohello", 25))
    assert((for {
      _  <- modify[String]{s => s * s.length }
      s1 <- get[String]
    } yield s1.length).run("hello")==("hellohellohellohellohello", 25))

    val article = new Article(headline = "Issues", abstr = "fire in subway", body = "more longer text")
    val m_w = for {
      _ <- wordCounts(article.headline)
      _ <- wordCounts(article.abstr)
      _ <- wordCounts(article.body)
    } yield ()
    assert(m_w.exec(Map.empty[String, Int])==Map("Issues" -> 2, "fire in subway" -> 2, "more longer text" -> 2))

    val article2 = new Article(headline = "Issues2", abstr = "fire in subway2", body = "more longer text2")
    val articles = List(article,article2)

    val ms = articles map wordCountsForArticle // List of IndexedState

    val m_z = ms.foldLeft(State { s: Map[String, Int] => (s, ()) }) { (resultM, currM) =>
      resultM flatMap { _: Unit => currM }
    }
    val dd = m_z.run(Map.empty[String, Int])
    assert(dd==(
      Map(
        "fire in subway2" -> 2
      , "more longer text" -> 2
      , "more longer text2" -> 2
      , "fire in subway" -> 2
      , "Issues2" -> 2, "Issues" -> 2
    ),())
    )

  }

}
