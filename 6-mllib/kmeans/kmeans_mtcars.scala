/*
   1 ) Enter these instructions on Spark Shell... one by one.
        So you can see what's going on.

   2) Also keep an eye on Spark Shell UI (port 4040) as you are executing the statements..

    3) After you got it working, you can run the entire script as 
            $   ~/spark/bin/spark-shell  -i  kmeans_mtcars.scala
*/

import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.Vector

// MT Cars Data is as follows:
//"name","mpg","cyl","disp","hp","drat","wt","qsec","vs","am","gear","carb"
//Header row does NOT appear in dataset
def parseData(vals : RDD[String]) : RDD[(String, Vector)] = {
  vals.map { s =>
    val splitData = s.split(',')
    val numericFields = splitData.drop(1)
    val name = splitData(0)
    val doubles = numericFields.map(_.toDouble)
    val vectors = Vectors.dense(doubles)
    (name, vectors) // (key , value) RDD
  }
}


// ## TODO-1 : load data
// Load and parse the data
val data = sc.textFile("../../data/mtcars/mtcars.csv")
data.collect

// ## TODO-2 parse the data and print
val NamesandData = parseData(data)
NamesandData.collect


// ## TODO-3 : Get only the vector out of NamesandData (the second item in the tuple)
val onlyVectors = NamesandData.map { case (name, vector) => vector } 
//one-liner: 
//      val onlyVectors = data.map(s => Vectors.dense(s.split(',').drop(1).map(_.toDouble)))

onlyVectors.collect


// ## TODO-last : Enable caching as a last step
// ##  Since we are doing multiple iterations, may be caching will help
// onlyVectors.cache
// onlyVectors.count  // force caching


// ## TODO-4 : Run Kmeans
//  Kmeans (vectors,   number_of_clusters,  num_iterations)
//      number_of_clusters : (2)  this is the 'K' in K-Means.  Start with 2
//      number_if_iterations : (20) start with 10 , and try 20
val clusters = KMeans.train(onlyVectors, 2, 10)

// ## TODO-5 : Evaluate clustering by computing Within Set Sum of Squared Errors
val WSSSE = clusters.computeCost(onlyVectors)
println("Within Set Sum of Squared Errors = " + WSSSE)

// ## TODO-6 Print out a list of the clusters and each point of the clusters
val groupedClusters = NamesandData.groupBy{rdd => clusters.predict(rdd._2)}
groupedClusters.collect

// ## TODO-7 : pretty print
val carsByCluster =NamesandData.map(s => (clusters.predict(s._2), (s._1,s._2))).sortByKey().collect()
carsByCluster.foreach { println }


