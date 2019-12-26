package paragraph72

final case class Rational(numerator: Int, denominator: Int)

/**
  No implicit Ordering defined for paragraph72.Rational.
  When Companion Object name different than type class
 */
object Rational {
  implicit val ordering: Ordering[Rational] = Ordering.fromLessThan[Rational]((x, y) =>
    (x.numerator.toDouble / x.denominator.toDouble) <
    (y.numerator.toDouble / y.denominator.toDouble)
  )
}

object RationalGreaterThanOrdering {
  implicit val ordering: Ordering[Rational] = Ordering.fromLessThan[Rational]((x, y) =>
    (x.numerator.toDouble / x.denominator.toDouble) >
      (y.numerator.toDouble / y.denominator.toDouble)
  )
}
