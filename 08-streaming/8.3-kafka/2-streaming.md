<link rel='stylesheet' href='../../assets/css/main.css'/>

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
    $    cd ~/spark-labs/08-streaming/8.3-kafka
```

**=> Edit the file : `src/main/scala/x/KafkaDirectStreaming.scala`**  
**There are no TODO items to fix at this point**

## STEP 3: Build the project
We will use `sbt` to build the project.  

** ==> Inspect the `build.sbt` file**
```bash
    $   cd ~/spark-labs/08-streaming/8.3-kafka

    #  compile
    $   sbt clean compile

    # Create an assembly (fat jar) using
    $   sbt assembly
```

**=> Inspect generated jar files**
```bash
    $ ls -l   target/scala-2.11/
```

output may look like...
```console
drwxr-xr-x 3 sujee staff 102 Jan 20 22:31 classes/
-rw-r--r-- 1 sujee staff 24M Jan 20 22:31 kafka-streaming-assembly-1.0.jar
```

**=> Notice the size difference in both jars.  What accounts for the 'fat jar's size?**   

We are going to use the assembly jar to run Kafka streaming.


## STEP 4: Running Kafka streaming
Make sure you have Kafka up and running.  For reference
* Terminal #1  : zookeeper
* Terminal #2  : Kafka broker
* Terminal #3  : Kafka console producer (we will paste data here)
* Terminal #4  : Ran Kafka consumer

Here is the screen shot (click on image to see full size image)

<a href="../../assets/images/8.3a-streaming-small.png"><img src="../../assets/images/8.3a-streaming-small.png" style="border: 5px solid grey; max-width:100%;"/></a>


**=> Stop Kafka consumer by hitting `Ctrl + C` in Terminal #4**  

**=> Launch kafka streaming application as follows**  
```bash
  $    ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.KafkaDirectStreaming  target/scala-2.11/kafka-streaming-assembly-1.0.jar  localhost:9092  clickstream
```

Parameters explained:
* **localhost:9092**   - kafka broker
* **clickstream** - topic

## STEP 5: Feed some data into producer window (Terminal #3)
**=> Try typing / pasting the following text in terminal #3**  
```
foo
bar
baz
```

**=> Notice the kafka streaming console**  
```console
-------------------------------------------
Time: 1429859630000 ms
-------------------------------------------
(null,foo)
(null,bar)
(null,hello world)
```

Your setup might look like the following picture
(click on the image to see full size image)

<a href="../../assets/images/8.3b-streaming-small.png"><img src="../../assets/images/8.3b-streaming-small.png" style="border: 5px solid grey; max-width:100%;"/></a>

## STEP 6: Continue fixing the TODO items 1-4

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