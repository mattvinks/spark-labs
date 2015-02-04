Lab : Up and Running With Spark
-----

== STEP 1) login to the Spark node
Instructor will provide details


== STEP 2) 'installing' Spark
There is no 'install'.  Just unzip/untar and run :-)
(copy paste the following commands on terminal,  do not include $ in your commands)

  $   cd
  $   rm -rf  spark   # cleanup existing spark installation (if any)
  $   tar xvf files/spark-1.2.0-bin-hadoop2.4.tgz
  $   mv spark-1.2.0-bin-hadoop2.4  spark

Now we have spark installed in  ~/spark  directory


== STEP 3) Running Spark
  $   cd ~/spark
  $   ./sbin/start-all.sh


Verify Spark is running by 'jps' command
  $ jps

Your output may look like this..
  30624 Jps
  30431 Master
  30565 Worker

you will see 'Master' and 'Worker'  processes running.
(you probably will get different values for process ids - first column )

Spark UI will be at port 8080 of the host.
In browser go to
  http://your_spark_host_address:8080
(be sure to use the 'public' ip address)
bingo!  Now we have spark running.


== STEP 4) Explore Spark UI
Q : Is Master and Worker running on the same node?

Q : Inspect memory & CPU available for Spark worker

Q : Note the Spark master URI, it will be something like
      spark://host_name:7077
    We will need this for later labs