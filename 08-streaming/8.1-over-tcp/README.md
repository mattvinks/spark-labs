<link rel='stylesheet' href='../../assets/css/main.css'/>

[<< back to main index](../../README.md) 

Lab 8.1 - Spark Streaming - Blocked IP Addresses TCP 
==================================

### Overview
Submit a job for Spark Streaming to find all the Blocked IP Addresses Over Socket/TCP

### Depends On 
None

### Run time
30-40 mins


## STEP 1: Go to project directory
```bash
    $    cd ~/spark-labs/08-streaming/8.1-over-tcp
```


## Step 2 : Edit files
**edit file : `src/main/scala/x/BlkIPOverTCP.scala`**  
**And fix the TODO items 1,2 and 3 (not 4, not yet)**

See [Edit Files](../../edit-files.md) section for help.


## Step 3: Compile the project
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
  "org.apache.spark" %% "spark-core" % "1.6.1" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.6.1" % "provided"
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

```console
drwxr-xr-x  3 vsistla  staff   102B Apr 16 09:59 classes/
-rw-r--r--  1 vsistla  staff    13K Apr 16 09:59 over-tcp_2.10-0.1.jar
```

`over-tcp_2.10-0.1.jar`  is our code compiled.
 

## Step 4: Run Netcat Server to simulate network traffic

Open another terminal into Spark node (terminal #2)

Use `nc` command to move text you type in terminal #2 to port 9999
Open an terminal and run this command at prompt

```bash
    $ nc -lk 9999

    # if this gives an error like 'Protocol not available' use this
    $  ~/bin/nc  -lk 9999
```

## Step 5: Run the streaming application

```bash
    # be in project root directory
    # $ cd  ~/spark-labs/8-streaming/8.1-over-tcp

    $   ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.BlkIPOverTCP  target/scala-2.10/over-tcp_2.10-0.1.jar
```

Lets call this Terminal #1

Also note --master url `local[2]`
* We are using a local 'embedded' server  (quick for development)
* And we need at least 2 cpu cores -- one for receiver (long running task) and another for our program.  
If only allocated one core `local[1]`  the program will have run-time errors or won't run!


## Step 6:  Test by typing text in the terminal

In the Terminal #2, copy and paste the following lines (these are lines from our clickstream data)

```console

1420070400000,ip_1,user_5,clicked,facebook.com,campaign_6,139,session_98
1420070400864,ip_2,user_3,viewed,facebook.com,campaign_4,35,session_98
1420070401728,ip_8,user_8,clicked,youtube.com,campaign_12,115,session_92
1420070402592,ip_1,user_2,blocked,wikipedia.org,campaign_5,129,session_91
1420070403456,ip_7,user_7,viewed,funnyordie.com,campaign_11,12,session_13
1420070405184,ip_4,user_9,blocked,bbc.co.uk,campaign_20,27,session_94
```

Inspect the output from Spark streaming on terminal #1

You should see something similar to this screen shot.
(Click on the image for larger version)   

<a href="../../images/8.1a-streaming.png"><img src="../../images/8.1a-streaming.png" style="border: 5px solid grey; max-width:100%;"/></a>

**=>  Hit Ctrl+C  on terminal #1 to kill Spark streaming application**

## Step 7: Save data into files
Printing is fine for development & debugging,  but in production we'd want to save the results.

[Edit the source file](../../edit-files.md) and complete TODO-4

```scala
    // TODO 4 : save the results
    linesWBlocked.saveAsTextFiles("out/blocked-lines")
    blockedIPs.saveAsTextFiles("out/blocked-IPs")
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

```bash
    # you may need to adjust the file name 
    $   cat out/blocked-IPs-*/part-00000
```
