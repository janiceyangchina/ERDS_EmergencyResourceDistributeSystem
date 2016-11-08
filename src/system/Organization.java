/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.2.4305 modeling language!*/

package system;
import java.util.*;

// line 135 "../../../class diagram (1).ump"
// line 258 "../../../class diagram (1).ump"
public class Organization
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Organization Attributes
  private String name;
  private String location;

  //Organization Associations
  private List<Person> persons;
  private ERDS eRDS;
  private List<ResourceAvailability> resourceAvailabilities;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Organization(String aName, String aLocation, ERDS aERDS)
  {
    name = aName;
    location = aLocation;
    persons = new ArrayList<Person>();
    boolean didAddERDS = setERDS(aERDS);
    if (!didAddERDS)
    {
      throw new RuntimeException("Unable to create organization due to eRDS");
    }
    resourceAvailabilities = new ArrayList<ResourceAvailability>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    // line 138 "../../../class diagram (1).ump"
    if (aName.length() <= 2 || aName.length() >= 20 ){return false;}
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setLocation(String aLocation)
  {
    boolean wasSet = false;
    // line 141 "../../../class diagram (1).ump"
    if (aLocation.length() <= 2 || aLocation.length() >= 20 ){return false;}
    location = aLocation;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getLocation()
  {
    return location;
  }

  public Person getPerson(int index)
  {
    Person aPerson = persons.get(index);
    return aPerson;
  }

  public List<Person> getPersons()
  {
    List<Person> newPersons = Collections.unmodifiableList(persons);
    return newPersons;
  }

  public int numberOfPersons()
  {
    int number = persons.size();
    return number;
  }

  public boolean hasPersons()
  {
    boolean has = persons.size() > 0;
    return has;
  }

  public int indexOfPerson(Person aPerson)
  {
    int index = persons.indexOf(aPerson);
    return index;
  }

  public ERDS getERDS()
  {
    return eRDS;
  }

  public ResourceAvailability getResourceAvailability(int index)
  {
    ResourceAvailability aResourceAvailability = resourceAvailabilities.get(index);
    return aResourceAvailability;
  }

  public List<ResourceAvailability> getResourceAvailabilities()
  {
    List<ResourceAvailability> newResourceAvailabilities = Collections.unmodifiableList(resourceAvailabilities);
    return newResourceAvailabilities;
  }

  public int numberOfResourceAvailabilities()
  {
    int number = resourceAvailabilities.size();
    return number;
  }

  public boolean hasResourceAvailabilities()
  {
    boolean has = resourceAvailabilities.size() > 0;
    return has;
  }

  public int indexOfResourceAvailability(ResourceAvailability aResourceAvailability)
  {
    int index = resourceAvailabilities.indexOf(aResourceAvailability);
    return index;
  }

  public static int minimumNumberOfPersons()
  {
    return 0;
  }

  public Person addPerson(String aName, int aAge, String aGender, ERDS aERDS)
  {
    return new Person(aName, aAge, aGender, aERDS, this);
  }

  public boolean addPerson(Person aPerson)
  {
    boolean wasAdded = false;
    if (persons.contains(aPerson)) { return false; }
    if (persons.contains(aPerson)) { return false; }
    if (persons.contains(aPerson)) { return false; }
    if (persons.contains(aPerson)) { return false; }
    Organization existingOrganization = aPerson.getOrganization();
    boolean isNewOrganization = existingOrganization != null && !this.equals(existingOrganization);
    if (isNewOrganization)
    {
      aPerson.setOrganization(this);
    }
    else
    {
      persons.add(aPerson);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePerson(Person aPerson)
  {
    boolean wasRemoved = false;
    //Unable to remove aPerson, as it must always have a organization
    if (!this.equals(aPerson.getOrganization()))
    {
      persons.remove(aPerson);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addPersonAt(Person aPerson, int index)
  {  
    boolean wasAdded = false;
    if(addPerson(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersons()) { index = numberOfPersons() - 1; }
      persons.remove(aPerson);
      persons.add(index, aPerson);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePersonAt(Person aPerson, int index)
  {
    boolean wasAdded = false;
    if(persons.contains(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersons()) { index = numberOfPersons() - 1; }
      persons.remove(aPerson);
      persons.add(index, aPerson);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPersonAt(aPerson, index);
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
      existingERDS.removeOrganization(this);
    }
    eRDS.addOrganization(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfResourceAvailabilities()
  {
    return 0;
  }

  public ResourceAvailability addResourceAvailability(int aQuantity, Resource aResource)
  {
    return new ResourceAvailability(aQuantity, this, aResource);
  }

  public boolean addResourceAvailability(ResourceAvailability aResourceAvailability)
  {
    boolean wasAdded = false;
    if (resourceAvailabilities.contains(aResourceAvailability)) { return false; }
    if (resourceAvailabilities.contains(aResourceAvailability)) { return false; }
    if (resourceAvailabilities.contains(aResourceAvailability)) { return false; }
    if (resourceAvailabilities.contains(aResourceAvailability)) { return false; }
    Organization existingOrganization = aResourceAvailability.getOrganization();
    boolean isNewOrganization = existingOrganization != null && !this.equals(existingOrganization);
    if (isNewOrganization)
    {
      aResourceAvailability.setOrganization(this);
    }
    else
    {
      resourceAvailabilities.add(aResourceAvailability);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeResourceAvailability(ResourceAvailability aResourceAvailability)
  {
    boolean wasRemoved = false;
    //Unable to remove aResourceAvailability, as it must always have a organization
    if (!this.equals(aResourceAvailability.getOrganization()))
    {
      resourceAvailabilities.remove(aResourceAvailability);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addResourceAvailabilityAt(ResourceAvailability aResourceAvailability, int index)
  {  
    boolean wasAdded = false;
    if(addResourceAvailability(aResourceAvailability))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfResourceAvailabilities()) { index = numberOfResourceAvailabilities() - 1; }
      resourceAvailabilities.remove(aResourceAvailability);
      resourceAvailabilities.add(index, aResourceAvailability);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveResourceAvailabilityAt(ResourceAvailability aResourceAvailability, int index)
  {
    boolean wasAdded = false;
    if(resourceAvailabilities.contains(aResourceAvailability))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfResourceAvailabilities()) { index = numberOfResourceAvailabilities() - 1; }
      resourceAvailabilities.remove(aResourceAvailability);
      resourceAvailabilities.add(index, aResourceAvailability);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addResourceAvailabilityAt(aResourceAvailability, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=persons.size(); i > 0; i--)
    {
      Person aPerson = persons.get(i - 1);
      aPerson.delete();
    }
    ERDS placeholderERDS = eRDS;
    this.eRDS = null;
    placeholderERDS.removeOrganization(this);
    for(int i=resourceAvailabilities.size(); i > 0; i--)
    {
      ResourceAvailability aResourceAvailability = resourceAvailabilities.get(i - 1);
      aResourceAvailability.delete();
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "location" + ":" + getLocation()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "eRDS = "+(getERDS()!=null?Integer.toHexString(System.identityHashCode(getERDS())):"null")
     + outputString;
  }
}