package hands_on_scala.basic_scala

/**
  * Define a pair of methods withFileWriter and withFileReader that can be called as
  * shown below. Each method should take the name of a file, and a function value that is called with a
  * java . io . BufferedReader or java . io . BufferedWriter that it can use to read or write data.
  * Opening and closing of the reader/writer should be automatic, such that a caller cannot forget to
  * close the file. This is similar to Python "context managers" or Java "try-with-resource" syntax.
  *
  * You can use the Java standard library APIs java . nio . file . Files . newBufferedWriter and
  * newBufferedReader for working with file readers and writers.
  */


object ContextManagers extends App{
  def withFileWriter[T](fileName: String)(handler: java.io.BufferedWriter => T) = {
    val output = java.nio.file.Files.newBufferedWriter(java.nio.file.Paths.get(fileName))
    try handler(output)
    finally output.close()
  }

  def withFileReader[T](fileName: String)(handler: java.io.BufferedReader => T) = {
    val input = java.nio.file.Files.newBufferedReader(java.nio.file.Paths.get(fileName))
    try handler(input)
    finally input.close()
  }

  withFileWriter("File.txt") { writer =>
    writer.write("Hello\n")
    writer.write("World!")
  }
  val result = withFileReader("File.txt") { reader =>
    reader.readLine() + "\n" + reader.readLine()
  }
  assert(result == "Hello\nWorld!")
}
