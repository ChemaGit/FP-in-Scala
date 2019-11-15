package scala_and_functional_programming.exercises

/*
	head = first element of the list
	tail = remainder of the list
	isEmpty = is this list empty?
	add(int) => new List with this element added
	toString => a string representation of the list
*/
abstract class MyList {
	def head: Int
	def tail: MyList
	def isEmpty: Boolean
	def add(element: Int): MyList
	def printElements: String
	override def toString: String = s"[${printElements}]"
}

object Empty extends MyList {
	def head: Int = throw new NoSuchElementException
	def tail: MyList = throw new NoSuchElementException
	def isEmpty: Boolean = true
	def add(element: Int): MyList = new Cons(element, Empty)
	def printElements: String = "[]"
}

class Cons(h: Int, t: MyList) extends MyList {
	def head: Int = h
	def tail: MyList = t
	def isEmpty: Boolean = false
	def add(element: Int): MyList = new Cons(element, this)
	def printElements: String =
		if(t.isEmpty) s"$h"
		else s"$h ${t.printElements}"
}

object ListText extends App {
	val list = new Cons(1, Empty)
	println(list.head)
	val list2 = new Cons(1, new Cons(2, new Cons(3, Empty)))
	println(list2.tail.head)
	println(list2.add(4).head)
	println(list2.isEmpty)

}


object ListTest extends App {
	val list = new Cons(1, Empty)
	println(list.head)

	val list2 = new Cons(1, new Cons(2, new Cons(3, Empty)))
	println(list2.tail.head)
	println(list2.add(4).head)
	println(list2.isEmpty)

	println(list2.toString)

	val listOfIntegers: MyList = new Cons(1, new Cons(2, new Cons(3, Empty)))
	val listOfStrings: MyList = new Cons(4, new Cons(5, Empty))

	println(listOfIntegers.toString)
	println(listOfStrings.toString)
}

/*
TAKEAWAYS
Scala offers class-based inheritance
	- access modifiers: private, protected, default(none = public)
	- need to pass in constructor arguments to parent class

class Animal
class Cat extends Animal

Derived classes can override members or methods

Reuse parent fields/methods with super

Prevent inheritance with final and sealed

abstract classes

traits

Inheriting from a class and multiple traits
*/
