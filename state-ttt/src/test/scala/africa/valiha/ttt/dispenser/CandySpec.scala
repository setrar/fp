package africa.valiha.ttt.dispenser

import org.scalatest.FunSpec

import scalaz.State.{get, modify}
import scalaz.{Applicative, State}
import scalaz.std.list._

class CandySpec extends FunSpec {

  def simulate(inputs: List[Input]): State[Candy, (Int, Int)] =
    for {
      _ <- Applicative[({type candyState[A] = State[Candy,A]})#candyState]
                .sequence(inputs.map(modify[Candy] _ compose Candy.withRule))
      s <- get
    } yield (s.coins, s.candies)


  describe("When evaluating run") {
    it("should inserting a coin into a locked machine cause it to unlock if there’s any candy left.") {
      val (state1, a) = simulate(List(Coin)).run(Candy(locked = true, 1, 0))
      assert(!state1.locked)
      assert(a == (1, 1)) // $1, 1 candy left
    }

    it("should turning the knob on an unlocked machine will cause it to dispense candy and become locked.") {
      val (state2, b) = simulate(List(Turn)).run(Candy(locked = false, 1, 1))
      assert(state2.locked)
      assert(state2.candies == 0)
      assert(b == (1, 0)) // $1, No candies left
    }

    it("should the Input Candy has 1 coin and 1 candy, and 1 candy if successfully bought.") {
      val (state3, x) = simulate(Coin :: Turn :: Nil).run(Candy(locked = true, 1, 1))
      assert(state3.locked)
      assert(state3.candies == 0)
      assert(x == (2, 0)) // $2, No candies left
    }

    it("should the Input Candy has 10 coins and 5 candies, and a total of 4 candies are successfully bought.") {
      val (state4, y) = simulate(List(Coin, Turn, Coin, Turn, Coin, Turn, Coin, Turn)).run(Candy(locked = true, 5, 10))
      assert(state4.locked)
      assert(state4.coins == 14)
      assert(state4.candies == 1)
      assert(y == (14, 1)) // $14, 1 candy left
    }
  }

  describe("When evaluating eval (Discard the state)") {
    it("should the Input Candy has 1 coin and 1 candy, and 1 candy is successfully bought.") {
      val z = simulate(Coin :: Turn :: Nil).eval(Candy(locked = true, 1, 1))
      assert(z == (2, 0)) // $2, No candies left
    }
  }

  describe("When evaluating exec (Show only the state)") {
    it ("should the Input Candy has 1 coin and 1 candy, and 1 candy is successfully bought.") {
      val state5 = simulate(Coin :: Turn :: Nil).exec(Candy(locked = true, 1, 1))
      assert(state5.locked)
      assert(state5.candies == 0)
    }
  }

}
