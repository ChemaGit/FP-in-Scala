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

  /**
    * Implement the function tail for removing the first
    * element of a List. Note that the function takes
    * constant time
    * @param ds
    * @tparam A
    * @return
    */
  def tail[A](ds: List[A]): List[A] = ds match {
    case Nil => sys.error("tail of an empty list")
    case Cons(_, tail) => tail
  }

  /**
    * Generalize tail to the function drop, which removes the first
    * n elements from a list.
    * @param l
    * @param n
    * @tparam A
    * @return
    */
  @annotation.tailrec
  def drop[A](l: List[A], n: Int): List[A] = {
    n match {
      case num if (num <= 0) => l
      case _ => {
        l match {
          case Nil => Nil
          case Cons(_, tail) => drop(tail, n - 1)
        }
      }
    }
  }

  /**
    * Removes elements from the List prefix as long as they match
    * a predicate.
    * @param a1
    * @param f
    * @tparam A
    * @return
    */
    @annotation.tailrec
  def dropWhile[A](a1: List[A], f: A => Boolean): List[A] = {
    a1 match {
      case Nil => Nil
      case Cons(h, tail) => {
        if(f(h)) dropWhile(tail, f)
        else a1
      }
    }
  }

  // TODO: def append => pag 36

  /**
    * Using the same idea, implement the function setHead
    * for replacing the first element of a List with a different
    * value
    * @param x
    * @param ds
    * @tparam A
    * @return
    */
  def setHead[A](x: A, ds: List[A]): List[A] = ds match {
    case Nil => sys.error("set head of an empyt list")
    case Cons(_ ,tail) => Cons(x, tail)
  }

  /**
    * Variadic functions in Scala
    * Variadic functions: meaning it accepts zero
    * or more arguments of type A
    * By calling this function
    * apply and placing it in the companion object,
    * we can invoke it with syntax like
    * List(1,2,3,4) or List("hi","bye"),
    * with as many values as we want separated
    * by commas (we sometimes call this the list literal
    * or just literal syntax). Variadic functions
    * are just providing a little syntactic sugar
    * for creating and passing a Seq of elements explicitly.
    * The special _* type annotation allows us to pass a Seq to a
    * variadic method.
    * @param as
    * @tparam A
    * @return
    */
  def apply[A](as: A*): List[A] = {
    if(as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  }
}

object Main extends App {
  val ex1: List[Double] = Nil
  val ex2: List[Int] = Cons(1, Nil)
  val ex3: List[String] = Cons("a", Cons("b", Nil))

  // todo: Pattern Matching
  /**
    * What will be the result of the following expression?
    */
  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + List.sum(t)
    case _ => 101
  }
  println(x)

  println(List.tail(List(1,2,3,4)))
  println(List.tail(List("a", "b", "c", "d")))
  println(List.setHead(1, List(2,3,4,5)))
  println(List.drop(List(1,2,3,4,5), 2))
  println(List.dropWhile(List(2,4,6,7,8,10), (n: Int) => n % 2 == 0))
}
