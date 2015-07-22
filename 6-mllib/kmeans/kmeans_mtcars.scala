import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.Vector

// MT Cars Data is as follows:
//"name","mpg","cyl","disp","hp","drat","wt","qsec","vs","am","gear","carb"
//Header row does NOT appear in dataset

// parses a line returns named vector
def parseData(vals : RDD[String]) : RDD[(String, Vector)] = {
  vals.map { s =>
    val splitData = s.split(',')
    val numericFields = splitData.drop(1) // drop name
    val name = splitData(0)
    val doubles = numericFields.map(_.toDouble)
    val vectors = Vectors.dense(doubles)
    (name, vectors)
  }
}


// Load and parse the data
val data = sc.textFile("../../data/mtcars/mtcars.csv")
val NamesandData = parseData(data)
val onlyVectors = NamesandData.map { case (string, vector) => vector } 

// #  TODO : enable caching, which one to cache?
// #  Compare runs with / without caching
// onlyVectors.cache
// onlyVectors.count // force caching

// Cluster the data into two classes using KMeans
// TODO: Pick different values of K / numclusters
val numClusters = 2 // Value of K in Kmeans
// TODO : how many iterations?  Try values from 10-20
val numIterations = 10   

val t1 = System.nanoTime()
val clusters = KMeans.train(onlyVectors, numClusters, numIterations)
val t2 = System.nanoTime()
println("### Kmeans ran in  %,f ms".format((t2-t1)/1e6) )

// Evaluate clustering by computing Within Set Sum of Squared Errors
val WSSSE = clusters.computeCost(onlyVectors)
println("Within Set Sum of Squared Errors = " + WSSSE)

// Print out a list of the clusters and each point of the clusters
val groupedClusters = NamesandData.groupBy{rdd => clusters.predict(rdd._2)}.collect()

val carsByCluster = NamesandData.map(s => 
  (clusters.predict(s._2), s._1)).sortByKey().collect()
carsByCluster.foreach { println }

exit  // exit shell



