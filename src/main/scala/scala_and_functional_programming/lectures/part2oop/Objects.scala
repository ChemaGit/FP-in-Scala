package scala_and_functional_programming.lectures.part2oop

/*
Scala doesn't have "static" values/methods

Scala objects
	- are in their own class
	- are the only instance
	- singleton pattern in one line!
object Person {
	val N_EYES = 2
	def canFly: Boolean = false
}

Scala companions
	- can access each other's private members
	- Scala is more OO than Java!
class Person
object Person

Scala applications
	def main(args: Array[String]): Unit
	object MyApp extends App
*/

object Objects {
	// SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
	// Scala object = SINGLETON INSTANCE
	object Person {
		val N_EYES = 2
		def canFly: Boolean = false

		def canSwim(value: Boolean): Boolean = value

		def apply(mother: Person, father: Person): Person = new Person("Bobbie")
	}

	class Person(val name: String) {
		// instance-level functionality
	}

	// COMPANIONS => same scope and the same name between object and class
	

	def main(args: Array[String]): Unit = {
		println(Person.N_EYES)
		println(Person.canFly)
		println(Person.canSwim(true))

		val mary = new Person("Mary")
		val john = new Person("John")
		println(mary == john)

		val person1 = Person
		val person2 = Person
		println(person1 == person2)

		val bobbie = Person.apply(mary, john)
		val bobbie2 = Person(mary, john)

	}

	// Scala Applications = Scala object with
	// def main(args: Array[String]): Unit
	
}
