package io.scalac.scala.basic.dataStructures.green

import org.scalatest.{MustMatchers, WordSpec}

class MapSpec extends WordSpec with MustMatchers {

  "Data.Map" should {

    val sampleMap = Map("Thomas" -> 38,
      "Mark" -> 12,
      "John" -> 47,
      "Hanna" -> 15,
      "Gwen" -> 22)

    "return None if key is missing" in {
      sampleMap.get("Joshua") mustBe None
    }

    "return appropriate value with direct reference to key" in {
      sampleMap("Mark") mustBe 12
    }

    "increase all values by one" in {
      val modifiedMap = sampleMap.mapValues(value => value + 1)
      modifiedMap("Mark") mustBe 13
    }

    "change all keys to lower case and subtract one from values" in {
      val modifiedMap = sampleMap.map(pair => pair._1.toLowerCase -> (pair._2 - 1))
      // val modifiedMap = sampleMap.map{ case (key, value) => key.toLowerCase() -> (value -1)}
      modifiedMap("john") mustBe 46
    }

  }
}
