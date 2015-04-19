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

    val input = args(0) // can be wildcard  'dir/*.log'

    val conf = new SparkConf().setAppName("Clickstream")
    //conf.setMaster("local[*]")
    val sc = new SparkContext(conf)

    println ("### Ahoy mate, fix TODO items!")

    // ## TODO-1 : create an input rdd; hint : sc.textFiles(input)
    /*
    val clickstream = ???

    // TODO-2  : count how many records we have
    val count =  ???  
    println("### total clickstream records " + count)
    */

    // count per domain - uncomment this comment block once fixed
    /*
    val groupedByDomain = clickstream.map{
      line => {
        val tokens = line.split(",")
        // TODO-3 : extract the domain by position ..tokens(???)
        val domain = ???

        // TODO-4 : return 'domain' as key and '1' as value
        (???, ???)
      }
    }
    */


    // count the number of domains
    /* 
    // TODO-5 :   from groupedByDomain, calculate domain count
    val domainCount = ???
    println ("### domain count : \n" + domainCount)
    */


    // top domains
    /*
    // TODO 6 : sort this by top domains first
    val topDomains = domainCount.toList.sortBy{_._2}
    println ("### top domains : \n" + topDomains)
    */

  }
}
