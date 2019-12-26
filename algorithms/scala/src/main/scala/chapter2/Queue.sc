
import scala.collection.mutable

val a = Array[Int](1,2,3,4,5,6)

val q = mutable.Queue.empty[Int]

q.enqueueAll(a)

q.dequeueAll(_ > 10)

//q.enqueueAll(a)

q.dequeue()

q.dequeueFirst(_ < 4)

q

q.dequeueAll(_ < 8)

q.enqueueAll(a)

q

val one = q.dequeue()

val l1 = q.dequeueAll(_ != one )

q.enqueueAll(l1)

val two = q.dequeue()

val l2 = q.dequeueAll(_ != two )
