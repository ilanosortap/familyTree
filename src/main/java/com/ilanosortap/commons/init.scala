package com.ilanosortap.commons

import com.ilanosortap.FamilyTree.{Family, Person}
import org.json.JSONObject

object init {
  val leader = new Person("King Shan",List(),"M",null,None,None,0)
  val wife = new Person("Queen anga",List(),"F",null,None,None,0)
  val family = new Family(List(leader),leader)
  family.addSpouse(wife,leader)


  val members_list =
    """{"from_family": true, "name": "Ish", "sex": "M", "parent": "King Shan"},
      |{"from_family": true, "name": "Chit", "sex": "M", "parent": "King Shan"},
      |{"from_family": true, "name": "Aras", "sex": "M", "parent": "King Shan"},
      |{"from_family": true, "name": "Vich", "sex": "M", "parent": "King Shan"},
      |{"from_family": true, "name": "Satya", "sex": "F", "parent": "King Shan"},
      |{"from_family": false, "name": "Amba", "sex": "F", "partner_name": "Chit"},
      |{"from_family": false, "name": "Lika", "sex": "F", "partner_name": "Vich"},
      |{"from_family": false, "name": "Vyan", "sex": "M", "partner_name": "Satya"},
      |{"from_family": true, "name": "Dritha", "sex": "M", "parent": "Chit"},
      |{"from_family": true, "name": "Tritha", "sex": "M", "parent": "Chit"},
      |{"from_family": true, "name": "Vritha", "sex": "M", "parent": "Chit"},
      |{"from_family": true, "name": "Vila", "sex": "M", "parent": "Vich"},
      |{"from_family": true, "name": "Chika", "sex": "F", "parent": "Vich"},
      |{"from_family": true, "name": "Asva", "sex": "M", "parent": "Satya"},
      |{"from_family": true, "name": "Savya", "sex": "M", "parent": "Satya"},
      |{"from_family": true, "name": "Saayan", "sex": "M", "parent": "Satya"},
      |{"from_family": false, "name": "Jaya", "sex": "F", "partner_name": "Dritha"},
      |{"from_family": false, "name": "Kpila", "sex": "M", "partner_name": "Chika"},
      |{"from_family": false, "name": "Satvy", "sex": "F", "partner_name": "Asva"},
      |{"from_family": false, "name": "Mina", "sex": "F", "partner_name": "Saayan"},
      |{"from_family": true, "name": "Jata", "sex": "M", "parent": "Drita"},
      |{"from_family": true, "name": "Driya", "sex": "F", "parent": "Drita"},
      |{"from_family": true, "name": "Kriya", "sex": "M", "parent": "Savya"},
      |{"from_family": true, "name": "Misa", "sex": "M", "parent": "Saayan"},
      |{"from_family": false, "name": "Mnu", "sex": "M", "partner_name": "Driya"},
      |{"from_family": false, "name": "Gru", "sex": "M", "partner_name": "Lavanya"},
      |{"from_family": false, "name": "Chitra", "sex": "F", "partner_name": "Aras"}
      |{"from_family": true, "name": "Ahit", "sex": "M", "parent": "Aras"},
      |{"from_family": true, "name": "Jnki", "sex": "F", "parent": "Aras"},
      |{"from_family": true, "name": "Lavanya", "sex": "F", "parent": "Jnki"},
      |{"from_family": true, "name": "Vyas", "sex": "M", "parent": "Vyan"},
      |{"from_family": true, "name": "Atya", "sex": "M", "parent": "Vyan"},
      |{"from_family": false, "name": "Arit", "sex": "M", "partner_name": "Jnki"},
      |{"from_family": false, "name": "Krpi", "sex": "F", "partner_name": "Vyas"},
      |""".stripMargin

  def apply()=
  {
    val members = members_list.split("\n")
    for(m <- members)
    {
      val mInJson = new JSONObject(m)
      if(mInJson.getBoolean("from_family"))
      {
        val parent = family.findByName(mInJson.getString("parent"))
        if(parent.isDefined) {
          if (parent.get.sex == "M") {

            val spouse = parent.get.spouse
            if (spouse.isDefined) {
              val child = new Person(mInJson.getString("name"), List(), mInJson.getString("sex"), spouse.get, parent, None, parent.get.generation + 1)
              family.addNewBorn(child, spouse.get)
              //println(child.name,child.father.get.name,child.mother.name)

            }
          }
          else {
            val child = new Person(mInJson.getString("name"), List(), mInJson.getString("sex"), parent.get, parent.get.spouse, None,  parent.get.generation + 1)
            family.addNewBorn(child, parent.get)
          }
        }
      }
      else
      {
        val member = family.findByName(mInJson.getString("partner_name"))
        if(member.isDefined) {
          val spouse = new Person(mInJson.getString("name"), List(), mInJson.getString("sex"), null, None, member, member.get.generation)
          family.addSpouse(spouse, member.get)
        }
      }
    }
  }
}
