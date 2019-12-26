package paragraph47

sealed trait Expression {
  def eval: Calculation = this match {
    case Addition(left, right) => // left.eval + right.eval
        left.eval match {
          case Failure(reason) => Failure(reason)
          case Success(r1) =>
            right.eval match {
              case Failure(reason) => Failure(reason)
              case Success(r2) => Success(r1 + r2)
            }
        }
    case Subtraction(left, right) => // left.eval - right.eval
      left.eval match {
        case Failure(reason) => Failure(reason)
        case Success(r1) =>
          right.eval match {
            case Failure(reason) => Failure(reason)
            case Success(r2) => Success(r1 - r2)
          }
      }
    case Number(value) => Success(value)
    case Division(numerator,denominator) => //numerator.eval / denominator.eval
      numerator.eval match {
        case Failure(reason) => Failure(reason)
        case Success(r1) =>
          denominator.eval match {
            case Failure(reason) => Failure(reason)
            case Success(r2) =>
              if (r2 == 0)
                Failure("Divide by Zero")
              else
                Success(r1 / r2)
          }
      }
    case SquareRoot(value) => // value.eval / value.eval
      value.eval match {
        case Failure(reason) => Failure(reason)
        case Success(r) =>
          if (r < 0)
            Failure("Square root of negative number")
          else
            Success(Math.sqrt(r))
      }
  }
}
final case class Addition(left: Expression, right: Expression) extends Expression
final case class Subtraction(left: Expression, right: Expression) extends Expression
final case class Number(value: Double) extends Expression
final case class Division(numerator: Expression, denominator: Expression) extends Expression
final case class SquareRoot(value: Expression) extends Expression
