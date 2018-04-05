package na.implicits

import scala.reflect.{ClassTag, classTag}

/**
 * https://docs.scala-lang.org/tutorials/FAQ/context-bounds.html
 *
 * A context bound describes an implicit value. It is used to declare that for some type A, there is an implicit value
 * of type B[A] available.
 *
 * Syntax is basically a syntactic sugar around implicits that goes like this:
 *              def f[A : B](a: A) = g(a) // where g requires an implicit value of type B[A]
 *
 * Syntax in full, omitting the syntactic sugar would look like:
 *      def g[A : B](a: A) = h(a)
 *      def g[A](a: A)(implicit ev: B[A]) = h(a)
 *
 * Context bounds are mainly used in what has become known as typeclass pattern, as a reference to Haskell’s type classes.
 * Basically, this pattern implements an alternative to inheritance by making functionality available through a sort of
 * implicit adapter pattern.  Scalaz is a library that makes heavy use of this pattern.
 *
 * */
class ContextBounds {

    /**
     * common usage of context bound. An Array initialization on a parametrized type requires a ClassTag to be available,
     * for arcane reasons related to type erasure and the non-erasure nature of arrays.
     */
    def func1[A : ClassTag](n: Int, v: A) = {
        new Array[A](n)

        println(classTag[A].runtimeClass)
    }

    /**
     * same as above, with the only difference that it doesn't take an instance of type A, which forces the compiler to inject a
     * a ClassTag[Nothing] which is the subtype type of all other types.
     */
    def func2[A : ClassTag](n: Int) = {
        new Array[A](n)

        println(classTag[A].runtimeClass)
    }

    /**
     * Another very common example in the library is a bit more complex:
     * Here, implicitly is used to retrieve the implicit value we want, one of type Ordering[A]
     */
    def func3[A : Ordering](a: A, b: A) = implicitly[Ordering[A]].compare(a, b)

    /**
     * syntactic sugar de-sugared
     */
    def func3_[A](a: A, b: A)(implicit ord: Ordering[A]) = ord.compare(a, b)

    /**
     * Context bounds are more useful when you just need to pass them to other methods that use them.
     * For example, the method sorted on Seq needs an implicit Ordering
     */
    def reverseSort[T : Ordering](seq: Seq[T]) = seq.reverse.sorted
}

case class TEST(a: String, b: String)

object ContextBounds extends App {

    val cb = new ContextBounds

    println(cb.func3("a","b"))

    cb.func1(5, TEST("",""))
}
