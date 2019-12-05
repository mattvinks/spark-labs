from pyspark.sql import SparkSession
from pyspark.sql.functions import explode
from pyspark.sql.functions import split

# Initialize Spark
spark = SparkSession \
    .builder \
    .appName("Structed Streaming") \
    .getOrCreate()

# Set loglevel to Error
spark.sparkContext.setLogLevel("ERROR")


## TODO-1 : read from socket 10000
lines = spark \
    .readStream \
    .format("socket") \
    .option("host", "localhost") \
    .option("port", 10000) \
    .load()

# query1 = lines \
#     .writeStream \
#     .outputMode("append") \
#     .format("console") \
#     .start()

# query1.awaitTermination()



## TODO-2  :filter lines that has 'x'
x = lines.filter(lines["value"].contains("x"))

## TODO-3 : To run query2 , comment out query1
query2 = x \
        .writeStream \
        .outputMode("append") \
        .format("console") \
        .start()
query2.awaitTermination()


# wait forever until user terminate manually
spark.streams.awaitAnyTermination() 
spark.stop()
