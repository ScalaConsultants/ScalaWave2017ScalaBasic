package io.scalac.scala.basic.dataStructures.red

import org.scalatest._

class SeqSpec extends WordSpec with MustMatchers {

  def combineIntoSeq(item1: Int, item2: Int, item3: Int): Seq[Int] = ???

  def paginateSequence(pageSize: Int, pageNumber: Int, data: Seq[Int]): Seq[Int] = ???

  def removeDuplicates(data: Seq[Int]): Seq[Int] = ??? // IntelliJ will help you with the API

  "Data.Seq" should {

    "allow building new Sequences" in {
      combineIntoSeq(1, 2, 3) mustBe Seq(1,2,3)
      combineIntoSeq(100, 101, 99) mustBe Seq(100,101,99)
      combineIntoSeq(0, 1, 0) mustBe Seq(0,1,0)
    }

    "allow dropping and taking elements" in {
      val seq = Seq(1,2,3,4,5,6,7,8,9,0)
      paginateSequence(pageSize = 2, pageNumber = 0, seq) mustBe Seq(1,2)
      paginateSequence(pageSize = 3, pageNumber = 0, seq) mustBe Seq(1,2,3)
      paginateSequence(pageSize = 2, pageNumber = 1, seq) mustBe Seq(3,4)
      paginateSequence(pageSize = 2, pageNumber = 2, seq) mustBe Seq(5,6)
    }

    "have a reach API" in {
      removeDuplicates(Seq(1,2,3,4)) mustBe Seq(1,2,3,4)
      removeDuplicates(Seq(1,1,1,1)) mustBe Seq(1)
      removeDuplicates(Seq()) mustBe Seq.empty
    }
  }
}
