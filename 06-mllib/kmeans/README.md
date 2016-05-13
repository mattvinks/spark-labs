<link rel='stylesheet' href='../../assets/main.css'/>

[<< back to main index](../../README.md) 

# KMeans Lab

In this lab we discuss how to use kmeans clustering in Spark.

Step 1: Examine the MTCars dataset
---------------

Check out the dataset that we are going to cluster: MTCars. Those of you
with experience in R should remember this as one of R's internal datasets.

This dataset contains some statistics on 1974 Cars from Motor Trends

<img src="../../images/6.1-cars2.png" style="border: 5px solid grey; max-width:100%;" />

You can also download and view the raw data in Excel : [cars.csv](../../data/mtcars/mtcars.csv)

Here are the columns:
* name   - name of the car
*  mpg   - Miles/(US) gallon                        
*  cyl   - Number of cylinders                      
*  disp  - Displacement (cu.in.)                    
*  hp    - Gross horsepower                         
*  drat  - Rear axle ratio            
*  mpg   - Miles/(US) gallon                        
*  cyl   - Number of cylinders                      
*  disp  - Displacement (cu.in.)                    
*  hp    - Gross horsepower                         
*  drat  - Rear axle ratio            

Are there any natural clusters you can identify from this data?


Step 2: Inspect the script
--------------
Open file `kmeans_mtcars.scala` and examine it.  
This is a fully functional K-means script.  
We are going to run this **line-by-line** in Spark Shell to understand what is going on.

Step 3 : Launch Spark Shell
-----------
```
    # go to the kmeans dir
    $  cd   ~/spark-labs/6-mllib/kmeans

    # start shell
    $  ~/spark/bin/spark-shell
```

**=> Open the script `kmeans_mtcaras.scala` in a favorite editor.**  

**=> Copy paste snippets from text editor into Spark shell.  Watch the execution and the results**  
Hint : When copying functions like `parseData` copy the entire function {} instead of line by line.

**=> Also keep an eye on Spark web UI (4040) **


Step 4 : ParseData
----------
In this function, we're going to go from the RDD input file
to an output as a tuple of Vector Names and Vectors.  Vector 
names are derived from the first (0th) column, whereas the
numerical data comprises the rest, which is converted into
a vector.dense

    def parseData(vals : RDD[String]) : RDD[(String, Vector)] = {
      vals.map { s =>
        val splitData = s.split(',')
        val numericFields = splitData.drop(1)
        val name = splitData(0)
        val doubles = numericFields.map(_.toDouble)
        val vectors = Vectors.dense(doubles)
        (name, vectors)
      }
    }


Note: In scala we could re-write this whole function as the following one-liner:

    onlyVectors = data.map(s => Vectors.dense(s.split(',').drop(1).map(_.toDouble)))


Step 5: Make an RDD of ONLY the vectors (not the names)
------------------
Now that we have the names and vectors, we can go back and get only the vectors, as kmeans only works on an RDD of vectors (no names attached).

```scala
    val data = sc.textFile("../../data/mtcars/mtcars.csv")
    val namesAndData = parseData(data)
    val onlyVectors = namesAndData.map { case (name, vector) => vector } 
```


Step 6: Run KMeans
-----------------
The following code runs KMeans

```scala
    val clusters = KMeans.train(onlyVectors, 2, 10)
```

* first argument : the vectors RDD
* second argument (2) : how many clusters (this is the K in KMeans)
* number of iterations (10)


Step 7 : Printing out the clusters
----------------------
The following code prints out the clusters in a user-friendly way
```scala
    val carsByCluster =namesAndData.map(s => (clusters.predict(s._2), (s._1,s._2))).sortByKey().collect()

    carsByCluster.foreach { println }
```

The output may look like the following.  
Here we see **two clusters** (cluster 0 and cluster 1).  
**Compare the cars in each cluster,  Pay special attention to MPG (first attribute in vector) and CYLINDERS (second attribute).**

