package in2_scala

object TheOptionType {
	def main(args: Array[String]): Unit = {
		val lst = List(1,2,3,4,5)
		val bol = lst.find(v => v > 6)
		println(bol.toString)

		val r = lst.find(v => v > 3)
		println(r.get)
		println(bol.getOrElse(0))

		val r1 = lst.find(v => v > 6).map(v => v * 2)
		println(r1.getOrElse("Nothing"))
		val r2 = lst.find(v => v > 3).map(v => v * 2)
		println(r2.getOrElse("Nothing"))
		
		lst.find(v => v > 3) match {
			case Some(n) => println(n * 5)
			case None => println("Nothing")
		}

		
	}
}
