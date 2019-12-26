package chapter2


import scala.collection.{immutable, mutable}
//import scala.collection.mutable._

object Figure_2_4 {

  def merge(x: immutable.Seq[Int], y: Seq[Int]): Seq[Int] = {
    val k = x.length
    val l = y.length

    if (k == 0) y
    else if (l == 0) x
    else if (x.head <= y.head)
      x.head +: merge(x.tail, y)
    else
      y.head +: merge(x, y.tail)

  }

  def mergeSort(a: immutable.Seq[Int]): immutable.Seq[Int] = {
    val n: Int = a.length

    if (n > 1) {
      val (l, r) = a.splitAt(n / 2)
      merge(mergeSort(l), mergeSort(r))
    }
    else
      a

  }

  def iterativeMergeSort(a: Seq[Int]): Seq[Int] = {

    val q: mutable.Queue[Int] = mutable.Queue()
    val rq: mutable.Queue[Int] = mutable.Queue()

    for (i <- a.indices) q.enqueue(a(i))

    val q1 = q
    val q2 = q


    while (q.length > 1) {
      val one = q.dequeue()
      val l1 = q.dequeueAll(_ != one )
      q.enqueueAll(l1)
      val two = q.dequeue()
      val l2 = q.dequeueAll(_ != two )
      q.enqueueAll(l2)
      val m = merge( List(one), List(two))
      rq.enqueueAll(m)
    }

    rq.toList
  }

}
