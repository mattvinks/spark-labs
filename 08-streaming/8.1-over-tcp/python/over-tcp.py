from pyspark import SparkContext
from pyspark.streaming import StreamingContext

sc = SparkContext("local[2]", "OverTCP")

## TODO-1 : Create a streaming context with 5 second
## batch interval
## Hint : 5
ssc = StreamingContext(sc, 5)


## TODO-2 Create a DStream on localhost:10000
## Hint : 10000
lines = ssc.socketTextStream("localhost", 10000)
lines.pprint()

## TODO-3 : filter for lines that has 'xyz'
x = lines.filter(lambda line : "xyz" in line)
x.pprint()


## TODO-4 : save the output
x.saveAsTextFiles("out")


ssc.start()             # Start the computation
ssc.awaitTermination()  # Wait for the computation to terminate
