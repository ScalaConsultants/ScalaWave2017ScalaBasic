package io.scalac.scala.basic.dataStructures.green

import org.scalatest._

class SetSpec extends WordSpec with MustMatchers {

  def combineSets(set1: Set[Int], set2: Set[Int]): Set[Int] =
    set1 ++ set2

  def setFromList(sequence: Seq[Int]): Set[Int] =
    sequence.toSet

  def setFromMapValues(map: Map[Int, String]): Set[String] =
    map.values.toSet

  "Data.Set" should {
    "allow combining sets" in {
      val combinedSets1 = combineSets(Set(-1,0,1,2,3), Set(1,2,3,4,5))
      combinedSets1 mustBe Set(-1,0,1,2,3,4,5)
      val combinedSets2 = combineSets(Set(3,2,1,2,3), Set(1,2,3))
      combinedSets2 mustBe Set(1,2,3)
    }

    "be created from other structures" in {
      setFromList(Seq.empty) mustBe Set.empty
      setFromList(Seq(1,2,3)) mustBe Set(1,2,3)
      setFromList(Seq(1,1,2,2)) mustBe Set(1,2)

      setFromMapValues(Map(1 -> "foo", 2 -> "bar", 3 -> "baz")) mustBe Set("foo", "bar", "baz")
      setFromMapValues(Map(1 -> "aaa", 2 -> "bbb", 3 -> "aaa")) mustBe Set("aaa", "bbb")
    }
  }
}
