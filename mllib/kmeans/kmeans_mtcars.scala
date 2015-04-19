import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.linalg.Vector

// MT Cars Data is as follows:
//"name","mpg","cyl","disp","hp","drat","wt","qsec","vs","am","gear","carb"
//Header row does NOT appear in dataset

def parseData(vals : RDD[String]) : RDD[(String, Vector)] = {
  vals.map { s =>
    // TODO: split the data by commas
    val splitData = s.split(???)
    // get rid of (drop) any non-numeric fields.
    val numericFields = splitData.drop(1)
    //TODO: Get the name out of splitData (column 0)
    val name = splitData(???)
    //TODO: map all the fields to double from string (use 'toDouble')
    val doubles = numericFields.map(x => x.???)
    // Convert the doubles to a Vectors.dense
    val vectors = > Vectors.dense(doubles)
    // TODO: return a tuple of name, vectors
    (???, ???)
  }
}


// Load and parse the data
val data = sc.textFile("../../data/mtcars/mtcars.csv")
val NamesandData = parseData(data)

//TODO: Get only the vector out of NamesandData (the second item in the tuple)
val onlyVectors =  ???

// Cluster the data into two classes using KMeans
// TODO: Pick different values of K / numclusters
val numClusters = 2 // Value of K in Kmeans
val clusters = KMeans.train(onlyVectors, numClusters, 20)

// Evaluate clustering by computing Within Set Sum of Squared Errors
val WSSSE = clusters.computeCost(onlyVectors)
println("Within Set Sum of Squared Errors = " + WSSSE)

// Print out a list of the clusters and each point of the clusters
val groupedClusters = NamesandData.groupBy{rdd => clusters.predict(rdd._2)}.collect()

groupedClusters.foreach { println }



