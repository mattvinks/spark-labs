A Dating Site Recommendations

This lab explores a well known dataset from the Czech dating website libimseti.cz.  We'll
just call it the "dating" dataset. :)

Normally we talk of users and items as different entities, but in dating websites we 
relate users to one another.

In our example, we're going to ignore the gender and orientation of each user in doing
the recommendations.   The dating dataset does include a file which identifies the 
gender of each participant, but for simplicity we're not handling it here. This isn't
as bad as it sounds, as most users likely will rate only one gender of dating site
participants, and will no dobut receive recommendations from the same gender.

The checked in version is a tiny subset of the actual, as only the first 9999 users are
included.  Furthermore, the ratings outside the subset are ignored, so a good portion of
users have no data.

The example here will take the dating site.

Running the data

spark-shell -i dating.scala

you can find recommendations for a particular user by typing the following:

scala> recsForEachUser(4) 
res4: Array[Int] = Array(7964, 6499, 6269, 9287)

Beware: some numbers aren't represented (e.g. 3)


