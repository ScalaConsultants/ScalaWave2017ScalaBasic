import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "io.scalac",
      scalaVersion := "2.12.2",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Scala-Basic",
    libraryDependencies ++= Seq(
      akka,
      akkaTestkit % Test,
      scalaTest % Test
    )
  )
