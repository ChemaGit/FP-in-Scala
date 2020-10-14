package hands_on_scala.notable_scala_features

/**
  * A second way that implicit parameters are useful is by using them to associate values to types.
  * This is often called a typeclass
  */
object TypeClassInference extends App {

  /**
    * Problem Statement: Parsing Command Line Arguments
    *
    * Let us consider the task of parsing command-line arguments, given as String s, into Scala values of various
    * types: Int s, Boolean s, Double s, etc.
    */

/*
A first sketch may be writing a generic method to parse the values.

def parseFromString[T](s: String): T = ...

val args = Seq("123", "true", "7.5")
val myInt = parseFromString[Int](args(0))
val myBoolean = parseFromString[Boolean](args(1))
val myDouble = parseFromString[Double](args(2))

- How does the parseCliArgument know how to convert the given String into an arbitrary T ?
- How does it know what types T a command-line argument can be parsed into, and which it cannot?
For example, we should not be able to parse a java . net . DatagramSocket from an input string.
 */

  /**
    * Separate Parser Objects
    *
    * A second sketch at a solution may be to define separate parser objects, one for each type we need to be
    * able to parse. For example:
    *
    * @tparam T
    */

  trait StrParserB[T]{ def parse(s: String): T }
  object ParseInt extends StrParserB[Int]{ def parse(s: String) = s.toInt }
  object ParseBoolean extends StrParserB[Boolean]{ def parse(s: String) = s.toBoolean }
  object ParseDouble extends StrParserB[Double]{ def parse(s: String) = s.toDouble }

  val argss = Seq("123", "true", "7.5")
  val myInt = ParseInt.parse(argss(0))
  val myBoolean = ParseBoolean.parse(argss(1))
  val myDouble = ParseDouble.parse(argss(2))
/*
This works. However, it then leads to another problem: if we wanted to write a method that didn't parse a
String directly, but parsed a value from the console, how would we do that? We have two options.

Re-Using Our StrParsers
The first option is writing a whole new set of object s dedicated to parsing from the console:

trait ConsoleParser[T]{ def parse(): T }
  object ConsoleParseInt extends ConsoleParser[Int]{
    def parse() = scala.Console.in.readLine().toInt
  }
  object ConsoleParseBoolean extends ConsoleParser[Boolean]{
    def parse() = scala.Console.in.readLine().toBoolean
  }
  object ConsoleParseDouble extends ConsoleParser[Double]{
    def parse() = scala.Console.in.readLine().toDouble
  }
  val myInt = ConsoleParseInt.parse()
  val myBoolean = ConsoleParseBoolean.parse()
  val myDouble = ConsoleParseDouble.parse()

  The second option is defining a helper method that receives a StrParser [T] as an argument, which we
  would need to pass in to tell it how to parse the type T

  def parseFromConsole[T](parser: StrParserB[T]) = parser.parse(scala.Console.in.readLine())
  val myInt = parseFromConsole[Int](ParseInt)
  val myBoolean = parseFromConsole[Boolean](ParseBoolean)
  val myDouble = parseFromConsole[Double](ParseDouble)

these solutions are clunky:
1. The first because we need to duplicate all the Int / Boolean / Double /etc. parsers. What if we need to
parse input from the network? From files? We would need to duplicate every parser for each case.
2. The second because we need to pass these ParseFoo objects everywhere. Often there is only a
single StrParser [Int] we can pass to parseFromConsole [Int] . Why can't the compiler infer it for
us?
 */


  /**
    * Solution: Implicit StrParser
    * The solution to the problems above is to make the instances of StrParser
    * implicit :
    *
    * @tparam T
    */
  trait StrParser[T]{ def parse(s: String): T }
  object StrParser{
    implicit object ParseInt extends StrParser[Int]{
      def parse(s: String) = s.toInt
    }
    implicit object ParseBoolean extends StrParser[Boolean]{
      def parse(s: String) = s.toBoolean
    }
    implicit object ParseDouble extends StrParser[Double]{
      def parse(s: String) = s.toDouble
    }

    implicit def ParseSeq[T](implicit p: StrParser[T]) = new StrParser[Seq[T]]{
      def parse(s: String) = s.split(',').toSeq.map(p.parse)
    }

    implicit def ParseTuple[T, V](implicit p1: StrParser[T], p2: StrParser[V]) =
      new StrParser[(T, V)]{
        def parse(s: String) = {
          val Array(left, right) = s.split('=')
          (p1.parse(left), p2.parse(right))
        }
      }
  }

  def parseFromString[T](s: String)(implicit parser: StrParser[T]) = {
    parser.parse(s)
  }

  def parseFromConsole[T](implicit parser: StrParser[T]) = {
    parser.parse(scala.Console.in.readLine())
  }

  val argsb = Seq("123", "true", "7.5")
  val myIntb = parseFromString[Int](argsb(0))
  val myBooleanb = parseFromString[Boolean](argsb(1))
  val myDoubleb = parseFromString[Double](argsb(2))

  assert(myIntb == 123)
  assert(myBooleanb == true)
  assert(myDoubleb == 7.5)

  assert(parseFromString[Seq[Boolean]]("true,false,true") == Seq(true, false, true))
  assert(parseFromString[Seq[Int]]("1,2,3,4") == Seq(1, 2, 3, 4))

  assert(parseFromString[(Int, Boolean)]("123=true") == (123, true))
  assert(parseFromString[(Boolean, Double)]("true=1.5") == (true, 1.5))

  assert(
    parseFromString[Seq[(Int, Boolean)]]("1=true,2=false,3=true,4=false") ==
      Seq((1, true), (2, false), (3, true), (4, false))
  )

  assert(
    parseFromString[(Seq[Int], Seq[Boolean])]("1,2,3,4,5=true,false,true") ==
      (Seq(1, 2, 3, 4, 5), Seq(true, false, true))
  )

}
