import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1"
  lazy val akka = "com.typesafe.akka" %% "akka-actor" % "2.5.2"
  lazy val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % "2.5.2"
}
