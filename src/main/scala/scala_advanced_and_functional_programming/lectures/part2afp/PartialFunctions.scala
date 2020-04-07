package scala_advanced_and_functional_programming.lectures.part2afp

/**
  * Takeaways
  *
  * trait PartialFunction[-A, +B] extends (A => B) {
  *   def apply(x: A): B
  *   def isDefinedAt(x: A): Boolean
  * }
  *
  * How to use:
  *
  * val simplePF: PartialFunction[Int, Int] = { // done with pattern matching
  *   case 1 => 42
  *   case 2 => 65
  *   case 3 => 999
  * }
  *
  * Utilities:
  * - isDefinedAt
  * - lift
  * - orElse
  *
  * Used on other types:
  * - map, collect on collections
  * - recover
  */

object PartialFunctions extends App {

  val aFunction = (x: Int) => x + 1 // Function[Int, Int] === Int => Int

  println(aFunction(6))

  val aFussyFunction = (x: Int) =>
    if(x == 1) 42
    else if(x == 2) 56
    else if(x == 5) 999
    else throw new FunctionNotApplicableException

  class FunctionNotApplicableException extends RuntimeException

  val aNicerFussyFunction = (x: Int) => x match {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  }
  // {1, 2, 5} => Int

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  } // partial function value

  println(aPartialFunction(2))
  // println(aPartialFunction(57273)) crash the program

  // PF utilities
  println(aPartialFunction.isDefinedAt(67))

  // lift
  val lifted = aPartialFunction.lift // Int => Option[Int]
  println(lifted(2))
  println(lifted(98))

  val pfChain = aPartialFunction.orElse[Int, Int] { // Extends a partial function
    case 45 => 67
  }

  println(pfChain(2))
  println(pfChain(45))

  // PF extend normal functions
  val aTotalFunction: Int => Int = {
    case 1 => 99
  }

  // HOFs accept partial functions as well
  val aMappedList = List(1,2,3).map{
    case 1 => 42
    case 2 => 78
    case 3 => 1000
  }
  println(aMappedList)

  /*
    Note: PF can only have ONE parameter type
   */
  /**
    * Exercises
    *
    * 1 - a PF instance yourself (anonymous class)
    * 2 - dumb chatbot a PF
    */
  val aManualFussyFunction = new PartialFunction[Int, Int] {
    override def apply(x: Int): Int = x match {
      case 1 => 2
      case 2 => 65
      case 5 => 999
    }

    override def isDefinedAt(x: Int): Boolean =
      x == 1 || x == 2 || x == 5
  }
  println(aManualFussyFunction.isDefinedAt(10))

  val chatbot: PartialFunction[String, String] = {
    case "hello" =>"Hi, my name is HAL9000"
    case "goodbay" =>"Once you start talking to me, there is no return, human!"
    case "call mom" =>"Unable to find your phone without your credit card"
    case _ => "I don't understand you"
  }

  scala.io.Source.stdin.getLines().map(chatbot).foreach(println)

}
