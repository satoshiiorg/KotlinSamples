import java.util.Optional

// OptionalとNullableの変換

fun <T> T?.toOptional(): Optional<T> = Optional.ofNullable(this)
fun <T> Optional<T>.toNullable(): T? = orElse(null)

fun main(args: Array<String>) {
    val optionalString: Optional<String> = Optional.of("optional")
    val optionalEmpty: Optional<String>  = Optional.empty()
    val nullableString: String? = "nullable"
    val nullableEmpty: String?  = null
    
    println(optionalString)
    println(optionalEmpty)
    println(nullableString)
    println(nullableEmpty)
    
    println(optionalString.toNullable())
    println(optionalEmpty.toNullable())
    println(nullableString.toOptional())
    println(nullableEmpty.toOptional())
    
    println(null.toOptional())
}
