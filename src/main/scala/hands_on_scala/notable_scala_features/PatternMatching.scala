package hands_on_scala.notable_scala_features

/**
  * Scala allows pattern matching on values using the match keyword.
  * Apart from matching on primitive integers and
  * strings, you can also use match to extract values from ("destructure")
  * composite data types like tuples and case classes.
  */
object PatternMatching extends App {

  /**
    * Matching on String Patterns
    */
  def getDayMonthYear(s: String) = s match {
    case s"$day-$month-$year" => println(s"found day: $day, month: $month, year: $year")
    case _ => println("not a date")
  }
  getDayMonthYear("9-8-1965")
  // found day: 9, month: 8, year: 1965

  getDayMonthYear("9-8")
  // not a date

  def splitDate(s: String) = s match {
    case s"$day-$month-$year" => s"day: $day, mon: $month, yr: $year"
    case _ => "not a date"
  }

  println(splitDate("9-8-1965"))

  println(splitDate("9-8"))

  assert(splitDate("9-8-1965") == "day: 9, mon: 8, yr: 1965")

  assert(splitDate("9-8") == "not a date")

  /**
    * Matching on Ints
    */
  def dayOfWeek(x: Int) = x match {
    case 1 => "Mon"
    case 2 => "Tue"
    case 3 => "Wed"
    case 4 => "Thu"
    case 5 => "Fri"
    case 6 => "Sat"
    case 7 => "Sun"
    case _ => "Unknown"
  }
  dayOfWeek(5)
  // String = "Fri"

  dayOfWeek(-1)
  // String = "Unknown"

  assert(dayOfWeek(5) == "Fri")

  assert(dayOfWeek(-1) == "Unknown")

  /**
    * Matching on Strings
    */
  def indexOfDay(d: String) = d match {
    case "Mon" => 1
    case "Tue" => 2
    case "Wed" => 3
    case "Thu" => 4
    case "Fri" => 5
    case "Sat" => 6
    case "Sun" => 7
    case _ => -1
  }
  indexOfDay("Fri")
  // Int = 5

  indexOfDay("???")
  // Int = -1

  assert(indexOfDay("Fri") == 5)

  assert(indexOfDay("???") == -1)

  /**
    * Loops and Vals
    *
    * Pattern matching in for -loops is useful
    * when you need to iterate over collections of tuples
    *
    * Pattern matching in val statements is useful when you are sure the value will match the given pattern, and
    * all you want to do is extract the parts you want. If the value doesn't match, this fails with an exception
    */
  val fizzBuzz1 = for (i <- Range.inclusive(1, 100)) yield {
    val s =  (i % 3, i % 5) match {
      case (0, 0) => "FizzBuzz"
      case (0, _) => "Fizz"
      case (_, 0) => "Buzz"
      case _ => i
    }
    s
  }

  assert(fizzBuzz1.take(5) == Seq(1, 2, "Fizz", 4, "Buzz"))

  val fizzBuzz2 = for (i <- Range.inclusive(1, 100)) yield {
    val s = (i % 3 == 0, i % 5 == 0) match {
      case (true, true) => "FizzBuzz"
      case (true, false) => "Fizz"
      case (false, true) => "Buzz"
      case (false, false) => i
    }
    s
  }

  assert(fizzBuzz2.take(5) == Seq(1, 2, "Fizz", 4, "Buzz"))

  /**
    * Matching on Case Classes
    */
  case class Point(x: Int, y: Int)

  def direction(p: Point) = p match {
    case Point(0, 0) => "origin"
    case Point(_, 0) => "horizontal"
    case Point(0, _) => "vertical"
    case _ => "diagonal"
  }
  direction(Point(0, 0))
  // String = "origin"

  direction(Point(1, 1))
  // String = "diagonal"

  direction(Point(10, 0))
  // String = "horizontal"

  assert(direction(Point(0, 0)) == "origin")

  assert(direction(Point(1, 1)) == "diagonal")

  assert(direction(Point(10, 0)) == "horizontal")

  case class Person(name: String, title: String)

  def greet(p: Person) = p match {
    case Person(s"$firstName $lastName", title) => println(s"Hello $title $lastName")
    case Person(name, title) => println(s"Hello $title $name")
  }

  greet(Person("Haoyi Li", "Mr"))
  // Hello Mr Li

  greet(Person("Who?", "Dr"))
  // Hello Dr Who?

  assert(greet(Person("Haoyi Li", "Mr")) == "Hello Mr Li")
  assert(greet(Person("Who?", "Dr")) == "Hello Dr Who?")

  def greet2(husband: Person, wife: Person) = (husband, wife) match {
    case (Person(s"$first1 $last1", _), Person(s"$first2 $last2", _)) if last1 == last2 =>
      println(s"Hello Mr and Ms $last1")
    case (Person(name1, _), Person(name2, _)) => println(s"Hello $name1 and $name2")
  }

