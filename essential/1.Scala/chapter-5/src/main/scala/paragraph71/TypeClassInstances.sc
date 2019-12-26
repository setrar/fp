
import scala.math.Ordering

// 7.1.1 Ordering

val minOrdering = Ordering.fromLessThan[Int](_ < _)

val maxOrdering = Ordering.fromLessThan[Int](_ > _)

List(3, 4, 2).sorted(minOrdering)

List(3, 4, 2).sorted(maxOrdering)

// Implicit Values

implicit val absOrdering: Ordering[Int] = Ordering.fromLessThan[Int]{
  (x, y) => Math.abs(x) < Math.abs(y)
}

assert(List(-4, -1, 0, 2, 3).sorted == List(0, -1, 2, 3, -4))

assert(List(-4, -3, -2, -1).sorted == List(-1, -2, -3, -4))