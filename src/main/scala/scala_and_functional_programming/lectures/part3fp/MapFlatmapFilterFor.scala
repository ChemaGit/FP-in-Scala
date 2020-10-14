package scala_and_functional_programming.lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1,2,3)
  println(list)

  println(list.head)
  println(list.tail)

  // map
  println(list.map(v => v + 1))
  println(list.map(v => s"$v is a number"))

  // filter
  println(list.filter(v => v % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(v => toPair(v)))

  // print all combinations between two lists
  val number = List(1,2,3,4)
  val chars = List('a','b','c','d')
  val colors = List("black", "white")
  // List("a1","a2"....."d4"

  // "iterating"
  val combinations = chars
    .flatMap(c => number.flatMap(n => colors.map(color => s"$c$n-$color")))
  println(combinations)

  // foreach
  list.foreach(println)

  // for-conprehensions
  val forCombinations = for {
    n <- number if n % 2 == 0
    c <- chars
    color <- colors
  } yield (s"$c$n-$color")

  println(forCombinations)

  for {
    n <- number
  }println(n)

  // syntax overload
  list.map { x =>
    x * 2
  }

  /*
  1. MyList supports for comprehensions?
  map(f: A => B) => MyList[B]
  filter(p: A => Boolean): MyList[A]
  flatMap(f: A => MyList[B]) => MyList[B]
  2. A small collection of at most ONE element - Maybe[+T]
    - map, flatMap, filter
   */



}
