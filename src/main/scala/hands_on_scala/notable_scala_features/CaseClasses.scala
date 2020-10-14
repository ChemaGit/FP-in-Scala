package hands_on_scala.notable_scala_features

object CaseClasses {

  /**
    * Case Classes
    *
    * case classes are like normal classes, but meant to represent classes which are "just data": where all the
    * data is immutable and public, without any mutable state or encapsulation. Their name comes from the fact
    * that they support pattern matching via the case keyword.
    *
    * Case classes are defined with the case keyword, and can be instantiated without new.
    * All of their constructor parameters are public fields by default
    *
    * case class s give you a few things for free:
    * A.toString implemented to show you the constructor parameter values
    * A == implemented to check if the constructor parameter values are equal
    * A.copy method to conveniently create modified copies of the case class instance
    *
    */
  case class Point(x: Int, y: Int) {
    def z = x + y
  }

  def main(args: Array[String]): Unit = {
    val p = Point(1, 2)
    // p: Point = Point(1, 2)

    println(p.x)
    // Int = 1

    println(p.y)
    // Int = 2

    p.toString
    // String = "Point(1,2)"

    val p2 = Point(1, 2)
    p == p2
    // Boolean = true

    val p3 = p.copy(y = 10)
    // Point = Point(1, 10)

    val p4 = p3.copy(x = 20)
    // Point = Point(20, 10)

    p.z
    // Int = 3

  }
}
