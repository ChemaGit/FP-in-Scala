package hands_on_scala.basic_scala

object Loops extends App {

  var total = 0
  val items = Array(1,10,100,1000)

  for(item <- items) total += item

  println(total)

  total = 0

  for(i <- Range(0 ,5)) {
    println("Looping " + i)
    total += total + i
  }
  println(total)

  /**
    * You can loop over nested Array s by placing multiple <- s in the header of the loop:
     */
  val multi = Array(Array(1, 2, 3), Array(4, 5, 6))

  for (arr <- multi; i <- arr) println(i)

  /**
    * Loops can have guards using an if syntax:
    */
  for (arr <- multi; i <- arr; if i % 2 == 0) println(i)
}
