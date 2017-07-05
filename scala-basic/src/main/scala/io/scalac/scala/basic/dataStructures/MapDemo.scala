package io.scalac.scala.basic.dataStructures

object MapDemo extends App {
  val map: Map[Int, String] = Map(0 -> "zero", 1 -> "one", 2 -> "two", 3 -> "three")
  println(s"Defined a Map $map")

  println(s"Extracting values from a Map is easy ${map.get(2)} ${map.contains(2)}")
  println(s"Extracting values from a Map is easy ${map.get(99)} ${map.contains(99)}")
  println(s"Extracting values from a Map is easy ${map.getOrElse(99, -1)} ${map.contains(99)}")

  val updatedMap = map + (4 -> "four")
  println(s"Created a new Map $updatedMap, but the old one is left intact $map")

  //easy and safe conversions using the toX methods
  val convSeq: Seq[Int] = Seq(1,2,3)
  val set: Set[Int] = convSeq.toSet
  val pairSeq = Seq(1->"one", 2->"two")
  val convMap: Map[Int, String] = pairSeq.toMap
}
