// http://timperrett.com/2013/11/25/understanding-state-monad/

package africa.valiha.ttt.state

sealed trait Aspect
case object Green extends Aspect
case object Amber extends Aspect
case object Red   extends Aspect

sealed trait Mode
case object Off      extends Mode
case object Flashing extends Mode
case object Solid    extends Mode

// represents the actual display set: must be enabled before
// it can be used.
case class Signal(isOperational: Boolean, display: Map[Aspect, Mode])

object Signal {

  import scalaz.State, State._

  // just a lil' bit of sugar to use later on.
  type ->[A,B] = (A,B)

  // convenience alias as all state ops here will deal
  // with signal state.
//  type SignalState[A] = State[Signal,A]

  // dysfunctional lights revert to their flashing
  // red lights to act as a stop sign to keep folks safe.
  def apply(): Signal = Signal(
    isOperational = false,
    display = Map(Red -> Flashing, Amber -> Off, Green -> Off))

  def enable: State[Signal, Boolean] = State[Signal, Boolean]{ state =>
    val cp = state.copy(isOperational = true)
    (cp,cp.isOperational)
  }

  def change(seq: Aspect -> Mode*): State[Signal, Map[Aspect, Mode]] =
    for {
      m <- init
      _ <- modify(display(seq))
      s <- get
    } yield s.display

  // FIXME: requires domain logic to prevent invalid state changes
  // or apply any other domain rules that might be needed.
  // I leave that as an exercise for the reader.
  def display(seq: Seq[Aspect -> Mode]): Signal => Signal = signal =>
    if(signal.isOperational)
      signal.copy(display = signal.display ++ seq.toMap)
    else Signal()

  // common states the signal can be in.
  def halt  = change(Red -> Solid, Amber -> Off,   Green -> Off)
  def ready = change(Red -> Solid, Amber -> Solid, Green -> Off)
  def go    = change(Red -> Off,   Amber -> Off,   Green -> Solid)
  def slow  = change(Red -> Off,   Amber -> Solid, Green -> Off)

  def main(args: Array[String]): Unit = {

    import scalaz.State.{get => current}

    val program = for {
      _  <- enable
      r0 <- current // debugging
      _  <- halt
      r1 <- current // debugging
      _  <- ready
      r2 <- current // debugging
      _  <- go
      r3 <- current // debugging
      _  <- slow
      r4 <- current
    } yield r0 :: r1 :: r2 :: r3 :: r4 :: Nil

    val p = program.eval(Signal())
    p.zipWithIndex.foreach { case (v,i) =>
      println(s"r$i - $v")
    }

    assert(p==
      List(
        Signal(true,Map(Red -> Flashing, Amber -> Off, Green -> Off)),
        Signal(true,Map(Red -> Solid, Amber -> Off, Green -> Off)),
        Signal(true,Map(Red -> Solid, Amber -> Solid, Green -> Off)),
        Signal(true,Map(Red -> Off, Amber -> Off, Green -> Solid)),
        Signal(true,Map(Red -> Off, Amber -> Solid, Green -> Off))
      )
    )


  }

}
