package x


import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.storage.StorageLevel

/*
$   ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.OverTCP target/scala-2.11/over-tcp_2.11-1.0.jar
 */


object OverTCP {
  def main(args: Array[String]) {

    val sparkConf = new SparkConf().setAppName("BlkIPOverTCP")

    // TODO  1 : define window duration for 10 seconds
    val ssc = new StreamingContext(sparkConf, Seconds(???))

    // listen on port 9999, only cache it local memory
    val lines = ssc.socketTextStream("localhost", 9999, StorageLevel.MEMORY_ONLY)
    lines.print

    // TODO-2 : filter lines that contains 'blocked'
    /*
    val blocked = lines.filter(line => line.contains("???"))
    val blocked2 = blocked.map("BLOCKED:" + _) // better print
    blocked2.print
    */

    // TODO-3  : Save both RDDs (and uncomment this block)
    /*
    blocked.saveAsTextFiles("out-blocked")
    */

    // kick off the processing
    ssc.start()
    ssc.awaitTermination()
  }
}

