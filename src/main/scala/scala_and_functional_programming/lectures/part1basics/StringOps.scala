package scala_and_functional_programming.lectures.part1basics

object StringOps {
	def main(args: Array[String]): Unit = {
		val str: String = "Hello, I am learning Scala"

		println(str.charAt(2))
		println(str.substring(7,11))
		println(str.split(" ").toList)
		println(str.startsWith("Hello"))
		println(str.replace(" ", "-"))
		println(str.toLowerCase())
		println(str.length)

		val aNumberString = "45"
		val aNumber = aNumberString.toInt
		println(aNumber)
		println('a' +: aNumberString :+ 'z')
		println('p' +: aNumberString :+ 'w')
		println(str.reverse)
		println(str.take(2))

		// Scala-specific: String interpolators

		// S-interpolators
		val name = "David"
		val age = 12
		val greeting = s"Hello, my name is $name and I am $age years old"
		val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old."
		println(anotherGreeting)

		// F-interpolators
		val speed = 1.2F
		val myth = f"$name can eat $speed%2.2f burgers per minute"
		println(myth)

		/*
			- String interpolators: F
				For formatted strings, similar to printf
				Can check for type correctness
			val speed = 1.2f
			val name = "Chema"
			println(f"$name%s can eat $speed%2.2f burgers per minute")

			val x = 1.1f => value is float
			val str = f"$x%3d" => format requires Int => Compile error
		*/

		// raw-interpolator
		println(raw"This a \n newline")
		val escaped = "This is a \n newline"
		println(raw"$escaped")
	}
}
