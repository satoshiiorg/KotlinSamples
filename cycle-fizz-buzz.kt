/*
fun <T: Any> cycle(vararg xs: T): Sequence<T> {
    var i = 0
    return generateSequence { xs[i++ % xs.size] }
}
*/

fun <T: Any> List<T>.cycle(): Sequence<T> {
    var i = 0
    return generateSequence { this[i++ % this.size] }
}

fun main(args: Array<String>) {
//    val fizz = generateSequence(listOf(null, null, "Fizz")){}
//    val fizz = cycle("", "", "Fizz")
//    val buzz = cycle("", "", "", "", "Buzz")
    
    val fizz = listOf("", "", "Fizz").cycle()
    val buzz = listOf("", "", "", "", "Buzz").cycle()
    
    /*
    (1..100).asSequence()
            .zip(fizz.zip(buzz))
            .map{(i, fb) -> (fb.first + fb.second).let{if(it.isEmpty()) i.toString() else it}}
            .forEach(::println)
    */
    
    /*
    (1..100).asSequence()
            .zip(fizz.zip(buzz)){i, fb -> Triple(i, fb.first, fb.second)}
            .map{(i, f, b) -> (f + b).let{if(it.isEmpty()) i.toString() else it}}
            .forEach(::println)
    */
    fizz.zip(buzz)
        .take(50)
        .withIndex()
        .map{iv -> (iv.value.first + iv.value.second).let{if(it.isEmpty()) (iv.index + 1).toString() else it}}
        .forEach(::println)
}

