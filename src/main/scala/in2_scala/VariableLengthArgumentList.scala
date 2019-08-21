package in2_scala

object VariableLengthArgumentList{
	def main(args: Array[String]): Unit = {
		val l: List[Double] = List(1,2,3,4,5)
		println(average2(l:_*))
		println(average2(1))
		println(average2(1,2))
		println(average2(1,2,3))
		println(average2(1,2,3,4))
	}

	def average(n1: Double, n2: Double, n3: Double): Double = {
		(n1 + n2 + n2) / 3.0
	}

	def average2(n: Double*): Double = {
		n.length match {
			case 0 => 0.0
			case _ => n.sum / n.length
		}
	}

}
