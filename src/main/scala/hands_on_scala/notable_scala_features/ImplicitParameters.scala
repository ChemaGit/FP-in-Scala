package hands_on_scala.notable_scala_features

/**
  * An implicit parameter is a parameter that is automatically filled in for you when calling a function.
  *
  * Implicit parameters are similar to the default values. Both of them allow
  * you to pass in a value explicitly or fall back to some default. The main difference is that while default values
  * are "hard coded" at the definition site of the method, implicit parameters take their default value from
  * whatever implicit is in scope at the call-site.
  */
object ImplicitParameters extends App{
  /**
    * If you try to call bar without an implicit Foo in scope, you get a compilation error. To call bar , you need to
    * define an implicit value of the type Foo , such that the call to bar can automatically resolve it from the
    * enclosing scope
    */
  class Foo(val value: Int)
  def bar(implicit foo: Foo) = foo.value + 10

  // bar --> it doesn't compile

  implicit val foo: Foo = new Foo(1)
  // foo: Foo = ammonite.$sess.cmd1$Foo@451882b2

  val imp = bar // `foo` is resolved implicitly
  // imp: Int = 11
  println(imp)

  val imp1 = bar(foo) // passing in `foo` explicitly
  // imp1: Int = 11
  println(imp1)

  /**
    * Passing ExecutionContext to Futures
    *
    * Code using Future needs an ExecutionContext value in order to work.
    * Without implicit parameters, we have the following options:
    * Passing executionContext explicitly is verbose and can make your code harder to read: the logic we
    * care about is drowned in a sea of boilerplate executionContext passing
    * Making executionContext global would be concise, but would lose the flexibility of passing
    * different values in different parts of your program
    * Putting executionContext into a thread-local variable would maintain flexibility and conciseness,
    * but it is error-prone and easy to forget to set the thread-local before running code that needs it
    * All of these options have tradeoffs, forcing us to either sacrifice conciseness, flexibility, or safety. Scala's
    * implicit parameters provide a fourth option: passing executionContext implicitly, which gives us the
    * conciseness, flexibility, and safety that the above options are unable to give us.
    */

  /**
    * Dependency Injection via Implicits
    *
    * To resolve these issues, we can make all these functions take the executionContext as an implicit
    * parameter.
    */

  import scala.concurrent._
  trait Employee
  trait Role
  case class EmployeeWithRole(e: Employee, r: Role)
  def getEmployee(id: Int)(implicit ec: ExecutionContext): Future[Employee] = ???
  def getRole(employee: Employee)(implicit ec: ExecutionContext): Future[Role] = ???

  implicit def executionContext: ExecutionContext = ???

  def bigEmployee: Future[EmployeeWithRole] = {
    getEmployee(100).flatMap(e =>
      getRole(e).map(r =>
        EmployeeWithRole(e, r)
      )
    )
  }
}
