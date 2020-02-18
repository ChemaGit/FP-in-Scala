# OVERVIEW

````text
- Scala offers both mutable and immutable collections:
    - mutable collections can be updated in place
    - immutable collections never change

- We are using the immutable collections by default
````

````scala
package object scala {
  type List[+A] = immutable.List[A]
}

object Predef {
  type Map[A, +B] = immutable.Map[A, B]
  type Set[A] = immutable.Set[A]
}
````

````text
# Immutable Collections
    - Immutable collections are found in scala.collections.immutable package

# Mutable Collections
    - Mutable collections are found in scala.collections.mutable package

# Traversable
    - Base traint for all collections. Offers a great variety of methods:
        - maps: map, flatMap, collect
        - conversions: toArray, toList, toSeq
        - size info: isEmpty, size, nonEmpty
        - tests: exists, forall
        - folds: foldLeft, foldRight, reduceRight, reduceLeft
        - retrieval: head, find, tail
        - string ops: mkString
````