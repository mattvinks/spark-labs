// blank lines are important!

name := "TestApp"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.4.1" % "provided"
)

// for accessing files from S3 or HDFS
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.4.0" exclude("com.google.guava", "guava")

