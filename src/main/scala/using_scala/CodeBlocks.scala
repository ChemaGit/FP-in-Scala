package using_scala

import io.StdIn._

object CodeBlocks {
  def main(args: Array[String]): Unit = {
    val a = {
      println("Giving back 6")
      6
    }
    println(s"Value of a: $a")

    val b = {
      println("Enter a number")
      val num = readInt
      num * 2
    }
    println(s"Value of b: $b")

    val age = 18
    val response = if (age >= 21) {
      println("Old")
      "Come In"
    } else {
      println("Young")
      "Get Lost"
    }
    println(s" Value of response: $response")
    /***********************/
    println("How old are you?")
    val age2 = readInt
    val response2 = if(age < 21) "Get Lost" else "Come In"
   println(s"Value of response2: $response2")
  }
}
