package in_scala

import scala.io.StdIn._

object MatchExpression {
	def main(args: Array[String]): Unit = {
		print("Enter an integer: ")
		val num = readInt
		println(s"Factorial $num: " + fact(num))
		println()
		print("Enter an integer: ")
		val num2 = readInt
		println(s"sumSquares $num2: " + sumSquares(num2))		
		println()
		print("Enter an integer: ")
		val num3 = readInt
		println(s"countDown $num3: " + countDown(num3))
	}

	def fact(n: Int): Int = n match {
		case 0 => 1
		case _ => n * fact(n - 1)
	}

	def sumSquares(n: Int): Int = n match {
		case 1 => 1
		case _ => n * n + sumSquares(n - 1)
	}

	def countDown(n: Int): Unit = n match {
		case 0 =>
		case _ => {
			println(n)
			countDown(n - 1)
		}
	}
}
