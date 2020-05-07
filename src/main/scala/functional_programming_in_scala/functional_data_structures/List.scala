package functional_programming_in_scala.functional_data_structures

import scala.annotation.tailrec

/**
  * Singly linked lists
  */

/**
  * List data type,
  * parameterized on a type, A.
  */
sealed trait List[+A]

/**
  * A List data constructor
  * representing the empty list.
  */
case object Nil extends List[Nothing]

/**
  * Another data constructor, representing
  * nonempty lists. Note that tail is
  * another List[A], which may be Nil
  * or another Cons.
  */
case class Cons[+A](head: A, tail: List[A]) extends List[A]

/**
  * List companion object.
  * Contains functions for creating and
  * working with lists
  */
object List {
  /**
    * A function that uses pattern matching to add up
    * a list of integers
    * @param ints
    * @return
    */
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0 // The sum of the empty list is 0
    // The sum of a list starting with x is x plus the sum
    // of the rest of the list
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  /**
    * Again, placing f int its own argument group
    * after as and z lets type inference detemine
    * the input types to f
    * @param as
    * @param z
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  /**
    * Implementing foldRight via foldLeft is useful because it lets us implement
    * foldRight tail-recursively, which means it works even for large lists without overflowing
    * the stack.
    * @param as
    * @param z
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  def foldRight2[A,B](as: List[A], z: B)(f: (A, B) => B): B =
    foldLeft(as, z)( (b, a) => f(a, b))

  def sum2(ns: List[Int]) =
    foldRight(ns, 0)((x, y) => x + y)

  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _)

  /**
    * Compute the length of a list using foldRight.
    * @param as
    * @tparam A
    * @return Int
    */
  def length[A](as: List[A]): Int =
    foldRight(as,0)( (_, b) => b + 1)

  /**
    * Our implementation of foldRight is not tail-recursive and will result in a StackOverflowError
    * for large lists (we say it’s not stack-safe). Convince yourself that this is the
    * case, and then write another general list-recursion function,
    * foldLeft, that is tail-recursive
    */
  def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = {
    as match {
      case Nil => z
      case Cons(head, tail) => foldLeft(tail, f(z, head))(f)
    }
  }

  /**
    * write foldLeft in terms of foldRight
    * @param as
    * @param z
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  def foldLeft2[A,B](as: List[A], z: B)(f: (B, A) => B): B = {
    foldRight(as, z)( (a, b) => f(b, a))
  }

  /**
    * Write sum function using foldLeft
    * @param xs
    * @return
    */
  def sum3(xs: List[Int]): Int =
    foldLeft(xs, 0)( (b, a) => a + b)

  /**
    * Write product function using foldLeft
    * @param xs
    * @return
    */
  def product3(xs: List[Double]): Double =
    foldLeft(xs, 1.0)( (b, a) => a * b)

  /**
    * Write lengt function using foldLeft
    * @param xs
    * @return
    */
  def length2[A](xs: List[A]): Int =
    foldLeft(xs,0)( (b, _) => b + 1)

  /**
    * Write a function that returns the reverse of a list
    * See if you can write it using a fold
    * @param xs
    * @tparam A
    * @return
    */
  def reverse[A](xs: List[A]): List[A] =
    foldLeft(xs, List[A]())( (acc, a) => Cons(a, acc))

  /**
    * Implement the function tail for removing the first
    * element of a List. Note that the function takes
    * constant time
    * @param ds
    * @tparam A
    * @return
    */
  def tail[A](ds: List[A]): List[A] = ds match {
    case Nil => sys.error("tail of an empty list")
    case Cons(_, tail) => tail
  }

  /**
    * Generalize tail to the function drop, which removes the first
    * n elements from a list.
    * @param l
    * @param n
    * @tparam A
    * @return
    */
  @annotation.tailrec
  def drop[A](l: List[A], n: Int): List[A] = {
    n match {
      case num if (num <= 0) => l
      case _ => {
        l match {
          case Nil => Nil
          case Cons(_, tail) => drop(tail, n - 1)
        }
      }
    }
  }

