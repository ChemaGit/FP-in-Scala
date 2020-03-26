package scala_advanced_and_functional_programming.lectures.part1as

object AdvancedPatternMatching extends App {

  val number = List(1)

  val description = number match {
    case head :: Nil => println(s"The only element is $head.")
    case _ =>
  }

  /*
    - constants
    - wildcards
    - tuples
    - case classes
    - some special magic like above
   */

  class Person(val name: String, val age: Int)

  object Person{
    def unapply(arg: Person): Option[(String, Int)] =
      if(arg.age < 21) None
      else Some((arg.name, arg.age))

    def unapply(age: Int): Option[String] =
      Some(if (age < 21) "minor" else "major")
  }

  val bob = new Person("Bob", 25)
  val greeting = bob match {
    case Person(n, a) => s"Hi, my names is $n and I am $a years old"
  }

  println(greeting)

  val legalStatus = bob.age match {
    case Person(status) => s"My legal status is $status"
  }

  println(legalStatus)

  /*
     Exercise.
   */

  object even {
    def unapply(arg: Int): Boolean = arg % 2 == 0
  }

  object singleDigit {
    def unapply(arg: Int): Boolean = arg > -10 && arg < 10
  }

  val n: Int = 22
  val mathProperty = n match {
    case singleDigit() => "single digit"
    case even() => "an even number"
    case _ => "no property"
  }

  println(mathProperty)

}
