package com.ilanosortap

import com.ilanosortap.FamilyTree.Person
import com.ilanosortap.commons.init.family

object Processor {
  def addNewBorn(parent:String,child:String,sex:String): Unit =
  {
    val member = parent
    val m = family.findByName(member)
    if (m.isDefined && m.get.sex == sex) {
      val p = new Person(child, List(), sex, m.get, m.get.spouse,None, m.get.generation + 1)
      family.addNewBorn(p, m.get)
      println("CHILD_ADDITION_SUCCEEDED")

    }
    else if(m.isDefined && m.get.sex == "M")
      println("CHILD_ADDITION_FAILED")
    else
      println("Member not part of the family")
  }

  def getRelationship(name:String,relationship:String): Unit ={
    val member = family.findByName(name)

    if (member.isEmpty)
      println("member not present")
    else {
      relationship.toLowerCase match {
        case "paternal-uncle" => { member.get.getPaternalUncles().distinct.foreach(x => print(x.name+" "))
          println }
        case "paternal-aunt" => {member.get.getPaternalAunts().distinct.foreach(x => print(x.name+" "))
          println }
        case "maternal-uncle" => {member.get.getMaternalUncles().distinct.foreach(x => print(x.name+" "))
          println }
        case "maternal-aunt" => {member.get.getMaternalAunts().distinct.foreach(x => print(x.name+" "))
          println }
        case "sister-in-law" => {member.get.getSisterInLaws().distinct.foreach(x => print(x.name+" "))
          println }
        case "brother-in-law" => {member.get.getBrotherInLaws().distinct.foreach(x => print(x.name+" "))
          println }
        case "son" => {member.get.getSons().foreach(x => print(x.name+" "))
          println }
        case "daughter" => {member.get.getDaughters().foreach(x => print(x.name+" "))
          println }
        case "siblings" => {member.get.getSiblings().distinct.foreach(x => print(x.name+" "))
          println }
        case "sisters" => {member.get.getSisters().distinct.foreach(x => print(x.name+" "))
          println }
        case "father" => {println(member.get.father.get.name+" ")
          println }
        case "mother" => {println(member.get.mother.name+" ")
          println }
        case "children" => {member.get.getChildren().distinct.foreach(x => print(x.name+" "))
          println }
        case "sister" =>  {member.get.getSisters().distinct.foreach(x => print(x.name+" "))
          println }
      }

    }
  }
}
