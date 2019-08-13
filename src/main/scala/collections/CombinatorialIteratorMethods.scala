package collections

object CombinatorialIteratorMethods {
	def main(args: Array[String]) {
		val nums = Array(1,2,3,4,5)
		val r = nums.combinations(3)
		r.foreach(x => println(x.mkString(",")))
		println()
		println(r.hasNext)
		if(r.hasNext) println(r.next.mkString(","))
		println()
		val b = Array.tabulate(15)(i => i)
		println(b.mkString(","))
		val r1 = b.grouped(3)
		r1.foreach(x => println(x.mkString(",")))
		println()
		val r2 = b.grouped(4)
		r2.foreach(x => println(x.mkString(",")))
		println()
		val r3 = nums.inits
		r3.foreach(x => println(x.mkString(",")))
		println()
		println(nums.init.mkString(","))
		println()
		val r4 = nums.permutations
		r4.foreach(x => println(x.mkString(",")))
		println()
		val r5 = nums.sliding(3)
		r5.foreach(x => println(x.mkString(",")))
		println()
		val r6 = nums.tails
		r6.foreach(x => println(x.mkString(",")))
	}
}
