package africa.valiha.ttt.state

import scalaz.State

object ZipWithIndex {

  type ListMonad[A] = State[List[A], A]

  def ZipWithIndex[A](as: ListMonad[Unit]): ListMonad[Int] = ???
//    as.foldLeft(List())()

}