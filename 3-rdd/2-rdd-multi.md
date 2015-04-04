[<< back to main index](../README.md) / [RDD labs](./README.md)

Lab 3.2 : Operations On Multiple RDDs
=====================================
### Overview
learn operations that work with multiple RDDs

### Depends On 
None

### Run time
15 mins


--------
Meetup Recommendation
--------
User1 attends meetups  m1, m2 and m3.  
User2 attends meetups  m2, m3, m4  and m5

![generated files](../images/3.2.png)

** TODO: Find meetups common to both users ** 

** TODO: Find meetups attened by either user1 or user2 **

** TODO : Find meetups that only user1 attends **

** TODO: Recommending meetups to user**  
user1 and user2 has a couple of meetups in common.  Let's use to this to recommend meetups to both users  
* meetups recommended for user1 : m4 & m5
* meetups recommended for user2 : m1


-----
Hints
-----

### Step 1: start spark shell

### Step 2: create data sets using parallelize() method
#### ==   scala
```scala
val u1 = sc.parallelize(List("m1", "m2", "m3"))
```

#### == python
```python
u1 = sc.parallelize(["m1", "m2", "m3"])
```

### Step 3 : try the following operations in RDDs
`union`, `intersection`,  `distinct`,  `subtract`