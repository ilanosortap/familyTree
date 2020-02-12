package com.ilanosortap.FamilyTree

import com.ilanosortap.commons.Constants._

class Person(val name: String, var children:List[Person], var sex:String, var mother:Person, var father:Option[Person], var spouse:Option[Person], var generation:Int) {

  def addNewBorn(child: Person) ={
    this.children = child :: this.children
  }

  def marriage(spouse:Person)={
    this.spouse = Some(spouse)
  }


  def getSiblings()={
    this.mother.children.filter(_ != this)
  }

  def getBrothers()={

    var res : List[Person] =List()
    for( s <- this.getSisters()) {
      if(s.sex == male)
        res = s :: res
    }
    res
  }

  def getSisters()={
    var res : List[Person] =List()

    for( s <- this.getSiblings()) {
      if(s.sex == female) {

        res = s :: res
      }
    }
    res
  }

  def getChildren()={
    this.children
  }

  def getGrandChildren()={
    val children = this.children
    val grandChildren : List[Person]= List()

    for(child <- children){
      grandChildren ::: child.getChildren()
    }
    grandChildren
  }

  def getMother()={
    this.mother
  }
  def getFather()={
    this.father
  }

  def getDaughters()={
    val children = this.children
    var res : List[Person] = List()

    for(c<-children)
      {
        if(c.sex==female)
          res = c :: res
      }
    res
  }
  def getSons()={
    val children = this.children
    var res : List[Person] = List()

    for(c<-children)
    {
      if(c.sex==male)
        res = c :: res
    }
    res
  }

  def getPaternalUncles()={
    this.father.get.getBrothers()
  }

  def getMaternalUncles()={
    this.mother.getBrothers()
  }

  def getPaternalAunts()={
    this.father.get.getSisters()
  }

  def getMaternalAunts()={
    this.mother.getSisters()
  }


  def getGrandSons(member:Person)={
    val grandChildren = member.getGrandChildren()
    var grandSons : List[Person] = List()
    for(child <- grandChildren)
    {
      if(child.sex == male)
        grandSons = child :: grandSons
    }
    grandSons
  }

  def getGrandDaughters()={
    val grandChildren = this.getGrandChildren()
    var grandDaughters : List[Person] = List()
    for(child <- grandChildren)
    {
      if(child.sex == female)
        grandDaughters = child :: grandDaughters
    }
    grandDaughters
  }

  def getBrotherInLaws()={
    val siblings = this.getSisters() ::: this.getBrothers()
    var bil : List[Person]= List()
    for(s <- siblings) {
      if(s.spouse.get.sex == male)
        bil =  s.spouse.get :: bil
    }
    bil ::: this.spouse.get.getBrothers()
  }
  def getSisterInLaws()={
    val siblings = this.getSisters() ::: this.getBrothers()
    var sil : List[Person]= List()
    for(s <- siblings) {
      if(s.spouse.get.sex == female)
        sil =  s.spouse.get :: sil
    }
    sil ::: this.spouse.get.getBrothers()
  }

  def getCousins()={
    val parentSiblings = this.getMother().getSisters() ::: this.getMother().getBrothers() ::: this.getFather().get.getBrothers() ::: this.getFather().get.getSisters()
    var cousins : List[Person] = List()
    for(s<-parentSiblings){
      cousins = cousins ::: s.getChildren()
    }
    cousins
  }
}