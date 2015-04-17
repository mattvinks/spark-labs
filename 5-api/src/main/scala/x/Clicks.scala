package x

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._ // *** must have for rdd operations ***
import org.apache.spark.SparkConf
import org.apache.spark.rdd._

object Clicks {
  def main(args: Array[String]) {
    if (args.length < 1) {
      println ("need file(s) to load")
      System.exit(1)
    }

    val input = args(0) // can be wildcard 's3n://bucket/files'

    val conf = new SparkConf().setAppName("Clicks")
    //conf.setMaster("local[*]")
    val sc = new SparkContext(conf)

    // now logic

    // ## TODO 1 : create an input rdd; hint : sc.textFiles(input)
    val clickstream = sc.textFile(input)
    val count = clickstream.count()
    println("### total records " + count)

    // TODO 2 : count per domain
    val groupedByDomain = clickstream.map{
      line => {
        val tokens = line.split(",")
        val domain = tokens(3).trim // extract domain
        (domain, line)
      }
    }
    val domainCount = groupedByDomain.countByKey
    println ("### domain count : \n" + domainCount)

    // TODO 3 : sort this by top domains first
    val domainCountSorted = domainCount.map((domain,count) => (count,domain)).sort.map((count, domain) => (domain, count))

  }
}
