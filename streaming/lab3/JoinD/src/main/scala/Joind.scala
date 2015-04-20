import org.apache.spark._
import org.apache.spark.streaming._


object WindowedIP {
def main(args: Array[String]) {


val ssc = new StreamingContext("local[2]", "JoinD", Seconds(4)) 


val lines = ssc.socketTextStream("127.0.0.1", 9999)

val linesWClicked = lines.filter(line=>line.contains("clicked"))
val linesWViewed = lines.filter(line=>line.contains("viewed"))


val clicked = linesWClicked.flatMap(_.split(","))
val clickedpairs = clicked.map(word => (word, 1))

val viewed = linesWViewed.flatMap(_.split(","))
val viewedpairs = viewed.map(word => (word, 1))


//1st argument is Windows interval and 2nd argument is Sliding Interval

val clickedCounts = clickedpairs.reduceByKeyAndWindow((a:Int,b:Int) => (a + b), Seconds(4), Seconds(4))
val viewedCounts = viewedpairs.reduceByKeyAndWindow((a:Int,b:Int) => (a + b), Seconds(8), Seconds(4))

//TODO : Try joining without changing any of the slide durations and see what happens. 
//TODO : Then make the slide durations same

val allKeys = viewedCounts.join(clickedCounts)


//clickedCounts.print()
//viewedCounts.print()

// Comment above 3 print lines and now try to save it into a file and then into a specific folder. 
// If you want to save them in files 
//TODO: Try saving the files in a log folder by changing "Blk.txt" to "log/Blk.txt" etc. Uncomment. 


allKeys.saveAsTextFiles("same.txt")

//clickedCounts.saveAsTextFiles("Clk.txt")
//viewedCounts.saveAsTextFiles("Vid.txt")

ssc.start()
ssc.awaitTermination()
} 
}
