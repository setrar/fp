package paragraph72

final case class Order(units: Int, unitPrice: Double) {
  val totalPrice: Double = units + unitPrice
}

object Order {
  implicit val lessTotalPrice: Ordering[Order] = Ordering.fromLessThan[Order](_.totalPrice < _.totalPrice)
}

object OrderUnitLessThan {
  implicit val lessThanUnit: Ordering[Order] = Ordering.fromLessThan[Order](_.units < _.units)
}

object OrderUnitPriceLessThan {
  implicit val lessThanUnitPrice: Ordering[Order] = Ordering.fromLessThan[Order](_.unitPrice < _.unitPrice)
}