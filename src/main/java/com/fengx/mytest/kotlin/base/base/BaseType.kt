package com.fengx.mytest.kotlin.base.base

val one = 1 // Int
val threeBillion = 3000000000 // Long
val oneLong = 1L // Long
val oneByte: Byte = 1

val pi = 3.14 // Double
// val one: Double = 1 // 错误：类型不匹配
val oneDouble = 1.0 // Double

val e = 2.7182818284 // Double
val eFloat = 2.7182818284f // Float，实际值为 2.7182817


//可以使用下划线使数字常量更易读：

val oneMillion = 100_0000
val creditCardNumber = 1234_5678_9012_3456L
val socialSecurityNumber = 999_99_9999L
val hexBytes = 0xFF_EC_DE_5E
val bytes = 0b11010010_01101001_10010100_10010010


//shl(bits) – 有符号左移
//shr(bits) – 有符号右移
//ushr(bits) – 无符号右移
//and(bits) – 位与
//or(bits) – 位或
//xor(bits) – 位异或
//inv() – 位非

//UByte: 无符号 8 比特整数，范围是 0 到 255
//UShort: 无符号 16 比特整数，范围是 0 到 65535
//UInt: 无符号 32 比特整数，范围是 0 到 2^32 - 1
//ULong: 无符号 64 比特整数，范围是 0 到 2^64 - 1

fun main() {
    println(e)
    println(eFloat)


    // 与一些其他语言不同，Kotlin 中的数字没有隐式拓宽转换。 例如，具有 Double 参数的函数只能对 Double 值调用，而不能对 Float、 Int 或者其他数字值调用。
    fun printDouble(d: Double) { print(d) }
    val i = 1
    val d = 1.0
    val f = 1.0f
    printDouble(d)
    println()
//    printDouble(i) // 错误：类型不匹配
//    printDouble(f) // 错误：类型不匹配

    println(1.toDouble())

    val a:UShort = 1u
    println(a)

//    字符串模板
    val s = "abc"
    println("$s.length is ${s.length}") // 输出“abc.length is 3”

    // 数组
    val asc = Array(5) { e -> (e * e).toString() }
    asc.forEach { println(it) }

    val x: IntArray = intArrayOf(1, 2, 3)
    x[0] = x[1] + x[2]
// 大小为 5、值为 [0, 0, 0, 0, 0] 的整型数组
//    val arr = IntArray(5)
//
//// 例如：用常量初始化数组中的值
//// 大小为 5、值为 [42, 42, 42, 42, 42] 的整型数组
//    val arr = IntArray(5) { 42 }
//
//// 例如：使用 lambda 表达式初始化数组中的值
//// 大小为 5、值为 [0, 1, 2, 3, 4] 的整型数组（值初始化为其索引值）
//    var arr = IntArray(5) { it * 1 }
}