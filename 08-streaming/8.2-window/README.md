<link rel='stylesheet' href='../../assets/css/main.css'/>

[<< back to main index](../../README.md)

Lab 8.2 - Spark Streaming Windowed Calculations
==================================

### Overview
Submit a job for Spark Streaming and doing 'windowed count'

### Depends On
None

### Run time
30-40 mins


---------------------
STEP 1: Edit source file
---------------------
Go to the project root directory
```bash
    $    cd ~/spark-labs/08-streaming/8.2-window
```

**Inspect file : `src/main/scala/x/WindowedCount.scala`**  


--------------------------
STEP 2: Compile the project
--------------------------

```bash
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
-rw-r--r--  1 vsistla  staff    13K Apr 16 09:59 windowed-count_2.11-1.0.jar
```

`windowed-count_2.11-1.0.jar`  is our code compiled.

----------------
STEP 3: Run Netcat Server (Terminal #2)
----------------
Open another terminal into Spark node (terminal #2)

Use `nc` command to move text you type in terminal #2 to port 9999
Open an terminal and run this command at prompt

```bash
    $ nc -lk 9999

    # if this gives an error like 'Protocol not available' use this
    # $  ~/bin/nc  -lk 9999
```


--------------------------
STEP 4: Run The Application  (Terminal #1)
--------------------------
```bash
    $   ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.WindowedCount target/scala-2.11/windowed-count_2.11-1.0.jar
```



-------------------------
STEP 5:  Test by typing text Netcat Terminal #2
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
(Click on image to see larger version)   
<a href="../../assets/images/8.2-streaming-small.png"><img src="../../assets/images/8.2-streaming-small.png" style="border: 5px solid grey; max-width:100%;"/></a>


--------------------------
STEP 6: Enable Window Count
---------------------------
** ==> Edit the file : `src/main/scala/x/WindowedCount.scala` **  

** ==> fix TODO-1 to enable Window count**

** ==> Build and run the program**
```bash
    $   sbt package
    $    ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.WindowedCount target/scala-2.11/windowed-count_2.11-1.0.jar
```

**=> Paste some logs in netcat window (terminal #2)**

**=> Hit Contrl+C in terminal #2 to terminate Spark streaming**

**=> Inspect some files, what do you see?**
