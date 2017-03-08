import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors


// Loads data.
val dataset = spark.read.option("header", "true").option("inferschema", "true").csv("../../data/mtcars/mtcars_header.csv")

val assembler = new VectorAssembler().setInputCols(Array("mpg", "cyl")).setOutputCol("features")

val featureVector = assembler.transform(dataset)

// Trains a k-means model.
val kmeans = new KMeans().setK(2).setSeed(1L)
val model = kmeans.fit(featureVector)

// Evaluate clustering by computing Within Set Sum of Squared Errors.
val WSSSE = model.computeCost(featureVector)
println(s"Within Set Sum of Squared Errors = $WSSSE")

// Shows the result.
println("Cluster Centers: ")
model.clusterCenters.foreach(println)

// Print results
model.transform(featureVector).show

