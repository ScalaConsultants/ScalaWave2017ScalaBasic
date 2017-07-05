package io.scalac.scala.basic.dataStructures.red

import org.scalatest.{MustMatchers, WordSpec}

class MapSpec extends WordSpec with MustMatchers {

  "Data.Map" should {

    val sampleMap = Map("Thomas" -> 38,
      "Mark" -> 12,
      "John" -> 47,
      "Hanna" -> 15,
      "Gwen" -> 22)

    "return None if key is missing" in {
      sampleMap.get(???) mustBe None
    }

    "return appropriate value with a direct reference to key" in {
      sampleMap(???) mustBe 15
    }

    "increase all values by one" in {
      val modifiedMap: Map[String, Int] = ???
      modifiedMap("Mark") mustBe 13
    }

    "change all keys to lower case and subtract one from values" in {
      val modifiedMap: Map[String, Int] = ???
      // val modifiedMap = ???
      modifiedMap("john") mustBe 46
    }

  }
}

