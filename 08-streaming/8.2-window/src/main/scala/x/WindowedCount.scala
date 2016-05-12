package x

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._

/* convention
    /// instructions  (don't uncomment these)
    // commented out lines, to be uncommented when fixing TODO items
*/

/*
$  spark-submit  --master local[2]   --driver-class-path logging/  --class x.BlkIPOverTCP  target/scala-2.10/over-tcp_2.10-0.1.jar
 */


object WindowedCount {
  def main(args: Array[String]) {

    val sparkConf = new SparkConf().setAppName("WindowedCount")

    //TODO-1: Try batch interval 5 secs
    val ssc = new StreamingContext(sparkConf, Seconds(???))

    val lines = ssc.socketTextStream("localhost", 9999)
    val actionsKVPairs = lines.map{
      line => {
        val tokens = line.split(",")
        val action = tokens(3) // either blocked / viewed / clicked
        // TODO-2  : return action and 1
        (???, ???)
    }

    /// TODO-3: Try 10 seconds for both both the values for window intervals
    /// reduceByKeyAndWindow (reduce func,  window duration, sliding window)
    /// window duration (last 10 seconds)
    /// sliding window (10 seconds)
    //val windowedActionCounts = actionsKVPairs.reduceByKeyAndWindow((a:Int, b:Int) => (a+b), Seconds(???), Seconds(???))

    /// TODO-4 : print out counts
    windowedActionCounts.???

    //TODO-5: Save the count to a dir 'out/actions'
    windowedActionCounts.???

    ssc.start()
    ssc.awaitTermination()
  }
}
