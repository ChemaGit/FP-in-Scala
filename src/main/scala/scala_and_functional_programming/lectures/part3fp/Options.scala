package scala_and_functional_programming.lectures.part3fp

import scala.util.Random

/**
  * Meet Options
  *   - An Option is a wrapper for a value that might be present or not.
  *
  *   sealed abstract class Option[+A]
  *   case class Some[+A](x: A) extends Option[A]
  *   case object None extends Option[Nothing]
  *
  *   - Some wraps a concrete value
  *   - None is a singleton for absent values
  *
  *   Options are present in many places:
  *
  *   - map uses options on its basic get operation: prefer it over apply
  *
  *   val map = Map("key" -> "value")
  *   map.get("key") // Some(value)
  *   map.get("other") // None
  *
  *   - lots of functions on all collections work with options
  *
  *   val numbers = List(1,2,3)
  *   list.headOption // Some(1)
  *   list.find(_ % 2 == 0) // Some(2)
  */

/**
  * WRAPPING UP
  * Use Options to stay away from the Boogeyman: Nulls
  *   - avoid runtime crashes due to NPEs
  *   - avoid endless amount of null-related assertions
  *
  * A functional way of dealing with absence
  *   - map, flatMap, filter
  *   - orElse
  *   - others: fold, collect, toList
  *
  * If you design a method to return a (some type) but may return null,
  * return an Option[that type] instead.
  */

object Options extends App{

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  // unsafe APIs
  def unsafeMethod(): String = null
  // val result = Some(unsafeMethod()) // WRONG
  val result = Option(unsafeMethod()) // Some or None
  println(result) // None

  // chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse((Option(backupMethod())))

  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod():Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod().orElse(betterBackupMethod())

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // unsafe - DO NOT USE THIS

  // map, flatMap, filter
  println(myFirstOption.map(v => v* 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for-comprehensions

  /*
    Exercise
   */
  // fetched from elsewhere
  val config: Map[String, String] = Map("host" -> "176.45.36.1", "port" -> "80")

  class Connection {
    def connect = "Connected" // connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
      else None
    }
  }

  // try to establish a connection, if so - print the connect method
  def getConnection(): Connection = {
    val host = config.get("host") match {
      case Some(x) => x
      case None => ""
    }

    val port = config.get("port") match {
      case Some(x) => x
      case None => ""
    }
    Connection(host, port) match {
      case Some(connection) => connection
      case None => getConnection()
    }
  }

  println(getConnection().connect )

  // Other solution
  val host = config.get("host")
  val port = config.get("port")
  /*
    if(h != null)
      if(p != null)
        return Connection.apply(h,p)
    return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p)))
  val connectionStatus = connection.map(c => c.connect)
  // if (connectionStatus == null) println(None) else print(Some(connectionStatus.get))
  println(connectionStatus)
  /*
    if(status != null)
      println(status)
   */
  connectionStatus.foreach(println)

  // other solution
  // chained calls
  config.get("host")
    .flatMap(host => config.get("port")
    .flatMap(port => Connection(host, port))
    .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)

}
