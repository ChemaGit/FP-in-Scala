package collections

object StringsAsCollections {
	def main(args: Array[String]): Unit = {
		val str = "This is a test"
		println(str(3))
		println()
		str.foreach(println)
		println()
		val r = str.map(c => c + 1)
		println(r)
		println()
		val r1 = str.map(c => (c + 1).toChar)
		println(r1)
		println()
		val r2 = str.count(c => "aeiou".contains(c))
		println(s"count: $r2")
		println()
		val r3 = str.split(" ")
		println(r3.mkString(","))
		println()
		val str2 = "This  is  a  test."
		val r4 = str2.split(" ")
		println(r4.mkString(","))
		println()
		val r5 = str2.split(" +")
		println(r5.mkString(","))
		println()
		val r6 = "1 2 3 4 5".split(" ").map(n => n.toInt)
		println(r6.mkString(","))
	
	}
}
