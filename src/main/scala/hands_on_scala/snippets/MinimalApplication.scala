package hands_on_scala.snippets

object MinimalApplication extends cask.MainRoutes {
  @cask.get("/")
  def hello() = {
    "Hello World!"
  }
  initialize()
}
