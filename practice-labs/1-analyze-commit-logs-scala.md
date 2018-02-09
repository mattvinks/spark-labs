<link rel='stylesheet' href='../assets/css/main.css'/>

[<< back to main index](../README.md)

# Practice Lab 1 - Analyze Spark Commits


### Overview
Process CSV using Spark2 APIs

### Depends On
- [4.1 Dataframe](../04-dataframe/4.1-dataframe-scala.md)
- [4.2 SQL](../04-dataframe/4.2-sql-scala.md)
- [4.3 Dataset](../04-dataframe/4.3-dataset-scala.md)

### Run time
20-30 mins

## Analyze Spark commit logs (CSV data)
In this lab, we are going to analyze Spark commit logs.

The data is located in `data/spark-commits` directory.
- [data/spark-commits/spark-commits.log](/data/spark-commits/spark-commits.log)  : large file with about 20,000 commit logs
- [data/spark-commits/sample.log](/data/spark-commits/sample.log) : a small sample file

### Data Format:

- SHA
- committer
- email
- date
- comment

Separator is  |
```
sha|committer|email|date|comment

8f2142cfd2ca632a4afb0cc29cc358edbb21f8d|Dilip Biswal|dbiswal@us.ibm.com|Sat Feb 25 23:56:57 2017 -0800|[SQL] Duplicate test exception in SQLQueryTestSuite due to meta files(.DS_Store) on Mac

89608cf26226e28f331a4695fee89394d0d38ea0|Wenchen Fan|wenchen@databricks.com|Sat Feb 25 23:01:44 2017 -0800|[SPARK-17075][SQL][FOLLOWUP] fix some minor issues and clean up the code

6ab60542e8e803b1d91371a92f4aaef6a64106f6|Joseph K. Bradley|joseph@databricks.com|Sat Feb 25 22:24:08 2017 -0800|[MINOR][ML][DOC] Document default value for GeneralizedLinearRegression.linkPower
```

## Queries
- Find the person with most number of commits
- How many commits came from 'databricks.com'

## Steps
This is an open lab.  
Feel free to experiment.  
Use any of the APIs (RDD / Dataframe / Dataset) to analyze the data.

### Some Hints:

`spark.read.csv`  is a handy way to read CSV files.

```
val commits = spark.read.
              option("header", "???").  // true or false?
              option("delimiter", "???").  // what is our delimiter
              csv("/data/spark-commits/spark-commits.log")
```

For group by, you can use `email` column as a unique column.  

You can group by using Dataset API  or use SQL (register DF as a temptable first)
