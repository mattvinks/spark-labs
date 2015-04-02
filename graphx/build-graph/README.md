## Lab n.n : GraphX

### OverView
We will be running solutions for graphs using GraphX 

### Depends On 
None

### Run time
20 mins


## STEP 1: Login to your Spark node, start Spark shell
Instructor will provide details

## Build the graph from vertices and edges
 
All of the following steps are performed by entering the commands in the Spark Scala shell
 
     import org.apache.spark.graphx._
     import org.apache.spark.rdd.RDD
 
     val vertexArray = Array(
      (1L, ("Alice", 28)),
      (2L, ("Bob", 27)),
      (3L, ("Charlie", 65)),
      (4L, ("David", 42)),
      (5L, ("Ed", 55)),
      (6L, ("Fran", 50))
     )
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
 
     val vertexRDD: RDD[(Long, (String, Int))] = sc.parallelize(vertexArray)
     val edgeRDD: RDD[Edge[Int]] = sc.parallelize(edgeArray)
 
     val graph: Graph[(String, Int), Int] = Graph(vertexRDD, edgeRDD)
 
      graph.vertices.filter { case (id, (name, age)) => age > 30 }.collect.foreach {
       case (id, (name, age)) => println(s"$name is $age") }
  