package hands_on_scala.implementing_algorithms_in_scala

object Trie {

  /**
    * Trie is a tree-shaped data structure that behaves similarly to a Set [String], providing .add (s: String) and
    * .contains (s : String) methods, but has additional specialized functions that normal Sets do not:
    * .prefixesMatchingString (s : String) efficiently finds all strings in the Trie which are a prefix of the
    * string s. Useful for identifying words of varying length in a larger input string.
    * .stringsMatchingPrefix (s: String)
    * efficiently queries the Trie for all strings which contain a string
    * s as a prefix. Useful for things like autocompletes, where you want to narrow down a set of possible
    * completions given incomplete user input.
    * A Trie stores the strings it contains as a tree of characters. Words that share prefixes will share that portion
    * of the tree.
    *
    */
  class Trie() {
    class Node(var hasValue: Boolean,
               val children: collection.mutable.Map[Char, Node] = collection.mutable.Map())
    val root = new Node(false)
    def add(s: String) = {
      var current = root
      for (c <- s) current = current.children.getOrElseUpdate(c, new Node(false))
      current.hasValue = true
    }
    def contains(s: String): Boolean = {
      var current = Option(root)
      for (c <- s if current.nonEmpty) current = current.get.children.get(c)
      current.exists(_.hasValue)
    }

    /**
      * prefixesMatchingString0 returns a Set [Int] of the indices of the matching prefixes.
      * @param s
      * @return
      */
    def prefixesMatchingString0(s: String): Set[Int] = {
      var current = Option(root)
      val output = Set.newBuilder[Int]
      for ((c, i) <- s.zipWithIndex if current.nonEmpty) {
        if (current.get.hasValue) output += i
        current = current.get.children.get(c)
      }
      if (current.exists(_.hasValue)) output += s.length
      output.result()
    }

    /**
      * If we want the prefixes as strings, we can get them by calling . substring on the input
      * @param s
      * @return
      */
    def prefixesMatchingString(s: String): Set[String] = {
      prefixesMatchingString0(s).map(s.substring(0, _))
    }

    /**
      * stringsMatchingPrefix ( s : String)
      * efficiently queries the Trie for all strings which contain a string
      * s as a prefix.
      * @param s
      * @return
      */
    def stringsMatchingPrefix(s: String): Set[String] = {
      var current = Option(root)
      for (c <- s if current.nonEmpty) current = current.get.children.get(c) // initial walk
      if (current.isEmpty) Set()
      else {
        val output = Set.newBuilder[String]
        def recurse(current: Node, path: List[Char]): Unit = {
          if (current.hasValue) output += (s + path.reverse.mkString)
          for ((c, n) <- current.children) recurse(n, c :: path)
        }
        recurse(current.get, Nil) // recursive walk
        output.result()
      }
    }
  }

  def main(args: Array[String]): Unit = {
    /**
      * testing contains and add methods
      */
    val t = new Trie()
    t.add("mango")
    t.add("mandarin")
    t.add("map")
    t.add("man")

    println(t.contains("mango"))
    println(t.contains("mang"))
    println(t.contains("mandarin"))
    println(t.contains("mandarine"))

    /**
      * testing prefixesMatchingString0 method
      */
    t.prefixesMatchingString0("manible")
    // Set[Int] = Set(3)

    t.prefixesMatchingString0("mangosteen")
    // Set[Int] = Set(3, 5)

    /**
      * testing prefixesMatchingString
      */
    t.prefixesMatchingString("mangosteen")
    // Set[String] = Set("man", "mango")
  }
}
