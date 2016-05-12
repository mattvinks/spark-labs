name := "WindowedCount"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
"org.apache.spark" %% "spark-core" % "1.6.1" % "provided",
"org.apache.spark" %% "spark-streaming" % "1.6.1" % "provided"
)

