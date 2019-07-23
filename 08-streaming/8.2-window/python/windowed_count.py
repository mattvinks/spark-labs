from pyspark.sql import SparkSession
from pyspark.streaming import StreamingContext


def mapRed(line):
    tokens = line.split(",")
    if len(tokens) > 3:
        action = tokens[3]  # either blocked / viewed / clicked
        return action, 1
    else:
        return "Unknown", 1


spark = SparkSession \
    .builder \
    .appName("WindowedCount") \
    .getOrCreate()

sc = spark.sparkContext

ssc = StreamingContext(sc, 10)
ssc.checkpoint("../checkpoints")

lines = ssc.socketTextStream("localhost", 9999)
actions_kv_pairs = lines.map(lambda line: mapRed(line))

actions_kv_pairs.pprint()

# TODO-1: Try 10 seconds for both the values for window intervals
# reduceByKeyAndWindow (reduce func,  window duration, sliding window)
# window duration (last 10 seconds)
# sliding window (10 seconds)

# windowed_action_counts = actions_kv_pairs.reduceByKeyAndWindow(lambda a, b: ???, ???, ???)
# windowed_action_counts.pprint()

ssc.start()
ssc.awaitTermination()
