package com.fengx.mytest.kotlin.base

/**
 * 密封类实践
 */
fun main() {
    val human = Human.Man("etst", 18, 111, 24)
    handleHuman(human)
}

sealed class Human(val name: String, val age: Int) {
    class Man(name: String, age: Int, val a1: Int, val a2: Int) : Human(name, age)
    class Woman(name: String, age: Int, val a3: Int, val a4: Int) : Human(name, age)
}

//处理
fun handleHuman(human: Human) {
    when (human) {
        is Human.Man -> {
            //type 为man的处理
            println("test ${human.a1.toString() + human.a2.toString()}")
        }
        is Human.Woman -> {
            //type 为woman的处理
            println("test ${human.a3.toString() + human.a3.toString()}")
        }
    }
}