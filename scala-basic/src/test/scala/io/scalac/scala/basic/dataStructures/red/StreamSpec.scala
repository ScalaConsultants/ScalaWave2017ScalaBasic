package io.scalac.scala.basic.dataStructures.red

import org.scalatest.Matchers._
import org.scalatest._

import scala.util.Random

class StreamSpec extends WordSpec with MustMatchers {

  val randomDigits: Stream[Int] = 1 #:: randomDigits.map(_ => Random.nextInt(10))

  def takeUniqueUpTo(dataStream: Stream[Int], upTo: Int): Set[Int] = ???

  def buildStream(set: Set[Int], sequence: Seq[Int], map: Map[String, Int]): Stream[Int] = ???


  "Data.Stream" should {
    "allow access to small portions of stream" in {
      takeUniqueUpTo(randomDigits, 5).size should be <= 5
      takeUniqueUpTo(randomDigits, 15).size should be <= 10
      takeUniqueUpTo(randomDigits, 0).size mustBe 0
    }

    "be created from other collections" in {
      buildStream(Set(1), Seq(2,3,4), Map("five" -> 5)).take(5) mustBe Stream(1,2,3,4,5)
      buildStream(Set.empty, Seq(2,3,4), Map.empty).take(5) mustBe Stream(2,3,4)
      buildStream(Set(1,2,3,4), Seq.empty, Map("five" -> 5, "six" -> 6)).take(10) mustBe Stream(1,2,3,4,5,6)
    }

    "be created with helper functions" in {
      val infiniteConstantStream: Stream[Int] = ???
      infiniteConstantStream.drop(5).take(5) mustBe Stream(0,0,0,0,0)
      infiniteConstantStream.drop(12345).take(5) mustBe Stream(0,0,0,0,0)
    }
  }
}
