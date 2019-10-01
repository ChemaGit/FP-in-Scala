package scala_and_functional_programming.exercises

abstract class MyList[+A] {
	/*
		head = first element of the list
		tail = remainder of the list
		isEmpty = is this list empty
		add(int) => new list with this element added
		toString => a string representation of the list
	*/
	def head: A
	def tail: MyList[A]
	def isEmpty: Boolean
	def add[B >: A](v: B): MyList[B]
	def printElements: String
	// polymorphic call
	override def toString: String = s"[ $printElements ]"
}

object Empty extends MyList[Nothing] {
	def head: Nothing = throw new NoSuchElementException
	def tail: MyList[Nothing] = throw new NoSuchElementException
	def isEmpty: Boolean = true
	def add[B >: Nothing](v: B): MyList[B] = new Cons(v, Empty)
	def printElements: String = "[]"	
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
	def head: A = h
	def tail: MyList[A] = t
	def isEmpty: Boolean = false
	def add[B >: A](v: B): MyList[B] = new Cons(v, this)
	def printElements: String = 
		if(t.isEmpty) s"$h"
		else s"$h ${t.printElements}"
}


object ListTest extends App {
	val list = new Cons(1, Empty)
	println(list.head)

	val list2 = new Cons(1, new Cons(2, new Cons(3, Empty)))
	println(list2.tail.head)
	println(list2.add(4).head)

	println(list2.isEmpty)

	println(list2.toString)

	val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
	val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

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
