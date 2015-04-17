import org.apache.spark.streaming.{Seconds, StreamingContext} 
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.storage.StorageLevel

object WordCountOverTCP {
def main(args: Array[String]) {

val ssc = new StreamingContext("local[2]", "WordCountOverTCP", Seconds(1)) 

val lines = ssc.socketTextStream("localhost", 9999)

val words = lines.flatMap(_.split(" "))
val pairs = words.map(x => (x, 1))
val wordCounts = pairs.reduceByKey(_ + _) 
wordCounts.print()

ssc.start()

ssc.awaitTermination()

} 
}