  greet2(Person("James Bond", "Mr"), Person("Jane Bond", "Ms"))
  // Hello Mr and Ms Bond

  greet2(Person("James Bond", "Mr"), Person("Jane", "Ms"))
  // Hello James Bond and Jane

  assert(greet2(Person("James Bond", "Mr"), Person("Jane Bond", "Ms")) == "Hello Mr and Ms Bond")
  assert(greet2(Person("James Bond", "Mr"), Person("Jane", "Ms")) == "Hello James Bond and Jane")

  /**
    * Matching on tuples
    */

    val a = Array[(Int, String)]((1, "one"), (2, "two"), (3, "three"))
    for ((i, s) <- a) println(s + i)
    // one1
    // two2
    // three3

  assert(a.toSeq == Seq("one1", "two2", "three3"))

    val p = Point(123, 456)

    val Point(x, y) = p
    // x: Int = 123
    // y: Int = 456
  assert(x == 123)
  assert(y == 456)

    val s"$first $second" = "Hello World"
    // first: String = "Hello"
    // second: String = "World"

  assert(first == "Hello")
  assert(second == "World")

    val flipped = s"$second $first"
    // flipped: String = "World Hello"

  assert(flipped == "World Hello")

    val s"$first1 $second1" = "Hello"
    // scala.MatchError: Hello

    /**
      * Matching on tuple (Int, Int)
      */
    for (i <- Range.inclusive(1, 100)) {
      val s =  (i % 3, i % 5) match {
        case (0, 0) => "FizzBuzz"
        case (0, _) => "Fizz"
        case (_, 0) => "Buzz"
        case _ => i
      }
      println(s)
    }

    /**
      * Matching on tuple Boolean, Boolean)
      */
    for (i <- Range.inclusive(1, 100)) {
      val s = (i % 3 == 0, i % 5 == 0) match {
        case (true, true) => "FizzBuzz"
        case (true, false) => "Fizz"
        case (false, true) => "Buzz"
        case (false, false) => i
      }
      println(s)
    }

  /**
    * Nested Matches
    * Patterns can also be nested
    * Patterns can be nested arbitrarily deeply.
    */
  /**
    * Pattern Matching on Sealed Traits and Case Classes
    *
    * Pattern matching lets you elegantly work with structured data comprising case classes and sealed traits.
    * For example, let's consider a simple sealed trait that represents arithmetic expressions
    *
    * If an Expr is a Literal , the string is the value of the literal
    * If an Expr is a Variable , the string is the name of the variable
    * If an Expr is a BinOp , the string is the stringified left expression, followed by the operation, followed
    * by the stringified right expression
    */

  sealed trait Expr
  case class BinOp(left: Expr, op: String, right: Expr) extends Expr
  case class Literal(value: Int) extends Expr
  case class Variable(name: String) extends Expr

  def stringify(expr: Expr): String = expr match {
    case BinOp(left, op, right) => s"(${stringify(left)} $op ${stringify(right)})"
    case Literal(value) => value.toString
    case Variable(name) => name
  }

  BinOp(Variable("x"), "+", Literal(1)) // x + 1

  BinOp(
    Variable("x"),
    "*",
    BinOp(Variable("y"), "-", Literal(1)) // x * (y - 1)
  )

  BinOp(
    BinOp(Variable("x"), "+", Literal(1)),
    "*",
    BinOp(Variable("y"), "-", Literal(1)) // (x + 1) * (y - 1)
  )

  val smallExpr = BinOp(
    Variable("x"),
    "+",
    Literal(1)
  )

  stringify(smallExpr)
  // String = "(x + 1)"

  val largeExpr = BinOp(
    BinOp(Variable("x"), "+", Literal(1)),
    "*",
    BinOp(Variable("y"), "-", Literal(1))
  )

  stringify(largeExpr)
  // String = "((x + 1) * (y - 1))"

  assert(stringify(smallExpr) == "(x + 1)")

  def evaluate(expr: Expr, values: Map[String, Int]): Int = expr match {
    case BinOp(left, "+", right) => evaluate(left, values) + evaluate(right, values)
    case BinOp(left, "-", right) => evaluate(left, values) - evaluate(right, values)
    case BinOp(left, "*", right) => evaluate(left, values) * evaluate(right, values)
    case Literal(value) => value
    case Variable(name) => values(name)
  }

  evaluate(smallExpr, Map("x" -> 10))
  // Int = 11
  evaluate(largeExpr, Map("x" -> 10, "y" -> 20))
  // Int = 209

  assert(evaluate(largeExpr, Map("x" -> 10, "y" -> 20)) == 209)
  assert(evaluate(smallExpr, Map("x" -> 10)) == 11)
}
