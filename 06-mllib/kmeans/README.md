<link rel='stylesheet' href='../../assets/css/main.css'/>

[<< back to main index](../../README.md)

# KMeans Lab

In this lab we discuss how to use kmeans clustering in Spark.

## Step 1: Examine the MTCars dataset

Check out the dataset that we are going to cluster: MTCars. Those of you
with experience in R should remember this as one of R's internal datasets.

This dataset contains some statistics on 1974 Cars from Motor Trends

<img src="../../assets/images/6.1-cars2.png" style="border: 5px solid grey; max-width:100%;" />

You can also download and view the raw data in Excel : [cars.csv](../../data/mtcars/mtcars.csv)

Here are the columns:
* name   - name of the car
*  mpg   - Miles/(US) gallon                        
*  cyl   - Number of cylinders                      
*  disp  - Displacement (cu.in.)                    
*  hp    - Gross horsepower                         
*  drat  - Rear axle ratio            

Are there any natural clusters you can identify from this data?

We are going to use **MPG and CYL** attributes to cluster.


## Step 2: Inspect the script
Open file `kmeans_mtcars.scala` and examine it.  
This script has some *TODO* items in it.  
We are going to fix these TODO items and run the script **line-by-line** in Spark Shell to understand what is going on.

## Step 3 : Launch Spark Shell
```bash
    # go to the kmeans dir
    $  cd   ~/spark-labs/06-mllib/kmeans

    # start shell
    $  ~/spark/bin/spark-shell
```

**=> Open the script `kmeans_mtcaras.scala` in a favorite editor.**  

**=> Copy paste snippets from text editor into Spark shell.  Watch the execution and the results**  

**==> And record the K vs.  WSSE values in the provided [excel sheet](WSSSE-versus-k.xlsx). **  

**=> Also keep an eye on Spark web UI (4040)**

## Step 4: Run the whole script

```bash
    # go to the kmeans dir
    $  cd   ~/spark-labs/06-mllib/kmeans  

    # supply the file to run with -i flag
    $ ~/spark/bin/spark-shell -i kmeans_mtcars.scala
```


## ---- [Optional] Following is Explanation ---


## Step 5 : Printing out the clusters
The following code prints out the clusters in a user-friendly way
```scala
model.transform(featureVector).show

```

The output may look like the following.  
Here we see **two clusters** (cluster 0 and cluster 1).  
**Compare the cars in each cluster,  Pay special attention to MPG (first attribute in vector) and CYLINDERS (second attribute).**

```console
+-------------------+-----+---+----+-----+-----+---+---+----+----+----+---+--------------------+----------+
|              model| disp| hp|drat|   wt| qsec| vs| am|gear|carb| mpg|cyl|            features|prediction|
+-------------------+-----+---+----+-----+-----+---+---+----+----+----+---+--------------------+----------+
|          Mazda RX4|  160|110| 3.9| 2.62|16.46|  0|  1|   4|   4|21.0|6.0|          [21.0,6.0]|         1|
|      Mazda RX4 Wag|  160|110| 3.9|2.875|17.02|  0|  1|   4|   4|21.0|6.0|          [21.0,6.0]|         1|
|         Datsun 710|  108| 93|3.85| 2.32|18.61|  1|  1|   4|   1|22.8|4.0|[22.7999992370605...|         1|
|     Hornet 4 Drive|  258|110|3.08|3.215|19.44|  1|  0|   3|   1|21.4|6.0|[21.3999996185302...|         1|
|  Hornet Sportabout|  360|175|3.15| 3.44|17.02|  0|  0|   3|   2|18.7|8.0|[18.7000007629394...|         0|
|            Valiant|  225|105|2.76| 3.46|20.22|  1|  0|   3|   1|18.1|6.0|[18.1000003814697...|         0|
|         Duster 360|  360|245|3.21| 3.57|15.84|  0|  0|   3|   4|14.3|8.0|[14.3000001907348...|         0|
|          Merc 240D|146.7| 62|3.69| 3.19|   20|  1|  0|   4|   2|24.4|4.0|[24.3999996185302...|         1|
|           Merc 230|140.8| 95|3.92| 3.15| 22.9|  1|  0|   4|   2|22.8|4.0|[22.7999992370605...|         1|
|           Merc 280|167.6|123|3.92| 3.44| 18.3|  1|  0|   4|   4|19.2|6.0|[19.2000007629394...|         0|
|          Merc 280C|167.6|123|3.92| 3.44| 18.9|  1|  0|   4|   4|17.8|6.0|[17.7999992370605...|         0|
|         Merc 450SE|275.8|180|3.07| 4.07| 17.4|  0|  0|   3|   3|16.4|8.0|[16.3999996185302...|         0|
|         Merc 450SL|275.8|180|3.07| 3.73| 17.6|  0|  0|   3|   3|17.3|8.0|[17.2999992370605...|         0|
|        Merc 450SLC|275.8|180|3.07| 3.78|   18|  0|  0|   3|   3|15.2|8.0|[15.1999998092651...|         0|
| Cadillac Fleetwood|  472|205|2.93| 5.25|17.98|  0|  0|   3|   4|10.4|8.0|[10.3999996185302...|         0|
|Lincoln Continental|  460|215|   3|5.424|17.82|  0|  0|   3|   4|10.4|8.0|[10.3999996185302...|         0|
|  Chrysler Imperial|  440|230|3.23|5.345|17.42|  0|  0|   3|   4|14.7|8.0|[14.6999998092651...|         0|
|           Fiat 128| 78.7| 66|4.08|  2.2|19.47|  1|  1|   4|   1|32.4|4.0|[32.4000015258789...|         1|
|        Honda Civic| 75.7| 52|4.93|1.615|18.52|  1|  1|   4|   2|30.4|4.0|[30.3999996185302...|         1|
|     Toyota Corolla| 71.1| 65|4.22|1.835| 19.9|  1|  1|   4|   1|33.9|4.0|[33.9000015258789...|         1|
+-------------------+-----+---+----+-----+-----+---+---+----+----+----+---+--------------------+----------+
```