  /**
    * Removes elements from the List prefix as long as they match
    * a predicate.
    * @param a1
    * @param f
    * @tparam A
    * @return
    */
    @annotation.tailrec
  def dropWhile[A](a1: List[A], f: A => Boolean): List[A] = {
    a1 match {
      case Nil => Nil
      case Cons(h, tail) => {
        if(f(h)) dropWhile(tail, f)
        else a1
      }
    }
  }

  /**
    * It’s a little unfortunate that we need to state that the type of x is Int.
    * The first argument
    * to dropWhile is a List[Int], so the function in the second argument must
    * accept an Int. Scala can infer this fact if we group dropWhile into two argument lists:
    *
    * @param a1
    * @param f
    * @tparam A
    * @return
    */
  def dropWhile2[A](a1: List[A])(f: A => Boolean): List[A] = {
    a1 match {
      case Nil => Nil
      case Cons(h, tail) => {
        if(f(h)) dropWhile(tail, f)
        else a1
      }
    }
  }

  /**
    * This function adds the elements of one list
    * to the end of another
    * In this case, the immutable linked list
    * is much more efficient than an array!
    */
  def append[A](a1: List[A], a2: List[A]): List[A] = {
    a1 match {
      case Nil => a2
      case Cons(h, t) => Cons(h, append(t, a2))
    }
  }

  /**
    * Implement append in terms of foldLeft.
    * @param a1
    * @param a2
    * @tparam A
    * @return
    */
  def append2[A](a1: List[A], a2: List[A]): List[A] = {
    List.reverse(foldLeft(a2, List.reverse(a1))( (b, a) => Cons(a, b)))
  }
  /**
    * Implement append in terms of foldRight.
    * @param a1
    * @param a2
    * @tparam A
    * @return
    */
  def append3[A](a1: List[A], a2: List[A]): List[A] =
    foldRight(a1, a2)( (a, b) => Cons(a, b))

  /**
    * Write a function that concatenates a list of lists into a single list. Its runtime
    * should be linear in the total length of all lists.
    * @param a1
    * @tparam A
    * @return
    */
  def concat[A](a1: List[List[A]]): List[A] = {
    foldRight(a1,List[A]())( (a,z) => append(a, z))
  }

  /**
    * Write a function that transforms a list of integers
    * by adding 1 to each element.
    * @param a1
    * @return
    */
    @annotation.tailrec
  def addOne(a1: List[Int], acc: List[Int]): List[Int] = {
    a1 match {
      case Nil => List.reverse(acc)
      case Cons(h, t) => addOne(t, Cons(h + 1,acc))
    }
  }
  /**
    * Write a function that transforms a list of integers
    * by adding 1 to each element.
    * @param a1
    * @return
    */
  def addOne_B(a1: List[Int]): List[Int] = {
    foldRight(a1, Nil:List[Int])( (a, z) => Cons(a + 1, z))
  }

  /**
    * Write a function that turns each value in
    * a List[Double] into a String. You can use
    * the expression d.toString to convert some d: Double to a String.
    * @param a1
    * @return
    */
  def doubleToString(a1: List[Double]): List[String] = {
    foldRight(a1, Nil:List[String])( (a, z) => Cons(a.toString,z))
  }


