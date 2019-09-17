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

		/*
			1. Overload the + operator
	      		   mary + "the rockstar" => new person "Mary (the rockstar)"

			2. Add an age to the Person class
			   Add a unary + operator => new person with the age + 1
			   +mary => mary with the age incrementer

			3. Add a "learns" method in the Person class => "Mary learns Scala"
			   Add a learnsScala method, calls learns method with "Scala".
			   Use it in postfix notation.

			4. Overload the apply method
			   mary.apply(2) => "Mary watched Inception 2 times"
		*/
		println( (mary + "the Rockstar")() )
		println( (mary + "the Rockstar").apply() )

		println( (+mary).age)

		println(mary learnsScala)
		println(mary(10))

	}
	
	class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
		def likes(movie: String): Boolean = movie == favoriteMovie

		def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"

		def +(person: Person): String = s"${this.name} is going out for dinner with ${person.name}"

		def +(nickname: String): Person = new Person(s"${this.name} $nickname","Blade Runner", 50) 

		def unary_! : String = s"$name, what the heck?!"

		def unary_+ : Person = new Person(this.name, favoriteMovie, this.age + 1)

		def learns(thing: String) = s"${this.name} learns $thing."
		def learnsScala = this learns "Scala"

		def isAlive: Boolean = true

		def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

		def apply(times: Int): String = s"Mary watched $favoriteMovie $times times"

	}

}

/*
	Takeaways

	class Person(name: String) {
		def likes(movie: String): Boolean = {....}
		def unary_!(): String = {....}
		def isAlive(): Boolean = {.....}
		def apply(greeting: String): String = {.....}

		mary.likes("Inception")
		mary likes "Inception"

		mary.unary_!
		!mary //prefix notatiton

		mary.isAlive
		mary isAlive //postfix notation

		mary.apply("Hi there!")
		mary("Hi there!")
	}
*/
