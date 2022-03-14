package com.fengx.mytest.kotlin.base.coroutine

// 序列:如果使用一些消耗 CPU 资源的阻塞代码计算数字（每次计算需要 100 毫秒）那么我们可以使用 Sequence 来表示数字：
//fun simple(): Sequence<Int> = sequence { // 序列构建器
//    for (i in 1..3) {
//        Thread.sleep(1000) // 假装我们正在计算
//        yield(i) // 产生下一个值
//    }
//}
//
//fun main() {
//    simple().forEach { value -> println(value) }
//}

//import kotlinx.coroutines.*
//
////sampleStart
//suspend fun simple(): List<Int> {
//    delay(1000) // 假装我们在这里做了一些异步的事情
//    return listOf(1, 2, 3)
//}
//
//fun main() = runBlocking<Unit> {
//    simple().forEach { value -> println(value) }
//}
////sampleEnd

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * 名为 flow 的 Flow 类型构建器函数。
 * flow { ... } 构建块中的代码可以挂起。
 * 函数 simple 不再标有 suspend 修饰符。
 * 流使用 emit 函数 发射 值。
 * 流使用 collect 函数 收集 值。
 */
//sampleStart 为了表示异步计算的值流（stream），我们可以使用 Flow 类型（正如同步计算值会使用 Sequence 类型）：
fun simple(): Flow<Int> = flow { // 流构建器
    for (i in 1..3) {
        delay(1000) // 假装我们在这里做了一些有用的事情
        emit(i) // 发送下一个值
    }
}

fun main() = runBlocking<Unit> {
    // 启动并发的协程以验证主线程并未阻塞
    launch {
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay(1000)
        }
    }
    // 收集这个流
    simple().collect { value -> println(value) }

    // 流取消,流采用与协程同样的协作取消
    withTimeoutOrNull(1250) { // 在 1250 毫秒后超时
        simple().collect { value -> println(value) }
    }

    // test
    (1..3).asFlow().filter { it -> it != 1 }.collect { value -> println(value) }
}
//sampleEnd
