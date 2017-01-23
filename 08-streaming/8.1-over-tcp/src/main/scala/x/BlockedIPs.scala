package x


import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds,StreamingContext}
import org.apache.spark.storage.StorageLevel

/*
$  ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.BlockedIPs target/scala-2.11/over-tcp_2.11-1.0.jar
 */


object BlockedIPs {
  def main(args: Array[String]) {

    val sparkConf = new SparkConf().setAppName("BlkIPOverTCP")
    val ssc = new StreamingContext(sparkConf, Seconds(10))
    // listen on port 9999
    val lines = ssc.socketTextStream("localhost", 9999,  StorageLevel.MEMORY_ONLY)
    lines.print

    val blocked = lines.filter(line => line.contains("blocked"))
    blocked.print


    // TODO 1 : On those lines, extract just the IP address
    // hint  : data  format: 
    // timestamp, ip, userid, action, domain, campaign, cost, sessionid
    // 1420070400000,ip_1,user_5,clicked,facebook.com,campaign_6,139,session_98
    // hint : separator is comma
    // hint : ip address is second element : index (1)

    // extract lines with more than one column
    val blocked2 = blocked.filter(_.split(",").size > 1)
    val blockedIPs = blocked2.map(line => line.split(",")(1))

    // print the results
    blockedIPs.print()
    //blockedIPs.saveAsTextFiles("out-blockedIPs")

    // kick off the processing
    ssc.start()
    ssc.awaitTermination()
  }
}

