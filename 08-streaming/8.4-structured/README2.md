<head><title>Spark labs : 8.1 Streaming Over TCP</title></head>
<link rel='stylesheet' href='../../assets/css/main.css'/>

[<< back to main index](../../README.md) 

Lab 8.4 - Structured Streaming 2 (JSON)
==================================

### Overview
Run a Spark Structured Streaming  job analyzing JSON data

### Depends On 
None

### Run time
30-40 mins


## STEP 1: Go to project directory
```bash
    $    cd ~/spark-labs/08-streaming/8.4-structured
```


## Step 2 : Inspect file
**Inspect file : `src/main/scala/x/JSONStreaming.scala`**  


## Step 3: Complete TODO-1
Uncomment code block around TODO-1 (and only this one), so your code looks like this.  
Delete /\*  and \*/

```scala
    // TODO-1
    // figure out clickstream schema using sample file
    val sample = spark.read.json("clickstream.json")
    sample.printSchema
    val schema = sample.schema
    println ("Clickstream sample schema is : " + schema)
    // ---- end-TODO-1
```

Save the file.

## Step 4: Build the project

```bash
    # be in project root directory
    $  cd ~/spark-labs/08-streaming/8.4-structured
    $  sbt clean package
```


Make sure there are no errors and there is output in `target` dir.

## Step 5: Run the streaming application

```bash
    # be in project root directory
    $   cd ~/spark-labs/08-streaming/8.4-structured

    $   rm -f json-input/* 

    $  ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.JSONStreaming  target/scala-2.11/structured-streaming_2.11-1.0.jar
```

Note : `rm -f json-input/*`  is used to clear the input directory


```console
root
 |-- action: string (nullable = true)
 |-- campaign: string (nullable = true)
 |-- cost: long (nullable = true)
 |-- domain: string (nullable = true)
 |-- ip: string (nullable = true)
 |-- session: string (nullable = true)
 |-- timestamp: long (nullable = true)
 |-- user: string (nullable = true)

Clickstream sample schema is : StructType(StructField(action,StringType,true), StructField(campaign,StringType,true), StructField(cost,LongType,true), StructField(domain,StringType,true), StructField(ip,StringType,true), StructField(session,StringType,true), StructField(timestamp,LongType,true), StructField(user,StringType,true))
```


## Step 6:  Fix TODO-2
Edit file : `src/main/scala/x/JSONStreaming.scala`**  
And fix TODO-2.  Uncomment to 'TODO-2' block

```bash
    # be in project root directory
    $   cd ~/spark-labs/08-streaming/8.4-structured

    $   sbt clean package

    $   rm -f json-input/*  ;  
        ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.JSONStreaming  target/scala-2.11/structured-streaming_2.11-1.0.jar
```

Leave this terminal running (we will call it Spark terminal)

Open another terminal and issue the following commands.

```bash
    $   cd ~/spark-labs/08-streaming/8.4-structured

    $   ln clickstream.json    json-input/1.json
```

In Spark terminal you should see the first batch output

```console
-------------------------------------------
Batch: 0
-------------------------------------------
+-------+-----------+----+-----------------+----+----------+-------------+------+
| action|   campaign|cost|           domain|  ip|   session|    timestamp|  user|
+-------+-----------+----+-----------------+----+----------+-------------+------+
|clicked|campaign_19| 118|      youtube.com|ip_4|session_36|1420070400000|user_9|
|blocked|campaign_12|   5|     facebook.com|ip_3|session_96|1420070400864|user_5|
|clicked| campaign_3|  54|sf.craigslist.org|ip_9|session_61|1420070401728|user_8|
|blocked|campaign_18| 110|    wikipedia.org|ip_5|session_55|1420070402592|user_6|
|clicked| campaign_6|  15|comedycentral.com|ip_9|session_49|1420070403456|user_4|
|blocked| campaign_9| 139|          cnn.com|ip_8|session_13|1420070404320|user_7|
....
```
You should see something similar to this screen shot.
(Click on the image for larger version)   

<a href="../../images/8.4b1-json-streaming.png"><img src="../../images/8.4b1-json-streaming.png" style="border: 5px solid grey; max-width:100%;"/></a>

**=>  Hit Ctrl+C  on terminal #1 to kill Spark streaming application**

## Step 7 : TODO-3 / Query1

Edit file : `src/main/scala/x/JSONStreaming.scala`**  
And fix TODO-2.  Uncomment to 'TODO-3' block, un commenting the following section

```scala
// TODO-3 - query1 : aggregate query
val byDomain = clickstream.groupBy("domain").count
val query1 = byDomain.writeStream
                  .outputMode("complete")
                  .format("console")
                  .start()
// ----- end-TODO-3
```

Build and run streaming application

```bash
    # be in project root directory
    $   cd ~/spark-labs/08-streaming/8.4-structured

    $   sbt clean package

    $   rm -f json-input/*  ;  
        ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.JSONStreaming  target/scala-2.11/structured-streaming_2.11-1.0.jar
```

Copy files into `json-input` directory as follows.

```bash
    $   cd ~/spark-labs/08-streaming/8.4-structured

    $   ln clickstream.json    json-input/1.json
```


Spark will calculate results for query1:

```console
-------------------------------------------
Batch: 0
-------------------------------------------
// echo output
+-------+-----------+----+-----------------+----+----------+-------------+------+
| action|   campaign|cost|           domain|  ip|   session|    timestamp|  user|
| viewed| campaign_1|  24|        yahoo.com|ip_4|session_60|1420070411232|user_8|
|....
|blocked|campaign_19|  23|       amazon.com|ip_5|session_85|1420070415552|user_7|
| viewed|campaign_20| 133|       google.com|ip_9|session_69|1420070416416|user_7|
+-------+-----------+----+-----------------+----+----------+-------------+------+

// query1 output
+-----------------+-----+
|           domain|count|
+-----------------+-----+
|      nytimes.com|    1|
|      youtube.com|    2|
|        zynga.com|    1|
|       google.com|    2|
|        yahoo.com|    1|
|     facebook.com|    1|
|          cnn.com|    1|
|    wikipedia.org|    3|
|       sfgate.com|    1|
|       amazon.com|    2|
|   funnyordie.com|    1|
|sf.craigslist.org|    2|
|comedycentral.com|    2|
+-----------------+-----+
```


Copy more files and see the `domain count` change

```bash

    $   ln clickstream.json    json-input/2.json
    $   ln clickstream.json    json-input/3.json
```

**=>  Hit Ctrl+C  on terminal #1 to kill Spark streaming application**

## Step 8 : TODO-4  / Query2
Edit file : `src/main/scala/x/JSONStreaming.scala`**  
Uncomment to 'TODO-4' block, un-commenting the following section

```scala

    // TODO-4 query2 : filter
    val blocked = clickstream.filter("action == 'blocked'")
    val query2 = blocked.writeStream.
                outputMode("append").
                format("console")
                .start()
    // ----- end TODO-4
```


Build and run streaming application

```bash
    # be in project root directory
    $   cd ~/spark-labs/08-streaming/8.4-structured

    $   sbt clean package

    $   rm -f json-input/*  ;  
        ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.JSONStreaming  target/scala-2.11/structured-streaming_2.11-1.0.jar
```

Copy files into `json-input` directory as follows.

```bash
    $   cd ~/spark-labs/08-streaming/8.4-structured

    $   ln clickstream.json    json-input/1.json
    $   ln clickstream.json    json-input/2.json
    $   ln clickstream.json    json-input/3.json
```


**=>  Can you explain how `append` mode works for query2? **  

**=>  Hit Ctrl+C  on terminal #1 to kill Spark streaming application**
