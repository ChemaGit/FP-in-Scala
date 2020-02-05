package scala_and_functional_programming.lectures.part3fp

object HOFsCurries extends App {

  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
  // higher order function (HOF)

  // map, flatMap, filter in MyList

  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes, 2, f(x)) = f(f(f(x)))
  // nTimes(f, n, x) = f(f(...f(x)))) = nTimes(f, n - 1, f(x))
  @annotation.tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if(n <= 0) x
    else nTimes(f, n - 1, f(x))

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  // nTimesBetter = x x => f(f(f...(x)))
  // increment10 = nTimesBetter(plusOne, 10) = x => plusOne(plusOne....(x))
  // val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if(n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  // curried functions
  val superAdder = (x: Int) => (y: Int) => x + y

  val add3 = superAdder(3) // y => 3 + 1
  println(add3(10))
  println(superAdder(3)(10))

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  /**
    * EXERCISES
    * 1. Expand MyList ==> All this is in "OOExpandingOurCollection.scala"
    *   - foreach method A => Unit
    *     [1,2,3].foreach(x => println(x))
    *
    *   - sort function ( (A, A) => Int) => MyList
    *     [1,2,3].sort((x, y) => y - x) => [3,2,1]
    *
    *   - zipWith (list, (A, A) => B) => MyList[B]
    *     [1,2,3].zipWith([4,5,6], x * y) => [1 * 4, 2 * 5, 3 * 6]
    *
    *   - fold(start)(function) => a value
    *     [1, 2, 3].fold(0)(x + y) = 6
    *
    * 2. toCurry(f: (Int, Int) => Int => (Int => Int => Int)
    *    fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int
    *
    * 3. compose(f, g) => x => f(g(x))
    *    andthen(f, g) => x => g(f(x))
    */

}

/**
  * Functional programming = working with functions
  *   - pass functions as parameters
  *   - return functions as results
  *
  *   => Higher Order Functions (HOFs)
  *   def nTimesBetter(f: Int => Int, n: Int): Int => Int = ....
  *
  *   Currying = functions with multiple parameter lists
  *   def curriedFormatter(a: Int, b: Int)(c: String): String
  */