package functional_programming_in_scala.functional_data_structures

sealed trait Tree[+A]

case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {

  /**
    * Write a function size that counts the
    * number of nodes (leaves and branches) in a tree.
    * @param tr
    * @tparam A
    * @return
    */
  def size[A](tr: Tree[A]): Int = {
    def loop(t: Tree[A], acc: Int): Int = {
      t match {
        case Leaf(_) => acc + 1
        case Branch(l,r) => 1 + loop(l, acc) + loop(r, acc)
      }
    }
    loop(tr, 0)
  }
  def size2[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(l,r) => 1 + size(l) + size(r)
  }

  /*
  We're using the method `max` that exists on all `Int` values rather than an explicit
  `if` expression.
  Note how similar the implementation is to `size`.
  We'll abstract out the common pattern in a later exercise.
*/
  /**
    * Write a function maximum that returns the maximum element in a Tree[Int]. (Note:
    * In Scala, you can use x.max(y) or x max y to compute the maximum of two integers x
    * and y.)
    * @param tr
    * @tparam A
    * @return
    */
  def maximum[A](tr: Tree[Int]): Int = {
    tr match {
      case Leaf(v) => v
      case Branch(l, r) => maximum(l).max(maximum(r))
    }
  }

  /**
    * Write a function depth that returns the maximum path length from the root of a tree
    * to any leaf.
    * @param tr
    * @tparam A
    * @return
    */
  /*
 Again, note how similar the implementation is to `size` and `maximum`.
 */
  def depth[A](tr: Tree[A]): Int = tr match {
    case Leaf(_) => 0
    case Branch(l,r) => 1 + depth(l).max(depth(r))
  }

  /**
    * Write a function map, that modifies
    * each element in a tree with a given function.
    * @param tr
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  def map[A,B](tr: Tree[A])(f: A => B): Tree[B] = {
    tr match {
      case Leaf(v) => Leaf(f(v))
      case Branch(l, r) => Branch(map(l)(f), map(r)(f))
    }
  }

  /**
    * Generalize size, maximum, depth, and map, writing a new function fold that abstracts
    * over their similarities. Reimplement them in terms of this more general function. Can
    * you draw an analogy between this fold function and the left and right folds for List?
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  /*
  Like `foldRight` for lists, `fold` receives a "handler" for each of the data constructors
  of the type, and recursively accumulates some value using these handlers.
  As with `foldRight`, `fold(t)(Leaf(_))(Branch(_,_)) == t`, and we can use
  this function to implement just about any recursive function that would otherwise
  be defined by pattern matching.
  */
  def fold[A,B](t: Tree[A])(f: A => B)(g: (B,B) => B): B = t match {
    case Leaf(a) => f(a)
    case Branch(l,r) => g(fold(l)(f)(g), fold(r)(f)(g))
  }

  def sizeViaFold[A](tr: Tree[A]): Int = {
    fold(tr)(_ => 1)( (a, b) => a + b + 1)
  }

  def depthViaFold[A](tr: Tree[A]): Int = {
    fold(tr)(_ => 1)((a, b) => (a + 1).max(b + 1) )
  }

  def maximumViaFold[A](tr: Tree[Int]): Int = {
    fold(tr)( a => a)( (a, b) => a.max(b))
  }
  /*
  Note the type annotation required on the expression `Leaf(f(a))`. Without this annotation, we get an error like this:

  type mismatch;
    found   : fpinscala.datastructures.Branch[B]
    required: fpinscala.datastructures.Leaf[B]
      fold(t)(a => Leaf(f(a)))(Branch(_,_))
                                    ^
  This error is an unfortunate consequence of Scala using
  subtyping to encode algebraic data types. Without the
  annotation, the result type of the fold gets inferred
  as `Leaf[B]` and it is then expected that the second argument
  to `fold` will return `Leaf[B]`, which it doesn't
  (it returns `Branch[B]`). Really, we'd prefer Scala to
  infer `Tree[B]` as the result type in both cases.
  When working with algebraic data types in Scala, it's somewhat
  common to define helper functions that simply call the corresponding
  data constructors but give the less specific
  result type:

  def leaf[A](a: A): Tree[A] = Leaf(a)
  def branch[A](l: Tree[A], r: Tree[A]): Tree[A] = Branch(l, r)
*/

  def mapViaFold[A,B](tr: Tree[A])(f: A => B): Tree[B] = {
    fold(tr)(a => Leaf(f(a)): Tree[B])( (a, b) => Branch(a, b) )
  }

  def main(args: Array[String]): Unit = {
    println(Tree.size(Branch(Branch(Leaf(3),Leaf(5)),Branch(Leaf(2),Branch(Leaf(3), Leaf(7))))))
    println("**********************************************")
    println(Tree.size2(Branch(Branch(Leaf(3),Leaf(5)),Branch(Leaf(2),Branch(Leaf(3), Leaf(7))))))
    println("**********************************************")
    println(Tree.maximum(Branch(Branch(Leaf(3),Branch(Leaf(5),Branch(Leaf(23),Leaf(25)))),Branch(Leaf(2),Branch(Leaf(3), Branch(Leaf(7),Branch(Leaf(18),Leaf(21))))))))
    println("**********************************************")
    println(Tree.depth(Branch(Branch(Leaf(3),Branch(Leaf(5),Branch(Leaf(23),Leaf(25)))),Branch(Leaf(2),Branch(Leaf(3), Branch(Leaf(7),Branch(Leaf(18),Leaf(21))))))))
    println("**********************************************")
    println(Tree.depth(Branch(Branch(Leaf(3),Leaf(5)),Branch(Leaf(2),Branch(Leaf(3), Leaf(7))))))
    println("**********************************************")
    println(Tree.depth(Branch(Branch(Leaf('a'), Leaf('b')), Branch(Leaf('c'), Leaf('d')))))
    println("**********************************************")
    println(Tree.depth(Branch(Branch(Leaf('a'), Leaf('b')), Branch(Leaf('c'),Branch(Leaf('d'),Leaf('e'))))))
    println("**********************************************")
    println(Tree.map(Branch(Branch(Leaf(3),Leaf(5)),Branch(Leaf(2),Branch(Leaf(3), Leaf(7)))))(l => l * 2))
    println("**********************************************")
    println(Tree.fold(Branch(Branch(Leaf(3),Branch(Leaf(5),Branch(Leaf(23),Leaf(25)))),Branch(Leaf(2),Branch(Leaf(3), Branch(Leaf(7),Branch(Leaf(18),Leaf(21)))))))(a => a)( (a, b) => a * b))
    println("**********************************************")
    println(Tree.size(Branch(Branch(Leaf(3),Leaf(5)),Branch(Leaf(2),Branch(Leaf(3), Leaf(7))))))
    println("**********************************************")
    println(Tree.maximumViaFold(Branch(Branch(Leaf(27),Branch(Leaf(5),Branch(Leaf(23),Leaf(25)))),Branch(Leaf(2),Branch(Leaf(3), Branch(Leaf(7),Branch(Leaf(18),Leaf(21))))))))
    println("**********************************************")
    println(Tree.depth(Branch(Branch(Leaf(3),Branch(Leaf(5),Branch(Leaf(23),Leaf(25)))),Branch(Leaf(2),Branch(Leaf(3), Branch(Leaf(7),Branch(Leaf(18),Leaf(21))))))))
    println("**********************************************")
    println(Tree.mapViaFold(Branch(Branch(Leaf(3),Leaf(5)),Branch(Leaf(2),Branch(Leaf(3), Leaf(7)))))(l => l * 2))
  }
}
