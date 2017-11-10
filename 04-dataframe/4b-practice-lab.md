<link rel='stylesheet' href='../assets/css/main.css'/>

[<< back to main index](../README.md)

Lab 4B : Practice Lab
=================


### Overview
Deeper analytics of Clickstream data

### Depends On
- [4.1 Dataframe](4.1-dataframe.md)
- [4.2 SQL](4.2-sql.md)
- [4.3 Dataset](4.3-dataset.md)

### Run time
20-30 mins

## Analyze Clickstream logs

### Data Format:

The data is in [data/click-stream/clickstream.json](/data/click-stream/clickstream.json)

Clickstream data looks like this
```
{"session": "session_36", "domain": "youtube.com", "cost": 118, "user": "user_9", "campaign": "campaign_19", "ip": "ip_4", "action": "clicked", "timestamp": 1420070400000}

{"session": "session_96", "domain": "facebook.com", "cost": 5, "user": "user_5", "campaign": "campaign_12", "ip": "ip_3", "action": "blocked", "timestamp": 1420070400864}
```


Domains data looks like this
```
{"domain":"amazon.com","category":"SHOPPING"}
{"domain":"bbc.co.uk","category":"NEWS"}
{"domain":"facebook.com","category":"SOCIAL"}
```

## Read the data and export them as tables
```
val clickstreamDF = spark.read.json("/data/click-stream/json")
clickstreamDF.createOrReplaceTempView("clickstream")

val domainsDF = spark.read.json("/data/click-stream/domain-info.json")
domainsDF.createOrReplaceTempView("domains")

```

## Answer the following Queries

#### find all distinct domains we have seen

#### find total amount (`cost`) spent on each domain.  Sort the data in descending order
sample output
```console
+-------------+--------+
| domain      | cost   |
+-------------+--------+
|facebook.com | 10,100 |
|youtube.com  |  8,900 |
+-------------+--------+
```


### Find total money (clickstream.cost) spent per domain.CATEGORY
e.g  
    SOCIAL  100  
    NEWS    200  

Hint: 'join'


### Optimize the above query
Make the above query as fast as possible!  

Hints:   
- convert JSON to parquet!
- ???

#### calculate clicks/view ratio for each domain.  Sort the data in descending order
sample output
```console
+-------------+---------+---------+---------+-------------------------+
| domain      | views   |  clicks |  blocks |   clicks_per_1000_views |
|             |         |         |         |   (=clicks*1000/views)  |
+-------------+---------+---------+---------+-------------------------+
|facebook.com | 10,000  |   100   |     500 |        10               |
|youtube.com  |  5,000  |    20   |    1000 |         4               |
+-------------+---------+---------+---------+-------------------------+
```

#### Which domain gets most 'bang for the buck' ?
Calculate total cost spent vs. number of clicks from that domain.  
Another way to think about this is, how much are we spending to get a click from a domain?  (Don't forget to factor in money spent on blocks as well!)
