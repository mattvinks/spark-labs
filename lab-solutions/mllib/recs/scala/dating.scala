import org.apache.spark.mllib.recommendation.ALS
import org.apache.spark.mllib.recommendation.Rating


// For the dating website 
// Users = Users
// Items = other users

// ratings.dat is the dating website
val data = sc.textFile("../../../data/dating/medium/ratings.dat")
val ratings = data.map(_.split(',') match { case Array(user, item, rating) =>
    Rating(user.toInt, item.toInt, rating.toDouble)
  })

val model = ALS.train(ratings, rank = 10, iterations = 5, 0.01)

// Get rid of data to test model's effectiveness
val usersItems = ratings.map { case Rating(user, item, rating) =>
  (user, item)
}
// Do a test prediction
val recs = 
  model.predict(usersItems).map { case Rating(user, item, rating) => 
    ((user, item), rating)
  }
val ratingsAndRecs = ratings.map { case Rating(user, item, rating) => 
  ((user, item), rating)
}.join(recs)
val MSE = ratingsAndRecs.map { case ((user, item), (r1, r2)) => 
  val err = (r1 - r2)
  err * err
}.mean()
println("Mean Squared Error = " + MSE)

val personaldata = sc.textFile("personalratings.txt")
val personalratings = data.map(_.split(',') match { case Array(user, item, rating) =>
    Rating(user.toInt, item.toInt, rating.toDouble)
  })

val recsForEachUser = recs.map { case ((user, item), rating) => (user, item, rating) 
  }.collect.groupBy(_._1).mapValues(_.sortBy(- _._3).take(4)).mapValues(_.map(_._2))
