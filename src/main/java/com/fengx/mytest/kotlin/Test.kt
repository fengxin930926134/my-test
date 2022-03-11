package com.fengx.mytest.kotlin

import java.io.File

//sampleStart
fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // `obj` 在该条件分支内自动转换成 `String`
        return obj.length
    }
    // 在离开类型检测分支后，`obj` 仍然是 `Any` 类型
    println()
    return null
}
//sampleEnd

fun main() {

    val files = File("Test").listFiles()

    println(files?.size ?: "123")

    files?.let {
        println("adwad")
    } ?: print("\"123dawd\"")


    var myTurtle = Turtle()
    with(myTurtle) { // 画一个 100 像素的正方形
        penDown()
        for (i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }
    myTurtle.penDown()
    for (i in 1..4) {
        myTurtle.forward(100.0)
        myTurtle.turn(90.0)
    }
    myTurtle.penUp()


    var myTurtle2 = Turtle()

    myTurtle2 = myTurtle.also { myTurtle = myTurtle2 }
}

class Turtle {
    fun penDown() {}
    fun penUp() {}
    fun turn(degrees: Double) {}
    fun forward(pixels: Double) {}
}
