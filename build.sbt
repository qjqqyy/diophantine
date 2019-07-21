ThisBuild / version := "0.1.1"
ThisBuild / organization := "net.b0ss"
ThisBuild / scalaVersion := "2.13.0"

lazy val commonSettings = Seq(
  libraryDependencies ++= commonDependencies,
  target in assembly := file("deploy/work/"),
  test in assembly := {}
)

val commonDependencies =
  Seq("com.iheart" %% "ficus" % "1.4.7", "org.scalatest" % "scalatest_2.13" % "3.0.8" % "test")

val masterDependencies = Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.9",
  "com.typesafe.akka" %% "akka-stream" % "2.5.23"
)

val slaveDependencies = Seq("com.softwaremill.sttp" %% "core" % "1.6.3")

lazy val commons = (project in file("commons")).settings(commonSettings)
lazy val master = (project in file("master"))
  .dependsOn(commons)
  .settings(commonSettings)
  .settings(
    Seq(
      libraryDependencies ++= masterDependencies,
      mainClass in assembly := Some("net.b0ss.diophantine.master.http.CubelessHttpEndpoint"),
      assemblyJarName in assembly := "master.jar"
    )
  )

lazy val solver = (project in file("solver"))
  .dependsOn(commons)
  .settings(commonSettings)
  .settings(
    Seq(
      libraryDependencies ++= slaveDependencies,
      mainClass in assembly := Some("net.b0ss.diophantine.slave.http.CubicPellSearchHttpApp"),
      assemblyJarName in assembly := "solver.jar"
    )
  )
