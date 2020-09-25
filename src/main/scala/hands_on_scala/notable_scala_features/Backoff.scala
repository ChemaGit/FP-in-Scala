package hands_on_scala.notable_scala_features

object Backoff {

  /**
    * Modify the def retry function earlier that takes a by-name parameter and make it
    * perform an exponential backoff, sleeping between retries, with a configurable initial delay in
    * milliseconds:
    *
    * @param max
    * @param delay
    * @param f
    * @tparam T
    * @return
    */
  def retry[T](max: Int, delay: Int = 0)(f: => T): T = {
    var tries = 0
    var result: Option[T] = None
    var currentDelay = delay
    while (result == None) {
      try { result = Some(f) }
      catch {case e: Throwable =>
        Thread.sleep(currentDelay)
        currentDelay *= 2
        tries += 1
        if (tries > max) throw e
        else {
          println(s"failed, retry #$tries")
        }
      }
    }
    result.get
  }

  def main(args: Array[String]): Unit = {
    // val httpbin = "https://httpbin.org"
    val httpbin = "https://www.palote.es/status/500,400,300,100,100,900,1000"

    retry(max = 50, delay = 50 /*milliseconds*/) {
      // Only succeeds with a 200 response
      // code 1/3 of the time
      requests.get(
        s"$httpbin/status/200,400,500"
      )
    }
  }

}
