package chapter2

import org.scalatest._

import Figure_2_4._

class MergeSortSpec extends FlatSpec with Matchers {

  "The MergeSortSpec" should "not fail" in {

    mergeSort(Array(3,6,2,8,1,9,34)) shouldEqual Array(1,2,3,6,8,9,34)
    mergeSort(List(3,6,2,8,1,9,34)) shouldEqual List(1,2,3,6,8,9,34)

  }


}
