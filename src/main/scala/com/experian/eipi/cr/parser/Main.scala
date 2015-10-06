package com.experian.eipi.cr.parser

/**
 * Created by dtrieu on 9/21/15.
 */
object Main extends App {

  import com.experian.eipi.cr.parser.experian.ExperianParser

  import scala.io.Source


  val src = Source.fromInputStream(getClass.getResourceAsStream("/cr-0/ex/raw-836.txt")).mkString

  println("....hello...:" + src)

  val parser = implicitly[ExperianParser]

  val time = System currentTimeMillis

  for ( x <- 1 to 1000 ) {
    parser parse src
    print(" .")
  }

  val stop = (System.currentTimeMillis - time) / 1000

  println(s"\n$stop")

  val result = parser parse src
  println(result)


}
