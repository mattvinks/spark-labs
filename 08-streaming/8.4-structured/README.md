<head><title>Spark labs : 8.1 Streaming Over TCP</title></head>
<link rel='stylesheet' href='../../assets/css/main.css'/>

[<< back to main index](../../README.md) 

Lab 8.4 - Structured Streaming 1
==================================

### Overview
Run a Spark Structured Streaming  job

### Depends On 
None

### Run time
30-40 mins


## STEP 1: Go to project directory
```bash
    $    cd ~/spark-labs/08-streaming/8.4-structured
```


## Step 2 : Inspect file
**Inspect file : `src/main/scala/x/StructuredStreaming.scala`**  


## Step 3: Build the project
We will use `sbt` to build the project.  

**Inspect the `build.sbt` file**

```bash
    # be in project root directory
    $  cd ~/spark-labs/08-streaming/8.4-structured
    $  sbt clean package
```


Make sure there are no errors and there is output in `target` dir.

## Step 4: Run Netcat Server to simulate network traffic

Open another terminal into Spark node (terminal #2)

Use `nc` command to move text you type in terminal #2 to port 9999
Open an terminal and run this command at prompt

```bash
    $ nc -lk 9999

    # if this gives an error like 'Protocol not available' use this
    # $  ~/bin/nc  -lk 9999
```

## Step 5: Run the streaming application

```bash
    # be in project root directory
    $ cd ~/spark-labs/08-streaming/8.4-structured

    $   ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.StructuredStreaming  target/scala-2.11/structured-streaming_2.11-1.0.jar
```

Lets call this Terminal #1

Also note --master url `local[2]`
* We are using a local 'embedded' server  (quick for development)
* And we need at least 2 cpu cores -- one for receiver (long running task) and another for our program.  
If only allocated one core `local[1]`  the program will have run-time errors or won't run!


## Step 6:  Test by typing text in the terminal

In the Terminal #2, copy and paste the following lines (these are lines from our clickstream data)

```bash
Hello world
how are you?
good bye
world
```

Inspect the output from Spark streaming on terminal #1

You should see something similar to this screen shot.
(Click on the image for larger version)   

<a href="../../images/8.4a-structured-streaming.png"><img src="../../images/8.4a-structured-streaming.png" style="border: 5px solid grey; max-width:100%;"/></a>

**=>  Hit Ctrl+C  on terminal #1 to kill Spark streaming application**

