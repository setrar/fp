/**
 * Copyright (C) 2009-2013 Typesafe Inc. <http://www.typesafe.com>
 */
package actorbintree

import akka.actor._
import sun.security.x509.GeneralSubtrees
import scala.collection.immutable.Queue

object BinaryTreeSet {

  trait Operation {
    def requester: ActorRef
    def id: Int
    def elem: Int
  }

  trait OperationReply {
    def id: Int
  }

  /** Request with identifier `id` to insert an element `elem` into the tree.
    * The actor at reference `requester` should be notified when this operation
    * is completed.
    */
  case class Insert(requester: ActorRef, id: Int, elem: Int) extends Operation

  /** Request with identifier `id` to check whether an element `elem` is present
    * in the tree. The actor at reference `requester` should be notified when
    * this operation is completed.
    */
  case class Contains(requester: ActorRef, id: Int, elem: Int) extends Operation

  /** Request with identifier `id` to remove the element `elem` from the tree.
    * The actor at reference `requester` should be notified when this operation
    * is completed.
    */
  case class Remove(requester: ActorRef, id: Int, elem: Int) extends Operation

  /** Request to perform garbage collection*/
  case object GC

  /** Holds the answer to the Contains request with identifier `id`.
    * `result` is true if and only if the element is present in the tree.
    */
  case class ContainsResult(id: Int, result: Boolean) extends OperationReply
  
  /** Message to signal successful completion of an insert or remove operation. */
  case class OperationFinished(id: Int) extends OperationReply

}


class BinaryTreeSet extends Actor with Stash {
  import BinaryTreeSet._
  import BinaryTreeNode._

  def createRoot: ActorRef = context.actorOf(BinaryTreeNode.props(0))

  var root = createRoot

  // optional
  var pendingQueue = Queue.empty[Operation]

  // optional
  def receive = normal

  // optional
  /** Accepts `Operation` and `GC` messages. */
  val normal: Receive = {
    case Insert  (requester, id, elem) => root ! Insert  (requester,id,elem)
    case Contains(requester, id, elem) => root ! Contains(requester,id,elem)
    case Remove  (requester, id, elem) => root ! Remove  (requester,id,elem)
    case GC => stash()
  }

  // optional
  /** Handles messages while garbage collection is performed.
    * `newRoot` is the root of the new binary tree where we want to copy
    * all non-removed elements into.
    */
  def garbageCollecting(newRoot: ActorRef): Receive = ???

}

object BinaryTreeNode {
  trait Position

  case object Left extends Position
  case object Right extends Position

  case class CopyTo(treeNode: ActorRef)
  case object CopyFinished

  def props(elem: Int, initiallyRemoved: Option[Boolean] = Some(false)) = Props(classOf[BinaryTreeNode],  elem, initiallyRemoved)
}

class BinaryTreeNode(val elem: Int, initiallyRemoved: Option[Boolean] = Some(false)) extends Actor {
  import BinaryTreeNode._
  import BinaryTreeSet._

  var subtrees = Map[Position, ActorRef]()
  var removed = initiallyRemoved

  // optional
  def receive = normal

  // optional
  /** Handles `Operation` messages and `CopyTo` requests. */
  val normal: Receive = {
    case Remove(requester, id, elem) =>
      elem match {
        case x: Int if x == this.elem =>
          removed = Some(true)
          val parent = context.parent

          parent ! Remove(requester, id, elem) // -- FIXME must delete on parent subtree

          requester ! OperationFinished(id = id)
        case x: Int if x < this.elem =>
          subtrees get Left match {
            case Some(actorRef) => actorRef ! Remove(requester, id, elem)
            case _ =>
          }
        case x: Int if x > this.elem =>
          subtrees get Right match {
            case Some(actorRef) => actorRef ! Remove(requester, id, elem)
            case _ =>
          }
      }
    case Insert(requester, id, elem) =>
      elem match {
        case x: Int if x == this.elem =>
          // do not allow duplicate entries
          requester ! OperationFinished(id = id)
        case x: Int if x < this.elem =>
          subtrees get Left match {
            case Some(actorRef) => actorRef ! Insert(requester, id, elem)
            case _ =>
              val node = context.actorOf(BinaryTreeNode.props(elem))
              subtrees += (Left -> node)
              requester ! OperationFinished(id = id)
          }
        case x: Int if x > this.elem =>
          subtrees get Right match {
            case Some(actorRef) => actorRef ! Insert(requester, id, elem)
            case _ =>
              val node = context.actorOf(BinaryTreeNode.props(elem))
              subtrees += (Right -> node)
              requester ! OperationFinished(id = id)
          }
      }
    case Contains(requester, id, elem) =>
      if (subtrees.nonEmpty)
          elem match {
            case x: Int if x == this.elem =>
              removed match {
                case Some(test) => requester ! ContainsResult(id = id, result = !test)
                case _ =>
              }
            case x: Int if x < this.elem =>
              subtrees get Left match {
                case Some(actorRef) => actorRef ! Contains(requester, id, elem)
                case _ => requester ! ContainsResult(id = id, result = false)
              }
            case x: Int if x > this.elem =>
              subtrees get Right match {
                case Some(actorRef) => actorRef ! Contains(requester, id, elem)
                case _ => requester ! ContainsResult(id = id, result = false)
              }
          }
      else {
        val found = removed match {
          case Some(test) if test => false
          case Some(test) if !test => if (elem == this.elem) true else false
        }
        requester ! ContainsResult(id = id, result = found )
      }
    case _ => ???
  }

  // optional
  /** `expected` is the set of ActorRefs whose replies we are waiting for,
    * `insertConfirmed` tracks whether the copy of this node to the new tree has been confirmed.
    */
  def copying(expected: Set[ActorRef], insertConfirmed: Boolean): Receive = ???


}
