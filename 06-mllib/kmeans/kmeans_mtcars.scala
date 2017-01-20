import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors


// Conversion function for string --> float
def convertColumn(df: org.apache.spark.sql.DataFrame, name:String, newType:String) = {
  val df_1 = df.withColumnRenamed(name, "swap")
  df_1.withColumn(name, df_1.col("swap").cast(newType)).drop("swap")
}


// Loads data.
val dataset = spark.read.option("header", "true").csv("../../data/mtcars/mtcars_header.csv")

val dataset1 = convertColumn(dataset, "mpg", "float")
val dataset2 = convertColumn(dataset1, "cyl", "float")

val assembler = new VectorAssembler().setInputCols(Array("mpg", "cyl")).setOutputCol("features")

val featureVector = assembler.transform(dataset2)

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

