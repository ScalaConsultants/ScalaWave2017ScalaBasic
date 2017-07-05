package io.scalac.scala.basic.dataStructures

import scala.util.Random

object StreamDemo extends App {
  // don't worry too much about the syntax
  // for now you only have to notice that:
  // - next items are defined in terms of previous ones and
  // - that we have an infinite number of those
  val naturalNumbers: Stream[Int] = 1 #:: naturalNumbers.map(natural => natural + 1)
  println(s"Note that only one item in memory $naturalNumbers")
  println(s"... but if there's a need we can take more ${naturalNumbers.take(5).toList}")

  println(s"The first element of a collection in Scala is called Head ${naturalNumbers.head}")

  val rewindTheStreamBy5 = naturalNumbers.drop(5)
  val rewindTheStreamBy100 = naturalNumbers.drop(100)
  println(s"Since streams are immutable accessing the data doesn't damage it")
  println(s"Rewind by 5   ${rewindTheStreamBy5.take(5).toList}")
  println(s"Rewind by 100 ${rewindTheStreamBy100.take(5).toList}")

  val randomKeyPress: Stream[Char] = Random.nextPrintableChar() #:: randomKeyPress.map(_ => Random.nextPrintableChar())
  println(s"Streams are typed so you always know what to expect ${randomKeyPress.take(5).toList}")

  val withIndex = randomKeyPress.zipWithIndex.take(10)
  println(s"If needed you can number the elements in a stream ${withIndex.toList}")
}
