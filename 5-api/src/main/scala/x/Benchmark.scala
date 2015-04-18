package x

import org.apache.spark.{SparkConf, SparkContext}

/**
 * to invoke:
 */
object Benchmark {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Benchmark Application")
    val sc = new SparkContext(conf)
    val files = List ("s3n://opr-spark/1M.data",
      "s3n://opr-spark/10M.data",
      "s3n://opr-spark/100M.data",
      "s3n://opr-spark/500M.data",
      "s3n://opr-spark/1G.data",
      "s3n://opr-spark/5G.data")

    for(f <- files) {
      println ("### processing file : " + f)
      // count without caching
      val file = sc.textFile(f)
      for (i <- 1 to 5) {
        val t1 = System.nanoTime()
        val count = file.count()
        val t2 = System.nanoTime()
        println ("### %s : count (%,d) before caching took %,d ms".format(f, count, (t2-t1)/1000000 ))
      }

      // caching
      file.cache()
      println ("### cached rdd : " + file.toDebugString)

      for (i <- 1 to 5) {
        val t1 = System.nanoTime()
        val count = file.count()
        val t2 = System.nanoTime()
        println ("### %s : count (%,d) after caching took %,d ms".format(f, count, (t2-t1)/1000000 ))

      }
    }
  }
}
