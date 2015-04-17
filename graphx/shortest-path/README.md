## Lab n.n : GraphX

### OverView
We will find the shortest path on the graph from one point to another. The lab is done by executing each step
in the Spark shell. This allows the student to examine and understand each step, and to modify parameters as we go.
After you have executed the code in each step individually, you will collect this code in script, 
to simplify changing and expertimening

### Depends On 
None

### Run time
30 mins


## STEP 0: Login to your Spark node, start Spark shell
Instructor will provide details

## Step 1: Import the general GraphX libraries, for classes like Graph
 
    import org.apache.spark.graphx._

## Step 2: Import the specific GraphX libraries
 
    import org.apache.spark.graphx._
    
## Step 3: Import the library for random graph generation

Using random graphs will allow us to easily experiment with different graph sizes
    
    import org.apache.spark.graphx.util.GraphGenerators
    
## Step 4: Generate a graph

Note that our graph will have integers for vertices. On the edges we will put the distance between the two vertices. 
Later you will be able to add properties both to the vertices and edges. At first, we will use the graph size of 10. 
Later we will be able to experiment with larger graph sizes.

    val graph: Graph[Int, Double] =
        GraphGenerators.logNormalGraph(sc, numVertices = 10).mapEdges(e => e.attr.toDouble)
        
## Step 5: Fix the goal to reach. You can use any number, as long as it is within the graph size

    val sourceId: VertexId = 4
    
## Step 6: Initialize distances on the graph

Give the initial distance of infinity to all vertices other than the root

    val initialGraph = graph.mapVertices((id, _) => if (id == sourceId) 0.0 else Double.PositiveInfinity)

## Step 7: Compute shortest distances

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
    
## Step 8: Print out the results
 
    println(sssp.vertices.collect.mkString("\n"))
    
## Step 9: Start experimenting
 
### Collect all executed statement in a single text file.

You will be copying and pasting this file into Spark Scala shell

### Set the goal vertex in such a way that the code will through  an error. Explain why this happens

### Run the code on a larger graph of your choice

### Go back to a smaller size, then add vertix names to the graph, generating them randomly

## Step 10: Bonus lab
 
Construct the graph from the city distances given in the data/cities.txt file. Then calculate shortest distances.
    