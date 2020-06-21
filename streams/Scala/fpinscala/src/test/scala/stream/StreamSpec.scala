package stream

import bytes.BytePacking

import scala.language.postfixOps
import org.scalatest._

class StreamSpec extends FlatSpec with Matchers {
  "The Stream ADT" should "pass headOption cases" in {
    Stream().headOption shouldEqual None
    Stream(5, 6).headOption shouldEqual Some(5)
  }

  "The Stream ADT" should "pass generate list cases" in {
    Stream().toList shouldEqual Nil
    Stream(1, 2, 3).toList shouldBe List(1, 2, 3)
  }

  "The Stream ADT" should "pass take function cases" in {
    Stream().take(3).toList shouldBe Nil
    Stream(10, 2).take(0).toList shouldBe Nil
    Stream(10).take(3).toList shouldBe List(10)
    Stream(1, 6, 4, 18, 5, 0).take(5).toList shouldBe List(1, 6, 4, 18, 5)
  }

  "the Stream ADT" should "pass drop function cases" in {
    Stream(1, 2, 3, 4, 5).drop(2).toList shouldBe List(3, 4, 5)
    Stream(1, 2).drop(0).toList shouldBe List(1, 2)
    Stream(1, 2).drop(3).toList shouldBe Nil
    Stream(1).drop(1).toList shouldBe Nil
    Stream(1).drop(2).toList shouldBe Nil
    Stream().drop(5).toList shouldBe Nil
  }

  "The Stream ADT" should "pass takeWhile_ function cases (with early termination)" in {
    Stream[Int]().takeWhile_(_ % 2 == 0).toList shouldBe Nil
    Stream(1, 2, 3, 4, 5).takeWhile_(_ < 3).toList shouldBe List(1,2)
    Stream(1, 2, 3, 4, 5).takeWhile_(_ < 0).toList shouldBe Nil
    Stream(2, 4, 5).takeWhile_(_ % 2 == 0).toList shouldBe List(2,4)
    Stream(1, 2, 3).takeWhile_(_ % 2 == 0).toList shouldBe Nil // Discontinued Stream not holding the predicate
  }

  "The Stream ADT" should "pass exists_ function cases (with early termination)" in {
    Stream(-3.6, 4.5, 2.2).exists(_ < -3.0) shouldBe true // Discontinued Stream not holding the predicate
  }

  "The Stream ADT" should "pass exists function cases" in {
    Stream(1,2,3,4,8).exists(_ % 2 == 0) shouldBe true
    Stream(-3.6, 4.5, 2.2).exists(4.0 >) shouldBe true
  }

  "The Stream ADT" should "pass forAll function cases" in {
    Stream(2,4,6).forAll(_ % 2 == 0) shouldBe true
    Stream(2,5,8).forAll(_ % 2 == 0) shouldBe false
    Stream('a','b','c').forAll(_.toInt > 96) shouldBe true // ASCII code for lowercase
  }

  "The Stream ADT" should "pass takeWhile cases" in {
    Stream[Int]().takeWhile(0 == 2 %_).toList shouldBe Nil
    Stream(1,2,3,4,5).takeWhile(3 >).toList shouldBe List(1,2)
  }

  "The Stream ADT" should "pass folded headOption cases" in {
    Stream().headOption_ shouldEqual None
    Stream(5, 6).headOption_ shouldEqual Some(5)
  }

  "The Stream ADT" should "pass map cases" in {
    Stream[String]().map(_ => "").toList shouldBe Nil
    Stream(1,2).map(4 *).toList  shouldBe List(4,8)
  }

  "The Stream ADT" should "pass filter cases" in {
    Stream(1,2,3,4).filter(0 == 2 %_).toList shouldBe List(1,2)
    Stream(3,4,6,8,10).filter(0 == 2 %_).toList shouldBe Nil // Incremental case explanation needed ???
  }

  "The Stream ADT" should "pass append cases" in {
    Stream(1,2,3).append(Stream(4,5,6)).toList shouldBe List(1,2,3,4,5,6)
    Stream().append(Stream()).toList shouldBe Nil
  }

  "The Stream ADT" should "pass flatMap cases" in {
    Stream(1,2).flatMap((x: Int) => Stream[Double](x * 1.00)).toList shouldBe List[Double](1.000,2.000)
    Stream(1,2).flatMap((x: Int) => Stream[String](x + ".0")).toList shouldBe List[String]("1.0","2.0")
  }

  "The Stream ADT" should "pass Listing 5.3 of the red book" in {
    Stream(1,2,3,4).map(_ + 10).filter(_ % 2 == 0).toList shouldBe 12 :: 14 :: List()
  }

  "The Stream ADT" should "pass find cases" in {
    Stream(1,2,3,4).find(_ < 2).toList shouldBe List(1)
  }

  "The Stream ADT" should "pass infinite ones cases" in {
    Stream.ones.take(5).toList shouldBe List(1,1,1,1,1)
    Stream.ones.exists(_ % 2 != 0) shouldBe true
    Stream.ones.map(_ + 1).exists(_ % 2 == 0) shouldBe true
    Stream.ones.takeWhile(_ == 1).exists(_ == 1) shouldBe true
    Stream.ones.forAll(_ != 1) shouldBe false
  }

  "The Stream ADT" should "pass constant cases" in {
    Stream[Int]().constant(2).take(5).toList shouldBe List(2,2,2,2,2)
    Stream[Double]().constant(4.0).takeWhile(4==).exists(4==) shouldBe true
  }

  "The Stream ADT" should "pass from cases" in {
    Stream[Int]().from(1).take(5).toList shouldBe List(1,2,3,4,5)
    Stream[Int]().from(1000).take(5).toList shouldBe List(1000, 1001,1002,1003,1004)
  }

  "The Stream ADT" should "pass fibs cases" in {
    Stream.fibs.take(7).toList shouldBe List(0,1,1,2,3,5,8)
  }

  "The Stream ADT" should "pass UNFOLD fibs cases" in {
    Stream[Int]().unfoldFibs.take(7).toList shouldBe List(0,1,1,2,3,5,8)
  }

  "The Stream ADT" should "pass UNFOLD constant cases" in {
    Stream[Int]().unfoldConstant(2).take(5).toList shouldBe List(2,2,2,2,2)
    Stream[Double]().unfoldConstant(4.0).takeWhile(4==).exists(4==) shouldBe true
  }
}
