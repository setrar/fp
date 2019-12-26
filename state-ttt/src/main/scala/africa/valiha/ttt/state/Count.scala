package africa.valiha.ttt.state

import scalaz.State

object Count {

  type StateCoin = State[List[Int],Int]

  def count(target: Int): StateCoin = State{ coins =>
    if (target == 0)
      (coins,1)
    else
    if (coins.isEmpty || target < 0)
      (coins,0)
    else
      (coins,count(target).eval(coins.tail) + count(target - coins.head).eval(coins))
  }


  def main(args: Array[String]): Unit = {
    assert(count(15).eval(List(1, 5, 10, 25))==6)
    assert(count(15).exec(List(1, 5, 10, 25))==List(1, 5, 10, 25))
  }

}

