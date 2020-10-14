package hands_on_scala.basic_scala

object OptionExample {

  def hello(title: String, firstName: String, lastNameOpt: Option[String]) = {
    lastNameOpt match {
      case Some(lastName) => println(s"Hello $title. $lastName")
      case None => println(s"Hello $firstName")
    }
  }

  def hello2(name: Option[String]) = {
    for (s <- name) println(s"Hello $s")
  }

  def nameLength(name: Option[String]) = {
    name.map(_.length).getOrElse(-1)
  }

  def main(args: Array[String]): Unit = {
    hello("Mr", "Chema", None)
    hello("Mr", "Chema", Some("Nacher"))
    hello2(Some("Chema"))

    println(nameLength(Some("Chema")))

  }
}
