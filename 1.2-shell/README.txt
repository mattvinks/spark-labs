Spark Shell
-----------
Running Spark shell

-------
== STEP 1)
-------
  $   cd ~/spark
  $   ./bin/spark-shell

This will run the Spark shell application.  Watch the console output.
Once running, you will be presented with scala> prompt
(In fact this is a Scala shell)

Spark shell UI is available on port 4040.
In browser go to :   http://your_machine_address:4040
(use 'public' ip of machine)


-------
== STEP 2) Exploring Spark shell UI
-------
In a browser go to to :  http://your_spark_host:4040
Explore stage, storge, environment and executor tabs


-------
== STEP 3) Spark context
-------
Within Spark  shell,  variable 'sc' is the SparkContext
Type 'sc' and see what happens
  scala > sc

Also type 'sc.'  and double-TAB
This will show all the available methods on sc variable

Q : Print the name of application name

Q : Find the 'Spark master' for the shell

Quit Spark-shell using 'exit'  or pressing  Control+D


-------
== STEP 4) Load a file
-------
We have data files under 'spark-labs/data'
For ease of use, let's start spark-shell from spark-labs directory

  $    cd  ~/spark-labs
  $    ~/spark/bin/spark-shell
you should see the familiar   scala>  prompt

Use test file :  data/twinkle/seed.txt

  scala>
        val f = sc.textFile("data/twinkle/seed.txt")

Q : what is the 'type' of f ?


Q : print the first line / record from RDD
hint : use 'f.first()' function

Q : print first 3 lines of RDD
hint : take
hint : try appending the following at the end of take
      ...().foreach(println)


Q : print all the content from the file
hint : collect()

Q : How many lines are in the file?
hint : count()

Q : Inspect the 'Stages' section in Shell UI (in browser)
Explore what is happening


--------
== STEP 5) Searching RDDs
--------
Let's find how many lines contain the word 'twinkle'
We will use the 'filter' function

  >  val filtered = f.filter(line => line.contains("twinkle"))

After entering the above in Spark-shell, inspect the 'Stages' section in the UI.  How is the filter executed?
Can you explain the behavior?

Count how many lines contain the word 'twinkle'
hint : filtered.count()

Check the Stages in UI,  what do you see?

Q : print out all the lines containing the word 'twinkle'


----------
== STEP 6 )  Connecting Shell and  Spark server
----------

Quit spark-shell session (Control + D)

If Spark server is not running, start it as
  $   ~/spark/sbin/start-all.sh

Inspect the UI at port 8080 to make sure Spark server is running

Now start spark shell
  $    ~/spark/bin/spark-shell

Once the shell starts, check the _server_ UI on port 8080.

Q : Do you see the shell connected as an application?  why (not) ?

Let's connect Spark shell with the Spark server.
Make a note of Spark server uri (e.g  spark://host_name:7077)

Quit spark shell (Control+D)

Restart spark shell as follows
    $   ~/spark/bin/spark-shell   --master  spark-server-uri
                                            ^^^^^^^^^^^^^^^^
                                    update this to match your spark server
e.g.
   $   ~/spark/bin/spark-shell   --master  spark://localhost:7077


Once the shell started, check both UI
  spark server UI at port 8080
  spark shell UI at  port 4040

Explore both UIs

Redo steps (4) and (5) in this new environment