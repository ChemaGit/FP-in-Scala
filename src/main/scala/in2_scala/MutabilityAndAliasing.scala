package in2_scala

object MutabilityAndAliasing {
	def main(args: Array[String]): Unit = {
		val l = Array(0,1,2,3,4,5,6)
		println(l(1))
		l(1) = 100
		println(l(1))
		val b = l
		b(1) = 200
		println(l(1))
		
	}
}
