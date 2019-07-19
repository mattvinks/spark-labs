from pyspark.sql import SparkSession
from pyspark.sql.functions import broadcast

spark = SparkSession.builder.appName("spark - MapSideJoin").getOrCreate()
sc = spark.sparkContext

# Concept:

# In general, since your data are distributed among many nodes, 
# they have to be shuffled before a join that causes significant network I/O and slow performance. 

# In cases if we need to join large tables(fact tables) with smaller ones(dimension tables),
# instead of sending large table data over network, we can just broadcast the smaller table to
# all nodes to perform a map-side/broadcast join.

# Using broadcast variables which can be shared across nodes 
# ------------------------------------------IMPORTANT----------------------------------------
# Samples Used:(In this project directory)
# 1. store_locations.json
# 2. us_states.json
#  *---------------------------------------------------------------------------------------------

states_df = spark.read.json('/data/joins/us_states.json')
stores_df = spark.read.json('/data/joins/store_locations.json')

# ## TODO 1 - Set stateDf as a broadcast variable so that,
#  it will be shared across the nodes in the cluster
# Hint broadcast method available in sparkcontext
# Create a DataFrame of US state data with the broadcast variables.

# states_df_broadcasted = ??????????????????

# More hint: 
# states_df_broadcasted = broadcast(states_df)

states_df_broadcasted = broadcast(states_df)

# Join the DataFrames to get an aggregate count of stores in each US Region
print("###Number of stores in each US region :")

joined_df = stores_df.join(states_df_broadcasted, "state").groupBy("census_region").count();		

joined_df.show()

print("Press Enter to continue...")

scanner = sc._gateway.jvm.java.util.Scanner
sys_in = getattr(sc._gateway.jvm.java.lang.System, 'in')
result = scanner(sys_in).nextLine()

spark.stop()

