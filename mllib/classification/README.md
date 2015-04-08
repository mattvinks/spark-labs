Lab 1.1 : Up and Running With Spark
===================================
### OverView
We will be running a binary classifier using a Support Vector Machine (SVM)

### Depends On 
None

### Run time
20 mins


## STEP 1: Examine the Churn Dataset
The churn dataset is located in ../../churn/ directory.  There is already
broken out a churntest.csv and a churntrain.csv.  Normally, we split
up the dataset but in this case it's done for you.

Note the outcome variable is simply called "churn".  If the customer leaves,
churn is 1, if not, 0.

## STEP 2: Prepare the data:
MLLib only accepts numeric data.  This dataset has some non-numeric fields.
Note which fields are non-numeric.

## STEP 3: Modify the churn_svm.scala 
Complete the TODO items in this lab.

## STEP 5: execute the lab

```bash
  $   ../../../bin/spark-shell -i ./churn_svm.scala 

