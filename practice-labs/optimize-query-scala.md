<link rel='stylesheet' href='../assets/css/main.css'/>


# Optimize SQL query
We are using clickstream data.

**Work in pairs**

## Step 1 : Inspect clickstream / domain data

Clickstream data looks like this
```json
{"session": "session_36", "domain": "youtube.com", "cost": 118, "user": "user_9", "campaign": "campaign_19", "ip": "ip_4", "action": "clicked", "timestamp": 1420070400000}

{"session": "session_96", "domain": "facebook.com", "cost": 5, "user": "user_5", "campaign": "campaign_12", "ip": "ip_3", "action": "blocked", "timestamp": 1420070400864}
```

Domain data looks like this

```json
{"domain":"amazon.com","category":"SHOPPING"}
{"domain":"bbc.co.uk","category":"NEWS"}
{"domain":"facebook.com","category":"SOCIAL"}
```

## Step 2 : Read data
```scala
val clickstream = spark.read.json("/data/click-stream/json/")
clickstream.createOrReplaceTempView("clickstream")
clickstream.printSchema
clickstream.count
clickstream.show

val domains = spark.read.json("/data/click-stream/domain-info.json")
domains.createOrReplaceTempView("domains")
domains.printSchema
domains.count
domains.show
```

## Step 3 : Find traffic per domain-category
Come up with SQL query for this.  

Hint: Start with sql query like
```scala

val sql="""select clickstream.*, domains.*
from clickstream join domains  
ON (clickstream.domain = domains.domain)
"""
joined = spark.sql(sql)
```

Sort the output in descending order.  

Sample output might be (not actual numbers)
```console
SHOPPING 300
NEWS     200
SOCIAL   100
```


## Step 3 : Ready, set, Optimize!
Now apply all the tricks you have learned so far and execute the query in fastest time possible.

Be creative!
