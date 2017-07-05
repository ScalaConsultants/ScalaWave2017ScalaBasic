package io.scalac.scala.basic.functionalOperators

object MapDemo extends App {
  val dataSource: Seq[Int] = Seq(1,3,5,7,9,11)

  val transformedToText: Seq[String] = dataSource.map(number => number + "!")
  println(s"We transformed our data into text representation $transformedToText")
  val transformedToTextWithPrint: Seq[String] = dataSource.map(number => {
    println(s"Going to transform $number")
    number + "!"
  })
  println(s"We the last expression of our function, becomes the result of transformation $transformedToTextWithPrint")
  val transformedToTextShort: Seq[String] = dataSource.map(_ + "!")
  println(s"If the transformation function is short we can use _ to simplify it $transformedToTextShort")

  val chainedTransformations = dataSource.map(_ + 1).map(_.toString).map(_.length)
  println(s"Transformations can work in together by chaining $chainedTransformations")

  val dataSet: Set[Int] = dataSource.toSet
  val transformedToTextSet: Set[String] = dataSet.map(_ + "!")
  println(s"We can also transform other collections $transformedToTextSet")

  val transformedModuleSequence = dataSource.map(_ % 2)
  val transformedModuleSet = dataSet.map(_ % 2)
  println(s"Transformed collections keep the same traits they had before transformation $transformedModuleSequence != $transformedModuleSet")


  val dataMap = Map("one" -> 1, "two" -> 2, "three" -> 3)
  val transformedMapPairs = dataMap.map ( (pair) => {
    (pair._1 + "+1", pair._2 + 1)
  })
  println(s"Internally Map is set of pairs, so transforming means taking a pair and producing another one $transformedMapPairs")
  val transformedMapPairsPretty = dataMap.map {
    case (key, value) => (key + "+1", value + 1)
  }
  println(s"To simplify operations on pairs you can use this syntax: it transforms an element in case it's a pair $transformedMapPairs")

  val mapTransformedToSeq = dataMap.map(_._1)
  println(s"We can also transform between data types $mapTransformedToSeq")

}
