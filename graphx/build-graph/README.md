## Lab n.n : GraphX

### OverView
We will be constructing graphs and doing basic operations on them 

### Depends On 
None

### Run time
20 mins


## STEP 1: Login to your Spark node, start Spark shell
Instructor will provide details

## Step 2: Build the graph from vertices and edges, setting the data in the code
 
All of the following steps are performed by entering the commands in the Spark Scala shell

#### Import the necessary libraries
 
     import org.apache.spark.graphx._
     import org.apache.spark.rdd.RDD
 
#### Construct the array of vertices. Can you explain the structure of the elements in this array?
 
     val vertexArray = Array(
      (1L, ("Alice", 28)),
      (2L, ("Bob", 27)),
      (3L, ("Charlie", 65)),
      (4L, ("David", 42)),
      (5L, ("Ed", 55)),
      (6L, ("Fran", 50))
     )
     
     
####  Construct the array of edges. Explain how edges are referencing the vertices
     
     val edgeArray = Array(
       Edge(2L, 1L, 7),
       Edge(2L, 4L, 2),
       Edge(3L, 2L, 4),
       Edge(3L, 6L, 3),
       Edge(4L, 1L, 1),
       Edge(5L, 2L, 2),
       Edge(5L, 3L, 8),
       Edge(5L, 6L, 3)
     )
 
#### Prepare the data for Spark processing. What do ".parallelize" methods below accomplish?
 
     val vertexRDD: RDD[(Long, (String, Int))] = sc.parallelize(vertexArray)
     val edgeRDD: RDD[Edge[Int]] = sc.parallelize(???)
 
#### Construct the graph from the vertices and edges
 
     val graph: Graph[(String, Int), Int] = Graph(???, ???)
 
#### Filter by age > 30

    graph.vertices.filter { case (id, (name, age)) => age > 30 }

#### Prepare the results for further processing. What does '.collect' method do? When can you use it, and when you cannot?
 
    graph.vertices.filter { case (id, (name, age)) => age > 30 }.collect.
      
#### Print names and ages of each
      
      graph.vertices.filter { case (id, (name, age)) => age > 30 }.collect.
        foreach { case (id, (name, age)) => println(s"$name is $age") }

Note that in the last step you can enter one line at a time, so that the shell output looks as follows

    scala> graph.vertices.filter { case (id, (name, age)) => age > 30 }.collect.
     | foreach { case (id, (name, age)) => println(s"$name is $age") }
