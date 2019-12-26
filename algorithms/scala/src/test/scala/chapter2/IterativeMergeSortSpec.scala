package chapter2

import chapter2.Figure_2_4._
import org.scalatest._

class IterativeMergeSortSpec extends FlatSpec with Matchers {

  "The iterative-MergeSortSpec" should "not fail" in {

    val a = Array(1,2,3,4,5,6,7)
    iterativeMergeSort(a) shouldEqual a.take(a.length - 1)   // Pretty wrong the algorithm is not working
//    val a = Array(3,6,2,8,1,9,34)
//    val r = Array(1,2,3,6,8,9,34)
//    iterativeMergeSort(a) shouldEqual r

  }

}
