package scala_and_functional_programming.lectures.part4pm

import functional_programming_in_scala.functional_data_structures.Cons
import functional_programming_in_scala.functional_data_structures.List
import functional_programming_in_scala.functional_data_structures.Nil

object AllThePatterns extends App {

  // 1 - constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
  }

  // 2 - match anything
  // 2.a wildcard
  val matchAnything = x match {
    case _ => "Whatever"
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3 - tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) =>
    case (something, 2) => s"I've found $something"
  }

  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) =>
  }
  // PMs can be NESTED!

  // 4 - case classes - constructor pattern
  val aList: List[Int] = Cons(1, Cons(2, Nil))
  val matchAList = aList match {
    case Nil =>
    case Cons(h, t) =>
    case Cons(head, Cons(subHead, subTail)) =>
  }

  // 5 - list patterns
  val aStandardList = List(1, 2, 3, 42)
//  val standardListMatching = aStandardList match {
//    case List(1,_,_,_) => // extractor - advanced
//    case List(1, _*) => // list of arbitrary length - advanced
//    case 1 +: List(_) => // infix pattern
//    case List(1,2,3) :+ 42 => // infix pattern
//  }

  // 6 - type spcifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => // explicit type specifier
    case _ =>
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_, _) => // name binding => use the name later(here)
    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested patterns
  }

  // 8 - multi-patterns
  val multipattern = aList match {
    case Nil | Cons(0, _) => // compound pattern (multi - pattern)
  }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 =>
  }

  // ALL

  /*
    Question.
   */
  val numbers = List(1, 2, 3)
  val numbersMatch = numbers match {
    case listOfString: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
  }

  println(numbersMatch)
  //  JVM trick question

}
