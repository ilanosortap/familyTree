package com.ilanosortap.FamilyTree

class Family(var members : List[Person],var leader:Person) {

  def init(member:Person): Unit =
  {
    this.leader = member
    this.members = member :: this.members
  }

  def findByName(member: String)={
    var res : Option[Person] = None
    for(m <- members)
      {
        if(m.name == member)
           res = Some(m)
      }
    res
  }

  def addSpouse(spouse:Person,member: Person)={
    spouse.marriage(member)
    member.marriage(spouse)
    spouse.generation = member.generation
    this.members = spouse :: this.members
  }

  def addNewBorn(child:Person,member:Person)={
    if(this.findByName(member.name).isDefined) {
      this.members = child :: this.members
      member.addNewBorn(child)
      if(member.spouse.isDefined)
      member.spouse.get.addNewBorn(child)
    }
  }


}
