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
-----------------------
* Login to the node (Instructor will provide details)
* start simple http-server as follows
```
    $  http-server spark-labs  -p 8000 -s  &
```
* goto http://your_machine_address:8000
* open .md files.. enjoy!

Editors
-------
We recommend using a 'programmer's editor to view these files. These editors have integrated file browser and allows navigating through files quickly.
* For Windows : Sublime, NodePad++, Textpad
* For Mac : Sublime,  TextWrangler
* For Linux : Sublime, GEdit, Vim


----
Labs
----
1. Scala Primer  (1-scala)

2. Spark Intro  (2-intro)
    - [2.1 Install and run Spark](2-intro/2.1-install-spark.md)
    - [2.2 Spark Shell](2-intro/2.2-shell.md)

3. RDDs  (3-rdd)
    - [3.1 RDD basics](3-rdd/3.1-rdd-basics.md)
    - [3.2 Working with multiple RDDs](3-rdd/3.2-rdd-multi.md)
    - [3.3 Key Value Pair RDDs](3-rdd/3.3-rdd-kv.md)
    - [3.4 Map Reduce](3-rdd/3.4-mapreduce.md)
    - [3.5 Clickstream processing](3-rdd/3.5-clickstream.md)
    - [3.6 Caching](3-rdd/3.6-caching.md)

* 5-api : [Spark API](5-api/README.md)

* MLlib: [MLlib Labs](mllib/README.md)

