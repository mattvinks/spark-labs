GraphX Lab 1 : Shortest Path
============================

### OverView
We will find the shortest path on the graph from one point to another. The lab is done by executing each step
in the Spark shell. This allows the student to examine and understand each step, and to modify parameters as we go.
After you have executed the code in each step individually, you will collect this code in script, 

For our data, we will use LinkedIn. Therefore, the shortest path will tell us how to connect to the target person
we want to connect with.

![Mark's LinkedIn account](../../images/linkedin.png)

### Depends On 
None

### Run time
30 mins


## STEP 0: Start Spark Shell
```bash
$   ~/spark/bin/spark-shell
```

## Step 1: Import the general GraphX libraries, for classes like Graph
 
    import org.apache.spark.graphx._
    
## Step 3: Import the library for random graph generation

Using random graphs will allow us to easily experiment with different graph sizes
    
    import org.apache.spark.graphx.util.GraphGenerators
    
## Step 4: Generate a graph

    val vertexArray = Array(
        // direct connections
        (1L, ("Mark Kerzner", 2757)),
        (2L, ("Sujee Maniyam", 891)),
        (3L, ("Yaakov Weintraub", 105)),
        (4L, ("Packt Publishing", 2984)),
        (5L, ("Barry Kaufman ", 500)),
        // indirect connections
        (6L, ("Ron Bodkin", 500)),
        (7L, ("Ron's friend", 500))
        
In this graph, we have the user name and the number of connections. The number of connections is a natural things to have; 
we will store it, but not use it at this time.
        
## Step 5: Fix the goal to reach. Set to to calculate the shortest connection paths for Mark Kerzner

    val sourceId: VertexId = ?
    
## Step 6: Initialize connections on the graph

    val edgeArray = Array(
        Edge(1L, 2L, 1),
        Edge(1L, 3L, 1),
        Edge(1L, 4L, 1),
        Edge(1L, 5L, 1),
        Edge(2L, 6L, 1),
        Edge(6L, 7L, 1)
    )

Which connections are direct for Mark and which are indirect?

## Step 7: For the graph of LinkedIn connections

    val vertexRDD: RDD[(Long, (String, Int))] = sc.parallelize(???)
    val edgeRDD: RDD[Edge[Int]] = sc.parallelize(???)
    val graph: Graph[(String, Int), Int] = Graph(???, ???)
    
## Step 8: Prepare the graph to be used for computations.

Set the initial distance from Mark to Mark to 0, and all other distances to infinity

    val initialGraph = graph.mapVertices((id, _) => if (id == sourceId) 0.0 else Double.PositiveInfinity)

## Step 9: Compute shortest distances

Use Pregel to compute shortest distances between the root and every other vertix on the graph. 
Please note that since computating the shortest distance between two vertices anyway involves computing many intermediate short distances,
Pregel takes a generic approach of computing all shortest distances

    val sssp = initialGraph.pregel(Double.PositiveInfinity)(
        (id, dist, newDist) => math.min(dist, newDist), // Vertex Program
            triplet => {  // Send Message
            if (triplet.srcAttr + triplet.attr < triplet.dstAttr) {
                Iterator((triplet.dstId, triplet.srcAttr + triplet.attr))
            } else {
                Iterator.empty
            }
        },
        (a,b) => math.min(a,b) // Merge Message
    )
    
## Step 10: Collect and print out the results
 
    println(sssp.vertices.collect.mkString("\n"))
    
Explain the results
    
## Bonus 1
 
### Collect all executed statement in a single text file.

You will be copying and pasting this file into Spark Scala shell

### Set the goal vertex in such a way that the code will through  an error. Explain why this happens

## Bonus 2
 
Construct a small graph of air flights between cities. Use 4-6 cities. Put the prices of flying
between two cities into the edges above, replacing the number "1" with the actual price.
Calculate the cheapest flights between cities.