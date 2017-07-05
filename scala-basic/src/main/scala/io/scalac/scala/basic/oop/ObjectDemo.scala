package io.scalac.scala.basic.oop

object ObjectDemo extends App {
  val numberPi = 3.14

  object NestedObject {
    def sayHello() = {
      println(s"Hi!")
    }
  }
  NestedObject.sayHello()
}
