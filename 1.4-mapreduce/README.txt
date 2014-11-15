Map Reduce in Spark
======

Learn MapReduce in Spark step by step

== STEP 1) map
start spark shell
  $   ~/spark/bin/spark-shell

  > val input = Array ("hello world", "goodbye world", "bye bye")
  > val r = sc.makeRDD(input)

apply map() function to rdd
syntax for map function is :    map(line => line.split(" "))
Print out the results by collect()


== STEP 2) flatMap
apply flatMap( line => line.split(" ")) to rdd
print out the results


== STEP 3)
apply
  flatmap(line => line.split(" ")).map(word => (word, 1))


== STEP 4) reduceByKey
apply
  reduceByKey(_+_)
to step (3)


== STEP 5)  Generating data
If you haven't done so yet,  generate some 'twinkle' data
  $   cd  ~/spark-labs/data/twinkle
  $   ./create-data-files.sh

This script will generate a bunch of data files at various sizes (1M, 10M, 100M, 500M and 1G)
Verify the data files and their sizes by doing a
  $   ls -lh
We are going to use these files in spark


Load one of the larger files (100M + )
   > val f = sc.textFile("file path")

Do the word count on this file
