import Dependencies._

ThisBuild / scalaVersion     := "3.1.0" // "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "cl.fbd"
ThisBuild / organizationName := "fbd"

// sea http://www.gibbons.org.uk/scala3-fs2-july-2021
lazy val root = (project in file("."))
  .settings(
    name := "HelloFS2",
    libraryDependencies += "co.fs2" % "fs2-core_2.13" % "3.0.6",
    libraryDependencies += "co.fs2" % "fs2-io_2.13" % "3.0.6",
    libraryDependencies += "co.fs2" % "fs2-reactive-streams_2.13" % "3.0.6",

    libraryDependencies += scalaTest % Test
  )

