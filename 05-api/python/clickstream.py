# Usage:
# spark-submit  clickstream.py  <files to process>
# spark-submit  --master spark://localhost:7077 clickstream.py  <files to process>

# Multiple files can be specified

#e.g:
#- with 4G executor memory and turning off verbose logging
#   spark-submit  --master spark://localhost:7077 --executor-memory 4g   --driver-class-path logging/    clickstream.py  /data/cickstream/json/
#

import sys
import time
from pyspark.sql import SparkSession

if len(sys.argv) < 2:
    sys.exit("need file(s) to load")

## TODO-1: Give a name
spark = SparkSession.builder.appName("Process Clickstream -- MYNAME").getOrCreate()

f = sys.argv[1]

## TODO-2 : Load json data
##  hint : spark.read.json()
# clickstream = ???(input)
clickstream = spark.read.json(f)

## TODO-3 : count how many records
count = 0
## count = ???
print ("record count ", count)

## TODO-4 : count things by domain
# domainCount = clickstream.groupBy("???").???()
# domainCount.show()

line = input('### Hit Ctrl+C to terminate the program...')
spark.stop()  # close the session
