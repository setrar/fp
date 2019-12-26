package redbook

import org.scalatest.{FlatSpec, Matchers}
import scala.language.postfixOps

class TreeSpec extends FlatSpec with Matchers {



  "BTree ADT" should "be balanced" in {
    val node: Branch[Int] = Branch(Branch(Leaf(3), Leaf(4)), Branch(Leaf(6), Leaf(7)))
    node.balanced shouldBe 2
    node.isBalanced shouldBe true
  }

  "Tree ADT" should "answer some use cases before fold" in {

    Leaf("a").size_ shouldBe 1
    Branch(Leaf("a"), Leaf("b")).size_ shouldBe 3
    Branch(Leaf("a"), Branch(Leaf("a"), Leaf("b"))).size_ shouldBe 5
    Branch(Leaf(4), Branch(Leaf(10), Leaf(3))).maximum_ shouldBe 10
    Branch(Branch(Leaf(24), Leaf(5)), Branch(Leaf(10), Leaf(3))).maximum_ shouldBe 24
    Branch(Branch(Leaf(24), Leaf(5)), Branch(Leaf(10), Leaf(3))).depth_ shouldBe 2
    Leaf("a").depth_() shouldBe 0
    Branch(Leaf(1), Leaf(2)).map_(_ + 1) shouldBe Branch(Leaf(2), Leaf(3))
    Branch(Leaf("a"), Leaf("b")).map_(_ + "-") shouldBe Branch(Leaf("a-"), Leaf("b-"))
    Branch(Branch(Leaf(24), Leaf(5)), Branch(Leaf(10), Leaf(3))).map_(-1 *) shouldBe
      Branch(Branch(Leaf(-24), Leaf(-5)), Branch(Leaf(-10), Leaf(-3)))

  }

  "Tree ADT" should "answer some uses cases with fold" in {
    Leaf("a").size shouldBe 1
    Branch(Leaf("a"), Leaf("b")).size shouldBe 3
    Branch(Leaf("a"), Branch(Leaf("a"), Leaf("b"))).size shouldBe 5

    Branch(Leaf(4), Branch(Leaf(10), Leaf(3))).maximum shouldBe 10
    Branch(Branch(Leaf(24), Leaf(5)), Branch(Leaf(10), Leaf(3))).maximum shouldBe 24

    Branch(Branch(Leaf(24), Leaf(5)), Branch(Leaf(10), Leaf(3))).depth shouldBe 2
    Leaf("a").depth shouldBe 0


    Branch(Leaf(1), Leaf(2)).map(_ + 1) shouldBe Branch(Leaf(2), Leaf(3))
    Branch(Leaf("a"), Leaf("b")).map(_ + "-") shouldBe Branch(Leaf("a-"), Leaf("b-"))
    Branch(Branch(Leaf(24), Leaf(5)), Branch(Leaf(10), Leaf(3))).map(-1 *) shouldBe
      Branch(Branch(Leaf(-24), Leaf(-5)), Branch(Leaf(-10), Leaf(-3)))
    Branch(Branch(Leaf(24), Leaf(6)), Branch(Leaf(10), Leaf(4))).map(_ / 2) shouldBe
      Branch(Branch(Leaf(12), Leaf(3)), Branch(Leaf(5), Leaf(2)))
    Branch(Leaf(24), Branch(Leaf(10), Leaf(4))).map(_ / 2) shouldBe
      Branch(Leaf(12), Branch(Leaf(5), Leaf(2)))

  }

  "Tree ADT" should "answer extra uses cases with fold" in {
    Leaf(4).exist( a => a < 4) shouldBe false
    Branch(Leaf(24), Branch(Leaf(10), Leaf(4))).exist(_ % 2 == 0) shouldBe true
    Branch(Leaf('a'), Branch(Leaf('a'), Leaf('a'))).exist(_ == 'a') shouldBe true

  }

  "Tree ADT" should "use Monoid" in {
    val t = Branch(Leaf(24), Branch(Leaf(10), Leaf(4)))
    val intMax: Monoid[Int] = new Monoid[Int] {
      def op(x: Int, y: Int) = x max y
      val zero = 0
    }
//    Tree.foldRight(t)( (_: Int) => 1)(intMax) shouldBe 24

  }

}
