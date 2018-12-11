//タプルに拡張関数を生やせば例の複数レシーバに対するメソッド呼び出しが書ける

fun Pair<String, String>.concat() = first + second
fun <T> Pair<T?, T?>.equals() = first == second
fun Any?.println() = println(this)

class Foo {
    fun f(bar: Bar) = "hello"
}
class Bar {
}
fun g(bar: Bar) = "hello"

//よく使うなら拡張関数を定義した方が便利
fun Bar.f(foo: Foo) = foo.f(this)

fun main(args: Array<String>) {
    val foo = Foo()
    val bar = Bar()
    foo.f(bar).let(::println)
    bar.let(foo::f).let(::println)
    bar.let(::g).let(::println)
    bar.f(foo).let(::println)
    
//    baz.foo(bar)
//    bar.let(baz::foo)

    Pair("aaa", "bbb").concat().let(::println)
    Pair(null, null).equals().let(::println)
    null.println()
    "hello".println()
    
    fun Pair<Int, Int>.add() = first + second
    1.to(2).add().println()
    
    //val foo: Foo
    //val bar: Bar
    //fun Foo.baz(bar: Bar)
    //をbarから呼び出す
    
    //○　自身を引数に取る関数を自身のメソッドとして呼び出す
    //foo(bar) bar.let(::foo)
    
    //○　letを使ってメソッドチェーンを継続する
    
    //レシーバと引数を入れ替える
    //thisを返して破壊的メソッドを連続させるようなメソッドチェーンではなく、
    //流れるようなインターフェースのように、AをBに、BをCに変換する、
    //というような処理を連鎖させている場合、戻りをいったん格納してprintnするのは面倒
    //なのでlet(::println)のようなイディオムが有用な場合はある
}



