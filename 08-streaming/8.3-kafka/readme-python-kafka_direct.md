<link rel='stylesheet' href='../../assets/css/main.css'/>

[<< back to main index](../../README.md)  /  
[<< back to kafka streaming index](README.md)  

Lab 8.3b: Kafka Direct Streaming
=================

### Overview
Run a Kafka Direct Streaming job using pyspark

### Depends On 
[Kafka setup](1-kafka-setup.md)

### Run time
1hr - 1.5 hrs
# These labs assume Spark 2.4.3
* For a different version of Spark, adjust the libraries

## STEP 1: Get Kafka running

Follow [Kafka Streaming guide](1-kafka-setup.md) and have kafka running.

## STEP 2: Running Kafka streaming

Make sure you have Kafka up and running.  For reference
* Terminal #1  : zookeeper
* Terminal #2  : Kafka broker
* Terminal #3  : Kafka console producer (we will paste data here)
* Terminal #4  : Ran Kafka consumer

Here is the screen shot (click on image to see full size image)

<a href="../../assets/images/8.3a-streaming-small.png"><img src="../../assets/images/8.3a-streaming-small.png" style="border: 5px solid grey; max-width:100%;"/></a>


**=> Stop Kafka consumer by hitting `Ctrl + C` in Terminal #4**  


## STEP 3: Run the python file

Go to the project root directory
```bash
    $	cd ~/spark-labs/08-streaming/8.3-kafka
```
Run the python file **python/kafka_direct_streaming.py** using spark submit command

```bash
	$	~/spark/bin/spark-submit  --master local[2] --driver-class-path logging/ --packages org.apache.spark:spark-streaming-kafka-0-8_2.11:2.4.3 python/kafka_direct_streaming.py  localhost:9092  clickstream
```

Parameters explained:
* **localhost:9092**   - kafka broker
* **clickstream** - topic


## STEP 4: Feed some data into producer window (Terminal #3)
**=> Try typing / pasting the following text in terminal #3(producer terminal)**  
```
foo
bar
baz
```

**=> Notice the kafka streaming console**  
```console
-------------------------------------------
Time: 2019-07-22 12:07:40
-------------------------------------------
foo
bar
baz
```

## STEP 5: Fix the TODO items 1

* Edit the file python/kafka_direct_streaming.py
* Run using the spark-submit command

Test with following lines
```
1420070400000,ip_1,user_5,clicked,facebook.com,campaign_6,139,session_98
1420070400864,ip_2,user_3,viewed,facebook.com,campaign_4,35,session_98
1420070401728,ip_8,user_8,clicked,youtube.com,campaign_12,115,session_92
1420070402592,ip_1,user_2,blocked,wikipedia.org,campaign_5,129,session_91
1420070403456,ip_7,user_7,viewed,funnyordie.com,campaign_11,12,session_13
1420070404320,ip_9,user_5,clicked,foxnews.com,campaign_2,173,session_24
```
