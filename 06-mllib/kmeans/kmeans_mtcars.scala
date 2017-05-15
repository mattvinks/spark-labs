// execute this script from within 'spark-labs' directory

import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

// Load data
val dataset = spark.read.option("header", "true").
              option("inferschema", "true").
              csv("../../data/mtcars/mtcars_header.csv")

// ## TODO-1 : display the dataset
dataset.???

// ## TODO-2 : extract the columns we need : model, mpg and cyl
val dataset2 = dataset.select("model", "???", "???")

// ## TODO-3 : display dataset2
dataset2.???

val assembler = new VectorAssembler().
                    setInputCols(Array("mpg", "cyl")).
                    setOutputCol("features")
val featureVector = assembler.transform(dataset2)

// ## TODO-4 : display featureVector, what attributes do you see?
featureVector.???
// if you only getting partial data use `featureVector.show(40)`

// Trains a k-means model.
// ## TODO-5 : set K = 2, maxIterations to 10
val kmeans = new KMeans().setK(???).setMaxIter(???)
val model = kmeans.fit(featureVector)

// Evaluate clustering by computing Within Set Sum of Squared Errors.
val WSSSE = model.computeCost(featureVector)
println(s"Within Set Sum of Squared Errors = $WSSSE")

// Shows the result.
println("Cluster Centers: ")
model.clusterCenters.foreach(println)

// predict clusters
val predicted = model.transform(featureVector)
predicted.show

// print sorted by 'prediction'
predicted.sort("prediction").show

// lets count cars in each group
predicted.groupBy("prediction").count.show