**=> Does the clustering make sense?  Perhaps we have too few clusters?**

## Step 6 : Run the whole script
**=> Exit spark shell, by pressing Ctrl+D**

**=> Run the spark shell in terminal as follows**

```
    $ ~/spark/bin/spark-shell -i kmeans_mtcars.scala
```

This will run the entire script in one go!


## Step 7 : Adjust the cluster number (K)
Edit file `kmeans_mtcars.scala`.   
**=> Change the `number of clusters` argument supplied to `Kmeans.train()` from `2` to `3`  function (around line # 60).**  

Run the script again
```
    $ ~/spark/bin/spark-shell -i kmeans_mtcars.scala
```

Inspect the output.   
Does clustering of cars make sense?
Try different values of `K` (4 or 5).

What is the WSSSE value when K = 32?  Can you explain? :-)

Make a note of the "wall clock time. " We will optimize this later.
This can be done by setting sc.setLogLevel("INFO") and measuring the time.

##Step 8: Optimize Script

Even though the dataset is tiny, notice that the datasets are running on disk without caching.  Could the cache() method help us at all?  

Edit file `kmeans_mtcars.scala`.  
Cache dataframe `featureVectors`
```scala
    featureVectors.cache
    featureVectors.count  // force caching
```

Then run again and note the wall clock time.
```
    $ time  ~/spark/bin/spark-shell -i kmeans_mtcars.scala
```


## Step 9: Record and Plot WSSSE versus K
Perform a plot of WSSSE versus K.  
Use the provided [excel sheet](WSSSE-versus-k.xlsx).   
Use the "elbow" method to pick what seems to be a good value of k.  
Does that match your intuitive sense of what is the best?

The excel sheet also have a sample plot we did.  
And here is the 'elbow curve'.   

<img src="../../assets/images/6.1-wssse-vs-k.png" style="border: 5px solid grey; max-width:100%;" />

## Step 10: Add some new data, check and see if it changes the clusters.
Edit input data file `../../../data/mtcars/mtcars.csv`.  
Add some new rows to the mtcars dataset based on your favorite cars (or just
make up some fictitious cars).

Then run the script again.
```
    $ time  ~/spark/bin/spark-shell -i kmeans_mtcars.scala  2> logs
```

See how adding this affects the way the data is clustered?


## BONUS: Automatically iterate across k.
Is there a way you could modify this to loop through values of K instead of
manually changing the values?  

## BONUS 2: Programmatically Perform Elbow Method

Using the principles of the "elbow" method, what is a way you could automatically
select a k value?
