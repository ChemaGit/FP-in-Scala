package hands_on_scala.notable_scala_features

/**
  * "by-name" method parameters using a : => T syntax, which are evaluated each time
  * they are referenced in the method body. This has three primary use cases:
  * 1. Avoiding evaluation if the argument does not end up being used
  * 2. Wrapping evaluation to run setup and teardown code before and after the argument evaluates
  * 3. Repeating evaluation of the argument more than once
  */

object ByNameParameters extends App {

  /**
    * Avoiding Evaluation
    *
    * Often a method does not end up using all of its arguments all the time.
    * We can save a significant amount of CPU time and
    * object allocations which may make a difference in performance-sensitive applications.
    */
  var logLevel = 1

  def log(level: Int, msg: => String) = {
    if (level > logLevel) println(msg)
  }

  log(2, "Hello " + 123 + " World")
  // Hello 123 World

  logLevel = 3

  log(2, "Hello " + 123 + " World")
  // <no output>

  /**
    * Wrapping Evaluation
    *
    * Using by-name parameters to "wrap" the evaluation of your method in some setup/teardown code is
    * another common pattern.
    */
  def measureTime(f: => Unit) = {
    val start = System.currentTimeMillis()
    f
    val end = System.currentTimeMillis()
    println("Evaluation took " + (end - start) + " milliseconds")
  }

  measureTime(new Array[String](10 * 1000 * 1000).hashCode())
  // Evaluation took 24 milliseconds

  measureTime { // methods taking a single arg can also be called with curly brackets
    new Array[String](100 * 1000 * 1000).hashCode()
  }
  //Evaluation took 287 milliseconds
  /**
    * There are many other use cases for such wrapping:
    * - Setting some thread-local context while the argument is being evaluated
    * - Evaluating the argument inside a try - catch block so we can handle exceptions
    * - Evaluating the argument in a Future so the logic runs asynchronously on another thread
    */


  /**
    * Repeating Evaluation
    *
    * The following snippet defines a generic retry method: this method takes in an argument, evaluates it
    * within a try - catch block, and re-executes it on failure with a maximum number of attempts.
    *
    * Making retry take a by-name parameter is what allows it to repeat evaluation of the requests . get block
    * where necessary. Other use cases for repetition include running performance benchmarks or performing
    * load tests.
    */

  def retry[T](max: Int)(f: => T): T = {
    var tries = 0
    var result: Option[T] = None
    while (result == None) {
      try { result = Some(f) }
      catch {case e: Throwable =>
        tries += 1
        if (tries > max) throw e
        else {
          println(s"failed, retry #$tries")
        }
      }
    }
    result.get
  }

  val httpbin = "https://httpbin.org"

  retry(max = 5) {
    // Only succeeds with a 200 response
    // code 1/3 of the time
    requests.get(s"$httpbin/status/300,200,500")
  }
}
