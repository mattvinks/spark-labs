from pyspark.sql import SparkSession
from pyspark.sql.functions import explode
from pyspark.sql.functions import split

# Initialize Spark
spark = SparkSession \
    .builder \
    .appName("Structed Streaming ") \
    .getOrCreate()

# Set loglevel to Error
spark.sparkContext.setLogLevel("ERROR")

lines = spark \
    .readStream \
    .format("socket") \
    .option("host", "localhost") \
    .option("port", 10000) \
    .load()

# Split the lines into words
words = lines.select(
   explode(
       split(lines.value, " ")
   ).alias("word")
)

# Generate running word count
wordCounts = words.groupBy("word").count()

 # Start running the query that prints the running counts to the console
query = wordCounts \
    .writeStream \
    .outputMode("complete") \
    .format("console") \
    .start()

query.awaitTermination()

spark.stop()
