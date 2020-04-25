name := "learnakka"

version := "1.0"

scalaVersion := "2.13.2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.6.4",
  "com.typesafe.akka" %% "akka-testkit" % "2.6.4" % Test
)

