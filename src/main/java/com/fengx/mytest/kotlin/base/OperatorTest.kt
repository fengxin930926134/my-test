package com.fengx.mytest.kotlin.base

/**
 * 操作符重载
 * + plus
 * - minus
 */
fun main() {
    val money = Money(100)
    val money1 = Money(1200)
    val money2 = money + money1
    println(money2)
}

class Money(val value: Int){

    operator fun plus(money: Money): Money {
        val sum = value + money.value
        return Money(sum)
    }
}