<link rel='stylesheet' href='../../assets/main.css'/>

Lab : Classification with MLlib
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
churn is 1, if not, 0. The outcome variable happens to be the last variable in the dataset.

The other variables are as follows:
* 1:"state": what state (2 letter abberviation)
* 2:"account_length":  Length of customer account
* 3:"area_code" a string containing the area code 
* 4:"international_plan", whether or not international plan
* 5:"voice_mail_plan", whether or not voice mail plan
* 6:"number_vmail_messages", an integer with the number of voice mails
* 7:"total_day_minutes" an integer with total daytime minutes used
* 8:"total_day_calls" an integer with the total number of daytime calls
* 9:"total_day_charge": the charge for the daytime calls
* 10:"total_eve_minutes: total number of evening minutes used
* 11:"total_eve_calls": total numbe of evening calls
* 12:"total_eve_charge": total charge for evening calls
* 13:"total_night_minutes": total number of night minutes used
* 14:"total_night_calls": total number of nigh calls
* 15:"total_night_charge": total chage for nights
* 16:"total_intl_minutes": total international minutes used
* 17:"total_intl_calls", total number of international calls
* 18:"total_intl_charge": total charge for international calls.
* 19:"number_customer_service_calls" integer number of times called customer service 
* 20:"churn" Outcome Variable  yes/no churn or did not churn




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

## Step 5 : Complete the TODO items to generate score and labels.

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
  $   ~/spark/bin/spark-shell -i ./churn_svm.scala
```

### STEP 7: Note the Area under ROC

We measure our model's performance by measuring the area under the ROC.
