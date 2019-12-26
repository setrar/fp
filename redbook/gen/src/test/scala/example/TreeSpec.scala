package example

import org.scalatest._
import org.scalacheck._
import Gen._
import Arbitrary.arbitrary

class TreeSpec extends FlatSpec with Matchers {
  "The Hello object" should "say hello" in {

    val genLeaf = const(Leaf)

    val genNode = for {
      v <- arbitrary[Int]
      left <- genTree
      right <- genTree
    } yield Node(left, right, v)

    def genTree: Gen[Tree] = Gen.oneOf(genLeaf, genNode)


    genTree.sample shouldBe Some(Node(Leaf,Node(Node(Node(Node(Node(Node(Leaf,Leaf,-71),Node(Leaf,Leaf,-49),17),Leaf,-20),Leaf,-7),Node(Node(Leaf,Leaf,26),Leaf,-3),49),Leaf,84),-29))
  }
}
