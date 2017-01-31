// uses new style Direct Streaming

package x

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{StreamingContext, Seconds}
import org.apache.spark.streaming.kafka.KafkaUtils

/*
  $  sbt assembly

  $   ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.KafkaDirectStreaming    target/scala-2.11/kafka-streaming-assembly-1.0.jar  localhost:9092  clickstream

  $   ~/spark/bin/spark-submit  --master local[2]   --driver-class-path logging/  --class x.KafkaDirectStreaming    target/scala-2.11/kafka-streaming-assembly-1.0.jar  host1:9092,host2:9092  topic1,topic2

 */


object KafkaDirectStreaming {
  def main(args: Array[String]) {
    if (args.length < 2) {
      System.err.println("Need <brokers> <topics> ")
      System.err.println("e.g. localhost:9092  topic1 ")
      System.exit(1)
    }

    val Array(brokers, topics) = args
    println ("### reading from brokers : " + brokers + ",  topics : " + topics)
    val sparkConf = new SparkConf().setAppName("Kafka Streaming")
    val ssc =  new StreamingContext(sparkConf, Seconds(5))
    //ssc.checkpoint("checkpoint")
    val topicSet = topics.split(",").toSet
    val kafkaParams = Map ("metadata.broker.list" -> brokers)

    val stream = KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](ssc, kafkaParams, topicSet)

    // debug print
    stream.print()

    // map the values out  (null, v1)
    val lines = stream.map(_._2) 
    lines.print

    // TODO-1 : extract clicked records
    val clicks = lines.filter(_.contains("???")) 

    // TODO-2 : print clicks lines
    // clicks.???

    ssc.start()
    ssc.awaitTermination()
  }

}
