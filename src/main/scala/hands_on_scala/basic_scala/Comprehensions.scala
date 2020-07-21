package hands_on_scala.basic_scala

object Comprehensions extends App{
  val a = Array(1, 2, 3, 4)
  println(a.mkString("(",",",")"))

  val a2 = for (i <- a) yield i * i
  println(a2.mkString("(",",",")"))

  val a3 = for (i <- a) yield "hello " + i
  println(a3.mkString("(",",",")"))

  val a4 = for (
    i <- a
    if i % 2 == 0
  ) yield "hello " + i
  println(a4.mkString("(",",",")"))

  val b = Array(1, 2);
  val b1 = Array("hello", "world")

  val flattened = for {
    i <- b
    s <- b1
  } yield s + i
  println(flattened.mkString("(",",",")"))

  val flattened2 = for {
    s <- b1
    i <- b
  } yield s + i
  println(flattened2.mkString("(",",",")"))

  val fizzbuzz = for {i <- Range.inclusive(1, 100)} yield {
    if (i % 3 == 0 && i % 5 == 0) "FizzBuzz"
    else if (i % 3 == 0) "Fizz"
    else if (i % 5 == 0) "Buzz"
    else i.toString
  }
  assert(fizzbuzz.take(5) == Vector("1", "2", "Fizz", "4", "Buzz"))
  println(fizzbuzz)
}
