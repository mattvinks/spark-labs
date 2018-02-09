<link rel='stylesheet' href='../../assets/css/main.css'/>

[<< back to main index](../../README.md)

# KMeans Lab

In this lab we discuss how to use kmeans clustering in Spark.

## Step 1: Examine the MTCars dataset

Check out the dataset that we are going to cluster: MTCars. Those of you
with experience in R should remember this as one of R's internal datasets.

This dataset contains some statistics on 1974 Cars from Motor Trends

<img src="../../assets/images/6.1-cars2.png" style="border: 5px solid grey; max-width:100%;" />

You can also download and view the raw data in Excel : [cars.csv](/data/cars/mtcars_header.csv)

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
Open file [kmeans_mtcars.scala](kmeans_mtcars.scala) and examine it.  
This script has some *TODO* items in it.  
We are going to fix these TODO items and run the script **line-by-line** in Spark Shell to understand what is going on.

## Step 3 : Launch Spark Shell
```bash
    # start shell
    $  ~/spark/bin/spark-shell
```
### Download the [spreadsheet](WSSSE-versus-k.xlsx)

**=> Open the script `kmeans_mtcars.scala` in a favorite editor.**  

**=> Copy paste snippets from text editor into Spark shell.  Watch the execution and the results**  

**==> And record the K vs.  WSSE values in the provided WSSSE-versus-k.xlsx.**  


**=> Also keep an eye on Spark web UI (4040)**

**=> We will plot K vs. WSSSE as follows**

<img src="../../assets/images/6.1-wssse-vs-k.png" style="border: 5px solid grey; max-width:100%;" />

**=> set K=32 and compute WSSSE can you explain?**

## Step 4: Run the whole script
Once you fix TODO items in the script, we can run the entire script as follows

```bash
    # supply the file to run with -i flag
    $ ~/spark/bin/spark-shell -i ~/spark-labs/06-mllib/kmeans/kmeans_mtcars.scala
```

**Hint**
You can also load a file into scala shell Using
```scala
    :load file_name.scala
```

## Bonus 1 : Optimiz Script
Can we cache the data to speed up computations?  
Try this
```scala
    dataset.cache
    dataset.count
```

## Bonus 2 : Adding your own cars
Edit input data file `/data/cars/mtcars.csv`.  
Add some new rows to the mtcars dataset based on your favorite cars (or just
make up some fictitious cars).

Then run the script again.
```
    $ time  ~/spark/bin/spark-shell -i kmeans_mtcars.scala  2> logs
```

See how adding this affects the way the data is clustered?
