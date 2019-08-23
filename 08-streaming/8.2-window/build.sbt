name := "Windowedcount"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
"org.apache.spark" %% "spark-core" % "2.4.3" % "provided",
"org.apache.spark" %% "spark-streaming" % "2.4.3"  % "provided"
)

