package scala_and_functional_programming.lectures.part3fp

object WhatsAFunction extends App {
  // DREAM: use functions as first class elements
  // problem: oop

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function1[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("10"))

  val adder = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A, B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS

  /*
    1. a function which takes 2 strings and concatenates them
    2. transform the MyPredicate and MyTransformer into function types => implements this in "OOExpandingOurCollection"
    3. define a function which takes an int and returns another function which takes an int and returns an int
      - what's  the type of this function
      - how to do it
   */

  def concatenator: (String,String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String =  s"$a$b"
  }

  println(concatenator("Hello", " Scala"))

  // the type is going to be Function1[Int, Function1[Int, Int]]
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried function

  def returnInt(x: Int): Int => Int = {
    (y: Int) => x + y
  }

  val adder4 = returnInt(8)
  println(adder4(8))
  println(returnInt(8)(8))

}

trait MyFunction[A,B] {
  def apply(element: A): B = ???
}

/**
  * TAKEAWAYS
  * We want to work with functions:
  *   - pass functions as parameters
  *   - use functions as values
  *
  * Problem: Scala works on top of the JVM
  *   - designed for Java
  *   - first-class elements: objects(instances of classes)
  *
  * Solution: ALL Scala functions are objects!
  *   - function traits, up to 22 params
  *   - syntaic sugar function types
  *
  *   trait Function1[-A, +B] {
  *     def apply(element: A): B
  *   }
  *
  *   Function2[Int, String, Int]   (Int, String) => Int
  */
