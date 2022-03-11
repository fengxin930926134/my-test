package com.fengx.mytest.kotlin.base.base;

/**
 * 委托
 * 也是代理模式
 */
interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
}

class Derived(b: Base) : Base by b

fun main() {
    val b = BaseImpl(10)
    val c = BaseImpl(11)
    Derived(c).print()
}
