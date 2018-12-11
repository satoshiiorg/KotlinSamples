// 公開APIを変えずにコンストラクタをトップレベル関数にリファクタリングする

// Kotlinのコンストラクタ呼び出しは形式上はパッケージレベル関数の呼び出しと変わらないので
// コーディング規約的な難点(UpperCamelかLowerCamelか)だけ無視すれば仕様上の問題はない？

fun main(args: Array<String>) {
    val foo = Foo("test")
    println(foo.bar)
}

// 古い定義
/*
class Foo public constructor(val bar: String) {
}
*/

/*
// 諸事情によりこうなったとする
class Foo private constructor(val bar: String) {
    companion object {
        fun create(bar: String) = Foo(bar)
    }
}
// これはコンパニオンのFoo()の呼び出しが曖昧になるのでエラー
fun Foo(bar: String) = Foo.create(bar)
*/

/*
// こうしてみる
class Foo private constructor(val bar: String, dummy: String) {
    companion object {
        fun create(bar: String) = Foo(bar, "")
    }
}
// 通る
fun Foo(bar: String) = Foo.create(bar)
*/
// 単にパッケージプライベートにすればOK?

//この方が普通か
//ただし、これができるのは元のクラスがfinalの場合だけ
//none-finalの場合はFooが継承されていることを考慮する必要がある
//逆に元がfinalなら同名のinterfaceとパッケージレベル関数を定義すれば公開APIは保てる？
interface Foo {
    val bar : String
}
private class FooImpl(override val bar : String) : Foo {
    companion object {
        fun create(bar : String) = FooImpl(bar)
    }
}
fun Foo(bar : String) : Foo = FooImpl.create(bar)

