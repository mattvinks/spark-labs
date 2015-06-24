name := "Over TCP"

version := "0.1"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
"org.apache.spark" %% "spark-core" % "1.3.1" % "provided",
"org.apache.spark" %% "spark-streaming" % "1.3.1"  % "provided"
)

