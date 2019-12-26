package africa.valiha.ttt.dispenser

sealed trait Input
case object Coin extends Input
case object Turn extends Input

case class Candy(locked: Boolean, candies: Int, coins: Int)

object Candy {

  def withRule(i: Input): Candy => Candy = (s: Candy) => (i, s) match {
    case (_, Candy(_, 0, _))        => s
    case (Coin, Candy(false, _, _)) => s
    case (Turn, Candy(true, _, _))  => s
    case (Coin, Candy(true, candy, coin)) => Candy(locked = false, candy, coin + 1)
    case (Turn, Candy(false, candy, coin)) => Candy(locked = true, candy - 1, coin)
  }

}

