package com.fengx.mytest.kotlin.base.base
// 一个接口可以从其他接口派生，意味着既能提供基类型成员的实现也能声明新的函数与属性。很自然地，实现这样接口的类只需定义所缺少的实现：
interface Named {
    val name: String
}

interface Person1 : Named {
    val firstName: String
    val lastName: String

    override val name: String get() = "$firstName $lastName"
}

data class Employee(
        // 不必实现“name”
        override val firstName: String,
        override val lastName: String
) : Person1


//实现多个接口时，可能会遇到同一方法继承多个实现的问题：

interface A {
    fun foo() { print("A") }
    fun bar()
}

interface B {
    fun foo() { print("B") }
    fun bar() { print("bar") }
}

class C : A {
    override fun bar() { print("bar") }
}

class D : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super<B>.bar()
    }
}

//函数式（SAM）接口 只有一个抽象方法的接口称为函数式接口或 单一抽象方法（SAM）接口。函数式接口可以有多个非抽象成员，但只能有一个抽象成员。
//可以用 fun 修饰符在 Kotlin 中声明一个函数式接口。
//fun interface IntPredicate  {
//    fun accept(i: Int): Boolean
//}
//
//fun main() {
//    D().foo()
//    println()
//    // 创建一个类的实例
//    val isEven = object : IntPredicate {
//        override fun accept(i: Int): Boolean {
//            println(i)
//            return true
//        }
//
//    }
//
//    isEven.accept(222)
//
//    val intPredicate = IntPredicate { println(it);it % 2 == 0 }
//    intPredicate.accept(200)
//}