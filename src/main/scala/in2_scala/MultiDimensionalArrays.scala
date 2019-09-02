package in2_scala

object MultiDimensionalArrays {
	def main(args: Array[String]): Unit = {
		val twod = Array(Array(1,2,3),Array(4,5,6))
		println(twod(0).mkString(","))
		println(twod(0)(1))
		println()
		val muld = Array.fill(3,5)(0)
		muld.foreach(x => println(x.mkString(",")))
		println()
		val muld2 = Array.tabulate(10,10)((i, j) => i * j)
		muld2.foreach(x => println(x.mkString(",")))
		println()
		println(muld2(7)(6))

		// with List
		val muld3 = List.tabulate(10,10)( (i, j) => i * j)
		muld3.foreach(println)
	}
}
