name := "diophantine"

lazy val commonSettings = Seq(
  organization := "net.b0ss",
  version := "0.1.1",
  scalaVersion := "2.13.0",
  libraryDependencies ++= commonDependencies,
  test in assembly := {}
)

val commonDependencies =
  Seq("com.iheart" %% "ficus" % "1.4.7", "org.scalatest" % "scalatest_2.13" % "3.0.8" % "test")

val masterDependencies = Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.9",
  "com.typesafe.akka" %% "akka-stream" % "2.5.23"
)

val slaveDependencies = Seq("com.softwaremill.sttp" %% "core" % "1.6.3")

lazy val commons = project.in(file("commons")).settings(commonSettings)
lazy val master =
  project
    .in(file("master"))
    .dependsOn(commons)
    .settings(commonSettings)
    .settings(libraryDependencies ++= masterDependencies)
    .settings(
      mainClass in assembly := Some("net.b0ss.diophantine.master.http.CubelessHttpEndpoint")
    )

lazy val solver =
  project
    .in(file("solver"))
    .dependsOn(commons)
    .settings(commonSettings)
    .settings(libraryDependencies ++= slaveDependencies)
    .settings(
      mainClass in assembly := Some("net.b0ss.diophantine.slave.http.CubicPellSearchHttpApp")
    )
