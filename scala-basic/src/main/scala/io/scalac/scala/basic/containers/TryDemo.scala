package io.scalac.scala.basic.containers

import scala.util._


object TryDemo extends App {
  def asANumber(numberString: String): Try[Double] = Try { numberString.toDouble }

  val callSafely = Try { "foo-bar-baz".toDouble }
  println(s"Although the code above throws, the flow was not distorted - a plain Try value is created")
  val calledSuccessfully = asANumber("123.000")
  val calledWithError = asANumber("one-two-three")

  calledSuccessfully match {
    case Success(result) =>
      println(s"The result is: $result")
    case Failure(error) =>
      println(s"The error is: $error")
  }

  calledWithError match {
    case Success(result) =>
      println(s"The result is: $result")
    case Failure(error) =>
      println(s"The error is: $error")
  }

  val withDefault = calledWithError.getOrElse(-1)
  println(s"Unwrapped result is $withDefault")

  val withDefaultPerCase = calledWithError.recover {
    case _:NumberFormatException => 0
  }
  println(s"Recovered from error with $withDefaultPerCase")

  val mappedValue = calledSuccessfully.map(_ * 2)
  println(s"Mapped value is $mappedValue")
  val mappedError = calledWithError.map(_ * 2)
  println(s"Mapped error $mappedError ... is still an error")

  val filteredResults = calledSuccessfully.filter(_ <= 100)
  println(s"Filter can turn Success into a Failure, but no the other way around $filteredResults")

  /// since we have map and filter defined, we can use Try with for
  val forResult = for {
    numberA <- asANumber("100")
    numberB <- asANumber("23")
  } yield numberA + numberB
  println(s"For comprehension result is $forResult")
}
