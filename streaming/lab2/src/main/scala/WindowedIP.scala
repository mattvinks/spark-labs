import org.apache.spark._
import org.apache.spark.streaming._


object WindowedIP {
def main(args: Array[String]) {

//TODO: Try batch interval with 3 seconds and run and see what happens.  
//TODO: After that change Seconds(1) to  # seconds that is a multiple of Windows Interval and Sliding Interval.

val ssc = new StreamingContext("local[2]", "WindowedIP", Seconds(1)) 


val lines = ssc.socketTextStream("127.0.0.1", 9999)

val linesWBlocked = lines.filter(line=>line.contains("blocked"))
val linesWClicked = lines.filter(line=>line.contains("clicked"))
val linesWViewed = lines.filter(line=>line.contains("viewed"))

val blocked = linesWBlocked.flatMap(_.split(" "))
val blockedpairs = blocked.map(word => (word, 1))

val clicked = linesWClicked.flatMap(_.split(" "))
val clickedpairs = clicked.map(word => (word, 1))

val viewed = linesWViewed.flatMap(_.split(" "))
val viewedpairs = viewed.map(word => (word, 1))


//1st argument is Windows interval and 2nd argument is Sliding Interval
// TODO: Try changing both the values for blockedCounts, clickedCounts and viewedCounts. Example 2, 4, 6 respectively. 

val blockedCounts = blockedpairs.reduceByKeyAndWindow((a:Int,b:Int) => (a + b), Seconds(2), Seconds(2)) 
val clickedCounts = clickedpairs.reduceByKeyAndWindow((a:Int,b:Int) => (a + b), Seconds(2), Seconds(2))
val viewedCounts = viewedpairs.reduceByKeyAndWindow((a:Int,b:Int) => (a + b), Seconds(2), Seconds(2))

// TODO: This prints to the terminal. Uncomment the 3 lines.              

//blockedCounts.print()
//clickedCounts.print()
//viewedCounts.print()

// Comment above 3 print lines and now try to save it into a file and then into a specific folder. 
// If you want to save them in files 
//TODO: Try saving the files in a log folder by changing "Blk.txt" to "log/Blk.txt" etc. Uncomment. 


//blockedCounts.saveAsTextFiles("Blk.txt")
//clickedCounts.saveAsTextFiles("Clk.txt")
//viewedCounts.saveAsTextFiles("Vid.txt")

ssc.start()
ssc.awaitTermination()
} 
}
