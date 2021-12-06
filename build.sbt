import Dependencies._

ThisBuild / scalaVersion     := "3.1.0" // "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "cl.fbd"
ThisBuild / organizationName := "fbd"

val http4sVersion = "0.23.0"
val circeVersion = "0.14.1"

// see http://www.gibbons.org.uk/scala3-fs2-july-2021

lazy val root = (project in file("."))
  .settings(
    name := "HelloFS2",

    libraryDependencies += "org.http4s" %% "http4s-dsl" % http4sVersion,
    libraryDependencies += "org.http4s" %% "http4s-blaze-client" % http4sVersion,
    libraryDependencies += "org.http4s" %% "http4s-circe" % http4sVersion,

    libraryDependencies += "io.circe"   %% "circe-generic" % circeVersion,

    libraryDependencies += scalaTest % Test
  )

