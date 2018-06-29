package na.collections

class Ground {
    /**
     * 1- flatten, takes a collection of collections and does a foldRight, to combine them all in one collection
     */
    Vector(Vector(1,2,3), Vector(2,3,4), Vector(5,6,7)).flatten

    //List, Stream, Queue and Stack are all under the LinearSeq

    //Vector, Range, NumericRange and String are all under the IndexedSeq

    /**
     * 2- xs flatMap f is equivalent to (xs map f).flatten
     */

    Vector(1,2,3).flatMap(num => Vector(num + 1))

    // same as:

    Vector(1,2,3).map(num => Vector(num + 1)).flatten

    /**
     * For expression comes in the following syntax:
     *
     * for (s) yield e, where s is a sequence of generators and filters and e is an expression whose value is returned
     *
     * A generator is in the form p <- e where p is the pattern and e is the expression whose value is a collection
     *
     * A filter is in the form of: if f where f is a boolean expression
     *
     * If there are several generators, the last one vary faster than the first.
     *
     * Instead of (), {} can be used and then the sequence of generators can be used without semicolon on separate lines
     */

    /**
     * operations ending with a column are such as their right side is the receiver of the call while their left side is the
     * parameter passed... as opposed to all other infix operators, whose receiver is the left side and the operand is the right side..
     *
     * usually infix operators are seen as method calls to their left operands, while operators that ends with (:)
     * are seen as method calls to their right hand side operands
     *
     * This is a very intuitive sort of syntactic sugar to let the prepend functions that add items to the start of the
     * list look as the item comes first before the sequence
     *
     * example:
     *      1 +: Seq[Empty]   will be interpreted by the compiler as Seq.Empty +: 1 which prepends the element 1 to the list (Prepend)
     *
     *      Seq[Empty].:+ 1, will append 1 at the end of the Seq (Append)
     *
     *      1::2::Nil will be interpreted by the compiler as Nil.::.(2).::(1)
     */

    /**
     * Principal operation that can be done with every type of collections:
     *      LinearSe    ==> Head and tail
     *      IndexedSeq  ==> Index Of a Set is Co
     *      Set         ==> Contains
     *
     * Sets are unordered collections, i.e. added items don't appear in the set with the order of their addition to the set, while with a list,
     * the items appear in the list with the exact order they've been added with.
     *
     * Databases are more like Sets of rows rather than List of rows. order usually doesn't matter and records are unique in some way
     */

    /**
     * For expressions are mapped to map, flatMap and filter higher order functions
     *
     * Scala compiler, compiles for expressions into map, flatMap and a lazy variant of filter. The lazy variant just remembers the filter
     * instead of creating an intermediate data structure that would contains a filtered version of the initial collection
     *
     * That means that you can just define map, flatMap and filter for your own custom types, with which you want to use for Expressions
     */

    /**
     * for expression representing a map
     */
    def map[T,U](xs: List[T], f: T => U): List[U] = {
        for (x <- xs) yield f(x)
    }

    /**
     * for expression representing a flatMap
     */

    def flatMap[T,U](xs: List[T], f: T => Iterable[U]): List[U] = {
        for (x <- xs ; y <- f(x)) yield y
    }

    def filter[T](xs: List[T], p: T => Boolean): List[T] = {
        for (x <- xs if p(x)) yield x
    }

    /**
     * mkString on any collection, will print all elements of a collection with a separator in between.
     */
    Vector(1,2,3,4).mkString("+") //would print 1+2+3+4

}