  /*
 A natural solution is using `foldRight`, but our implementation of `foldRight` is not stack-safe. We can
 use `foldRightViaFoldLeft` to avoid the stack overflow (variation 1), but more commonly, with our current
 implementation of `List`, `map` will just be implemented using local mutation (variation 2). Again, note that the
 mutation isn't observable outside the function, since we're only mutating a buffer that we've allocated.
 */
  /**
    * Write a function map that generalizes modifying each element
    * in a list while maintaining
    * the structure of the list.
    *
    * @param as
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  def map[A, B](as: List[A])(f: A => B): List[B] = {
    foldRight(as,List[B]())( (a, z) => Cons(f(a),z))
  }

  def map_1[A,B](l: List[A])(f: A => B): List[B] =
    foldRight2(List.reverse(l), Nil:List[B])((h,t) => Cons(f(h),t))

  def map_2[A, B](l: List[A])(f: A => B): List[B] = {
    @tailrec
    def go[A, B](ls: List[A], acc: List[B])(func: A => B): List[B] = {
      ls match {
        case Nil => acc
        case Cons(h:A, t:List[A]) => go(t, Cons(func(h),acc )) (func)
      }
    }
    go(List.reverse(l), List[B]())(f)
  }

  /**
    * Write a function filter that removes elements from a list unless
    * they satisfy a given
    * predicate. Use it to remove all odd numbers from a List[Int].
    *
    * The discussion about map also applies here
    *
    * @param as
    * @param f
    * @tparam A
    * @return
    */
  def filter[A](as: List[A])(f: A => Boolean): List[A] = {
    foldRight(as, List[A]())( (a, z) =>{if(f(a)) Cons(a, z) else z} )
  }

  def filter_2[A](l: List[A])(f: A => Boolean): List[A] = {
    val buf = new collection.mutable.ListBuffer[A]
    def go(l: List[A]): Unit = l match {
      case Nil => ()
      case Cons(h,t) => if (f(h)) buf += h; go(t)
    }
    go(l)
    List(buf.toList: _*) // converting from the standard Scala list to the list we've defined here
  }

  /**
    * Write a function flatMap that works like map except that the function given will return
    * a list instead of a single result, and that list should be inserted into the final resulting
    * list.
    * For instance, flatMap(List(1,2,3))(i => List(i,i)) should result in
    * List(1,1,2,2,3,3).
    *
    * @param as
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  def flatMap[A,B](as: List[A])(f: A => List[B]): List[B] = {
    //concat(map(as)(a => f(a)))
    foldRight(as,List[B]())( (a,z) => append(f(a), z))
  }

  /**
    * Use flatMap to implement filter.
    */
  def filterViaFlatMap[A](as: List[A])(f: A => Boolean): List[A] = {
    flatMap(as)(a => if (f(a)) Cons(a, Nil) else Nil)
  }

  /**
    * Write a function that accepts two lists and constructs
    * a new list by adding corresponding
    * elements. For example, List(1,2,3) and List(4,5,6) become List(5,7,9).
    */
  /*
To match on multiple values, we can put the values into a pair and match on the pair, as shown next, and the same
syntax extends to matching on N values (see sidebar "Pairs and tuples in Scala" for more about pair and tuple
objects). You can also (somewhat less conveniently, but a bit more efficiently) nest pattern matches: on the
right hand side of the `=>`, simply begin another `match` expression. The inner `match` will have access to all the
variables introduced in the outer `match`.

The discussion about stack usage from the explanation of `map` also applies here.
*/
  def addPairwise(a: List[Int], b: List[Int]): List[Int] = {
    @tailrec
    def go(l1: List[Int], l2: List[Int], acc: List[Int]): List[Int] = {
      (l1,l2) match {
        case (Nil, Nil) => acc
        case (_, Nil) => acc
        case (Nil, _) => acc
        case (Cons(h, t), Cons(h1, t1)) => go(t, t1, Cons(h + h1, acc))
      }
    }
    List.reverse(go(a,b, List()))
  }

