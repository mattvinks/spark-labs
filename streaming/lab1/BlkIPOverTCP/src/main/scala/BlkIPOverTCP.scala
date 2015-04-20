import org.apache.spark._
import org.apache.spark.streaming._


object BlkIPOverTCP {
def main(args: Array[String]) {

val ssc = new StreamingContext("local[2]", "BlkIPOverTCP", Seconds(30)) 

val lines = ssc.socketTextStream("localhost", 9999)

val linesWBlocked = lines.filter(line=>line.contains("blocked"))


val words =linesWBlocked.flatMap(_.split(","))

words.saveAsTextFiles("BlkIP.txt")

//to print a specific folder. If that folder doesn't exit, it will be created. 

//words.saveAsTextFiles("log/BlkIP.txt")


//To Print to screen
//words.print()

ssc.start()

ssc.awaitTermination()

} 
}

