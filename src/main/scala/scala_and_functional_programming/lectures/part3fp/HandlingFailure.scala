package scala_and_functional_programming.lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

/**
  * Let's Try[T]
  * Exceptions are handled inside try-catch blocks:
  * try {
  *   val config: Map[String, String] = loadConfig(path)
  * } catch {
  *   case _: IOException => // handle IOException
  *   case _: Exception => // handle other Exception
  * }
  *
  * - Multiple / nested try's make the code hard to follow
  * - We can't chain multiple operations prone to failure
  *
  * A Try is a wrapper for a computation that might fail or not
  *
  * sealed abstract class Try[+T]
  * //Wrap failed computations
  * case class Failure[+T](t: Throwable) extends Try[T]
  * case class Success[+T](value: T) extends Try[T]
  *
  *
  * Wrapping Up
  *
  * Use Try to handle exceptions gracefully
  * - avoid runtime crashes due to uncaught exception
  * - avoid an endless amount of try-catches
  *
  * A functional way of dealing with failure
  * - map, flatMap, filter
  * - orElse
  * - others: fold, collect, toList, conversion to Options
  */

object HandlingFailure extends App {

  // create Success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU BUSTER")

  // Try objects via the apply method
  val potencialFailure = Try(unsafeMethod())
  println(potencialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potencialFailure.isSuccess)

  //  orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  // IF you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFallback = betterUnsafeMethod().orElse(betterBackupMethod())

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))
  // => for-comprehensions

  /*
    Exercise
   */
  val hostname = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if(random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if(random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  // if you get the html page from the connection, print it to the console
  // i.e. call renderHTML
  val connect = Try(HttpService.getConnection(hostname,port))
  connect.isSuccess match {
    case true => println(renderHTML(connect.get.get("https://google.com")))
    case _ => println(connect.getOrElse("Someone else took the port"))
  }

  val possibleConnection = HttpService.getSafeConnection(hostname, port)
  val posibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  posibleHTML.foreach(renderHTML)

  // shorthand version
  HttpService.getSafeConnection(hostname, port)
    .flatMap(connection => connection.getSafe("/home"))
    .foreach(renderHTML)

  // for-comprehension version
  for {
    connection <- HttpService.getSafeConnection(hostname, port)
    html <- connection.getSafe("/home")
  } renderHTML(html)

}
