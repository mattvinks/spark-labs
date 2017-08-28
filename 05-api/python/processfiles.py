# Usage:
# spark-submit  --master spark://localhost:7077 processfiles.py  <files to process>

# Multiple files can be specified
# file to process can be :  /etc/hosts
#                           scripts/1M.data
#                           s3n:#elephantscale-public/data/twinkle/100M.data
#                           tachyon:#tachyon_ip_address:19998/file

#e.g:
#- with 4G executor memory and turning off verbose logging
#   spark-submit  --master spark:#localhost:7077 --executor-memory 4g   --driver-class-path logging/    processfiles.py  s3n:#/elephantscale-public/data/twinkle/1G.data
#

import sys
import time
from pyspark.sql import SparkSession

if len(sys.argv) < 1:
    sys.exit("need file(s) to load")

## TODO 1: Give a name
spark = SparkSession.builder.appName("Process Files -- MYNAME").getOrCreate()

for file in sys.argv:
    f = spark.read.text(file) 

    t1 = time.time()
    count = f.rdd.count()
    t2 = time.time()

    print("### {}: count:  {} ,  time took:  {} ms".format(file, count, (t2-t1)/1e6))
    line = input('### Hit enter to terminate the program...')

    spark.stop()  # close the session
