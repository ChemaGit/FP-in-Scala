package collections

object ReduceAndFold {
	def main(args: Array[String]): Unit = {
		println("****reduce******")
		val a = List(5,2,9,3,1,8,6)
		val r = a.reduceLeft( (v, v1) => v + v1)
		println(r)
		println()
		val r1 = a.reduceLeft( (a,b) => {println(a + ", " + b);a + b})
		println(r1)
		println
		val r2 = a.reduceRight( (a, b) => {println(a + ", " + b);a + b})
		println(r2)
		println()
		val r3 = a.reduceLeft( (a, b) => {println(a + ", " + b);a - b})
		println(r3)
		println()
		val r4 = a.reduceRight( (a, b) => {println(a + ", " + b);a - b})
		println(r4)
		println()
		println("*****fold*****")
		val res5 = a.foldLeft(0)( (a,b) => a + b)
		println(res5)
		println()
		val res6 = a.foldLeft("")( (a,b) => a + b)
		println(res6)
		println()
		val res7 = a.foldLeft("")( (a,b) => a + b + ",")
		println(res7)
		println()
		
	}
}
