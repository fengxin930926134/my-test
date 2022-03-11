package com.fengx.mytest.kotlin.base.mset

fun main() {
    // 迭代器
    val numbers = mutableListOf("one", "two", "three", "four")
    val numbersIterator = numbers.iterator()
    while (numbersIterator.hasNext()) {
        print("${numbersIterator.next()}  ")
    }
    println()

    // for循环
    for (item in numbers) {
        print("$item  ")
    }
    println()

    // foreach
    numbers.forEach {
        print("$it  ")
    }
    println()

    // 双向迭代器
    val listIterator = numbers.listIterator()
    while (listIterator.hasNext()) listIterator.next()
    println("Iterating backwards:")
    while (listIterator.hasPrevious()) {
        print("Index: ${listIterator.previousIndex()} ")
        println(", value: ${listIterator.previous()}")
    }

    // 可变迭代器 MutableIterator扩展了Iterator 可以在迭代时从集合中删除元素
    val mutableIterator = numbers.iterator()
    if (listIterator.hasNext()) {
        mutableIterator.next()
        mutableIterator.remove()
    }
    println(numbers)
    // 除了删除元素， listIterator=MutableListIterator（根据集合类型决定） 还可以在迭代列表时插入和替换元素。
    val mutableListIterator = numbers.listIterator()
    mutableListIterator.next()
    mutableListIterator.add("two")
    mutableListIterator.next()
    mutableListIterator.set("three3")
    println(numbers)

}