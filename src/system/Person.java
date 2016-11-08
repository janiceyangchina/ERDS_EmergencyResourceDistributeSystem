/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.2.4305 modeling language!*/

package system;
import java.util.*;

// line 160 "../../../class diagram (1).ump"
// line 278 "../../../class diagram (1).ump"
public class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String name;
  private int age;
  private String gender;

  //Person Associations
  private List<Skill> skills;
  private ERDS eRDS;
  private Organization organization;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aName, int aAge, String aGender, ERDS aERDS, Organization aOrganization)
  {
    name = aName;
    age = aAge;
    gender = aGender;
    skills = new ArrayList<Skill>();
    boolean didAddERDS = setERDS(aERDS);
    if (!didAddERDS)
    {
      throw new RuntimeException("Unable to create person due to eRDS");
    }
    boolean didAddOrganization = setOrganization(aOrganization);
    if (!didAddOrganization)
    {
      throw new RuntimeException("Unable to create person due to organization");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    // line 164 "../../../class diagram (1).ump"
    if (aName.length() <= 2 || aName.length() >= 20 ){return false;}
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAge(int aAge)
  {
    boolean wasSet = false;
    // line 167 "../../../class diagram (1).ump"
    if (aAge <= 18 || aAge >=150 ){return false;}
    age = aAge;
    wasSet = true;
    return wasSet;
  }

  public boolean setGender(String aGender)
  {
    boolean wasSet = false;
    // line 170 "../../../class diagram (1).ump"
    if ( (! aGender.equals("Female")) && (!aGender.equals("Male")) ){return false;}
    gender = aGender;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getAge()
  {
    return age;
  }

  public String getGender()
  {
    return gender;
  }

  public Skill getSkill(int index)
  {
    Skill aSkill = skills.get(index);
    return aSkill;
  }

  public List<Skill> getSkills()
  {
    List<Skill> newSkills = Collections.unmodifiableList(skills);
    return newSkills;
  }

  public int numberOfSkills()
  {
    int number = skills.size();
    return number;
  }

  public boolean hasSkills()
  {
    boolean has = skills.size() > 0;
    return has;
  }

  public int indexOfSkill(Skill aSkill)
  {
    int index = skills.indexOf(aSkill);
    return index;
  }

  public ERDS getERDS()
  {
    return eRDS;
  }

  public Organization getOrganization()
  {
    return organization;
  }

  public static int minimumNumberOfSkills()
  {
    return 0;
  }

  public boolean addSkill(Skill aSkill)
  {
    boolean wasAdded = false;
    if (skills.contains(aSkill)) { return false; }
    skills.add(aSkill);
    if (aSkill.indexOfPerson(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSkill.addPerson(this);
      if (!wasAdded)
      {
        skills.remove(aSkill);
      }
    }
    return wasAdded;
  }

  public boolean removeSkill(Skill aSkill)
  {
    boolean wasRemoved = false;
    if (!skills.contains(aSkill))
    {
      return wasRemoved;
    }

    int oldIndex = skills.indexOf(aSkill);
    skills.remove(oldIndex);
    if (aSkill.indexOfPerson(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSkill.removePerson(this);
      if (!wasRemoved)
      {
        skills.add(oldIndex,aSkill);
      }
    }
    return wasRemoved;
  }

  public boolean addSkillAt(Skill aSkill, int index)
  {  
    boolean wasAdded = false;
    if(addSkill(aSkill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSkills()) { index = numberOfSkills() - 1; }
      skills.remove(aSkill);
      skills.add(index, aSkill);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSkillAt(Skill aSkill, int index)
  {
    boolean wasAdded = false;
    if(skills.contains(aSkill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSkills()) { index = numberOfSkills() - 1; }
      skills.remove(aSkill);
      skills.add(index, aSkill);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSkillAt(aSkill, index);
    }
    return wasAdded;
  }

  public boolean setERDS(ERDS aERDS)
  {
    boolean wasSet = false;
    if (aERDS == null)
    {
      return wasSet;
    }

    ERDS existingERDS = eRDS;
    eRDS = aERDS;
    if (existingERDS != null && !existingERDS.equals(aERDS))
    {
      existingERDS.removePerson(this);
    }
    eRDS.addPerson(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setOrganization(Organization aOrganization)
  {
    boolean wasSet = false;
    if (aOrganization == null)
    {
      return wasSet;
    }

    Organization existingOrganization = organization;
    organization = aOrganization;
    if (existingOrganization != null && !existingOrganization.equals(aOrganization))
    {
      existingOrganization.removePerson(this);
    }
    organization.addPerson(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Skill> copyOfSkills = new ArrayList<Skill>(skills);
    skills.clear();
    for(Skill aSkill : copyOfSkills)
    {
      aSkill.removePerson(this);
    }
    ERDS placeholderERDS = eRDS;
    this.eRDS = null;
    placeholderERDS.removePerson(this);
    Organization placeholderOrganization = organization;
    this.organization = null;
    placeholderOrganization.removePerson(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "age" + ":" + getAge()+ "," +
            "gender" + ":" + getGender()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "eRDS = "+(getERDS()!=null?Integer.toHexString(System.identityHashCode(getERDS())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "organization = "+(getOrganization()!=null?Integer.toHexString(System.identityHashCode(getOrganization())):"null")
     + outputString;
  }
}