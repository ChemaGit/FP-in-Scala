package hands_on_scala.basic_scala

object MethodsAndFunctions {

  def printHello(times: Int) = {
    println("hello " + times)
  }

  def printHello2(times: Int = 0) = {
    println("hello " + times)
  }

  def hello(i: Int = 0) = {
    "hello " + i
  }

  class Box(var x: Int) {
    def update(f: Int => Int) = {
      x = f(x)
    }

    def printMsg(msg: String) = {
      println(msg + x)
    }
  }

  def main(args: Array[String]): Unit = {

    /**
      * METHODS
      */
    printHello(1)
    printHello(times = 2) // argument name provided explicitly

    printHello2()

    println(hello())
    val helloHello = hello(123) + " " + hello(456)
    println(helloHello.reverse)

    /**
      * FUNCTION VALUES
      */
    var g: Int => Int = i => i + 1
    println(g(15))

    g = i => i * 2
    println(g(15))

    /**
      * Methods taking functions
      */
    val b = new Box(1)
    b.printMsg("Hello")
    b.update(i => i + 5)
    b.printMsg("Hello")

    b.update(i => i * 5)
    b.printMsg("Hello")

    b.update(_ + 5)
    b.printMsg("Hello")

    def increment(i: Int) = i + 1
    val c = new Box(123)
    c.update(increment) // Providing a method reference
    c.printMsg("Hello")
    c.update(x => increment(x)) // Explicitly writing out the lambda
    c.printMsg("Hello")
    c.update{x => increment(x)} // Methods that take a single lambda can be called with {}s
    c.printMsg("Hello")
    c.update(increment(_)) // You can also use the `_` placeholder syntax
    c.printMsg("Hello")


    def myLoop(start: Int, end: Int)(callback: Int => Unit) = {
        for (i <- Range(start, end)) {
          callback(i)
        }
    }

    myLoop(start = 5, end = 10) { i => println(s"i has value ${i}")}
  }
}
