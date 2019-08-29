package in2_scala

object Currying {

	def add(x: Int): Int => Int = y => x + y
	
	def add2(x: Int)(y: Int):Int = x + y

	def courseAverage(tests: Int*)(assns: Int*)(quizzes: Int*): Double = {
		0.4 * tests.sum/tests.length + 0.4 * assns.sum/assns.length + 0.2 * quizzes.sum/quizzes.length
	} 

	def normalAdd(x: Int, y: Int) = x + y

	def main(args: Array[String]): Unit = {
		println("add: " + add(5)(6))
		val plus5 = add(5)
		println("plus5: " + plus5(6))
		println()
		println("add2: " + add2(7)(8))
		println()
		val plus2 = add2(5)_
		println("plus2: " + plus2(6))
		println()
		println("courseAverage: " + courseAverage(90,80)(100,96)(50,32))
		println()
		val res = add2{println("hi")
			5}
			{println("there")
			6}
		println("add2: " + res)
	}
}
