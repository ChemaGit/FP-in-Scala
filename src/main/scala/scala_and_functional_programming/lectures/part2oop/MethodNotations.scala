package scala_and_functional_programming.lectures.part2oop

import  scala.language.postfixOps

object MethodNotations {

	def main(args: Array[String]): Unit = {
		val mary = new Person("Marie", "Inception")
		println(mary.likes("Inception"))
		println(mary likes "Inception") // equivalent
		// infix notation = operator notation (syntactic sugar)

		// "operators" in Scala
		val tom = new Person("Tom", "Fight Club")
		println(mary hangOutWith tom)

		println(mary + tom)
		println(mary.+(tom))

		println(1 + 2)
		println(1.+(2))

		// ALL OPERATORS ARE METHODS

		// prefix notation -> unary operators
		val x = -1  // equivalent with 1.unary_-
		val y = 1.unary_-
		// unary_ prefix only works with - + ~ !
		println(!mary)
		println(mary.unary_!)

		// postfix notation
		println(mary.isAlive)
		println(mary isAlive)

		// apply
		println(mary.apply())
		println(mary()) //equivalent
	}
	
	class Person(val name: String, favoriteMovie: String) {
		def likes(movie: String): Boolean = movie == favoriteMovie

		def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"

		def +(person: Person): String = s"${this.name} is going out for dinner with ${person.name}"

		def unary_! : String = s"$name, what the heck?!"

		def isAlive: Boolean = true

		def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

	}

}
