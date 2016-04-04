<link rel='stylesheet' href='../assets/main.css'/>

[<< back to main index](../README.md) 

Lab 10 : Hadoop & Spark
================================

### Overview
Using Spark and Hadoop

### Depends On 
None

### Run time
20-30 mins

----------------------------
STEP 1: Login to Hadoop + Spark cluster
----------------------------
For this lab, you will log into a cluster that has Hadoop & Spark setup.  Instructor will provide details.

----------------------------
STEP 1: Start Spark Shell  In Local Mode
----------------------------

```
    $   cd
    $   ~/spark/bin/spark-shell
```

As Spark shell is starting up, it will publish a UI on port 4040.  If that port is not available, it will keep trying 4041 , 4042 ..etc

```
6/01/21 05:15:00 WARN Utils: Service 'SparkUI' could not bind on port 4040. Attempting port 4041.
16/01/21 05:15:00 WARN Utils: Service 'SparkUI' could not bind on port 4041. Attempting port 4042.
```


**=> Note this port number.  This is where you'd access Spark Shell UI**  
http://host_name:port   
**=> Make sure you can access the Shell UI in browser.**  

----------------------------
STEP 2: Process a Local File 
----------------------------
In the previous Spark Shell try this
```scala

    val a = sc.textFile("README-local.txt")
    a.count

```

**=> What is the result?**  

**=> Quit the shell by pressing Ctrl+d**  

----------------------------
STEP 3: Launch Shell Connecting To Master
----------------------------

Quit the local mode spark shell, if you haven't.  

**=> Start the Spark Shell as follows.**  
INstructor will give you URL for Spark Master.  Plug that in place of 'XXX' below

**=> Also replace the name 'My Spark Shell' to some thing unique, so we can identify the application.

```
    $    ~/spark/bin/spark-shell  --master  XXX_MASTER_URL_GOES_HERE  --name "MY Spark Shell"
```

**=> Inspect Spark Master UI and make sure your application shows up**


----------------------------
STEP 4: Process Local File Again (in Cluster Mode)
----------------------------
In the previous Spark Shell try this
```scala

    val a = sc.textFile("README-local.txt")
    a.count

```

**=> What is the result? Can you explain it?**


----------------------------
STEP 5: Process HDFS File
----------------------------
In the previous Spark Shell try this
```scala
    // Instructor will provide HDFS URL
    val a = sc.textFile("HDFS_URL_GOES_HERE/user/ec2-user/readme-in-hdfs.txt")
    a.count

```

**=> Can Spark access files on HDFS?  yay!**

**=> Read contents of entire HDFS directory**

```scala
    // read entire dir
    val b = sc.textFile("HDFS_URL_GOES_HERE/user/ec2-user/data/billing/csv/")
    b.count
```

**=> What is the result?**


----------------------------
STEP 5: Interactive With Hive (HiveContext)
----------------------------
In Spark Shell try the following

```scala
    import org.apache.spark.sql.hive.HiveContext
    val hiveCtx = new org.apache.spark.sql.hive.HiveContext(sc)

```

**=> Inspect tables and schemas**

```scala
    // inspect tables
    hiveCtx.tables
    hiveCtx.tableNames

    // inspect individual tables 
    // TODO : replace 'MY_TABLE' with the correct table name
    hiveCtx.table("MY_TABLE").printSchema
    hiveCtx.table("MY_TABLE").show
```

**=> Try a few queries**
```scala
    val count = hiveCtx.sql("select count(*) from MY_TABLE")
    count.collect
    count.show

    // try more queries...
    // find total by customer
    //      "select customer_id, SUM(cost) as total from XXXX group by customer_id"

    // find the biggest transaction cost?
    //      Hint : MAX ?
```


----------------------------
STEP 6: Spark SQL + Dataframes + Hadoop
----------------------------
In this section we are going to use Dataframes to process data in HDFS.

Try this in spark shell.

```scala
    val df1 = sqlContext.read.json("HDFS_URL/user/ec2-user/data/billing/json-100M/sample.json")
     df1.printSchema
     df1.show
```

**=> Observe the data frame processing**


**=> Now try to read the entire JSON directory**

```scala
    // pull in all data
     val df1 = sqlContext.read.json("HDFS_URL_HERE/user/ec2-user/data/billing/json-100M/")
     df1.printSchema
     df1.show
```

**=> Using SQL in Dataframes**

```scala
    // let's prep for SQL
    // TODO : change MY_SPARK_TABLE to some thing appropriate
     df1.registerTempTable("MY_SPARK_TABLE")

     // see tables
     sqlContext.tableNames
     sqlContext.tables("MY_SPARK_TABLE")
     sqlContext.tables("MY_SPARK_TABLE").show
```

**=> Issue a few queries**

```scala
    // run queries
     sqlContext.sql("select * from MY_SPARK_TABLE").show
     sqlContext.sql("select count(*) from MY_SPARK_TABLE").show

     // more queries
     // q1 : find total by customer
     // q2 : find my top-10 customes
```


----------------------------
STEP 7: Discussion Time
----------------------------
Discuss the findings...for example things like
- performance (run time)
- ease of use
- any thing else...