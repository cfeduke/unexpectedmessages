import sbt._
import sbt.Keys._

object UnexpectedMessagesBuild extends Build {

  lazy val unexpectedmessages = Project(
    id = "unexpectedmessages",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "UnexpectedMessages",
      organization := "com.deploymentzone",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.10.0",
      scalacOptions ++= Seq("-feature", "-deprecation"),
      resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases",
      libraryDependencies ++= Seq(
        "com.typesafe.akka" %% "akka-actor" % "2.1.2",
        "com.typesafe.akka" %% "akka-testkit" % "2.1.2",
        "org.scalatest" % "scalatest_2.10" % "2.0.M6-SNAP30" % "test"
      )
    )
  )
}