  def addPairwise2(a: List[Int], b: List[Int]): List[Int] = (a,b) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(h1,t1), Cons(h2,t2)) => Cons(h1+h2, addPairwise(t1,t2))
  }

  /**
    * Generalize the function you just wrote so that
    * it’s not specific to integers or addition.
    * Name your generalized function zipWith.
    *
    * This function is usually called `zipWith`.
    * The discussion about stack usage from the explanation of `map` also
    * applies here. By putting the `f` in the second argument list,
    * Scala can infer its type from the previous argument list.
    */
  def zipWith[A,B,C](a: List[A], b: List[B])(f:(A,B) => C): List[C] = {
    @tailrec
    def go(l1: List[A], l2: List[B], acc: List[C],f:(A,B) => C): List[C] = {
      (l1,l2) match {
        case (Nil, Nil) => acc
        case (_, Nil) => acc
        case (Nil, _) => acc
        case (Cons(h, t), Cons(h1, t1)) => go(t, t1, Cons(f(h,h1), acc), f)
      }
    }
    List.reverse(go(a,b, List(), f))
  }

  def zipWith2[A,B,C](a: List[A], b: List[B])(f: (A,B) => C): List[C] = (a,b) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(h1,t1), Cons(h2,t2)) => Cons(f(h1,h2), zipWith(t1,t2)(f))
  }

  /**
    * implement hasSubsequence for checking whether a List contains
    * another List as a subsequence. For instance, List(1,2,3,4) would have
    * List(1,2), List(2,3), and List(4) as subsequences, among others.
    * @param sup
    * @param sub
    * @tparam A
    * @return
    */
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = {
    @tailrec
    def loop1(sp: List[A], sb: List[A]): Boolean = {
      (sp, sb) match {
        case (Cons(h, t), Cons(h1, Nil)) if h == h1 => true
        case(Cons(h,t),Cons(h1,t1)) if h == h1 => loop1(t, t1)
        case (_ , _) => false
      }
    }
    @tailrec
    def loop(sp: List[A], sb: List[A]): Boolean = {
      sp match {
        case l if List.length(l) < List.length(sb) => false
        case (Cons(h, t)) if !loop1(sp, sb) => loop(t, sb)
        case _ => true
      }
    }
    (sup,sub) match {
      case (Nil, Nil) => false
      case (_ , Nil) => false
      case (Nil, _) => false
      case (_, _) => loop(sup, sub)
    }
  }

  /*
  There's nothing particularly bad about this implementation,
  except that it's somewhat monolithic and easy to get wrong.
  Where possible, we prefer to assemble functions like this using
  combinations of other functions. It makes the code more obviously
  correct and easier to read and understand. Notice that in this
  implementation we need special purpose logic to break out of our
  loops early. In Chapter 5 we'll discuss ways of composing functions
  like this from simpler components, without giving up the efficiency
  of having the resulting functions work in one pass over the data.

  It's good to specify some properties about these functions.
  For example, do you expect these expressions to be true?

  (xs append ys) startsWith xs
  xs startsWith Nil
  (xs append ys append zs) hasSubsequence ys
  xs hasSubsequence Nil
  */
  @annotation.tailrec
  def startsWith[A](l: List[A], prefix: List[A]): Boolean = (l,prefix) match {
    case (_,Nil) => true
    case (Cons(h,t),Cons(h2,t2)) if h == h2 => startsWith(t, t2)
    case _ => false
  }
  @annotation.tailrec
  def hasSubsequence2[A](sup: List[A], sub: List[A]): Boolean = sup match {
    case Nil => sub == Nil
    case _ if startsWith(sup, sub) => true
    case Cons(h,t) => hasSubsequence2(t, sub)
  }

  /**
    * returns a List consisting of all
    * but the last element of a List.
    * So, given List(1,2,3,4), init will
    * return List(1,2,3)
    *
    * @param l
    * @tparam A
    * @return
    */
  def init[A](l: List[A]): List[A] = {
    l match {
      case Nil => Nil
      case Cons(h, Nil) => Nil
      case Cons(h, t) => Cons(h, init(t))
    }
  }

  /**
    * Using the same idea, implement the function setHead
    * for replacing the first element of a List with a different
    * value
    * @param x
    * @param ds
    * @tparam A
    * @return
    */
  def setHead[A](x: A, ds: List[A]): List[A] = ds match {
    case Nil => sys.error("set head of an empyt list")
    case Cons(_ ,tail) => Cons(x, tail)
  }

  /**
    * Variadic functions in Scala
    * Variadic functions: meaning it accepts zero
    * or more arguments of type A
    * By calling this function
    * apply and placing it in the companion object,
    * we can invoke it with syntax like
    * List(1,2,3,4) or List("hi","bye"),
    * with as many values as we want separated
    * by commas (we sometimes call this the list literal
    * or just literal syntax). Variadic functions
    * are just providing a little syntactic sugar
    * for creating and passing a Seq of elements explicitly.
    * The special _* type annotation allows us to pass a Seq to a
    * variadic method.
    * @param as
    * @tparam A
    * @return
    */
  def apply[A](as: A*): List[A] = {
    if(as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  }
}

