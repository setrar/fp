package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }


  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("times") {
    assert(times(List('a', 'b', 'a')) === List(('a', 2), ('b', 1)))
  }


  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("singleton") {
    assert(singleton(List(Leaf('e',2))) === true)
    assert(singleton(List(Leaf('e',2),Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5))) === false)
    assert(singleton(List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3))) === true )
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("create a code tree") {
    assert(createCodeTree(List('a','b')) === Leaf('b',1))
    assert(createCodeTree(List('a','b','a','v')) === Leaf('a',2))
  }

  test("decode case") {
    new TestTrees {
      assert(decode(t1, List(0)) === "a".toList)
      assert(decode(t1, List(1)) === "b".toList)
      assert(decode(t2, List(0,0)) === "a".toList)
      assert(decode(t2, List(0,1)) === "b".toList)
      assert(decode(t2, List(1)) === "d".toList)
      assert(decode(t2, List(0,0,1)) === "ad".toList)
    }
  }

  test("decode secret") {
    assert(decodedSecret === List('h', 'u', 'f', 'f', 'm', 'a', 'n', 'e', 's', 't', 'c', 'o', 'o', 'l'))
  }

  test("encode case") {
    new TestTrees {
      assert(encode(t1)("a".toList) === List(0))
    }
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

}
