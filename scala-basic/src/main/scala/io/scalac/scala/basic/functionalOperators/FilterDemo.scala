package io.scalac.scala.basic.functionalOperators

object FilterDemo extends App{
  val dataSource: Seq[Int] = Seq(1, 2, 3, 4, 5, 6)

  val keepAll = dataSource.filter(elem => true)
  val dropAll = dataSource.filter(elem => false)
  println(s"Using fixed values we can keep $keepAll or drop $dropAll all items")

  val keepEvenNumbers: Seq[Int] = dataSource.filter(_ % 2 == 0)
  println(s"Filter + custom functions make it really easy to remove items that we aren't interested in $keepEvenNumbers")

  val chainedOperators: Seq[String] = dataSource.map(_ - 3).filter(_ < 0).map(_.toString)
  println(s"Real power comes from chaining operators together $chainedOperators")
}
