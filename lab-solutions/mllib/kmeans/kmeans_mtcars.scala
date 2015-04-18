import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors

// MT Cars Data is as follows:
//"name","mpg","cyl","disp","hp","drat","wt","qsec","vs","am","gear","carb"
//Header row does NOT appear in daset

// Load and parse the data
val data = sc.textFile("../../../data/mtcars/mtcars.csv")
val splitData = data.map(s => s.split(','))
val parsedData = data.map(s => Vectors.dense(s.split(',').drop(1).map(_.toDouble))).cache()
val NamesandData = data.map(s => (s.split(',')(0), Vectors.dense(s.split(',').drop(1).map(_.toDouble)))).cache()

// Cluster the data into two classes using KMeans
val numClusters = 2 // Value of K in Kmeans
val clusters = KMeans.train(parsedData, numClusters, 20)

// Evaluate clustering by computing Within Set Sum of Squared Errors
val WSSSE = clusters.computeCost(parsedData)
println("Within Set Sum of Squared Errors = " + WSSSE)

// Print out a list of the clusters and each point of the clusters
val groupedClusters = NamesandData.groupBy{rdd => clusters.predict(rdd._2)}.collect()

groupedClusters.foreach { println }



