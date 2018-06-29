package na.implicits

/**
 * https://docs.scala-lang.org/tutorials/FAQ/finding-implicits.html
 *
 * Implicits in Scala refers to either a value that can be passed “automatically”, so to speak, or a conversion from one
 * type to another that is made automatically.
 *
 * Types pf Implicits:
 *      1- Implicit Conversion.
 *              If one calls a method m on an object o of a class C, and that class does not support method m, then
 *              compiler looks for an implicit conversion from C to something that does support m
 *
 *              Example: "abc".map(_.toInt)
 *
 *      2- Implicit Parameters
 *              The other kind of implicit is the implicit parameter. These are passed to method calls like any other parameter,
 *              but the compiler tries to fill them in automatically. If it can’t, it will complain. One can pass these parameters explicitly,
 *
 *          Example:
 *              def foo[T](t: T)(implicit integral: Integral[T]): Unit = {
 *                  println(integral)
 *              }
 *
 *      3- Implicit conversions as implicit parameters.
 *
 *         There’s one situation where an implicit is both an implicit conversion and an implicit parameter.
 *
 *         Example:
 *              def getIndex[T, CC](seq: CC, value: T)(implicit conv: CC => Seq[T]) = seq.indexOf(value)

                getIndex("abc", 'a')
 *
 *      4- Context Bounds
 *           Scalaz is a library that makes heavy use of this pattern.
 */
class Implicits {

    /**
     * implicit that is both an implicit conversion and implicit parameter.
     * The method getIndex can receive any object, as long as there is an implicit conversion available from its class to Seq[T].
     * Because of that, a String can be passed to getIndex, and it will work.
     * Behind the scenes, the compiler changes seq.IndexOf(value) to convert(seq).indexOf(value).
     */
    def getIndex[T, CC](seq: CC, value: T)(implicit convert: CC => Seq[T]) = seq.indexOf(value)

    getIndex("abc", 'a')


    def sum[T](list: List[T])(implicit integral: Integral[T]): T = {
        import integral._   // get the implicits in question into scope
        list.foldLeft(integral.zero)(_ + _)
    }

    /**
     * The syntactic sugar version for it, called a context bound, which is made less useful by the need to refer to the implicit.
     * A straight conversion of that method looks like this:
     * */
    /*def sum[T : Integral](list: List[T]): T = {
        val integral = implicitly[Integral[T]]
        import integral._   // get the implicits in question into scope
        list.foldLeft(integral.zero)(_ + _)
    }*/

}

