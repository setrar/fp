/**
 * Copyright (C) 2009-2015 Typesafe Inc. <http://www.typesafe.com>
 */
package actorbintree

import akka.actor.{ Props, ActorRef, ActorSystem }
import org.scalatest.{ BeforeAndAfterAll, FlatSpec }
import akka.testkit.{ TestProbe, ImplicitSender, TestKit }
import org.scalatest.Matchers
import scala.util.Random
import scala.concurrent.duration._
import org.scalatest.FunSuiteLike

class BinaryTreeSuite(_system: ActorSystem) extends TestKit(_system) with FunSuiteLike with Matchers with BeforeAndAfterAll with ImplicitSender
{

  def this() = this(ActorSystem("BinaryTreeSuite"))

  override def afterAll: Unit = system.terminate() // .shutdown()

  import actorbintree.BinaryTreeSet._

  def receiveN(requester: TestProbe, ops: Seq[Operation], expectedReplies: Seq[OperationReply]): Unit =
    requester.within(5.seconds) {
      val repliesUnsorted = for (i <- 1 to ops.size) yield try {
        requester.expectMsgType[OperationReply]
      } catch {
        case ex: Throwable if ops.size > 10 => fail(s"failure to receive confirmation $i/${ops.size}", ex)
        case ex: Throwable                  => fail(s"failure to receive confirmation $i/${ops.size}\nRequests:" + ops.mkString("\n    ", "\n     ", ""), ex)
      }
      val replies = repliesUnsorted.sortBy(_.id)
      if (replies != expectedReplies) {
        val pairs = (replies zip expectedReplies).zipWithIndex filter (x => x._1._1 != x._1._2)
        fail("unexpected replies:" + pairs.map(x => s"at index ${x._2}: got ${x._1._1}, expected ${x._1._2}").mkString("\n    ", "\n    ", ""))
      }
    }

  def verify(probe: TestProbe, ops: Seq[Operation], expected: Seq[OperationReply]): Unit = {
    val topNode = system.actorOf(Props[BinaryTreeSet])

    ops foreach { op =>
      topNode ! op
    }

    receiveN(probe, ops, expected)
    // the grader also verifies that enough actors are created
  }

  test("just lookups") {
    val topNode = system.actorOf(Props[BinaryTreeSet])

    topNode ! Contains(testActor, id = 0, 0)
    expectMsg(ContainsResult(0, true))

    topNode ! Contains(testActor, id = 1, 1)
    expectMsg(ContainsResult(1, false))
  }

  test("more lookups") {
    val requester = TestProbe()
    val requesterRef = requester.ref
    val ops = List(
      Contains(requesterRef, id=50, 0),
      Contains(requesterRef, id=70, 2)
    )

    val expectedReplies = List(
      ContainsResult(id=50, true),
      ContainsResult(id=70, false)
    )

    verify(requester, ops, expectedReplies)
  }

  test("root insert") {
    val topNode = system.actorOf(Props[BinaryTreeSet])

    topNode ! Insert(testActor, id = 10, 0)
    topNode ! Contains(testActor, id = 20, 0)

    expectMsg(OperationFinished(10))
    expectMsg(ContainsResult(20, true))
  }

  test("no duplicates") {
    val topNode = system.actorOf(Props[BinaryTreeSet])

    topNode ! Insert(testActor, id = 11, 1)
    topNode ! Contains(testActor, id = 12, 1)

    topNode ! Insert(testActor, id = 13, 1)
    topNode ! Contains(testActor, id = 14, 1)

    expectMsg(OperationFinished(11))
    expectMsg(ContainsResult(12, true))

    expectMsg(OperationFinished(13))
    expectMsg(ContainsResult(14, true))
  }

  test("proper inserts") {
    val topNode = system.actorOf(Props[BinaryTreeSet])

    topNode ! Insert(testActor, id = 1, 1)
    topNode ! Contains(testActor, id = 2, 1)

    expectMsg(OperationFinished(1))
    expectMsg(ContainsResult(2, true))
  }

  test("proper inserts and lookups") {
    val topNode = system.actorOf(Props[BinaryTreeSet])

    topNode ! Contains(testActor, id = 1, 1)
    expectMsg(ContainsResult(1, false))

    topNode ! Insert(testActor, id = 2, 1)
    topNode ! Contains(testActor, id = 3, 1)

    expectMsg(OperationFinished(2))
    expectMsg(ContainsResult(3, true))
  }

  test("instruction example") {
    val requester = TestProbe()
    val requesterRef = requester.ref
    val ops = List(
      Insert(requesterRef, id=100, 1),
      Contains(requesterRef, id=50, 2),
      Remove(requesterRef, id=10, 1),
      Insert(requesterRef, id=20, 2),
      Contains(requesterRef, id=80, 1),
      Contains(requesterRef, id=70, 2)
      )

    val expectedReplies = List(
      OperationFinished(id=10),
      OperationFinished(id=20),
      ContainsResult(id=50, false),
      ContainsResult(id=70, true),
      ContainsResult(id=80, false),
      OperationFinished(id=100)
      )

    verify(requester, ops, expectedReplies)
  }

  test("insert after remove") {
    val requester = TestProbe()
    val requesterRef = requester.ref

    val ops = List(
      Insert(requesterRef, id = 25, 1)
      ,Contains(requesterRef, id = 26, 1)
      ,Remove(requesterRef, id = 27, 1)
      ,Contains(requesterRef, id = 28, 1)
      ,Insert(requesterRef, id = 29, 1)
      ,Contains(requesterRef, id = 30, 1)
    )

    val expectedReplies = List(
      OperationFinished(25)
      ,ContainsResult(26, true)
      ,OperationFinished(27)
      ,ContainsResult(28, false)
      ,OperationFinished(29)
      ,ContainsResult(id = 30, true)
    )
    verify(requester, ops, expectedReplies)
  }

  ignore("behave identically to built-in set (includes GC)") {
    val rnd = new Random()
    def randomOperations(requester: ActorRef, count: Int): Seq[Operation] = {
      def randomElement: Int = rnd.nextInt(100)
      def randomOperation(requester: ActorRef, id: Int): Operation = rnd.nextInt(4) match {
        case 0 => Insert(requester, id, randomElement)
        case 1 => Insert(requester, id, randomElement)
        case 2 => Contains(requester, id, randomElement)
        case 3 => Remove(requester, id, randomElement)
      }

      for (seq <- 0 until count) yield randomOperation(requester, seq)
    }

    def referenceReplies(operations: Seq[Operation]): Seq[OperationReply] = {
      var referenceSet = Set.empty[Int]
      def replyFor(op: Operation): OperationReply = op match {
        case Insert(_, seq, elem) =>
          referenceSet = referenceSet + elem
          OperationFinished(seq)
        case Remove(_, seq, elem) =>
          referenceSet = referenceSet - elem
          OperationFinished(seq)
        case Contains(_, seq, elem) =>
          ContainsResult(seq, referenceSet(elem))
      }

      for (op <- operations) yield replyFor(op)
    }

    val requester = TestProbe()
    val topNode = system.actorOf(Props[BinaryTreeSet])
    val count = 1000

    val ops = randomOperations(requester.ref, count)
    val expectedReplies = referenceReplies(ops)

    ops foreach { op =>
      topNode ! op
      if (rnd.nextDouble() < 0.1) topNode ! GC
    }
    receiveN(requester, ops, expectedReplies)
  }
}
