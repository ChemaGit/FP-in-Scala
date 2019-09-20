package scala_and_functional_programming.lectures.part2oop

object InheritanceAndTraits {
	def main(args: Array[String]): Unit = {
		val cat = new Cat
		cat.eat
		cat.goBed

		val dog = new Dog("Canido")
		dog.eat		
		println(dog.creatureType)
		println(dog.familyType)

		val duck = new Duck("Palmipedo")
		println(duck.familyType)

		// type substitution (broad: polymorphism)
		val unknownAnimal: Animal = new Dog("Pit Bull")
		unknownAnimal.eat
	}
	
	// single class inheritance
	class Animal {
		val familyType = "nature"
		val creatureType = "wild"
		def eat = println("some food")
		protected def sleep = println("zzzzzz....")
		private def drik = println("glugluglu")
	}

	class Cat extends Animal {
		def goBed = sleep
	}

	class Person(name: String, age: Int) {
		def this(name: String) = this(name, 0)
	}

	class Adult(name: String, age: Int, idCard: String) extends Person(name)

	// overriding
	class Dog(override val familyType: String) extends Animal {
		override val creatureType = "domestic"
		override def eat = {
			super.eat
			println("Some more food")
		}
	}

	class Duck(typeF: String) extends Animal {
		override val familyType = typeF
	}

}

// preventing overrides
//	1. use final on members
//	2. use final on the entire class
//	3. seal the class "sealed class Animal" = extend classes in THIS FILE, prevent extension in other files
