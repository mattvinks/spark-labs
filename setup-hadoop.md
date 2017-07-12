<link rel='stylesheet' href='assets/css/main.css'/>

# Hadoop / Spark setup

### To be completed by Instructor

## 1 - Execute the setup script

``` bash
    # change MYNAME
    $  cd  ~/spark-labs/scripts/

    $  ./setup-clickstream.sh
```

## 2 - Verify data in HDFS UI


## 3 - Jupyter server start

``` bash
PYSPARK_PYTHON=python3 PYSPARK_DRIVER_PYTHON=jupyter PYSPARK_DRIVER_PYTHON_OPTS=notebook ~/spark/bin/pyspark

```
Access http://URL:8888
