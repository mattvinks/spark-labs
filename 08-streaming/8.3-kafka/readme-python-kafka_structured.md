<link rel='stylesheet' href='../../assets/css/main.css'/>

[<< back to main index](../../README.md)  /  
[<< back to kafka streaming index](README.md)  

Lab 8.3b: Kafka Structured Streaming
=================

### Overview
Run a Kafka Structured Streaming job using pyspark

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
Run the python file **python/kafka_structured_streaming.py** using spark submit command

```bash
	$	~/spark/bin/spark-submit  --master local[2] --driver-class-path logging/ --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.4.3 python/kafka_structured_streaming.py clickstream
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
root
 |-- key: binary (nullable = true)
 |-- value: binary (nullable = true)
 |-- topic: string (nullable = true)
 |-- partition: integer (nullable = true)
 |-- offset: long (nullable = true)
 |-- timestamp: timestamp (nullable = true)
 |-- timestampType: integer (nullable = true)

-------------------------------------------
Batch: 0
-------------------------------------------
+---+-----+-----+---------+------+---------+-------------+
|key|value|topic|partition|offset|timestamp|timestampType|
+---+-----+-----+---------+------+---------+-------------+
+---+-----+-----+---------+------+---------+-------------+

-------------------------------------------
Batch: 1
-------------------------------------------
+----+----------+-----------+---------+------+--------------------+-------------+
| key|     value|      topic|partition|offset|           timestamp|timestampType|
+----+----------+-----------+---------+------+--------------------+-------------+
|null|[66 6F 6F]|clickstream|        0|    32|2019-07-22 12:16:...|            0|
|null|[62 61 7A]|clickstream|        0|    33|2019-07-22 12:16:...|            0|
|null|[62 61 72]|clickstream|        1|    31|2019-07-22 12:16:...|            0|
+----+----------+-----------+---------+------+--------------------+-------------+

```

## STEP 5: Change schema

* Edit the file python/kafka_structured_streaming.py
* Comment "option 1"
* Uncomment 'option 2' where we specify schema for Kafka
* Save the file
* Run using the spark-submit command

**=> Try typing / pasting the following text in terminal #3(producer terminal)**  
```
foo
```

**=> Notice the kafka streaming console** 
```console
root
 |-- key: string (nullable = true)
 |-- value: string (nullable = true)
 |-- topic: string (nullable = true)
 |-- partition: integer (nullable = true)
 |-- offset: long (nullable = true)
 |-- timestamp: timestamp (nullable = true)

-------------------------------------------
Batch: 0
-------------------------------------------
+---+-----+-----+---------+------+---------+
|key|value|topic|partition|offset|timestamp|
+---+-----+-----+---------+------+---------+
+---+-----+-----+---------+------+---------+

-------------------------------------------
Batch: 1
-------------------------------------------
+----+-----+-----------+---------+------+--------------------+
| key|value|      topic|partition|offset|           timestamp|
+----+-----+-----------+---------+------+--------------------+
|null|  foo|clickstream|        1|    32|2019-07-22 12:19:...|
+----+-----+-----------+---------+------+--------------------+

```