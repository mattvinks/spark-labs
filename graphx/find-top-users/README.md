## Lab n.n : GraphX

### OverView
We will be running solutions for graphs using GraphX 

### Depends On 
None

### Run time
20 mins


## STEP 1: Login to your Spark node, start Spark shell
Instructor will provide details

## Load my user data and parse into tuples of user id and attribute list
 
    val lab_home = "/home/mark/projects/ES/spark-labs/"
    val users = (sc.textFile("file:///" + lab_home + "graphx/data/users.txt")
      .map(line => line.split(",")).map( parts => (parts.head.toLong, parts.tail) ))
  