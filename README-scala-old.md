<link rel='stylesheet' href='assets/css/main.css'/>

Spark Labs - Scala
==========
Welcome to Spark labs bundle.  This is the Scala track.

## To Instructor
Create a lab bundle as follows
```bash
    $    git archive --format=zip HEAD -o spark-labs.zip
```

Viewing the files:
-----------------
Markdown files are plain text files and can be viewed in any text editor.

For best viewing experience we recommend the following setup

* Get Chrome browser from : https://www.google.com/chrome/browser/desktop/

* Install **Markdown Preview Plus Plugin** for Chrome.  
Go to this url in Chrome & click add to Chrome.  
    https://chrome.google.com/webstore/detail/markdown-preview-plus/febilkbfcbhebfnokafefeacimjdckgl?hl=en-US  
If this link does not work, search for markdown-preview-plus in https://chrome.google.com/webstore

* Once installed, go to 'Window --> Extensions' menu of Chrome ;   Find 'Markdown Preview Plus' plugin ;  set 'Allow access to file urls'  
Also go to 'options' of the MPP plugin and enable 'HTML view'

* Now open any .md file using Chrome (File --> Open) or drag-and-drop


Editors
-------
We recommend using a 'programmer's editor' to view these files. These editors have integrated file browser and allows navigating through files quickly.

* For Windows : [Sublime](http://www.sublimetext.com/), [NodePad++](http://notepad-plus-plus.org/), [Textpad](http://www.textpad.com/)

* For Mac : [Sublime](http://www.sublimetext.com/),  [TextWrangler](http://www.barebones.com/products/textwrangler/)

* For Linux : [Sublime](http://www.sublimetext.com/), GEdit, Vim

<a name="data"/>

Labs
---
```
    $   git clone  --depth 1 git@github.com:elephantscale/spark-labs.git
```

Data
----
The VMs already have data loaded.  This for your own reference.

[Link to Full Dataset](https://s3.amazonaws.com/elephantscale-public/data/datasets.zip)
(Note: Large download, ~200 Meg)

- Click the above link to download or
- use `wget` from command line
```
    $    wget   "https://s3.amazonaws.com/elephantscale-public/data/datasets.zip"
```




# Labs

### 1 - Scala Primer
- [1.1 - Scala shell](01-scala/README.md)
- [1.2 - Functions]
- [Setup 1](setup1.md) - Instructor to demo first
- [1.3 - File IO](01-scala/1.3-file.md)
- [1.4 - Higher Order Functions](01-scala/1.4-functions.md)
- [1.5 - Vending Machine class](01-scala/vending-machine/1.5-README.md)
- [1.6 - Unit testing with SPECS2](01-scala/vending-machine/1.6-SPECS-README.md)

### 2 - Spark Intro
- [2.1 - Install and run Spark](02-intro/2.1-install-spark-scala.md)
- 2.2 - Spark Shell - [Standalone](02-intro/2.2-shell-scala.md)  || [Hadoop version](02-intro/2.2H-spark-shell-hadoop.md)

### 3 - RDDs & Datasets
- [3.1 - RDD basics](03-rdd/3.1-rdd-basics-scala.md)
- [3.1b - Dataset basics](03-rdd/3.1b-dataset-basics-scala.md)
- [3.6 - Caching](03-rdd/3.6-caching-scala.md)
- **Optional RDD labs provided for reference (not done in class)**
    - [3.4 - Map Reduce](03-rdd/3.4-mapreduce.md)
    - [3.5 - Clickstream processing](03-rdd/3.5-clickstream.md)
    - [3.2 - Working with multiple RDDs](03-rdd/3.2-rdd-multi.md)
    - [3.3 - Key Value Pairs](03-rdd/3.3-key-value.md)

### 4 - Dataframes and Datasets
- [4.1 - Dataframes](04-dataframe/4.1-dataframe-scala.md)
- [4.2 - Spark SQL ](04-dataframe/4.2-sql-scala.md)
- [4.3 - Dataset](04-dataframe/4.3-dataset-scala.md)
- [4.4 - Caching 2 SQL](04-dataframe/4.4-caching-2-sql-scala.md)
- [4.5 - Spark & Hive (Hadoop)](04-dataframe/4.5-spark-and-hive-scala.md)
- [4.6 - Data formats](04-dataframe/4.6-data-formats-scala.md)


### 5 - API
- [5.1 - Submit first application](05.1-api-scala/5.1-submit.md)
- BONUS :  [5.2 - Mapreduce using API](05.1-api-scala/5.2-mapreduce.md)

### Practice Labs for end of day 2 & 3
- [Practice Lab 1 - Analyze Spark Commit logs](practice-labs/commit-logs-scala.md)
- [Practice Lab 2 - Optimize SQL query](practice-labs/optimize-query-scala.md)
- [Practice Lab 3 - Analyze house sales data](practice-labs/house-sales-scala.md)


### 6 - MLLib
- [6.1 - Kmeans](06-mllib/kmeans/README.md)
- [6.2 - Recommendations](06-mllib/recs/README.md)
- [6.3 - Classification](06-mllib/classification/README.md)

### 7 - GraphX
- [7.1  - Influencers (Twitter)](07-graphx/7.1-influencer.md)
- [7.2  - Shortest path (in LinkedIn)](07-graphx/7.2-shortest-path.md)

### 8 - Streaming
- [8.1 - Streaming over TCP](08-streaming/8.1-over-tcp/README.md)
- [8.2 - Windowed Count](08-streaming/8.2-window/README.md)
- 8.3 - Kafka Streaming
    * [8.3a - Kafka Direct Streaming](08-streaming/8.3-kafka/README.md)
    * [8.3b - Kafka Structured Streaming](08-streaming/8.3-kafka/3-kafka-structured-streaming.md)
- 8.4 - Structured Streaming
    * [8.4a - Structured Streaming 1](08-streaming/8.4-structured/README.md)
    * [8.4b - Structured Streaming 2 (JSON)](08-streaming/8.4-structured/README2.md)

### 9 - Operations
- [9.1 - Cluster setup](09-ops/9.1-cluster-setup.md)

### 10 - Spark and Hadoop (all the Hadoop labs are grouped here)
- [2.2H - Spark Shell on Hadoop](02-intro/2.2H-spark-shell-hadoop.md)
- [3.1 - Loading RDDs from HDFS](03-rdd/3.1-rdd-basics.md)
- [4.2 - Spark SQL on Hadoop](04-dataframe/4.2-sql.md)
- [4.4H - Spark & Hive](04-dataframe/4.4-spark-and-hive.md)
