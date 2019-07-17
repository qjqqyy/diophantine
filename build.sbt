name := "diophantine"
organization := "net.b0ss"

lazy val commonSettings = Seq(
  scalaVersion := "2.13.0",
  libraryDependencies ++= Seq(
    "org.scalatest" % "scalatest_2.13" % "3.0.8" % "test",
    "com.typesafe.akka" %% "akka-actor" % "2.5.23",
    "com.iheart" %% "ficus" % "1.4.7"
  ),
  test in assembly := {}
)

lazy val commons = project.in(file("commons")).settings(commonSettings)

lazy val solver =
  project
    .in(file("solver"))
    .dependsOn(commons)
    .settings(commonSettings)
    .settings(mainClass in assembly := Some("slave.solver.CubicPellSearchCliApp"))

lazy val master =
  project
    .in(file("master"))
    .dependsOn(commons)
    .settings(commonSettings)
    .settings(mainClass in assembly := Some("master.tcpserver.CubelessTcpEndpoint"))
