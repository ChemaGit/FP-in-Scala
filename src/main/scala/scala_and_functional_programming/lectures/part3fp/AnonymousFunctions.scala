package scala_and_functional_programming.lectures.part3fp

object AnonymousFunctions extends App {
  // anonymous function (LAMBDA)
  val doubler = (x: Int) => x * 2

  // multiple params in a lambda
  val adder = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething: () => Int = () => 3

  // careful
  println(justDoSomething) // function itself
  println(justDoSomething()) // call

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // More syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  /*
    1. MyList: replace all FunctionX calls with lambdas

      println(listOfIntegers.map(v => v * 2).toString)
      println(listOfIntegers.filter(elem => elem % 2 == 0).toString)
      println(listOfIntegers.flatMap(elem => new ConsGB(elem, new ConsGB(elem + 1, EmptyGB))).toString)

    2. Rewrite the "special" adder as an anonymous function
   */

  val specialAdder = (x: Int) => (y: Int) => x + y


  println(specialAdder(5)(10))
}

/**
  TAKEAWAYS
    Instead of passing anonymous FunctionX instances every time
      * cumbersome
      * still object-oriented!
        (x, y) => x + y
        (name: String, age: Int) => s"$name is $age years old"

        - return type always inferred
        - parentheses mandatory for more than one parameter
        - type optional

      Further sugar: val add: (Int, Int) => Int = _ + _
  */