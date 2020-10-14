package scala_and_functional_programming.lectures.part2oop
// traits vs abstract classes
// 1 - traits do not have constructor parameters
// 2 - multiple traits may be inherited by the same class
// 3 - traits = behavior, abstract class = "thing"

// SCALA'S TYPE HIERARCHY
//	Scala.Any ==> scala.AnyRef(java.lang.Object) ==> String, List, Set ..... ==> scala.Null ==> scala.Nothing
// 	Scala.AnyVal ==> Int, Unit, Boolean, Float(classes that extends *AnyVal) ==> scala.Nothing
object AbstractDataTypes extends App {

	// abstract
	abstract class Animal {
		val creatureType: String = "Wild"
		def eat: Unit
	}

	class Dog extends Animal {
		override val creatureType: String = "Canine"
		override def eat: Unit = println("crunch crunch")
	}

	// traits
	trait Carnivore {
		def eat(animal: Animal): Unit
		val preferredMeal: String = "fresh meat"
	}

	trait ColdBlooded

	class Crocodile extends Animal with Carnivore with ColdBlooded {
		override val creatureType: String = "croc"
		def eat: Unit = println("nomnomnom")
		def eat(animal: Animal): Unit = s"I'm a croc and I'm eating ${animal.creatureType}"
	}

	val dog = new Dog
	val croc = new Crocodile
	croc.eat(dog)
}
