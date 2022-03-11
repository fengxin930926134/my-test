package com.fengx.mytest.kotlin.base.base

// 如果主构造函数没有任何注解或者可见性修饰符，可以省略这个 constructor 关键字。
open class Person private constructor(
        val firstName: String,
        val lastName: String,
        var age: Int, // 尾部逗号
) { /*...*/ }

//class zPerson: Person("1", "1", 3) {
//}

class InitOrderDemo(name: String?) {
    val firstProperty = "First property: $name".also {
        println(it)
    }

    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name?.length}".also(::println)

    init {
        println("Second initializer block that prints ${name?.length}")
    }

    // 类也可以声明前缀有 constructor的次构造函数：
    constructor(name: String?, text: Any) : this(name) {
        // 如果类有一个主构造函数，每个次构造函数需要用this委托给主构造函数⬆
        println("构造函数2号$text")
    }

//    如果派生类没有主构造函数，那么每个次构造函数必须使用super 关键字初始化其基类型，或委托给另一个做到这点的构造函数。 请注意，在这种情况下，不同的次构造函数可以调用基类型的不同的构造函数：
//    class MyView : View {
//        constructor(ctx: Context) : super(ctx)
//
//        constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs)
//    }
}

//类以及其中的某些或全部成员可以声明为 abstract。 抽象成员在本类中可以不用实现。 并不需要用 open 标注抽象类或者函数。
//
//abstract class Polygon {
//    abstract fun draw()
//}
//
//class Rectangle : Polygon() {
//    override fun draw() {
//        // draw the rectangle
//    }
//}

//Kotlin 对于可覆盖的成员以及覆盖后的成员需要显式修饰符：
//
//open class Shape {
//    open fun draw() { /*……*/ }
//    fun fill() { /*……*/ }
//}
//
//class Circle() : Shape() {
//    override fun draw() { /*……*/ }
//}

// 从哪个超类型继承的实现，请使用由尖括号中超类型名限定的 super ，如 super<Base>：
//class Square() : Rectangle(), Polygon {
//    // 编译器要求覆盖 draw()：
//    override fun draw() {
//        super<Rectangle>.draw() // 调用 Rectangle.draw()
//        super<Polygon>.draw() // 调用 Polygon.draw()
//    }
//}

class Rectangle(val width: Int, val height: Int) {
    var text: String = "ahahah"

    var area: Int = 0
        private set
    var value2: String = ""
        set(value) {
            area = value.toInt()
            field = value
        }
        get() = this.width.toString()

    lateinit var hhh:String
//    要检测一个 lateinit var 是否已经初始化过，请在该属性的引用上使用 .isInitialized：
//
//    if (foo::bar.isInitialized) {
//        println(foo.bar)
//    }
    fun getHHH(): String = if (::hhh.isInitialized) hhh else "123"
}

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

fun main() {
//    InitOrderDemo("哈哈哈")
//    InitOrderDemo(null)
    InitOrderDemo("11", 1)

    val rectangle = Rectangle(1, 2)
    println(rectangle.area)
    rectangle.value2 = "123"
    println(rectangle.area)
    println(rectangle.getHHH())

    println(rectangle.text)

    val valueOf = Color.valueOf("RED")
    println(valueOf.rgb)
}