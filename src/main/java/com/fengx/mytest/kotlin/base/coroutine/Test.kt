package com.fengx.mytest.kotlin.base.coroutine

import kotlinx.coroutines.*


//fun main() = runBlocking { // this: CoroutineScope
//    launch {
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello") // main coroutine continues while a previous one is delayed
//}

////sampleStart  suspend声明挂起函数
//fun main() = runBlocking { // this: CoroutineScope
//    launch { doWorld() }
//    println("Hello")
//}
//
//// this is your first suspending function
//suspend fun doWorld() {
//    delay(1000L)
//    println("World!")
//}
////sampleEnd

// 使用coroutineScope构建器声明自己的作用域。
suspend fun doWorld() = coroutineScope {  // this: CoroutineScope
    val launch = launch {
        delay(2000L)
        println("World 2")
    }
    // 显式等待其完成
    // launch.join()
    launch {
        delay(1000L)
        println("World 1")
    }
    println("Hello")
}

suspend fun cancelTest1() = coroutineScope {
    val job = launch {
        repeat(1000) { i ->
            delay(500L)
            println("job: I'm sleeping $i ...")
        }
    }
    delay(1300L) // 延迟一段时间
    println("main: I'm tired of waiting!")
//    job.cancel() // 取消该作业
    //job.join() // 等待作业执行结束
    job.cancelAndJoin()
    println("main: Now I can quit.")
}

/**
 * 协程的取消是 协作 的。一段协程代码必须协作才能被取消。如果协程正在执行计算任务，并且没有检查取消的话，那么它是不能被取消的
 */
suspend fun cancelTest2() = coroutineScope {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 10) { // 一个执行计算的循环，只是为了占用 CPU
            // 每秒打印消息两次
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // 等待一段时间
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // 取消一个作业并且等待它结束
    println("main: Now I can quit.")
}

/**
 * 有两种方法来使执行计算的代码可以被取消。第一种方法是定期调用挂起函数来检查取消。对于这种目的 yield 是一个好的选择。 另一种方法是显式的检查取消状态。
 */
suspend fun cancelTest3() = coroutineScope {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        // 可以被取消的计算循环
        while (isActive && i < 10) { // 一个执行计算的循环，只是为了占用 CPU
            // 每秒打印消息两次
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // 等待一段时间
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // 取消一个作业并且等待它结束
    println("main: Now I can quit.")
}

fun main() = runBlocking {
//    repeat(100_000) { // 启动大量的协程
//        doWorld()
//    }

//    cancelTest1()
//    cancelTest2()
//    cancelTest3()

    // 使用函数设定超时时间
//    withTimeout(1300L) {
//        repeat(1000) { i ->
//            println("I'm sleeping $i ...")
//            delay(500L)
//        }
//    }

    val result = withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
        "Done" // 在它运行得到结果之前取消它
    }
    println("Result is $result")
}
