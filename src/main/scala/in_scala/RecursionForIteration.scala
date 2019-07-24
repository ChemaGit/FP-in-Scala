package in_scala

import scala.io.StdIn._

object RecursionForIteration {
	def main(args: Array[String]): Unit = {
		println("factorial(5): " + factorial(5))
		println()
		print("Introduce un numero para calcular el factorial: ")
		val num = readLine.toInt
		println()
		println(s"factorial($num): " + factorial(num))
		println()
		println(s"sumSquares($num): " + sumSquares(num))
		countDown(num)
		countFromTo(num, 20)

	}

	def factorial(x: Int): Long = {
		@annotation.tailrec
		def loop(n: Int, acum: Long): Long = {
			n match {
				case 1 => acum
				case _ => loop(n - 1, n * acum)
			}
		}
		if(x == 1) 1
		else loop(x, 1)
	}

	def sumSquares(n: Int): Long = {
		@annotation.tailrec
		def loop(x: Int, acum: Long): Long = {
			x match {
				case 1 => acum
				case _ => loop(x - 1, (x * x) + acum)
			}
		}
		if(n == 1) 1
		else loop(n, 1)
	}

	def countDown(n: Int): Unit = {
		if(n > 0){
			println(n)
			countDown(n - 1)
		}
	}

	def countFromTo(from: Int, to: Int): Unit = {
		if(from <= to) {
			println(from)
			countFromTo(from + 1, to)
		}
	}
}
