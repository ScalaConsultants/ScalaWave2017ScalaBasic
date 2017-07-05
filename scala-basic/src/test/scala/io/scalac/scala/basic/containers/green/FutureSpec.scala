package io.scalac.scala.basic.containers.green

import org.scalatest._
import org.scalatest.concurrent.ScalaFutures

import scala.concurrent.Future

class FutureSpec extends WordSpec with MustMatchers with ScalaFutures {

  //we need this to control Future's execution
  import scala.concurrent.ExecutionContext.Implicits.global

  case class ServerConnection() {
    def getReliableResource(): Future[Int] = Future.successful {
      42 // return immediately
    }

    private var invocationCount = 0 //Note: that's not particularly safe, but will do as an example
    def getRemoteResource(): Future[Int] = Future {
      invocationCount += 1
      if(invocationCount % 2 == 0) 101 else throw new RuntimeException("Bzzt, something went wrong on the line")
    }
  }

  def fetchAndUnpackReliableResource(server: ServerConnection, unpack: (Int) => String): Future[String] = {
    for {
      res <- server.getReliableResource()
    } yield unpack(res)
  }

  def fetchRemoteResource(server: ServerConnection, unpack: (Int) => String): Future[String] = {
    for {
      res <- server.getRemoteResource()
    } yield unpack(res) // it's never called, but makes types match
  }

  def fetchRemoveResourceWithDefault(server: ServerConnection,
                                     default: Int,
                                     unpack: (Int) => String): Future[String] = {
    fetchRemoteResource(server, unpack).recover{
      case _ =>
        unpack(default)
    }
  }

  def fetchRemoveResourceWithRetry(server: ServerConnection,
                                   retryCount: Int,
                                   unpack: (Int) => String): Future[String] = {
    fetchRemoteResource(server, unpack).recoverWith {
      case _ if retryCount > 0 =>
        fetchRemoveResourceWithRetry(server, retryCount-1, unpack)
    }
  }


  //NOTE: Scala Test provides us with a special syntax .futureValue that allows us to write more readable code
  // if you want to know how it does it ask the lecturer about

  "Containers.Future" should {
    "handle successful transformations" in {
      val server = ServerConnection()

      val asyncAddition = fetchAndUnpackReliableResource(server, (x) => (x + 1).toString)
      val asyncFormat = fetchAndUnpackReliableResource(server, (x) => s"Got $x")

      asyncAddition.futureValue mustBe "43"
      asyncFormat.futureValue mustBe "Got 42"
    }

    "handle async failures (1)" in {
      val server = ServerConnection()

      val asyncAddition = fetchRemoteResource(server, (x) => (x + 1).toString)
      intercept[RuntimeException]{
        asyncAddition.futureValue
      }
    }

    "handle async failures (2)" in {
      val server = ServerConnection()

      val asyncAddition = fetchRemoveResourceWithDefault(server, 0, (x) => (x + 1).toString)
      asyncAddition.futureValue mustBe "1"
    }

    "handle async failures (3)" in {
      val server = ServerConnection()

      val asyncAddition = fetchRemoveResourceWithRetry(server, 0, (x) => (x + 1).toString)
      intercept[RuntimeException]{
        asyncAddition.futureValue
      }
      val asyncAdditionWithRetry = fetchRemoveResourceWithRetry(server, 1, (x) => (x + 1).toString)
      asyncAdditionWithRetry.futureValue mustBe "102"
    }
  }
}
