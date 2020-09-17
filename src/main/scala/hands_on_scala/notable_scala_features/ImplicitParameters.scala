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
}
