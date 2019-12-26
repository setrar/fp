package paragraph72

import org.scalatest._

class OrderSpec extends FlatSpec with Matchers {
  "The Order by total amount ordered" should "be ascendant" in {
    List(Order(1,2.00), Order(3,4.00), Order(1,3.99)).sorted shouldEqual List(Order(1,2.00), Order(1,3.99), Order(3,4.00))
  }
  "The Order by units ordered" should "be ascendant" in {
    import paragraph72.OrderUnitLessThan._
    List(Order(1,3.99), Order(1,2.00), Order(3,4.00)).sorted shouldEqual List(Order(1,3.99), Order(1,2.00), Order(3,4.00))
  }
  "The Order by unit Price ordered" should "be ascendant" in {
    import paragraph72.OrderUnitPriceLessThan._
    List(Order(1,3.99), Order(1,2.00), Order(3,4.00)).sorted shouldEqual List(Order(1,2.00), Order(1,3.99), Order(3,4.00))
  }
}
