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


## Step 2 : Fix TODO-1 & TODO-2
**Edit file : [src/main/scala/x/OverTCP.scala](src/main/scala/x/OverTCP.scala)**  
Fix TODO-1 and 2 (only)

See [Edit Files](../../edit-files.md) section for help.


## Step 3: Compile the project
We will use `sbt` to build the project.  

**==> Inspect the `build.sbt` file**

**=> Build the project**
```bash
    # be in project root dir
    $   cd ~/spark-labs/08-streaming/8.1-over-tcp  
    $   sbt package
    # to do a clean rebuild use
    #  sbt clean package
```

Make sure there are no errors and there is output in `target` dir.
```bash
    $  ls -l   target/scala-2.11
```

You should see output like follows

```console
drwxr-xr-x  3 vsistla  staff   102B Apr 16 09:59 classes/
-rw-r--r--  1 vsistla  staff    13K Apr 16 09:59 over-tcp_2.11-1.0.jar
```

`over-tcp_2.10-1.0.jar`  is our code compiled.


## Step 4: Run Netcat Server to simulate network traffic

Open another terminal into Spark node (terminal #2)

Use `nc` command to move text you type in terminal #2 to port 10000
Open an terminal and run this command at prompt

```bash
    $ nc -lk 10000

    # if this gives an error like 'Protocol not available' use this
    # $  ~/bin/nc  -lk 10000
```

## Step 5: Run the streaming application

```bash
    # be in project root directory
    $  cd  ~/spark-labs/08-streaming/8.1-over-tcp

    $   ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.OverTCP target/scala-2.11/over-tcp_2.11-1.0.jar
```

Lets call this Terminal #1

Also note --master url `local[2]`
* We are using a local 'embedded' server  (quick for development)
* And we need at least 2 cpu cores -- one for receiver (long running task) and another for our program.  
If only allocated one core `local[1]`  the program will have run-time errors or won't run!


## Step 6:  Test by typing text in the terminal

Enter some text in netcat terminal

```
a
b
```

Inspect the output from Spark streaming on terminal #1

You should see something similar to this screen shot.
(Click on the image for larger version)   

<a href="../../assets/images/8.1a.png"><img src="../../assets/images/8.1a.png" style="border: 5px solid grey; max-width:100%;"/></a>

**=>  Hit Ctrl+C  on terminal #1 to kill Spark streaming application**

## Step 7 : Filter (TODO-3)
**==>  Edit file :  `src/main/scala/x/OverTCP.scala`  **  
** ==> Uncomment block around TODO-2, filter lines that has the word 'blocked'**

```scala
    // TODO-2 : filter lines that contains 'blocked'
    val blocked = lines.filter(line => line.contains("???"))
    val blocked2 = blocked.map("BLOCKED:" + _) // better print
    blocked2.print
```
**==> Compile and run the code**

```bash
    $   cd ~/spark-labs/08-streaming/8.1-over-tcp  
    $   sbt package
    $   ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.OverTCP target/scala-2.11/over-tcp_2.11-1.0.jar    
```

Using 'netcat' program, send some data to streaming.  Make sure some data has 'blocked' text

```console
a
b blocked
```

Output may look like this:

<a href="../../assets/images/8.1b"><img src="../../assets/images/8.1b.png" style="border: 5px solid grey; max-width:100%;"/></a>

## Step 8: Save data into files (TODO-4)

**==> Edit file : ``**
**Uncomment TODO-3 code block so it looks like this**

```scala
    // TODO-3  : Save both RDDs (and uncomment this block)
    blocked.saveAsTextFiles("out-blocked")
```

**=> Build and run the program**
```bash
    $   sbt package
    $   ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.OverTCP target/scala-2.11/over-tcp_2.11-1.0.jar
```

**=> Send some data through netcat window (terminal #2)**

**=> Hit Contrl+C in terminal #2 to terminate Spark streaming**

**=> Inspect the `out` directory**

```bash
        $   ls -l
```

Output may look like this:

```console
drwxr-xr-x 4 sujee staff 136 Jan 22 22:42 out-blocked-1485153750000/
drwxr-xr-x 8 sujee staff 272 Jan 22 22:42 out-blocked-1485153760000/
drwxr-xr-x 6 sujee staff 204 Jan 22 22:42 out-blocked-1485153770000/
drwxr-xr-x 4 sujee staff 136 Jan 22 22:55 out-blocked-1485154510000/
drwxr-xr-x 4 sujee staff 136 Jan 22 22:55 out-blocked-1485154520000/
```

**=> Inspect some files, what do you see?**

```bash
    # you may need to adjust the file name
    $   find out-blocked*
```

Output may look like this:

```console
out-blocked-1485153760000/_SUCCESS
out-blocked-1485153760000/part-00000
out-blocked-1485153760000/part-00001
out-blocked-1485153770000
out-blocked-1485153770000/._SUCCESS.crc
out-blocked-1485153770000/.part-00000.crc
out-blocked-1485153770000/_SUCCESS
out-blocked-1485153770000/part-00000
```

Files
* part-0000 : this is data
* _SUCCESS_ : indicates that directory is complete
* crc : Checksum files

## Bonus Lab  1 : Extract BLOCKED IPs
**==> Edit file :  `src/main/scala/x/OverTCP.scala`**  

**==> Fix BONUS-LAB to extract blocked IPs**

**==> Compile and run a program like this**

```bash
    $   sbt package
    $   ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.OverTCP target/scala-2.11/over-tcp_2.11-1.0.jar
```

**==> Test with this clickstream data, using netcat window**

```
1420070400000,ip_1,user_5,clicked,facebook.com,campaign_6,139,session_98
1420070400864,ip_2,user_3,viewed,facebook.com,campaign_4,35,session_98
1420070401728,ip_8,user_8,clicked,youtube.com,campaign_12,115,session_92
1420070402592,ip_1,user_2,blocked,wikipedia.org,campaign_5,129,session_91
1420070403456,ip_7,user_7,viewed,funnyordie.com,campaign_11,12,session_13
```

## Bonus Lab  2 : Network Word Count
**==> Inspect file : [src/main/scala/x/NetworkWordCount.scala](src/main/scala/x/NetworkWordCount.scala)**

**==> Compile and run it**
```bash
    $   sbt package
    $   ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.NetworkWordCount target/scala-2.11/over-tcp_2.11-1.0.jar
```

**==> Test with data using NC**
```bash
    $  nc -lk 10000
        a b c
        a
        b c
        d
```
