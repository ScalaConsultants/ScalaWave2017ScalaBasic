package io.scalac.scala.basic.oop

object Tester {
  def test() = {
    println(AccessModifiersDemo.NestedObject.visibleFromTheOutside)
    //    println(AccessModifiersDemo.NestedObject.hiddenFromOutsideAccess) //won't compile
    //    println(AccessModifiersDemo.NestedObject.visibleInAccessModifierDemo)
  }
}

object AccessModifiersDemo extends App {
  object NestedObject {
    val visibleFromTheOutside = 1
    private val hiddenFromOutsideAccess = 2
    private[AccessModifiersDemo] val visibleInAccessModifierDemo = 3
  }
  Tester.test()
  println(s"Here we can access both: ${NestedObject.visibleFromTheOutside + NestedObject.visibleInAccessModifierDemo}")


  case class Modifiers(val readonly: Int, var mutable: Int, default: Int)
  val mod = Modifiers(1,2,3)
//  mod.readonly = 10
  mod.mutable = 20
//  mod.default = 30
  println(s"By default prefer the immutable members, but others are also possible: $mod")
}
