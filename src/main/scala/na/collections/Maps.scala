package na.collections

/**
 * Maps are unique in scala in the sense that they are Iterable and Functions at the same time.
 * They extend the function type key => value. They can be used anywhere functions can
 *
 */
class Maps {

    val capitalOfCountries = Map ("US" -> "Washington", "Italia" -> "Roma")

    //no such element exception if key doesn't exist
    capitalOfCountries("US") // gives u washington and it looks like a function call cos Maps are also functions


    // maps are partial functions, in the sense that they can lead to an element no found when the key isn't there..
    // that is the when the function is not defined for a specific key. withDefaultValue, turns a map into a total function.

    val capitalOfCountries2 = Map ("US" -> "Washington", "Italia" -> "Roma") withDefaultValue "unknown"

    capitalOfCountries2("") //would return "unknown"

    /**
     * Map can be passed as argument to the map function, as Maps are partial functions themselves.
     *
     */

    val charCode = Map('T' -> 1, 'E' -> 2, 'X' -> 3, 'T' -> 4)

    "TEXT" map charCode

}
