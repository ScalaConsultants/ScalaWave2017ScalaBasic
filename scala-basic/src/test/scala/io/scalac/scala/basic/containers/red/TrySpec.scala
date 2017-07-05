package io.scalac.scala.basic.containers.red

import org.scalatest.{MustMatchers, WordSpec}

import scala.util._

class TrySpec extends WordSpec with MustMatchers {

  case class ConfigurationManager(values: Map[String, Int]) {
    def withValueInserted(key: String, value: Int): ConfigurationManager = ???

    def fillWithConfigData(readFromFile: Seq[(String, String)]): ConfigurationManager = ???

    def fillWithConfigData(readFromFile: Seq[(String, String)], default: Int): ConfigurationManager = ???

    def get(key: String): Option[Int] = values.get(key)
  }

  "Containers.Try" should {
    "fill configuration manager with data" in {
      val manager = ConfigurationManager(Map.empty)
        .withValueInserted("db-port", 2345)
        .withValueInserted("log-port", 5678)
        .withValueInserted("kafka-port", 7890)

      manager.get("foo") mustBe None
      manager.get("db-port") mustBe Some(2345)
      manager.get("log-port") mustBe Some(5678)
      manager.get("kafka-port") mustBe Some(7890)
    }

    "fill configuration manager with raw data" in {
      val emptyManager = ConfigurationManager(Map.empty)

      //let's pretend we read it from the disk
      val dataFromFile = Seq(
        ("db-port", "2345"),
        ("log-port", "foo-bar"),
        ("kafka-port", "7890"))

      val manager = emptyManager.fillWithConfigData(dataFromFile)

      manager.get("foo") mustBe None
      manager.get("db-port") mustBe Some(2345)
      manager.get("log-port") mustBe None
      manager.get("kafka-port") mustBe Some(7890)
    }

    "fill configuration manager with raw data, using default for non-numbers" in {
      val emptyManager = ConfigurationManager(Map.empty)
      val defaultValue = 9000

      //let's pretend we read it from the disk
      val dataFromFile = Seq(
        ("db-port", "2345"),
        ("log-port", "foo-bar"),
        ("kafka-port", "7890"))

      val manager = emptyManager.fillWithConfigData(dataFromFile, defaultValue)

      manager.get("foo") mustBe None
      manager.get("db-port") mustBe Some(2345)
      manager.get("log-port") mustBe Some(defaultValue)
      manager.get("kafka-port") mustBe Some(7890)
    }
  }
}

