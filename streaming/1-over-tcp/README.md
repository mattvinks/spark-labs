[<< back to main index](../../README.md) 

Lab 1 - Spark Streaming - Blocked IP Addresses TCP 
==================================

### Overview
Submit a job for Spark Streaming to find all the Blocked IP Addresses Over Socket/TCP

### Depends On 
None

### Run time
20-30 mins


---------------------
STEP 1: Edit source file
---------------------
Go to the project root directory
```bash
$    cd ~/spark-labs/streaming/1-over-tcp
```

**edit file : `src/main/scala/x/BlkIPOverTCP.scala`**  
**And fix the TODO items 1,2 and 3 (not 4, not yet)**

```
$    vi  src/main/scala/x/BlkIPOverTCP.scala
# or 
$    nano  src/main/scala/x/BlkIPOverTCP.scala
```


--------------------------
STEP 2: Compile the project
--------------------------
We will use `sbt` to build the project.  

**Inspect the `build.sbt` file**
```bash
$  cat   build.sbt
```

The file will look follows:
```scala
// blank lines are important!

name := "Over TCP"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.3.1" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.3.1" % "provided"
)

```


**=> Build the project**
```bash
$   sbt package
# to do a clean rebuild use
#  sbt clean package
```

Make sure there are no errors and there is output in `target` dir.
```bash
$  ls -l   target/scala-2.10
```
You should see output like follows
```
drwxr-xr-x  3 vsistla  staff   102B Apr 16 09:59 classes/
-rw-r--r--  1 vsistla  staff    13K Apr 16 09:59 over-tcp_2.10-0.1.jar
```

`over-tcp_2.10-0.1.jar`  is our code compiled.
 
--------------------------
STEP 3: Run The Application
--------------------------
```
$   ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.BlkIPOverTCP  target/scala-2.10/over-tcp_2.10-0.1.jar
```

Lets call this Terminal #1

----------------
STEP 4: Run Netcat Server to send text through TCP connection.
----------------
Open another terminal into Spark node (terminal #2)

Use `nc` command to move text you type in terminal #2 to port 9999
Open an terminal and run this command at prompt

```bash
$ nc -lk 9999
```

-------------------------
STEP 5:  Test by typing text in the terminal
-------------------------

In the Terminal #2, copy and paste the following lines (these are lines from our clickstream data)
```
1420070400000,ip_1,user_5,clicked,facebook.com,campaign_6,139,session_98
1420070400864,ip_2,user_3,viewed,facebook.com,campaign_4,35,session_98
1420070401728,ip_8,user_8,clicked,youtube.com,campaign_12,115,session_92
1420070402592,ip_1,user_2,blocked,wikipedia.org,campaign_5,129,session_91
1420070403456,ip_7,user_7,viewed,funnyordie.com,campaign_11,12,session_13
1420070405184,ip_4,user_9,blocked,bbc.co.uk,campaign_20,27,session_94
```

Inspect the output from Spark streaming on terminal #1

You should see something similar to this screen shot.
(Right click on image and open it in a separate tab to see full size)
![spark streaming 1](../../images/streaming-1a.png)

**=>  Hit Ctrl+C  on terminal #1 to kill Spark streaming application**

--------------------------
STEP 6: Save data into files
---------------------------
Printing is fine for development & debugging,  but in production we'd want to save the results.

Edit the file and complete TODO-4
```
$    vi  src/main/scala/x/BlkIPOverTCP.scala
# or 
$    nano  src/main/scala/x/BlkIPOverTCP.scala
```

**=> Build and run the program**
```bash
$   sbt package
$   ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.BlkIPOverTCP  target/scala-2.10/over-tcp_2.10-0.1.jar
```

**=> Paste some logs in netcat window (terminal #2)**

**=> Hit Contrl+C in terminal #2 to terminate Spark streaming**

**=> Inspect the `out` directory**
```bash
$   find out/
```

**=> Inspect some files, what do you see?**
