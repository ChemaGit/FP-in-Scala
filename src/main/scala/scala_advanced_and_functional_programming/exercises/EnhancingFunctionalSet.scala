package scala_advanced_and_functional_programming.exercises

import scala.annotation.tailrec

trait MySetA[A] extends (A => Boolean) {

  /*
    EXERCISE - implement a functional set
   */
  def apply(elem: A): Boolean =
    contains(elem)

  def contains(elem: A): Boolean
  def +(elem: A): MySetA[A]
  def ++(anotherSet: MySetA[A]): MySetA[A] // union

  def map[B](f: A => B): MySetA[B]
  def flatMap[B](f: A => MySetA[B]): MySetA[B]
  def filter(predicate: A => Boolean): MySetA[A]
  /*
  EXERCISE
  - removing an element
  - intersection with another
  - difference with another set
 */
  def foreach(f: A => Unit): Unit
  def -(elem: A): MySetA[A]
  def &(anotherSet: MySetA[A]):MySetA[A] // intersection
  def --(anotherSet: MySetA[A]): MySetA[A] // difference
  // EXERCISE - implement a unary_! = NEGATION of a set
  // set[1,2,3] =>
  def unary_! : MySetA[A]
}

class EmptySetA[A] extends MySetA[A] {
  def contains(elem: A): Boolean = false
  def +(elem: A): MySetA[A] = new NonEmptySetA[A](elem, this)
  def ++(anotherSet: MySetA[A]): MySetA[A] = anotherSet

  def map[B](f: A => B): MySetA[B] = new EmptySetA[B]
  def flatMap[B](f: A => MySetA[B]): MySetA[B] = new EmptySetA[B]
  def filter(predicate: A => Boolean): MySetA[A] = this
  def foreach(f: A => Unit): Unit = ()

  def -(elem: A): MySetA[A] = this
  override def &(anotherSet: MySetA[A]): MySetA[A] = this

  override def --(anotherSet: MySetA[A]): MySetA[A] = this

  override def unary_! : MySetA[A] = ??? //new AllInclusiveSet[A]
}

//class AllInclusiveSet[A] extends MySetA[A] {
//
//}

class PropertyBasedSet[A](property: A => Boolean) extends MySetA[A] {
  override def contains(elem: A): Boolean = true

  override def +(elem: A): MySetA[A] = this

  override def ++(anotherSet: MySetA[A]): MySetA[A] = this

  override def map[B](f: A => B): MySetA[B] = ???

  // naturals = AllInclusiveSet[Int] = all the natural numbers
  // naturals.map(x => x % 3) => ???
  // [0, 1 ,2]
  override def flatMap[B](f: A => MySetA[B]): MySetA[B] = ???

  override def filter(predicate: A => Boolean): MySetA[A] = ??? // property-based set

  override def foreach(f: A => Unit): Unit = ???

  override def -(elem: A): MySetA[A] = ???

  override def &(anotherSet: MySetA[A]): MySetA[A] = filter(anotherSet)

  override def --(anotherSet: MySetA[A]): MySetA[A] = filter(!anotherSet)

  override def unary_! : MySetA[A] = new EmptySetA[A]
}

class NonEmptySetA[A](head: A, tail: MySetA[A]) extends MySetA[A] {
  def contains(elem: A): Boolean = {
    elem == head || tail.contains(elem)
  }
  def +(elem: A): MySetA[A] = {
    if(this.contains(elem)) this
    else new NonEmptySetA[A](elem, this)
  }
  def ++(anotherSet: MySetA[A]): MySetA[A] = {
    tail ++ anotherSet + head
  }

  def map[B](f: A => B): MySetA[B] = {
    tail.map(f) + f(head)
  }
  def flatMap[B](f: A => MySetA[B]): MySetA[B] = {
    tail.flatMap(f) ++ f(head)
  }
  def filter(predicate: A => Boolean): MySetA[A] = {
    val filteredTail = tail.filter(predicate)
    if(predicate(head)) filteredTail + head
    else filteredTail
  }
  def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  def -(elem: A): MySetA[A] = {
    if(head == elem) tail
    else tail -(elem) + this.head
  }

  override def &(anotherSet: MySetA[A]): MySetA[A] = {
    filter(anotherSet)
  }

  override def --(anotherSet: MySetA[A]): MySetA[A] = {
    filter(x => !anotherSet.contains(x))
  }

  // new operator
  def unary_! : MySetA[A] = ???
}

object MySetA {
  def apply[A](values: A*): MySetA[A] = {
    @tailrec
    def buildSet(valSeq: Seq[A], acc: MySetA[A]): MySetA[A] = {
      if(valSeq.isEmpty) acc
      else buildSet(valSeq.tail, acc + valSeq.head)
    }
    buildSet(values.toSeq, new EmptySetA[A])
  }
}

object MySetPlaygroundA extends App {
  val s = MySetA(1,2,3,4)
  s.foreach(println)
  println("**********")
  (s + 5).foreach(println)
  println("**********")
  s + 5 ++ MySetA(-1, -2) + 3 foreach(println)
  println("**********")
  (s + 5 ++ MySetA(-1, -2) + 3).map(x => x * 10).foreach(println)
  println("**********")
  (s + 5 ++ MySetA(-1, -2) + 3).flatMap(x => MySetA(x * 10)).foreach(println)
  println("**********")
  (s + 5 ++ MySetA(-1, -2) + 3).flatMap(x => MySetA(x * 10)).filter(x => x % 2 == 0).foreach(println)
  println("**********")
  s.-(1).foreach(println)
  println("**********")
  val s2 = MySetA(2, 3)
  (s & s2).foreach(println)
  println("**********")
  (s -- s2).foreach(println)

}

object EnhancingFunctionalSet {

}

