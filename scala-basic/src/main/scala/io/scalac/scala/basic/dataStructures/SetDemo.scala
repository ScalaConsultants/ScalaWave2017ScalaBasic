package io.scalac.scala.basic.dataStructures

object SetDemo extends App {
  val set: Set[Int] = Set(1,2,3,1,2,3)
  println(s"This Set has elements $set")

  val containsOne = Set(1,2,3).contains(1)
  println(s"This Set contains 1? $containsOne")

  val combined = Set(1,2) ++ Set(2,3)
  println(s"Combined Sets $combined")
}
