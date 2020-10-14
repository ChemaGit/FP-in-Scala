package hands_on_scala.notable_scala_features


object Test {

  def retry[T](max: Int)(f: => T): T = {
    var tries = 0
    var result: Option[T] = None
    while (result == None) {
      try {
        result = Some(f)
      }
      catch {
        case e: Throwable => tries += 1
        if (tries > max) throw e
        else {
          println(s"failed, retry #$tries")
        }
      }
    }
    result.get
  }

  def myMethod(n: Int): Int = {
    val num = scala.util.Random.nextInt(10)
    if(n < num) throw new Exception
    else num
  }
  /*******************************************/
  import scala.annotation.tailrec

  trait Gender

  case object Female extends Gender

  case object Male extends Gender

  case class Inhabitant(age: Int= 50, gender: Gender)

  val olderThen = 30

  val inhabitantsBoth: List[Inhabitant] = List(Inhabitant(gender=Male), Inhabitant(gender=Female))
  val inhabitantsFemale: List[Inhabitant] = List(Inhabitant(gender=Female), Inhabitant(gender=Female))
  val inhabitantsMale: List[Inhabitant] = List(Inhabitant(gender=Male), Inhabitant(gender=Male))


  @tailrec
  def countOldManFloor(inhabitants: List[Inhabitant]): Int = inhabitants match {
    case inhabitant :: inhabitants  if inhabitant.age > olderThen => 1
    case inhabitant :: inhabitants => countOldManFloor(inhabitants)
    case Nil => 0
  }

  def countOldManFloor2(inhabitants: List[Inhabitant]): Int = {
    def checkGender(inhabitant: Gender): Boolean = inhabitant match {
      case Male => true
      case _ => false
    }

    @tailrec
    def loop(lst: List[Inhabitant], cont: Int): Int = {
      lst match {
        case Nil => cont
        case (h :: tail) if h.age > olderThen && checkGender(h.gender) => loop(tail, cont + 1)
        case _ => loop(lst.tail, cont)
      }
    }
    loop(inhabitants, 0)
  }

  def main(args: Array[String]): Unit = {
    // println(retry(max = 5){myMethod(-1)})
    println(countOldManFloor(inhabitantsBoth))
    println(countOldManFloor(inhabitantsMale))
    println(countOldManFloor(inhabitantsFemale))

    println(countOldManFloor2(inhabitantsBoth))
    println(countOldManFloor2(inhabitantsMale))
    println(countOldManFloor2(inhabitantsFemale))
  }
}
