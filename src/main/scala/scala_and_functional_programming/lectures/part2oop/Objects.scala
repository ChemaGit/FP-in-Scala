package scala_and_functional_programming.lectures.part2oop

object Objects {
	// SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
	// Scala object = SINGLETON INSTANCE
	object Person {
		val N_EYES = 2
		def canFly: Boolean = false

		def apply(mother: Person, father: Person): Person = new Person("Bobbie")
	}

	class Person(val name: String) {
		// instance-level functionality
	}

	// COMPANIONS => same scope and the same name between object and class
	

	def main(args: Array[String]): Unit = {
		println(Person.N_EYES)
		println(Person.canFly)

		val mary = Person("Mary")
		val john = Person("John")
		println(mary == john)

		val person1 = Person
		val person2 = Person
		println(person1 == person2)

		val bobbie = Person.apply(mary, john)

	}

	// Scala Applications = Scala object with
	// def main(args: Array[String]): Unit
	
}
