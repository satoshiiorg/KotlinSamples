
typealias Entry = Map.Entry<String, Int>

// fun <K, V, R> mergeBy(transform: (List<V>) -> R, vararg maps : Map<K, V>) = maps.toList().mergeBy(transform)
fun <K, V, R> List<Map<K, V>>.mergeBy(transform: (List<V>) -> R): Map<K, R> {
    return this.flatMap(Map<K, V>::entries)
                .groupBy(Map.Entry<K, V>::key, Map.Entry<K, V>::value)
                .mapValues{transform(it.value)}
}


// map + map で重複分は後勝ちになるのでこれがデフォルトのマージ実装ともいえる
// 標準API上書きできるっけ？
// 型固定で詳細化することはできるだろうがちょっと嫌というか
// 典型的な悪い演算子オーバーロードのような気がする
// 各種Numberって加法について閉じてるっけ？
operator fun <K> Map<K, Int>.plus(other: Map<K, Int>) = listOf(this, other).mergeBy(List<Int>::sum)
// operator はインタフェースじゃないから Pluseable::plus 的なことはできない
// Numberにもplusはない



fun main(args: Array<String>) {
    val map1 = mapOf("one" to 1, "two" to 2, "three" to 3)
    val map2 = mapOf("one" to 2, "four" to 4, "six" to 6)
    /*
    val map3 = listOf(map1.entries, map2.entries)
                    .flatten()
                    .groupBy(Entry::key, Entry::value)
                    .mapValues{it.value.sum()}
                    // 関数合成はないのでこういう感じで参照することはできない
//                    .mapValues(Map.Entry<String, List<Int>>::value::sum)
    */
    val maps = listOf(map1, map2)
    
    println(map1 + map2)
//    val map3 = mergeBy(List<Int>::sum, map1, map2)
    println(maps.mergeBy(List<Int>::sum))
    println(maps.mergeBy(List<Int>::average))
    println(maps.mergeBy(List<Int>::max))
    println(maps.mergeBy(List<Int>::min))
    println(maps.mergeBy(List<Int>::first))
    println(maps.mergeBy(List<Int>::last))
    // KFunction7なので適用できない
//    println(maps.mergeBy(List<Int>::joinToString))
    println(maps.mergeBy{it.joinToString(separator="")})
}
