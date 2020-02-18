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

  // 0 and 1 are the first two numbers in the sequence,
  // so we start the accumulators with those.
  // At every iteration, we add the two numbers to get the next one.
  def fib(n: Int): Int = {
    @annotation.tailrec
    def loop(n: Int, prev: Int, cur: Int): Int =
      if (n == 0) prev
      else loop(n - 1, cur, prev + cur)
    loop(n, 0, 1)
  }

  // Solution with a List as accumulator
  def fibonacciTailRec(n: Int): Int = {
    @annotation.tailrec
    def loop(x: Int,acc: List[Int]): Int = {
      if(x == 1) acc.sum
      else {
        loop(x - 1, List(acc.sum, acc.head))
      }
    }
    n match {
      case 0 => 0
      case 1 => 1
      case 2 => 1
      case _ => loop(n - 2,List(1,1))
    }
  }



  def main(args: Array[String]): Unit = {
    println(formatAbs(-42))
    println(factorial(5))
    println(fibonacci(10))
    println(fibonacciTailRec(10))
  }

}
