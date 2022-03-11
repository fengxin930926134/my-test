package com.fengx.mytest.kotlin.base.mset

fun main() {
    // 不可变的
    val listOf = listOf("1", 1)
    val of = setOf("11", "222")
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1) // to 符号创建了一个短时存活的 Pair 对象，因此建议仅在性能不重要时才使用它。
    // 可变的
    val mutableListOf = mutableListOf("hahah", 124)
    val mutableMapOf = mutableMapOf<String, Any>()
    val mutableSetOf = mutableSetOf<String>()

    // 集合生成器
//    val buildList = buildList<Any> {
//        add("111")
//        add(123)
//        add('1')
//    }
//    val map = buildMap { // this is MutableMap<String, Int>, types of key and value are inferred from the `put()` calls below
//        put("a", 1)
//        put("b", 0)
//        put("c", 4)
//    }

    // 空集合
    val emptyList = emptyList<String>()

    // list 的初始化函数
    val list = List(3) { "it * 2" }
    println(list)

    // 具体类型构造函数
    val arrayList = ArrayList<String>()
    arrayList.add("哈哈哈")
    arrayList.add("哈哈哈")
    // 复制
    println(arrayList)
    // 集合复制函数，例如toList()、 toMutableList()、 toSet() 等等
    val toList = arrayList.toMutableSet()
    toList.add("123123123")
    println(toList)
    println(arrayList)

    // 调用其他集合的函数
    val numbers = listOf("one", "two", "three", "four")
    println(numbers.filter { it.length > 3 })
    println(numbers.map { it + "3" })
    println(numbers.mapIndexed { idx, value -> value + idx })
    println(numbers.associateWith { it.length }) // 关联生成map

}