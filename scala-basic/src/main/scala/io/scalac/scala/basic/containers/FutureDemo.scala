package io.scalac.scala.basic.containers

import scala.concurrent.Future
import scala.util.Random

object FutureDemo extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  println(s"Main Thread ${Thread.currentThread().getName}")
  
  val runOnAnotherThread = Future {
    println(s"Future Thread ${Thread.currentThread().getName}")
    Thread.sleep(3000) // you wouldn't sleep a thread in real code
    println("Done!")
    Random.nextInt(100)
  }

  println("Doesn't block!")
  runOnAnotherThread.map(res => println(s"Computation finished with $res"))

  val failOnAnotherThread = Future {
    Thread.sleep(3000)
    println("Failing!")
    "Fail".toDouble
  }
  failOnAnotherThread.recover {
    case err: NumberFormatException =>
      println(s"Received an error $err Using default")
      0
  }
  failOnAnotherThread.map(res => println(s"Computation failed with $res"))

  val runSequentially = for {
    a <- Future {
      Thread.sleep(1000)
      println("Sequential A ready")
      1
    }
    b <- Future {
      Thread.sleep(100)
      println("Sequential B ready")
      2
    }
  } yield a + b
  runSequentially.map(res => println(s"Sequential computation finished with $res"))

  val parallelAFuture = Future {
    Thread.sleep(1000)
    println("Parallel A ready")
    1
  }
  val parallelBFuture = Future {
    Thread.sleep(100)
    println("Parallel B ready")
    2
  }
  val runInParallel = for {
    a <- parallelAFuture
    b <- parallelBFuture
  } yield a + b
  runInParallel.map(res => println(s"Parallel computation finished with $res"))
  //TODO: Add colors to logs?

  val combineFewFutures = Future.sequence(Seq(runOnAnotherThread, runInParallel, Future.successful(1))).map { res =>
    println(s"Combining futures gave us $res which can be mapped ...")
    res.sum
  }.map { res =>
    println(s"... to  $res")
  }

  ///
  Thread.sleep(10000) // trick to block the execution until Futures finish
}
