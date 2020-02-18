package functional_programming_in_scala.getting_started_with_FP

/**
  * Declares a singleton object, which
  * simultaneously declares a class and its only instance.
  */
object Introducing {

  def abs(n: Int): Int = {
    if(n < 0) -n
    else n
  }

  private def formatAbs(x: Int) = {
    val msg = "The absoute value of %d is %d"
    msg.format(x, abs(x))
  }

  def factorial(x: Int): Int = {
    @annotation.tailrec
    def go(n: Int, acc: Int): Int = {
      if(n <= 0) acc
      else go(n - 1, n * acc)
    }
    go(x, 1)
  }


  def fibonacci(n: Int): Int = {
    if(n == 0) 0
    else if(n == 1) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }

  def fibonacciTailRec(n: Int): Int = {
    def loop(x: Int, acc: Int): Int = {
      if(x == 2) acc + 1
      else if(x == 1) acc + 1
      else if(x <= 0) acc
      else loop(x - 3, acc + (x - 1) + (x - 2))
    }
    loop(n, 0)
  }



  def main(args: Array[String]): Unit = {
    println(formatAbs(-42))
    println(factorial(5))
    println(fibonacci(3))
    println(fibonacci(4))
    println(fibonacciTailRec(3))
  }

}
