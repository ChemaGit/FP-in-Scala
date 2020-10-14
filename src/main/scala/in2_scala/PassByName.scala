package in2_scala

object PassByName {

	def incr(i: Int): Int = i + 1

	def incr2(i: Int): Int = {
		println("Start incr")
		i + 1
	}

	def incrFunc(i: () => Int): Int = {
		println("Start incrf")
		i() + 1
	}

	def incrByName(i: => Int): Int = {
		println("Start incrByName")
		i + 1
	}

	def thriceMultiplied(i: => Int): Int = i * i *i

	def threeTuple(i: Int) = (i, i, i)

	def threeTupleByName(i: => Int) = (i, i, i)

	def main(args: Array[String]): Unit = {
		println("incr: " + incr(3+4))
		println()
		println("incr2: " + incr2{println("eval args"); 3+4})
		println()
		println("incrFunc: " + incrFunc(() => {println("eval"); 3+4}))
		println()
		println("incrByName: " + incrByName{println("eval"); 3+4})
		println()
		println("thriceMultiplied: " + thriceMultiplied(3))
		var a = 0
		println("thriceMultiplied: " + thriceMultiplied{a += 1;a})
		println("thriceMultiplied: " + thriceMultiplied{a += 1;a})

		println()
		println("threeTuple: " + threeTuple(3))
		println("threeTupleByName: " + threeTupleByName(3))
		println()
		println("threeTuple: " + threeTuple(util.Random.nextInt(10)))
		println("threeTuple: " + threeTuple(util.Random.nextInt(10)))
		println("threeTuple: " + threeTuple(util.Random.nextInt(10)))
		println()
		println("threeTupleByName: " + threeTupleByName(util.Random.nextInt(10)))
		println("threeTupleByName: " + threeTupleByName(util.Random.nextInt(10)))
		println("threeTupleByName: " + threeTupleByName(util.Random.nextInt(10)))
		println("threeTupleByName: " + threeTupleByName(util.Random.nextInt(10)))
	}
}
