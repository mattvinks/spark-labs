<link rel='stylesheet' href='assets/css/main.css'/>

Spark Labs
==========
Welcome to Spark labs bundle.

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

* Open any *.md file using Chrome (File --> Open)


Editors
-------
We recommend using a 'programmer's editor' to view these files. These editors have integrated file browser and allows navigating through files quickly.

* For Windows : [Sublime](http://www.sublimetext.com/), [NodePad++](http://notepad-plus-plus.org/), [Textpad](http://www.textpad.com/)

* For Mac : [Sublime](http://www.sublimetext.com/),  [TextWrangler](http://www.barebones.com/products/textwrangler/)

* For Linux : [Sublime](http://www.sublimetext.com/), GEdit, Vim
----
Labs
----
1. **Scala Primer**  (01-scala)
    - [1.1 - Scala shell](01-scala/README.md)
    - [1.2 - Functions]
    - [Setup 1](setup1.md) - Instructor to demo first
    - [1.3 - File IO](01-scala/1.3-file.md)
    - [1.4 - Higher Order Functions](01-scala/1.4-functions.md)
    - [1.5 - Vending Machine class](01-scala/vending-machine/1.5-README.md)
    - [1.6 - Unit testing with SPECS2](01-scala/vending-machine/1.6-SPECS-README.md)

2. **Spark Intro**  (02-intro)
    - [2.1 - Install and run Spark](02-intro/2.1-install-spark.md)
    - [2.2 - Spark Shell](02-intro/2.2-shell.md)  | [Hadoop version](02-intro/2.2H-spark-shell-hadoop.md)

3. **RDDs**  (03-rdd)
    - [3.1 - RDD basics](03-rdd/3.1-rdd-basics.md)  | [Hadoop version](03-rdd/3.1H-rdd-hadoop.md)
    - [3.1b - Dataset basics](03-rdd/3.1b-dataset-basics.md)  | [Hadoop version](03-rdd/3.1H-rdd-hadoop.md)
    - [3.2 - Working with multiple RDDs](03-rdd/3.2-rdd-multi.md)
    - [3.3 - Key Value Pairs](03-rdd/3.3-key-value.md)
    - [3.6 - Caching](03-rdd/3.6-caching.md)
    - *optional labs*
        - [3.4 - Map Reduce](03-rdd/3.4-mapreduce.md)
        - [3.5 - Clickstream processing](03-rdd/3.5-clickstream.md)

4.  **Dataframes** (04-dataframe)
    - [4.1 - Dataframes](04-dataframe/4.1-dataframe.md)
    - [4.2 - SQL](04-dataframe/4.2-sql.md) | [Hadoop version](04-dataframe/4.2H-spark-sql-hadoop.md)
    - [4.3 - Dataset](04-dataframe/4.3-dataset.md)
    - [4.4 - Spark & Hive (Hadoop)](04-dataframe/4.4-spark-and-hive.md)
    - [4.5 - Data formats](04-dataframe/4.5-data-formats.md)

5. **API** (05-api)
    - [5.1 - Submit first application](05-api/5.1-submit.md)
    - [5.2 - Mapreduce using API](05-api/5.2-mapreduce.md)

6. **MLLib**  (06-mllib)
    - [6.1 - Kmeans](06-mllib/kmeans/README.md)
    - [6.2 - Recommendations](06-mllib/recs/README.md)
    - [6.3 - Classification](06-mllib/classification/README.md)

7. **GraphX** (07-graphx)
    - [7.1  - Influencers (Twitter)](07-graphx/7.1-influencer.md)
    - [7.2  - Shortest path (in LinkedIn)](07-graphx/7.2-shortest-path.md)

8. **Streaming** (08-streaming)
    - [8.1 - Streaming over TCP](08-streaming/8.1-over-tcp/README.md)
    - [8.2 - Windowed Count](08-streaming/8.2-window/README.md)
    - [8.3 - Kafka Streaming](08-streaming/8.3-kafka/README.md)
    - 8.4 - Structured Streaming
        * [8.4a - Structured Streaming 1](08-streaming/8.4-structured/README.md)
        * [8.4b - Structured Streaming 2 (JSON)](08-streaming/8.4-structured/README2.md)

9. **Operations** (09-ops)
    - [9.1 - Cluster setup](09-ops/9.1-cluster-setup.md)

10. **Spark and Hadoop** (all the Hadoop labs are grouped here)
    - [2.2H - Spark Shell on Hadoop](02-intro/2.2H-spark-shell-hadoop.md)
    - [3.1H - Loading RDDs from HDFS](03-rdd/3.1H-rdd-hadoop.md)
    - [4.2H - Spark SQL on Hadoop](04-dataframe/4.2H-spark-sql-hadoop.md)
    - [4.4H - Spark & Hive](04-dataframe/4.4-spark-and-hive.md)
