[<< back to main index](../../README.md)  /  
[<< back to kafka streaming index](README.md)  

Lab 8.3b: Kafka Streaming
=================

### Overview
Setting up Kafka

### Depends On 
[Kafka setup](1-kafka-setup.md)

### Run time
1hr - 1.5 hrs


-----------------------------
STEP 1: Get Kafka running
-----------------------------
Follow [Kafka Streaming guide](1-kafka-setup.md) and have kafka running.

---------------------
STEP 2: Edit source file
---------------------
Go to the project root directory
```bash
$    cd ~/spark-labs/8-streaming/8.3-kafka
```

**=> Inspect the file : `src/main/scala/x/KafkaStreaming.scala`**  
**There are no TODO items to fix at this point**

```
$    vi  src/main/scala/x/KafkaStreaming.scala
# or 
$    nano  src/main/scala/x/KafkaStreaming.scala
```

See how we are constructing the streaming context...
```scala
def main(args: Array[String]) {
    if (args.length < 4) {
      System.err.println("Usage: StreamingMain <zkQuorum> <group> <topics> <numThreads>")
      System.exit(1)
    }

    val Array(zkQuorum, group, topics, numThreads) = args
    val sparkConf = new SparkConf().setAppName("Kafka Streaming")
    val ssc =  new StreamingContext(sparkConf, Seconds(5))
    //ssc.checkpoint("checkpoint")
    val topicMap = topics.split(",").map((_,numThreads.toInt)).toMap
    val stream = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap)

    stream.print()
    // ....

```

--------------------------
STEP 3: Build the project
--------------------------
We will use `sbt` to build the project.  

**Inspect the `build.sbt` file**
```bash
$  cat   build.sbt
```

The file will look follows:
```scala
// blank lines are important!

import AssemblyKeys._

name := "kafka"

version := "1.0"

scalaVersion := "2.10.4"


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.4.1" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.4.1" % "provided",
  "org.apache.spark" %% "spark-streaming-kafka" % "1.4.1"
)

assemblySettings

mergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$")      => MergeStrategy.discard
  case "log4j.properties"                                  => MergeStrategy.discard
  case m if m.toLowerCase.startsWith("meta-inf/services/") => MergeStrategy.filterDistinctLines
  case "reference.conf"                                    => MergeStrategy.concat
  case _                                                   => MergeStrategy.first
}

```

**=> Things to observe**  
* we are using assembly to build a 'fat.jar'
* we need to add `spark-streaming-kafka` package
* The `assembly` plugin depends on this file:  `project/plugins.sbt`
 
```
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")

```

Create a package using
```
$   sbt package
```

Also create an assembly (fat jar) using
```
$   sbt assembly
```

**=> Inspect generated jar files**
```
$ ls -l   target/scala-2.10/
```

output may look like...
```
drwxr-xr-x  3 sujee  staff   102B Apr 23 23:18 classes/
-rw-r--r--  1 sujee  staff    12M Apr 23 23:18 kafka-assembly-1.0.jar
-rw-r--r--  1 sujee  staff   4.6K Apr 23 22:59 kafka_2.10-1.0.jar
```

**=> Notice the size difference in both jars.  What accounts for the 'fat jar's size?**   

We are going to use the assembly jar to run Kafka streaming.


--------------------------
STEP 4: Running Kafka streaming
--------------------------
Make sure you have Kafka up and running.  For reference
* Terminal #1  : zookeeper
* Terminal #2  : Kafka broker
* Terminal #3  : Kafka console producer (we will paste data here)
* Terminal #4  : Ran Kafka consumer

Here is the screen shot (right click on image open in a new tab to see full size image)

<img src="../../images/8.3a-streaming-small.png" style="border: 5px solid grey; max-width:100%;"/>


**=> Stop Kafka consumer by hitting `Ctrl + C` in Terminal #4**  

**=> Launch kafka streaming application as follows**  
```bash
$    ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.KafkaStreaming  target/scala-2.10/kafka-assembly-1.0.jar   localhost:2181   my-group   clickstream    1    
```

Parameters explained:
* **localhost:2181**    - zookeeper quorum
* **my-group**  - my application 'name' to be used with Kafka
* **clickstream** - topic
* **1**  - number of threads

--------------------------
STEP 5: Feed some data into producer window (Terminal #3)
--------------------------
**=> Try typing / pasting the following text in terminal #3**  
```
foo
bar
baz
```

**=> Notice the kafka streaming console**  
```
-------------------------------------------
Time: 1429859630000 ms
-------------------------------------------
(null,foo)
(null,bar)
(null,hello world)
```

Your setup might look like the following picture
(right click on image open in a new tab to see full size image)

<img src="../../images/8.3b-streaming-small.png" style="border: 5px solid grey; max-width:100%;"/>

--------------------------
STEP 6: Continue fixing the TODO items 1-4
--------------------------

* Edit the file
* build using `$   sbt assembly`
* run

Test with following lines
```
1420070400000,ip_1,user_5,clicked,facebook.com,campaign_6,139,session_98
1420070400864,ip_2,user_3,viewed,facebook.com,campaign_4,35,session_98
1420070401728,ip_8,user_8,clicked,youtube.com,campaign_12,115,session_92
1420070402592,ip_1,user_2,blocked,wikipedia.org,campaign_5,129,session_91
1420070403456,ip_7,user_7,viewed,funnyordie.com,campaign_11,12,session_13
1420070404320,ip_9,user_5,clicked,foxnews.com,campaign_2,173,session_24
```