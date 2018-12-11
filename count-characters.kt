// 文字列から指定の文字を数え上げる
fun main(args: Array<String>) {
    val a = "12000405060".count('0'::equals)
    println(a) // => 6
}
