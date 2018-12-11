// typealiasにComparable生やしてソートできる？
typealias MyString = String
fun MyString.compareTo(other: MyString) = length.compareTo(other.length)

fun main(args: Array<String>) {
    val m0: MyString = "AA"
    val m1: MyString = "B"
    val m2: MyString = "CCCC"
    val m3: MyString = "DDD"
    // String#compareTo で並ぶ
    listOf(m0,m1,m2,m3).sorted().forEach(::println)
    // もちろんこれならできる
//    listOf(m0,m1,m2,m3).sortedBy{it.length}.forEach(::println)
//    listOf(m0,m1,m2,m3).sortedBy(String::length).forEach(::println)
    val c = Comparator<String>{a, b -> a.length - b.length}
//    listOf("a","bbb","cc").sortedWith{(o1, o2) -> o2.length - o1.length}.forEach(::println)
//    listOf(m0,m1,m2,m3).sortedWith(MyString::compareTo).forEach(::println)
    // comparingInt
//    val arrayList = arrayListOf(1, 5, 2)
//    Collections.sort(arrayList, Comparator{ x, y -> y - x }).forEach(::println)

    val sa: (String)->String = {s -> s.toUpperCase()}
    val sa1: (String)->String = String::toUpperCase
    val sb: String.()->String = {toUpperCase()}
    val sb1: String.()->String = String::toUpperCase
    "test".sb().let(::println)
    "aaa".let(sa1).let(::println)
    "aaa".let(String::toUpperCase).let(::println)
//    val sc: String->String = {s -> s.toUpperCase()}
}