```console
    (0,("Hornet 4 Drive",[21.4,6.0,258.0,110.0,3.08,3.215,19.44,1.0,0.0,3.0,1.0]))
    (0,("Hornet Sportabout",[18.7,8.0,360.0,175.0,3.15,3.44,17.02,0.0,0.0,3.0,2.0]))
    (0,("Duster 360",[14.3,8.0,360.0,245.0,3.21,3.57,15.84,0.0,0.0,3.0,4.0]))
    (0,("Merc 450SE",[16.4,8.0,275.8,180.0,3.07,4.07,17.4,0.0,0.0,3.0,3.0]))
    (0,("Merc 450SL",[17.3,8.0,275.8,180.0,3.07,3.73,17.6,0.0,0.0,3.0,3.0]))
    (0,("Merc 450SLC",[15.2,8.0,275.8,180.0,3.07,3.78,18.0,0.0,0.0,3.0,3.0]))
    (0,("Cadillac Fleetwood",[10.4,8.0,472.0,205.0,2.93,5.25,17.98,0.0,0.0,3.0,4.0]))
    (0,("Lincoln Continental",[10.4,8.0,460.0,215.0,3.0,5.424,17.82,0.0,0.0,3.0,4.0]))
    (0,("Chrysler Imperial",[14.7,8.0,440.0,230.0,3.23,5.345,17.42,0.0,0.0,3.0,4.0]))
    (0,("Dodge Challenger",[15.5,8.0,318.0,150.0,2.76,3.52,16.87,0.0,0.0,3.0,2.0]))
    (0,("AMC Javelin",[15.2,8.0,304.0,150.0,3.15,3.435,17.3,0.0,0.0,3.0,2.0]))
    (0,("Camaro Z28",[13.3,8.0,350.0,245.0,3.73,3.84,15.41,0.0,0.0,3.0,4.0]))
    (0,("Pontiac Firebird",[19.2,8.0,400.0,175.0,3.08,3.845,17.05,0.0,0.0,3.0,2.0]))
    (0,("Ford Pantera L",[15.8,8.0,351.0,264.0,4.22,3.17,14.5,0.0,1.0,5.0,4.0]))
    (0,("Maserati Bora",[15.0,8.0,301.0,335.0,3.54,3.57,14.6,0.0,1.0,5.0,8.0]))
    (1,("Mazda RX4",[21.0,6.0,160.0,110.0,3.9,2.62,16.46,0.0,1.0,4.0,4.0]))
    (1,("Mazda RX4 Wag",[21.0,6.0,160.0,110.0,3.9,2.875,17.02,0.0,1.0,4.0,4.0]))
    (1,("Datsun 710",[22.8,4.0,108.0,93.0,3.85,2.32,18.61,1.0,1.0,4.0,1.0]))
    (1,("Valiant",[18.1,6.0,225.0,105.0,2.76,3.46,20.22,1.0,0.0,3.0,1.0]))
    (1,("Merc 240D",[24.4,4.0,146.7,62.0,3.69,3.19,20.0,1.0,0.0,4.0,2.0]))
    (1,("Merc 230",[22.8,4.0,140.8,95.0,3.92,3.15,22.9,1.0,0.0,4.0,2.0]))
    (1,("Merc 280",[19.2,6.0,167.6,123.0,3.92,3.44,18.3,1.0,0.0,4.0,4.0]))
    (1,("Merc 280C",[17.8,6.0,167.6,123.0,3.92,3.44,18.9,1.0,0.0,4.0,4.0]))
    (1,("Fiat 128",[32.4,4.0,78.7,66.0,4.08,2.2,19.47,1.0,1.0,4.0,1.0]))
    (1,("Honda Civic",[30.4,4.0,75.7,52.0,4.93,1.615,18.52,1.0,1.0,4.0,2.0]))
    (1,("Toyota Corolla",[33.9,4.0,71.1,65.0,4.22,1.835,19.9,1.0,1.0,4.0,1.0]))
    (1,("Toyota Corona",[21.5,4.0,120.1,97.0,3.7,2.465,20.01,1.0,0.0,3.0,1.0]))
    (1,("Fiat X1-9",[27.3,4.0,79.0,66.0,4.08,1.935,18.9,1.0,1.0,4.0,1.0]))
    (1,("Porsche 914-2",[26.0,4.0,120.3,91.0,4.43,2.14,16.7,0.0,1.0,5.0,2.0]))
    (1,("Lotus Europa",[30.4,4.0,95.1,113.0,3.77,1.513,16.9,1.0,1.0,5.0,2.0]))
    (1,("Ferrari Dino",[19.7,6.0,145.0,175.0,3.62,2.77,15.5,0.0,1.0,5.0,6.0]))
    (1,("Volvo 142E",[21.4,4.0,121.0,109.0,4.11,2.78,18.6,1.0,1.0,4.0,2.0]))
```



**=> Does the clustering make sense?  Perhaps we have too few clusters?** 


Step 8 : Run the whole script
------------
**=> Exit spark shell, by pressing Ctrl+D**

**=> Run the spark shell in terminal as follows**

```
    $ ~/spark/bin/spark-shell -i kmeans_mtcars.scala
```

This will run the entire script in one go!


Step 9 : Adjust the cluster number (K)
------
Edit file `kmeans_mtcars.scala`.   
**=> Change the `number of clusters` argument supplied to `Kmeans.train()` from `2` to `3`  function (around line # 60).**  

Run the script again
```
    $ ~/spark/bin/spark-shell -i kmeans_mtcars.scala
```

Inspect the output.   
Does clustering of cars make sense?
Try different values of `K` (4 or 5).

Make a note of the "wall clock time. " We will optimize this later.

Step 10: Optimize Script
----------

Even though the dataset is tiny, notice that the datasets are running on disk without caching.  Could the cache() method help us at all?  

Edit file `kmeans_mtcars.scala`.  
Cache RDD `onlyVectors` , around line number 50, as follows
```scala
    onlyVectors.cache
    onlyVectors.count  // force caching
```

Then run again and note the wall clock time.
```
    $ time  ~/spark/bin/spark-shell -i kmeans_mtcars.scala
```



Step 11: Record and Plot WSSSE versus K
-----------
Perform a plot of WSSSE versus K.  (Use excel or whatever application you prefer).   
Use the "elbow" method to pick what seems to be a good value of k.  Does that match your intuitive sense of what is the best?


Here is a [sample spreadsheet](WSSSE-versus-k.xlsx) we did.

And here is the 'elbow curve'.   

<img src="../../images/6.1-wssse-vs-k.png" style="border: 5px solid grey; max-width:100%;" />

Step 12: Add some new data, check and see if it changes the clusters.
-------
Edit input data file `../../../data/mtcars/mtcars.csv`.  
Add some new rows to the mtcars dataset based on your favorite cars (or just
make up some fictitious cars).

Then run the script again.
```
    $ time  ~/spark/bin/spark-shell -i kmeans_mtcars.scala
```

See how adding this affects the way the data is clustered?


BONUS: Automatically iterate across k.
------------
Is there a way you could modify this to loop through values of K instead of 
manually changing the values?  

BONUS 2: Programmatically Perform Elbow Method
--------

Using the principles of the "elbow" method, what is a way you could automatically
select a k value?