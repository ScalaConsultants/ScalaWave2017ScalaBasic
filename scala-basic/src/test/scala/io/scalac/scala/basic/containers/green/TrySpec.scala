package io.scalac.scala.basic.containers.green

import org.scalatest._

import scala.util.{Success, Try}

class TrySpec extends WordSpec with MustMatchers {

  case class ConfigurationManager(values: Map[String, Int]) {
    def withValueInserted(key: String, value: Int): ConfigurationManager =
      ConfigurationManager(values + (key -> value))

    private def addRawValue(key: String, rawValue: String): ConfigurationManager = {
      Try{ rawValue.toInt } match {
        case Success(parsed) =>
          withValueInserted(key, parsed)
        case _ =>
          this
      }
    }
    def fillWithConfigData(readFromFile: Seq[(String, String)]): ConfigurationManager = {
      readFromFile.foldLeft(this) ( (acc, kv) => acc.addRawValue(kv._1, kv._2))
    }

    private def addRawValue(key: String, rawValue: String, defaultValue: Int): ConfigurationManager = {
      Try{
        rawValue.toInt
      }.recover {
        case _:NumberFormatException => defaultValue
      } match {
        case Success(parsed) =>
          withValueInserted(key, parsed)
        case _ =>
          this
      }
    }
    def fillWithConfigData(readFromFile: Seq[(String, String)], default: Int): ConfigurationManager = {
      readFromFile.foldLeft(this) ( (acc, kv) => acc.addRawValue(kv._1, kv._2, default))
    }

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
