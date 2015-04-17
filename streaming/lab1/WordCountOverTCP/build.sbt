name := "Word Count Over TCP"

version := "0.1"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
"org.apache.spark" %% "spark-core" % "1.2.0",
"org.apache.spark" %% "spark-streaming" % "1.2.0"
)

