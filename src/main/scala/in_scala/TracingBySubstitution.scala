package in_scala

object TracingBySubstitution {
	def main(args: Array[String]): Unit = {

	}
	def fact(n: Int): Int = if(n < 2) 1 else n * fact(n - 1)

	def sumSquares(n: Int): Int = if(n < 2) 1 else n * n + sumSquares(n - 1)
}
