package africa.valiha.ttt.state

import scalaz.State
import scalaz.State.{get,modify,put,gets,iPut,iModify}

case class Session(version: Int = 0)

/*
https://gist.github.com/aappddeevv/6573279
 */
object StateSession {

  // Create a new output string and increment session version.
  def func1(arg: String): State[Session, String] =
    State((s: Session) => (s.copy(version = s.version + 1), arg + "-" + s.version))

  def composedFunction1: State[Session, String] = for {
    // Because we gave this val an explicit type, init does not need the type specification
    _ <- get[Session]
    // Increment the state and return state+value. Essentially, throw away the value.
    _ <- func1("joe")
    // Modify the state entirely.
    _ <- modify[Session] { _ => Session(100) }
    // Increment the state and return state+value. Essentially, throw away the value.
    _ <- func1("alice")
    // Modify the state directly again!
    _ <- put[Session](Session(200))
    // Increment the state and return state+value. Keep the value this time.
    x <- func1("nathan")
  } yield x // x is a State object with the type State[Session, String]

  // Now create a composition where the returned value changes. Since the returned
  // value does not really matter when the state propagates (the state needs to be the
  // same between for steps or have conversions available) so the returned value can
  // change each step of the way.
  def funcReturnInt(arg: String): State[Session, Int] =
    State((s: Session) => (s.copy(version=s.version+1), arg.toInt))

  def funcReturnStringNeedsInt(arg: String, i: Int): State[Session, String] =
    State((s: Session) => (s.copy(version=s.version+1), arg + "- " + s.version + "." + i))

  def funcReturnString(arg: String): State[Session, String] =
    State((s: Session) => (s.copy(version=s.version+1), arg + "-" + s.version))

  def composedFunction2: State[Session, String] = for {
    // by using put first, we effectively ignore any input value
    _ <- put[Session](Session())
    _ <- func1("joe")
    i <- funcReturnInt("30")
    x <- funcReturnStringNeedsInt("alice", i)
  } yield x

  // We can also compose the two composed functions together. We explicitly
  // make the type known (State[Session, String]) for illustration purposes
  // but the compiler can figure it out on its own.
  def composedFunction1and2: State[Session, String] = for {
    // Put the argument given by the programmer function into a state object
    _ <- get[Session]
    _ <- composedFunction1
    x <- composedFunction2
  } yield x

  // Composed a sequence using for-comprehension but use the values
  // returned from the function to do something in the for.
  def composedFunction3: State[Session,String] = for {
    _ <- get[Session]
    // The function returns a state object in v but due to the
    // way flatMap and map are defined on State objects, the v
    // will represent the value when specified on the rhs
    v <- funcReturnString("session")
    _ = println("Value returned was: " + v)
  } yield v // this is the composed function

  // Compose a function that grabs the state and makes it explicitly available
  // on the rhs of the for-expression line.
  def composedFunction4: State[Session,Session] = for {
    _ <- get[Session]
    _ <- State((x: Session) => (x.copy(version=x.version+1), "session" + "-" + x.version))
    // By calling get (which returns a function s => (s, s)), the state because
    // accessible as a value. Get is the same as init, both
    // put the current state into both the state and value of the returned tuple.
    s <- get[Session]
    // s maps its input value into both the state and value  (the state is now a value) so when its pulled apart
    // by map/flatMap, the value is the state. There are other ways to stick the state as a value.
    _ = println("This should print the state, because the state was turned into a value: " + s)
  } yield s // this is the composed function

  def composedFunction5: State[Session,Int] = for {
    _ <- get[Session]
    // We can use gets to generate a value anytime we want without touching the
    // the state. This allows us to breakup large functions and use
    // the same state across them all in the for-comprehension. This particular
    // function takes the current state and returns the version number.
    s <- gets[Session,Int]{ s: Session => s.version }
    _ = println("This should print the original input state's version value. The new value is in the value position: " + s)
  } yield s

  // Don't change the state, but return a new string based on the state.
  def funcUsesIntState: State[Int, String] =
    State( (x: Int) => (x+42, "blah-"+x))

  def composedFunction6 = for {
    // Put the Session value into a State
    _ <- get[Session]
    // Now like composedFunction5, keep the current state unchanged, but return a value
    // derived from that state.
    v <- gets[Session,Int]{ s: Session => s.version }
    // At this point we have the version number in the value position. So lets change
    // the type of the state on the fly and for fun, make that version number the new
    // state. This shows that we can change the type of state on the fly.
    _ <- iPut[Session, Int](v)
    // Now the state is only composed of an Int not the Session object. In this example
    // we are ignoring the actual value returned as well. This func does change the state
    // by adding 42 to it.
    _ <- funcUsesIntState
    // We can also change the state type inline with a computation to derive a new state.
    // Here we create a new state type that is a Map[String, Int].
    _ <- iModify { x: Int => Map("version" -> x) }
    // Just so we can return a value, we create one here using a very shortened
    // syntax that is not obvious at first glance. We are actually defining a function
    // that takes a state of the right type, and returns a String value.
    s <- State((m: Map[String, Int]) => (m, "last_value-" + m("version")))
  } yield s


  def main(args: Array[String]): Unit = {

    assert(composedFunction1(Session())==(Session(201),"nathan-200"))

    // Null is used because we have to call the composed function with
    // an argument. null is used for illustration purposes.
    assert(composedFunction2(null)==(Session(3),"alice- 2.30"))

    // Since composedFunction2 resets the state at the start of its function,
    // we should expect the same result as composedFunction2.
    assert(composedFunction1and2(Session())==(Session(3),"alice- 2.30"))

    assert(composedFunction3(Session(10))==(Session(11),"session-10"))

    assert(composedFunction4(Session(10))==(Session(11),Session(11)))

    assert(composedFunction5(Session(10))==(Session(10),10))

    // TODO State Return type not defined for function 6
    assert(composedFunction6(Session(10))==(Map("version" -> 52),"last_value-52"))

  }

}