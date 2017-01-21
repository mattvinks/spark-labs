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
    lines.print

    // TODO 2 : filter lines that contains 'blocked'
    //      filter (line => line.contains("???"))
    val blocked = lines.filter(???)

    // extract lines with more than one column
    val blocked2 = blocked.filter(_.split(",").size > 1)
    blocked2.print()

    // TODO 3 : On those lines, extract just the IP address
     /* hint  : data  format: 
     timestamp, ip, userid, action, domain, campaign, cost, sessionid
     1420070400000,ip_1,user_5,clicked,facebook.com,campaign_6,139,session_98
    */
    // hint : separator is comma
    // hint : ip address is second element : index (1)
    val blockedIPs = blocked2.map(line => line.split("??? what is the sep ???")(??? index ???))

    // print the results
    blockedIPs.print()

    /*
    // TODO  4  : Save both RDDs (and uncomment this block)
    // linesBlocked save to : "out/blocked-lines"
    // blockedIPs save to : "out/blocked-ips"
    linesWBlocked.saveAsTextFiles("out dir1")
    blockedIPs.saveAsTextFiles("out dir2")
    */

    // kick off the processing
    ssc.start()
    ssc.awaitTermination()
  }
}

