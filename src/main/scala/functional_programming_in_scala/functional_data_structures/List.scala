package functional_programming_in_scala.functional_data_structures

/**
  * Singly linked lists
  */

/**
  * List data type,
  * parameterized on a type, A.
  */
sealed trait List[+A]

/**
  * A List data constructor
  * representing the empty list.
  */
case object Nil extends List[Nothing]

/**
  * Another data constructor, representing
  * nonempty lists. Note that tail is
  * another List[A], which may be Nil
  * or another Cons.
  */
case class Cons[+A](head: A, tail: List[A]) extends List[A]

/**
  * List companion object.
  * Contains functions for creating and
  * working with lists
  */
object List {
  /**
    * A function that uses pattern matching to add up
    * a list of integers
    * @param ints
    * @return
    */
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0 // The sum of the empty list is 0
    // The sum of a list starting with x is x plus the sum
    // of the rest of the list
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = {
    if(as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  }
}

object Main extends App {
  val ex1: List[Double] = Nil
  val ex2: List[Int] = Cons(1, Nil)
  val ex3: List[String] = Cons("a", Cons("b", Nil))
}

// todo: 3.2 Pattern matching
