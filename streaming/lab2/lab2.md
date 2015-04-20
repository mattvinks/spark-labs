[<< back to main index](../README.md) / [Streaming labs](./README.md)

Lab 2 - Spark Streaming - Filtering IP Addresses over TCP and storing them in files and folders using Window  
==================================

### Overview
Submit a job for Spark Streaming to find and filter  IP Addresses Over Socket/TCP

### Depends On 
None

### Run time
10-20 mins


---------------------
STEP 1: Edit source file
---------------------
Go to the project root directory
```bash
$    cd ~/spark-labs/streaming/lab2
```

**edit file : `src/main/scala/WindowedIP.scala`**  
**And fix the TODO items**

```
$    vi  src/main/scala/WindowedP.scala
# or 
$    nano  src/main/scala/WindowedIP.scala
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

name := “Filtering IP Addresses Using Window“

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.3.1" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.3.1" % "provided"
)

```

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
-rw-r--r--  1 vsistla  staff    13K Apr 16 09:59 windowedip_2.10-1.0.jar
```

`windowedip_2.10-1.0.jar `  is our code compiled.
 
--------------------------
STEP 3: Run The Application
--------------------------
```
$  ~/spark-labs/streaming/lab2/sbt run
```
Note - hope you have modified log4j.properties in conf folder to remove verbose. 

Lets call this Terminal #1

----------------
STEP 4: Run Netcat Server to send text through TCP connection.
----------------

Use nc command to move text you type in terminal to port 9999
Open an terminal and run this command at prompt

$ nc -lk 9999

Lets call this Terminal #2

-------------------------
STEP 5:  Test by typing text in the terminal
-------------------------

In the Terminal #2, copy and paste as many lines from sample-more.csv file and see the application display the 
IPs in Terminal #1


--------------------------
STEP 6: Also can print to a file in your file system
---------------------------

Comment and uncomment respective lines to test this. 

End. 
