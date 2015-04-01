Lab 1.3.1 : RDD Basics operations
==================================
### OverView
* Learning basic operations like filter / map / count
* Also we will work with larger sized RDDs

### Depends On 
None

### Run time
20 mins


----------------------------
STEP 1: Basic RDD Operations
----------------------------
Change working directory to `spark-labs`.  This way, we can access data using relative paths (makes life simpler)
```
$  cd ~/spark-labs
```

## 1.1: Fire up Spark shell

#### == Scala:
```
$   ~/spark/bin/spark-shell
```

#### == Python
```
$    ~/spark/bin/pyspark
```

## 1.2: Load a small file:

#### == Scala:
```scala
val f = sc.textFile("data/twinkle/sample.txt")
```

#### == Python
```python
f = sc.textFile("data/twinkle/sample.txt")
```

** Q : what is the 'type' of f ? **  
Hint : just type `f` in the shell  
Here is a possible output
```
scala> f
res0: org.apache.spark.rdd.RDD[String] = data/twinkle/sample.txt MappedRDD[3] at textFile at <console>:12
```

## 1.3: Filter
Let's find how many lines contain the word 'twinkle'
We will use the 'filter' function

#### == Scala:
```scala
val filtered = f.filter(line => line.contains("twinkle"))
```

#### == Python
```python
filtered = f.filter(lambda line: "twinkle" in line)
```

After entering the above in Spark-shell, 
* Goto Spark shell UI (port number 4040)
* inspect the 'Stages' section in the UI.  
* How is the filter executed? Can you explain the behavior?

** Count how many lines contain the word 'twinkle' **  
hint : apply `count()` to `filtered` variable

Here is a sample output
```
15/03/31 23:19:30 INFO DAGScheduler: Stage 0 (count at <console>:17) finished in 0.074 s
15/03/31 23:19:30 INFO DAGScheduler: Job 0 finished: count at <console>:17, took 0.141676 s
res1: Long = 2  <--- this is the result of count()
```

** Check the Stages in UI,  what do you see? **  
** How long did the job take? **

** Print out all the lines containing the word 'twinkle' **  
hint : `collect()`

Here is a sample output
```
res2: Array[String] = Array(twinkle twinkle little star, twinkle twinkle little star)
```

Quit Spark-shell using 'exit'  or pressing  Control+D


------
== STEP 2)  Preparing data
------
Generate some 'twinkle' data
  $   cd  ~/spark-labs/data/twinkle
  $   ./create-data-files.sh

This script will generate a bunch of data files at various sizes (1M, 10M, 100M, 500M and 1G)
Verify the data files and their sizes by doing a
  $   ls -lh
We are going to use these files in spark


------
== STEP 2)  Start shell
------

change working dir to :   ~/spark-labs
  $     cd  ~/spark-labs

Let's start the shell with the following
  - connect to spark server (--master  flag)
  - and start with more memory than the default 256M (--executor-memory flag)


*** Scala
    $   ~/spark/bin/spark-shell   --master  spark-server-uri
                                            ^^^^^^^^^^^^^^^^
                                    update this to match your spark server
e.g.
   $   ~/spark/bin/spark-shell   --master  spark://localhost:7077



*** Python
    $   ~/spark/bin/pyspark   --master  spark-server-uri
                                            ^^^^^^^^^^^^^^^^
                                    update this to match your spark server

   $   ~/spark/bin/pyspark   --master  spark://localhost:7077

Once the shell started, check both UI
  spark server UI at port 8080
  spark shell UI at  port 4040


-----
=== STEP 3 ) Process large RDD
-----
In Spark Shell, load 'data/twinkle/100M.data'
hint : use   sc.textFile(".....")

Count number of lines that have the word "diamond"

Count number of lines that does NOT have the word 'diamond'
Hint : use negative operator  !

Verify both counts add up to the total line count

Notice the time taken for each operation


--------
== STEP 4)
--------
Try the above with larger data files : 500M.data  ... 1G.data

note the times taken


-------
== STEP 5) loading multiple directories
-------
Load all *.data files under  data/twinkle  directory
hint : val files = sc.textFile("wild card pattern here")

Do a count() on RDD.
Notice the partition count and time taken to execute
Verify partition count from Spark-Shell UI


-------
=== STEP 6)  Saving the files
------
Continuing with the big RDD created on step (5)....
Create a new RDD by filtering first RDD for word 'diamond'
Hint : filter

Save the new RDD into a directory
Hint :   rdd.saveAsTextFile("out1")

Inspect the console output during the run.

Inspect the output directory
You can use
  $    ls -lh   out1

To see files created use :
  $   less  out1/part-0000

What do you see as output?