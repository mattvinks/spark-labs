RDD Operations
========
In this lab, we are going to do RDD operations on 'large enough' RDDs

------
== STEP 1)  Preparing data
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
(Quit Spark-Shell if running)

Let's start the shell with the following
  - connect to spark server (--master  flag)
  - and start with more memory than the default 256M (--executor-memory flag)

  $  ~/spark/bin/spark-shell  --master <your master url here>   --executor-memory  1G

e.g.
  $   ~/spark/bin/spark-shell   --master  spark://localhost:7077  --executor-memory 1G

Verify the memory setting took effect by looking both UIs
    - spark server UI @ port 8080
    - spark-shell UI  @ port 4040


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
Hint :   rdd.saveAsTextFile("specify output directory here")

Inspect the console output during the run.

Inspect the output directory
You can use    ls -lh   output_dir
To see files created use :    less  output_dir/part-0000
What do you see as output?