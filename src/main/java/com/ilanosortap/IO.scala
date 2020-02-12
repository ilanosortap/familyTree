package com.ilanosortap

import com.ilanosortap.commons.init

object IO {


  def main(arg: Array[String]): Unit = {
    init()
    val file = scala.io.Source.fromFile(sys.env("FAMILY_HOME")+"/inputs/Problem1.txt")
    for(line <- file.getLines()) {
      val args = line.split(" ")
      val action = args(0)

      if (action == "ADD_CHILD")
         Processor.addNewBorn(args(1),args(2),args(3))

      if (action == "GET_RELATIONSHIP")
        Processor.getRelationship(args(1),args(2))
    }
  }
}
