package io.scalac.scala.basic.functionalOperators

object FoldDemo extends App {
  val dataSource: Seq[Int] = Seq(1,2,3,4,5,6)

  //Note: the examples below are often implemented in Scala already, often using fold
  val countItemsFrom0: Int = dataSource.foldLeft(0)((acc, item) => {
    println(s"Inspecting item $item. Current accumulator is $acc")
    acc + 1
  })
  println(s"(Naive) Count of items gave us $countItemsFrom0")

  val sumItemsStartingWithMinus1: Int = dataSource.foldLeft(-1)((acc, item) => {
    println(s"Inspecting item $item. Current accumulator for sum is $acc")
    acc + item
  })
  println(s"(Naive) Sum of items gave us $sumItemsStartingWithMinus1")

  val concatenateItems: String = dataSource
    .map(_.toString)
    .foldLeft("Items:")((acc, item) => {
      acc + item
    })
  println(s"(Naive) concat of items gave us $concatenateItems")

}