object Main extends App {
  val ex1: List[Double] = Nil
  val ex2: List[Int] = Cons(1, Nil)
  val ex3: List[String] = Cons("a", Cons("b", Nil))

  /**
    * What will be the result of the following expression?
    */
  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + List.sum(t)
    case _ => 101
  }
  println(x)

  println(List.tail(List(1,2,3,4)))
  println(List.tail(List("a", "b", "c", "d")))
  println(List.setHead(1, List(2,3,4,5)))
  println(List.drop(List(1,2,3,4,5), 2))
  println(List.dropWhile(List(2,4,6,7,8,10), (n: Int) => n % 2 == 0))
  println(List.init(List(1,2,3,4)))

  val xs: List[Int] = List(1,2,3,4,5)
  println(List.dropWhile2(xs)(x => x % 2 == 0))
  println("****************")
  println(List.foldRight(List(1,2,3),Nil: List[Int])(Cons(_,_)))
  println("****************")
  println(List.length(xs))
  println("****************")
  println(List.foldLeft(List(1,2,3),0)( (a, b) => a + b))
  println("****************")
  println(List.sum3(xs))
  println("****************")
  println(List.product3(List(1.0,2.0,3.0,4.0,5.0)))
  println("****************")
  println(List.length2(List("a", "b", "c", "d")))
  println("****************")
  println(List.reverse(List("a", "b", "c", "d")))
  println("****************")
  println(List.foldRight2(List(1,2,3),0)( (b, a) => b + a))
  println("****************")
  println(List.foldLeft2(List(1,2,3,4),1)( (b, a) => b * a))
  println("****************")
  println(List.append2(List(1,2,3,4), List(5, 6)))
  println("****************")
  println(List.append3(List(1,2,3,4), List(5, 6)))
  println("****************")
  println(List.concat(List(List("a", "b"),List("c", "d"),List("e", "f"))))
  println("****************")
  println(List.addOne(List(1,2,3,4), Nil))
  println("****************")
  println(List.addOne_B(List(1,2,3,4)))
  println("****************")
  println(List.doubleToString(List(1.43,2.56,3.78,4.89)))
  println("****************")
  println(List.map(List(1,2,3,4))( a => a * 2))
  println("****************")
  println(List.map_1(List(1,2,3,4))( a => a * 2))
  println("****************")
  println(List.map_2(List(1,2,3,4))( a => a * 2))
  println("****************")
  println(List.filter(List(1,2,3,4))( a => a % 2 == 0))
  println("****************")
  println(List.flatMap(List(1,2,3,4))( a => List(a, a)))
  println("****************")
  println(List.filterViaFlatMap(List(1,2,3,4))( a => a % 2 == 0))
  println("****************")
  println(List.addPairwise(List(1,2,3,4),List(1,2,3)))
  println("****************")
  println(List.addPairwise2(List(1,2,3,4),List(1,2,3)))
  println("****************")
  println(List.zipWith(List(1,2,3,4),List(1,2,3))( (a,b) => a * b))
  println("****************")
  println(List.hasSubsequence(List(1,2,3,4),List(4,5)))
  println("****************")
}
