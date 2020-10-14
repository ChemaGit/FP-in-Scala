package hands_on_scala.basic_scala

object ClassesAndTraits {

  /**
    * Classes
    */
  class Foo(x: Int) {
    def printMsg(msg: String) = {
      println(msg + x)
    }
  }

  class Bar(val x: Int) {
    def printMsg(msg: String) = {
      println(msg + x)
    }
  }

  class Qux(var x: Int) {
    def printMsg(msg: String): Unit = {
      x += 1
      println(msg + x)
    }
  }

  class Baz(x: Int) {
    val bangs = "!" * x
    def printMsg(msg: String): Unit = {
      println(msg + bangs)
    }
  }

  /**
    * Traits
    * trait s are similar to interface s in traditional object-oriented languages:
    * a set of methods that multiple classes can inherit.
    * Instances of these classes can then be used interchangeably.
    */
  trait Point{
    def hypotenuse: Double
  }

  class Point2D(x: Double, y: Double) extends Point{
    override def hypotenuse = math.sqrt(x * x + y * y)
  }

  class Point3D(x: Double, y: Double, z: Double) extends Point{
    override def hypotenuse = math.sqrt(x * x + y * y + z * z)
  }

  def main(args: Array[String]): Unit = {
    /**
      * Classes
      */
    val f = new Foo(1)
    f.printMsg("Hello")

    val b = new Bar(1)
    println(b.x)

    val q = new Qux(1)
    q.printMsg("Hello")
    q.printMsg("Hello")

    val z = new Baz(3)
    z.printMsg("Hello")

    /**
      * Traits
      */

    val points: Array[Point] = Array(new Point2D(1, 2), new Point3D(4, 5, 6))

    for (p <- points) println(p.hypotenuse)

    val results = for (p <- points) yield p.hypotenuse

    assert(results.toSeq == Seq(2.23606797749979, 8.774964387392123))
  }
}
