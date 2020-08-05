package hands_on_scala.notable_scala_features

/**
  * Traits can also be defined sealed, and only extended by a fixed set of case classes.
  * The core difference between normal traits and sealed traits can be summarized as follows:
  *
  * - Normal trait s are open, so any number of classes can inherit from the trait as long as they provide
  * all the required methods, and instances of those classes can be used interchangeably via the trait's
  * required methods.
  * A normal trait hierarchy makes it easy to add additional sub-classes: just define your class and
  * implement the necessary methods. However, it makes it difficult to add new methods: a new
  * method needs to be added to all existing subclasses, of which there may be many.
  *
  * - Sealed traits are closed: they only allow a fixed set of classes to inherit from them, and all
  * inheriting classes must be defined together with the trait itself in the same file or REPL command.
  * A sealed trait hierarchy is the opposite: it is easy to add new methods, since a new method can
  * simply pattern match on each sub-class and decide what it wants to do for each. However, adding
  * new sub-classes is difficult, as you need to go to all existing pattern matches and add the case to
  * handle your new sub-class.
  *
  * In general, sealed trait s are good for modelling hierarchies where you expect the number of sub-classes
  * to change very little or not-at-all. A good example of something that can be modeled using sealed trait is
  * JSON.
  * - A JSON value can only be JSON null, boolean, number, string, array, or dictionary.
  * - JSON has not changed in 20 years, so it is unlikely that anyone will need to extend our JSON
  * with additional subclasses.
  * - While the set of sub-classes is fixed, the range of operations we may want to do on a JSON blob is
  * unbounded: parse it, serialize it, pretty-print it, minify it, sanitize it, etc.
  * Thus it makes sense to model a JSON data structure as a closed sealed trait
  * hierarchy rather than a normal open trait hierarchy.
  */
object SealedTraits extends App{
  sealed trait Point
  case class Point2D(x: Double, y: Double) extends Point
  case class Point3D(x: Double, y: Double, z: Double) extends Point

  def hypotenuse(p: Point) = p match {
    case Point2D(x, y) => math.sqrt(x * x + y * y)
    case Point3D(x, y, z) => math.sqrt(x * x + y * y + z * z)
  }

  val points: Array[Point] = Array(Point2D(1, 2), Point3D(4, 5, 6))

  for (p <- points) println(hypotenuse(p))
  // 2.23606797749979
  // 8.774964387392123

  sealed trait Json
  case class Null() extends Json
  case class Bool(value: Boolean) extends Json
  case class Str(value: String) extends Json
  case class Num(value: Double) extends Json
  case class Arr(value: Seq[Json]) extends Json
  case class Dict(value: Map[String, Json]) extends Json
}
