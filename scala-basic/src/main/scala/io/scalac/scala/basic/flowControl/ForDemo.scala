package io.scalac.scala.basic.flowControl

object ForDemo extends App {
  val doubleTheValues = for {
    item <- Seq(1,2,3,4,5,6)
  } yield item * 2
  println(s"Scala compiler decides how to desugar the expressions so you can just focus on the logic $doubleTheValues")


  val doubleEvenNumbers = for {
    item <- Seq(1,2,3,4,5,6)
    if item % 2 == 0
  } yield item * 2
  println(s"We can also filter while going through the collection $doubleEvenNumbers")


  val combineSequences = for {
    itemA <- Seq(1,2,3)
    itemB <- Seq(6,7,8)
  } yield itemA + itemB
  println(s"We can use for to easily combine many sources by traversing them one by one $combineSequences")


  val combineSequencesWithUpperBound = for {
    itemA <- Seq(1,2,3)
    itemB <- Seq(6,7,8)
    sum = itemA + itemB
    if sum < 10
  } yield sum
  println(s"In chains we can define variables to hold data, the same with for $combineSequencesWithUpperBound")


  val combineMapsByKeys = for {
    (keyA, valueA) <- Map("one" -> 1, "two" -> 2, "three" -> 3)
    (keyB, valueB) <- Map("two" -> 2, "three" -> 3, "four" -> 4)
    if keyA == keyB
  } yield (keyA, valueA + valueB)
  println(s"We can also operate on other structures $combineMapsByKeys")
}
