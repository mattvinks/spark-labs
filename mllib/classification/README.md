Lab 1.1 : Classification with MLlib
===================================
### OverView
We will be running a binary classifier using a Support Vector Machine (SVM)

### Depends On
None

### Run time
30 mins


## STEP 1: Examine the Churn Dataset
The churn dataset is located in ../../churn/ directory.  There is already
broken out a churntest.csv and a churntrain.csv.  Normally, we split
up the dataset but in this case it's done for you.

Note the outcome variable is simply called "churn".  If the customer leaves,
churn is 1, if not, 0.

##STEP 2: Prepare the data:
MLLib Vectors only accepts numeric data.  This dataset has some non-numeric fields.  Note which fields are non-numeric.

## STEP 3: Examine the churn_svm.scala
Examine the file churn_svm.scala

## Step 4 : Write function to parse data
Create a function called parseData to parse the data. It should return
type Labeledpoint. which should have the outcome variable churn,
and a Vectors.Dense of all the other variables

```scala
// ===== Scala =====
def parseData(vals : RDD[String]) : RDD[LabeledPoint] = {
  vals.map { s =>
    //TODO: drop the non-numeric fields, convert the rest to double.
    val parts = ???

    //TODO Return Labeldpoint:  Outcome variable, Vectors.Dense(all other variab
les
    LabeledPoint(???, ???)
  }
}
```

## Step 5 : Run the trained model on the test set.

Use model.predict on the test data.  Calculate the score.  Then return a tuple of score and point.label

```scala
// ===== Scala =====
val scoreAndLabels = splitTestData.map { point =>
  //TODO == use model.predict to get the score
  //val score = ???
  //(score, point.label)
}

```


## STEP 6: execute the lab

```bash
  $   ../../../bin/spark-shell -i ./churn_svm.scala
```

### STEP 7: Note the Area under ROC

We measure our model's performance by measuring the area under the ROC.
