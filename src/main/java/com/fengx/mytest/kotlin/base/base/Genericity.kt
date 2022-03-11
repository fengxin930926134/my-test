package com.fengx.mytest.kotlin.base.base

//声明处型变： 可以标注 Source 的类型参数 T 来确保它仅从 Source<T> 成员中返回（生产），并从不被消费。 为此请使用 out 修饰符：
interface Source<out T> {
    fun nextT(): T
}

class Test: Source<String> {
    override fun nextT(): String {
        return "这是一串字符串"
    }

}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // 这个没问题，因为 T 是一个 out-参数
    println(objects.nextT())
}

// 声明处型变 in, 它使得一个类型参数逆变，即只可以消费而不可以生产。逆变类型的一个很好的例子是 Comparable：
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

class Test2: Comparable<Number> {
    override fun compareTo(other: Number): Int {
        return other.toInt() - 1
    }

}

fun demo2(x: Comparable<Number>) {
    println(x.compareTo(1.0)) // 1.0 拥有类型 Double，它是 Number 的子类型
    // 因此，可以将 x 赋给类型为 Comparable <Double> 的变量
    val y: Comparable<Double> = x // OK！
    println(y.compareTo(1.0))
}

//默认的上界（如果没有声明）是 Any?。在尖括号中只能指定一个上界。 如果同一类型参数需要多个上界，需要一个单独的 where-子句：
fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
        where T : CharSequence,
              T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}
//所传递的类型必须同时满足 where 子句的所有条件。在上述示例中，类型 T 必须 既实现了 CharSequence 也实现了 Comparable

fun main() {
    demo(Test())

    demo2(Test2())
}