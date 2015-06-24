package x


import org.apache.spark._
import org.apache.spark.streaming._

/*
$  spark-submit  --master local[2]   --driver-class-path logging/  --class x.BlkIPOverTCP  target/scala-2.10/over-tcp_2.10-0.1.jar
 */

object BlkIPOverTCP {
  def main(args: Array[String]) {

    val sparkConf = new SparkConf().setAppName("BlkIPOverTCP")

    // TODO  1 : define window duration  in seconds (e.g. 10)
    val ssc = new StreamingContext(sparkConf, Seconds(???))

    // listen on port 9999
    val lines = ssc.socketTextStream("localhost", 9999)

    // TODO 2 : filter lines that contains 'blocked'
    val linesWBlocked = lines.filter(???)

    // TODO 3 : On those lines, extract just the IP address
    val blockedIPs = linesWBlocked.map(???)

    // print the results
    linesWBlocked.print()
    blockedIPs.print()

    /*
    // TODO  4  : Save both RDDs (and uncomment this block)
    // linesBlocked save to : "out/blocked-lines"
    // blockedIPs save to : "out/blocked-ips"
    linesWBlocked.saveAs_???___
    blockedIPs.saveAs_???___
    */

    // kick off the processing
    ssc.start()
    ssc.awaitTermination()
  }
}

