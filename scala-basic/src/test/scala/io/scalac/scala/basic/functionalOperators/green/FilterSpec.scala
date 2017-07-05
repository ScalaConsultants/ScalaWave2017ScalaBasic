package io.scalac.scala.basic.functionalOperators.green

import org.scalatest.{MustMatchers, WordSpec}

class FilterSpec extends WordSpec with MustMatchers {

  // briefly introduce case classes first?
  case class Message(data: String, important: Boolean)

  val forbiddenWords = Seq("Chicken", "Table", "Ruby", "Like")

  def importantOnly(messages: Seq[Message]): Seq[Message] = messages.filter(_.important)

  def removeForbiddenWords(words: Seq[String]): Seq[String] = words.filterNot(forbiddenWords.contains)

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
