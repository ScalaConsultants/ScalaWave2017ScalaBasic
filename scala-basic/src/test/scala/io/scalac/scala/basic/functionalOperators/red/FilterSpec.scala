package io.scalac.scala.basic.functionalOperators.red

import org.scalatest.{MustMatchers, WordSpec}

class FilterSpec extends WordSpec with MustMatchers {

  case class Message(data: String, important: Boolean)

  val forbiddenWords = Seq("Chicken", "Table", "Ruby", "Like")

  def importantOnly(messages: Seq[Message]): Seq[Message] = ???

  def removeForbiddenWords(words: Seq[String]): Seq[String] = ???

  "FunctionalOperators.Filter" should {
    "get only important messages" in {
      val messages = Seq(
        Message("Hello there", important = false),
        Message("Top secret", important = true),
        Message("I like trains", important = false),
        Message("Scala is cool", important = true))

      val filtered = importantOnly(messages)
      filtered.map(_.data) must contain only ("Top secret", "Scala is cool")
    }

    "filter out forbidden words" in {
      val words = Seq("I", "Like", "My", "Lovely", "Chicken")

      val filtered = removeForbiddenWords(words)
      filtered must contain only ("I", "My", "Lovely")
    }
  }
}
