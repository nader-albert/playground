package na.generics

import scala.reflect._

object Trials extends App {

    val test = new Test

    println("Array types ....... ")
    println(test.createArray(1,2))
    println(test.createArray("1","2"))

    println("check types ....... ")
    println(test.checkType(List))
    println(test.checkType(List[Int]))
    println(test.checkType(List[String]))
}

class Test {

    /**
     * A `ClassTag[T]` stores the erased class of a given type `T`, accessible via the `runtimeClass` field.
     *
     * ClassTags are a weaker special case of scala.reflect.api.TypeTags#TypeTags, in that they wrap only the runtime
     * class of a given type, whereas a TypeTag contains all static type information. That is, ClassTags are constructed
     * from knowing only the top-level class of a type, without necessarily knowing all of its argument types.
     *
     * This is particularly useful for instantiating `Array`s whose element types are unknown at compile time.
     */
    def createArray[A: ClassTag](seq: A*): Array[A] = Array[A](seq: _*)

    /**
     * ClassTag provides only the information needed to create types at runtime. If we ask for runtime class of List[Int],
     * we can see that information about Int that the list is parametrised with is lost i.e. they don’t care about type erasure.
     */
    def checkType[A <: AnyRef](a: A): Class[_] = {
        classTag[A].runtimeClass
    }

}