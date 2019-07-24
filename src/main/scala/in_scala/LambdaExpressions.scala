package in_scala

object DemoLambdaExpressions {
	def main(args: Array[String]): Unit = {
		println("f(5): " + f(5))
		println("sum(5,10): " + sum(5,10))
		println("f2(5): " + f2(5))
	}

	val f = (x: Double) => x * x
	val sum = (x: Double,y: Double) => x + y
	val f2 = (_: Double) * 2
}
