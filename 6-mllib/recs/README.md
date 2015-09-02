[<< back to main index](../../README.md) 

MLlib Recommendations Lab
=============================

## Introduction

This lab explores a well known dataset from the Czech dating website libimseti.cz.  We'll
just call it the "dating" dataset. :)

Normally we talk of users and items as different entities, but in dating websites we 
relate users to one another.

In our example, we're going to ignore the gender and orientation of each user in doing
the recommendations.   The dating dataset does include a file which identifies the 
gender of each participant, but for simplicity we're not handling it here. This isn't
as bad as it sounds, as most users likely will rate only one gender of dating site
participants, and will no doubt receive recommendations from the same gender. Naturally
there are always exceptions.

The checked in version is a tiny subset of the actual, as only the first 9999 users are
included.  Furthermore, the ratings outside the subset are ignored, so a good portion of
users have no data.

The example here will take the dating site.
file : [data/dating/medium/ratings.dat](../../data/dating/medium/ratings.dat)
Here is what the file looks like:
```
user1, user2, rating (1-10)
```

```
1,133,8
1,720,6
1,971,10
1,1095,7
1,1616,10
1,1978,7
1,2145,8
```


Complete the TODO items
======================

### Step 1:  Transform the Rating object of to a tuple of User, Item

```bash
$    cd  ~/spark-labs/6-mllib/recs

## edit the file  dating.scala
$  vi dating.scala
# or 
$ nano dating.scala
```

There are two TODO items you should complete in the scala code before attempting to run the 
code.

The first one is as follows:

```scala
// Get rid of rating to test model's effectiveness
// TODO: TRANSFORM Rating -> Tuple of (user, item)
// (i.e., get rid of the rating.

val usersItems = ???? // TODO complete this item
```

###Step 2:   Use the predict method to map the outpu to user,item
The second one is as follows:
```scala
// Do a test prediction
// TODO call model.predict() on userItems, and then map the output of that
to (user, item), rating

val recs = ??? // TODO:  COMPLETE THIS
```

`




### Step 3: Running the data

    ~/spark/bin/spark-shell -i dating.scala


Once you run this, the recommendations for all users will be put in an array
called recsForEachUser()

you can find recommendations for a particular user by typing the following:

scala> recsForEachUser(4) 
res4: Array[Int] = Array(7964, 6499, 6269, 9287)

Beware: some numbers aren't represented (e.g. 3)

### Step 4: Running on some of your own data

Create a file called personalratings.txt.  Include some test data as preferences.
We have included a samle personalratings.txt for you you can refer to it.

```scala
val personaldata = sc.textFile("personalratings.txt")
val personalratings = data.map(_.split(',') match { case Array(user, item, rating) =>
    Rating(user.toInt, item.toInt, rating.toDouble)
  })
```


