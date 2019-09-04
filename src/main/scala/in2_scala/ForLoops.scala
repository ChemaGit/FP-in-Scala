package in2_scala

object ForLoops {
	// 3 * x^2 + 2 * x -5
	// Array(3,2,-5)
	def evalPoly(coeffs: Array[Double],x: Double): Double = {
		var sum = 0.0
		for(i <- coeffs.indices) {
			sum += coeffs(i) * math.pow(x, coeffs.length - 1 - i)
		}
		sum
	}

	def main(args: Array[String]): Unit = {
		for(i <- 0 to 9) println(i)
		println()
		val lst = List(6,6,5,4,3,2,8,89,45)
		for(i <- lst) println(i)
		println()
		println(evalPoly(Array(3,2,-5),1))
	}
}
