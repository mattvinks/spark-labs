Spark Labs
==========
Welcome to Spark labs bundle.

Viewing the files:
-----------------
Markdown files are plain text files and can be viewed in any text editor.

For best viewing experience we recommend the following setup:
* Get Chrome browser from : https://www.google.com/chrome/browser/desktop/
* Install Markdown preview plus plugin : https://chrome.google.com/webstore/detail/markdown-preview-plus/febilkbfcbhebfnokafefeacimjdckgl?hl=en-US
* Once installed, go to 'Window --> Extensions' menu of Chrome ;   Find 'Markdown Preview Plus' plugin ;  set 'Allow access to file urls'
* Open any *.md file using Chrome (File --> Open)

Viewing files on Server
------------------------
* Login to the node (Instructor will provide details)
* start simple http-server as follows
```
$  nohup http-server ~/spark-labs  -p 8000 -s  &
```
* goto http://your_machine_address:8000
* open .md files.. enjoy!

----
Labs
----
1. **Scala Primer**  (1-scala)

2. **Spark Intro**  (2-intro)
    - [2.1 Install and run Spark](2-intro/2.1-install-spark.md)
    - [2.2 Spark Shell](2-intro/2.2-shell.md)

3. **RDDs**  (3-rdd)
    - [3.1 RDD basics](3-rdd/3.1-rdd-basics.md)
    - [3.2 Working with multiple RDDs](3-rdd/3.2-rdd-multi.md)
    - [3.3 Key Value Pair RDDs](3-rdd/3.3-rdd-kv.md)
    - [3.4 Map Reduce](3-rdd/3.4-mapreduce.md)
    - [3.5 Clickstream processing](3-rdd/3.5-clickstream.md)
    - [3.6 Caching](3-rdd/3.6-caching.md)

4. **API** (4-api)
    - [4.1 Submit first application](4-api/4.1-submit.md)
    - [4.2 Mapreduce using API](4-api/4.2-mapreduce.md)

5. **Streaming**
    - [5.1 Streaming over TCP](streaming/1-over-tcp/README.md)
    - [5.2 Windowed Count](streaming/2-window/README.md)

6. **MLLib** 
    - [6.1 Kmeans](mllib/kmeans/README.md)
    - [6.2 Recommendations](mllib/recs/README.md)
    - [6.3 Classification](mllib/classification/README.md)

7. **GraphX**
    - [7.1  Shortest path](graphx/1-shortest-path/README.md)
    - [7.2  Influencers](graphx/2-influencer/README.md)

8.  **Spark SQL**
    - [8.1 Dataframes](8-sql/8.1-dataframe.md)
    - [8.2 SQL](8-sql/8.2-sql.md)

