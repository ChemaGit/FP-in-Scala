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

  def main(args: Array[String]): Unit = {

    printHello(1)
    printHello(times = 2) // argument name provided explicitly

    printHello2()

    println(hello())
    val helloHello = hello(123) + " " + hello(456)
    println(helloHello.reverse)
  }
}
