KMeans Lab
==========

In this lab we discuss how to use kmeans clustering in Spark.

Step 1
======

Check out the dataset that we are going to cluster: MTCars. Those of you
with experience in R should remember this as one of R's internal datasets.

This dataset contains some statistics on 1974 Cars from Motor Trends

Here are the columns:
  mpg   Miles/(US) gallon                        
  cyl   Number of cylinders                      
  disp  Displacement (cu.in.)                    
  hp    Gross horsepower                         
  drat  Rear axle ratio            
  mpg   Miles/(US) gallon                        
  cyl   Number of cylinders                      
  disp  Displacement (cu.in.)                    
  hp    Gross horsepower                         
  drat  Rear axle ratio            

Are there any natural clusters you can identify from this data?

Step 2
===

run this with:
(You'll need to either have spark-shell in your path or give the 
 FQ path for it).

```bash
$ cd scala
$ ~/spark/bin/spark-shell -i kmeans_mtcars.scala
```

Check out the final results of the groupedClusters.  Does the clustering make sense?  Perhaps
we have too few clusters?

Step 2
======
Try experimenting with 3 clusters instead of two.  Rerun the results and see what you get.
Keep trying new values of k until the results seem to make some sense.

Step 3
======
Perform a plot of WSSSE versus K.  (Use excel or whatever application you prefer). Use the 
"elbow" method to pick what seems to be a good value of k.  Does that match your intuitive 
sense of what is the best?

Step 4
======
Copy the input file "../../../data/mtcars/mtcars.csv" to the present directory.
Add some new rows to the mtcars dataset based on your favorite cars (or just
make up some fictitious cars).

See how adding this affects the way the data is clustered?

BONUS
======
Is there a way you could modify this to loop through values of K instead of 
manually changing the values?  

BONUS 2
======
Using the principles of the "elbow" method, what is a way you could automatically
select a k value?
