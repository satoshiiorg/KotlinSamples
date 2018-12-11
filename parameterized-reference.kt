// メソッド参照に引数を渡す

// これは関数ではなく結果を返す
// println((Foo::use)(Foo(), Bar())) => 
//operator fun <T, R, P> (T.(P) -> R).invoke(receiver: T, param: P): R {
//    return this(receiver, param)
//}

// もう一段抽象化して部分適用するための関数を返す = カリー化
//operator fun <T, R, P> (T.(P)->R).invoke(param: P): (T)->R {
    // param固定でreceiverを受け取る関数を返す
//    return {receiver -> this(receiver, param)}
//}

operator fun <T, R, P> (T.(P)->R).invoke(param: P): (T)->R = {receiver -> this(receiver, param)}

fun main(args: Array<String>) {
//    println((Foo::use)(Bar()))
//    Foo().let((Foo::use)(Bar())).let(::println)
    
    fun Int.isMultipleOf(base: Int) = this % base == 0
    fun Int.add(other: Int) = this + other
    val plus: Int.(Int)->Int = Int::plus
    
    //ラムダで書くならこう
    (1..10).filter{it.isMultipleOf(2)}
        .map{it.plus(1)}
        .let(::println)
    
    // 関数参照で
    (1..10).filter((Int::isMultipleOf)(2))
//        .map((Int::add)<Int, Int, Int>(1)) // 通るが
//        .map((Int::plus)<Int, Int, Int>(1)) // これは結局通らない
        // Int::plus だと型が曖昧なのでエラーになる
        // キャストもできない（キャスト以前にキャスト対象のオブジェクトが決定できないため）
        .map((Int::add)(1))
        // これはできる
        //.map(plus(1))
        .let(::println)
    
    // Main.kt:11:22: error: this syntax is reserved for future use; to call a reference, enclose it in parentheses: (foo::bar)(args)
    //    .map(Int::add(1))
    
    
        // Maps.mutable.of("Key1", 1, "Key2", 2, "Key3", 3, "Key4", 4)
        //     .keyValuesView()
        //     .collect((k, v) -> Tuples.pair(k, v * 2))
        //     .toMap();
    mapOf("Key1" to 1, "Key2" to 2, "Key3" to 3, "Key4" to 4).map{ (k, v) -> k to (v * 2) }.toMap().let(::println)
}

/*
class Foo {
    fun use(bar: Bar) = "use ${bar}"
}
class Bar {
    override fun toString() = "bar"
}
*/


