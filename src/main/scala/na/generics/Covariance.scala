package na.generics

/**
 * because Nothing is a subType of every other type and because List are covariant in their type parameters, the empty list Nil
 * can be defined to extend List[Nothing]
 *
 */
class Covariance {

    //Functions are contravariant in their parameters and covariant in their return types


    // SubTyping rule by Barbara Liskov
    /**
     * if A < B, then everything one can do with a value of type B, one should be able to do with type A
     * The actual definition was such as: Let q(x) be a property provable about objects x of type B, then q(y) should be
     * provable for objects y of type A, where A :< B
     *
     * Functions are contravariant in their parameters and covariant in their return types
     * The compiler checks that the covariance combinations in a class definition are sound and sensible, basically it checks the following:
     *      1- covariant type parameters can only appear in method results
     *      2- contravariant type parameters can only appear in method arguments
     *      3- invariant type parameters can appear anywhere
     *      4- covariant type parameters may appear in lower bounds of method type parameters
     *      5- contravariant type parameters may appear in upper bounds of method type parameters
     */

    // Covariance problem with arrays

    /**
     * The issue is because Arrays are covariant like Lists
     *
     * Example:
     *  Assuming IntSet is the super type of NonEmpty and Empty
     *
     *
     *  To solve this problem Java stores the metadata of the array content in a TypeTag that denotes the actual type that can be assigned to
     *  the content of this array and throws and exception otherwise of type ArrayStateException.
     *
     *  Java:
     *  NonEmpty[] a = Array(new NonEmpty(1, Empty, Empty))
     *  IntSet[] = a
     *  b[0] = Empty // in java this would Throw an ArrayStateException
     *  NonEmpty s = a[0]  //clearly a problem
     *
     *  Scala: Arrays are made non covariant, to avoid this problem
     *
     *  val a: Array[NonEmpty] a = Array(new NonEmpty(1, Empty, Empty))
     *  val b: Array[IntSet] = a // in scala, this will result in a type error, as arrays are assumed non-covariant
     *  b[0] = Empty
     *  val s: NonEmpty = a[0]  //clearly a problem
     *
     *  So Scala rule is ... Immutable types can be covariant (List), while mutable types cannot (Array)
     **/

    /**
     *      IntSet
     *      EmptySet < IntSet
     *      NonEmptySet < IntSet
     *
     *      def prepend [U>:T] (elem: U): List[U] = new Cons(elem, this)
     *
     *      def f(xs: List[NonEmpty], x: Empty) = xs prepend x
     *
     *    this works cos the compiler knows that x has the type `NonEmpty` while the T of prepend is a `NonEmpty` since
     *    the list xs is a List[NonEmpty]
     *    The type inference of the compiler knows that `elem` which is of type U must be a super type of `T` which is `NonEmpty`....
     *    so it attempts to treat x as an IntSet, cos that's the first super type of both Empty and NonEmptySet and an
     *    EmptySet cannot be a NonEmpty set.. so the closest type is IntSet
     * */

}
