package x

import org.apache.spark._
import org.apache.spark.streaming._

object FindScalaLines {
  def main(args: Array[String]) {
    // Create streaming context
    val ssc = new StreamingContext("local[2]", 
                      "FindScalaLines", Seconds(10)) 
    // Create DStream from socket stream source
    val lines = ssc.socketTextStream("localhost", 9999)
    // Filter (creates new DStream
    val linesWScala = lines.filter(line=>line.contains("Scala"))
    // Output
    linesWScala.print()

    ssc.start()
    ssc.awaitTermination()
  } 
}
