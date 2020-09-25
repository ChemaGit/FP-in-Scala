package hands_on_scala.notable_scala_features

object AlgebraicSimplifications {

  sealed trait Expr
  case class BinOp(left: Expr, op: String, right: Expr) extends Expr
  case class Literal(value: Int) extends Expr
  case class Variable(name: String) extends Expr

  def stringify(expr: Expr): String = expr match {
    case BinOp(left, op, right) => s"(${stringify(left)} $op ${stringify(right)})"
    case Literal(value) => value.toString
    case Variable(name) => name
  }

  def evaluate(expr: Expr, values: Map[String, Int]): Int = expr match {
    case BinOp(left, "+", right) => evaluate(left, values) + evaluate(right, values)
    case BinOp(left, "-", right) => evaluate(left, values) - evaluate(right, values)
    case BinOp(left, "*", right) => evaluate(left, values) * evaluate(right, values)
    case Literal(value) => value
    case Variable(name) => values(name)
  }

  /**
    * Define a function that uses pattern matching on the Expr s to perform
    * simple algebraic simplifications:
    *
    * (1 + 1) => 2
    * ((1 + 1) * x) => (2 * x)
    * ((2 - 1) * x) => x
    * (((1 + 1) * y) + ((1 - 1) * x)) => (2 * y)
    *
    * @param expr
    */

  def simplify(expr: Expr): Expr = {
    val res = expr match{
      case BinOp(Literal(left), "+", Literal(right)) => Literal(left + right)
      case BinOp(Literal(left), "-", Literal(right)) => Literal(left - right)
      case BinOp(Literal(left), "*", Literal(right)) => Literal(left * right)

      case BinOp(left, "*", Literal(1)) => simplify(left)
      case BinOp(Literal(1), "*", right) => simplify(right)

      case BinOp(left, "+", Literal(0)) => simplify(left)
      case BinOp(Literal(0), "+", right) => simplify(right)

      case BinOp(left, "-", Literal(0)) => simplify(left)

      case BinOp(left, "*", Literal(0)) => Literal(0)
      case BinOp(Literal(0), "*", right) => Literal(0)

      case BinOp(left, "+", right) => BinOp(simplify(left), "+", simplify(right))
      case BinOp(left, "-", right) => BinOp(simplify(left), "-", simplify(right))
      case BinOp(left, "*", right) => BinOp(simplify(left), "*", simplify(right))

      case Literal(value) => Literal(value)
      case Variable(name) => Variable(name)
    }

    // We may need to re-simplify an expression multiple times in order to achieve
    // all the simplifications we want; only stop re-simplifying it if it stops changing
    if (res == expr) res
    else simplify(res)
  }

  def main(args: Array[String]): Unit = {
    val largeExpr = BinOp(
      BinOp(Variable("x"), "+", Literal(1)),
      "*",
      BinOp(Variable("y"), "-", Literal(1))
    )
    println(stringify(largeExpr))

    println(evaluate(largeExpr, Map("x" -> 10, "y" -> 20)))

    println(simplify(largeExpr))

    val smallExpr = BinOp(
      Literal(1),
      "+",
      Literal(1)
    )
    println(simplify(smallExpr))

    val expr = BinOp(BinOp(Literal(1),"+", Literal(1)), "*", Variable("x"))
    println(simplify(expr))
    val expr1 = BinOp(BinOp(Literal(2),"-", Literal(1)), "*", Variable("x"))
    println(simplify(expr1))


    val example1 = BinOp(Literal(1), "+", Literal(1))

    val str1 = stringify(example1)
    val simple1 = stringify(simplify(example1))
    assert(str1 == "(1 + 1)")
    assert(simple1 == "2")
    println(str1)
    println(simple1)

    val example2 = BinOp(BinOp(Literal(1), "+", Literal(1)), "*", Variable("x"))

    val str2 = stringify(example2)
    val simple2 = stringify(simplify(example2))
    assert(str2 == "((1 + 1) * x)")
    assert(simple2 == "(2 * x)")
    println(str2)
    println(simple2)

    val example3 = BinOp(
      BinOp(Literal(2), "-", Literal(1)),
      "*",
      Variable("x")
    )

    val str3 = stringify(example3)
    val simple3 = stringify(simplify(example3))
    assert(str3 == "((2 - 1) * x)")
    assert(simple3 == "x")
    println(str3)
    println(simple3)

    val example4 = BinOp(
      BinOp(BinOp(Literal(1), "+", (Literal(1))), "*", Variable("y")),
      "+",
      BinOp(BinOp(Literal(1), "-", (Literal(1))), "*", Variable("x"))
    )

    val str4 = stringify(example4)
    val simple4 = stringify(simplify(example4))
    assert(str4 == "(((1 + 1) * y) + ((1 - 1) * x))")
    assert(simple4 == "(2 * y)")
    println(str4)
    println(simple4)
  }
}
