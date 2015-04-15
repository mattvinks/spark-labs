<< [back to main index](../README.md) / [MapReduce labs](./README.md)

Lab 4.2 : Clickstream analysis
==============================
### Overview
Use MapReduce to analyze clickstream data

### Depends On 
[1-mapreduce-intro](1-intro.md)

### Run time
20 mins

----------------------------------------
STEP 1:  Inspect clickstream sample data
----------------------------------------

Clickstream data has the following format
```
timestmap, user_id,  action,  domain,  campaign_id,  cost, session
```
```
1325376000000,12552,viewed,sf.craigslist.org,16,59,session_41
1325376000864,68201,blocked,cnn.com,17,122,session_90
1325376005184,55235,clicked,google.com,18,134,session_61
```

Sample file located at  `~/spark-labs/data/click-stream/sample.txt`

We'd like to find out 'traffic from domains'


-------------------------
STEP 2: Start Spark Shell
-------------------------
```
$   cd ~/spark-labs
$   ~/spark/bin/spark-shell
```


-----------------------------------
STEP 3: Load the sample clickstream
-----------------------------------
```scala
// scala
val clicks = sc.textFile("data/click-stream/sample.txt")
```

**apply map() function to clicks**  
```scala
// scala
val tokens = map(lines => lines.split(","))
```

**Inspect the output**

**Extract 'domain' field**  
Hint : `lines.split(",")(???)  // what is the position of domain?`


----------------------
STEP 4: Create KV pair
----------------------
**Emit `(domain, 1)` from map**  
**print out the results (hint : `collect`)**
```scala
// scala
// val domainsKV = .....map(line => (extract_domain, 1))
```

--------------------------------
STEP 5:  Produce count by domain
--------------------------------
hint : `countByKey`
```scala
// scala
// val domainCount = domainsKV.???
```

**print out the results**


----------------------------------------------------------
Step 6:  Calculate advertising money spent on each domains
----------------------------------------------------------
hint 1 : there is a `cost` field  
hint 2 : instead of `countByValue`  use  `reduceByKey`


------------------------
Step 7: Find top domains
------------------------
**Sort the domain stats by frequency (top domain first)**


----
BONUS LAB 3)  Find top domains that users click most
----
  Hint : filter for 'click' action


----
BONUS LAB 4)  Find  view/click ratio for each domain
----

