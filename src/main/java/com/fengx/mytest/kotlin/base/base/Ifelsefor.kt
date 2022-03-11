package com.fengx.mytest.kotlin.base.base

import java.lang.RuntimeException

// 作为表达式
val max = if (1 > 2) 1 else 2

//if 表达式的分支可以是代码块，这种情况最后的表达式作为该块的值：
//val max = if (a > b) {
//    print("Choose a")
//    a
//} else {
//    print("Choose b")
//    b
//}

fun fun1(x: Int) {
    // 可以用任意表达式（而不只是常量）作为分支条件
    when (x) {
        "1".toInt() -> print("x == 1")
        2 -> print("x == 2")
        else -> {
            print("x is neither 1 nor 2")
        }
    }
}

//enum class Color {
//    RED, GREEN, BLUE
//}
//
//when (getColor()) {
//    Color.RED -> println("red")
//    Color.GREEN -> println("green")
//    Color.BLUE -> println("blue")
//    // 'else' 不需要， 因为枚举类把所有类型都展示了
//}

// 若要为多个情况定义公共行为，请将它们的条件在一行中与逗号组合：
fun fun2(x: Int): Unit {
    when (x) {
        0, 1 -> print("x == 0 or x == 1")
        else -> print("otherwise")
    }
}

//还可以检测一个值在（in）或者不在（!in）一个区间或者集合中：
//
//when (x) {
//    in 1..10 -> print("x is in the range")
//    in validNumbers -> print("x is valid")
//    !in 10..20 -> print("x is outside the range")
//    else -> print("none of the above")
//}

//如需在数字区间上迭代，请使用区间表达式:
fun fun3() {
//sampleStart
    for (i in 1..3) {
        print(i)
    }
    for (i in 6 downTo 0 step 2) {
        print(i)
    }
//sampleEnd
}

// 如果你想要通过索引遍历一个数组或者一个 list，你可以这么做：
fun fun4() {
    val arr = arrayOf("1", 1, 1.0)
    println()
    for (i in arr.indices) {
        print("${arr[i]}  ")
    }
    println()
    for ((index, item) in arr.withIndex()) {
        if (index == 1) {
            // 在循环中 Kotlin 支持传统的 break 与 continue 操作符
            continue
        }
        print("index = $index, item = $item ")
    }
    println()
}

//while (x > 0) {
//    x--
//}
//
//do {
//    val y = retrieveData()
//} while (y != null) // y 在此处可见

fun main() {
    println(max)
    fun1(1)
    println()
    fun2(1)
    fun3()
    fun4()

    null ?: throw RuntimeException()
}