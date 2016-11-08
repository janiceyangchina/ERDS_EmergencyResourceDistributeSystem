/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.2.4305 modeling language!*/

package system;
import java.util.*;

// line 108 "../../../class diagram (1).ump"
// line 243 "../../../class diagram (1).ump"
public class Resource
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Resource Attributes
  private String name;
  private String description;

  //Resource Associations
  private ERDS eRDS;
  private List<ResourceAvailability> resourceAvailabilities;
  private List<ResourceNeed> resourceNeeds;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Resource(String aName, String aDescription, ERDS aERDS)
  {
    name = aName;
    description = aDescription;
    boolean didAddERDS = setERDS(aERDS);
    if (!didAddERDS)
    {
      throw new RuntimeException("Unable to create resource due to eRDS");
    }
    resourceAvailabilities = new ArrayList<ResourceAvailability>();
    resourceNeeds = new ArrayList<ResourceNeed>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    if (aName.length() <= 2 || aName.length() >= 20 ){return false;}
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    // line 111 "../../../class diagram (1).ump"
    if (aDescription.length() <= 2 || aDescription.length() >= 20 ){return false;}
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getDescription()
  {
    return description;
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

  public ResourceNeed getResourceNeed(int index)
  {
    ResourceNeed aResourceNeed = resourceNeeds.get(index);
    return aResourceNeed;
  }

  public List<ResourceNeed> getResourceNeeds()
  {
    List<ResourceNeed> newResourceNeeds = Collections.unmodifiableList(resourceNeeds);
    return newResourceNeeds;
  }

  public int numberOfResourceNeeds()
  {
    int number = resourceNeeds.size();
    return number;
  }

  public boolean hasResourceNeeds()
  {
    boolean has = resourceNeeds.size() > 0;
    return has;
  }

  public int indexOfResourceNeed(ResourceNeed aResourceNeed)
  {
    int index = resourceNeeds.indexOf(aResourceNeed);
    return index;
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
      existingERDS.removeResource(this);
    }
    eRDS.addResource(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfResourceAvailabilities()
  {
    return 0;
  }

  public ResourceAvailability addResourceAvailability(int aQuantity, Organization aOrganization)
  {
    return new ResourceAvailability(aQuantity, aOrganization, this);
  }

  public boolean addResourceAvailability(ResourceAvailability aResourceAvailability)
  {
    boolean wasAdded = false;
    if (resourceAvailabilities.contains(aResourceAvailability)) { return false; }
    if (resourceAvailabilities.contains(aResourceAvailability)) { return false; }
    if (resourceAvailabilities.contains(aResourceAvailability)) { return false; }
    if (resourceAvailabilities.contains(aResourceAvailability)) { return false; }
    Resource existingResource = aResourceAvailability.getResource();
    boolean isNewResource = existingResource != null && !this.equals(existingResource);
    if (isNewResource)
    {
      aResourceAvailability.setResource(this);
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
    //Unable to remove aResourceAvailability, as it must always have a resource
    if (!this.equals(aResourceAvailability.getResource()))
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

  public static int minimumNumberOfResourceNeeds()
  {
    return 0;
  }

  public ResourceNeed addResourceNeed(int aRequiredQuantity, Emergency aEmergency)
  {
    return new ResourceNeed(aRequiredQuantity, this, aEmergency);
  }

  public boolean addResourceNeed(ResourceNeed aResourceNeed)
  {
    boolean wasAdded = false;
    if (resourceNeeds.contains(aResourceNeed)) { return false; }
    if (resourceNeeds.contains(aResourceNeed)) { return false; }
    if (resourceNeeds.contains(aResourceNeed)) { return false; }
    if (resourceNeeds.contains(aResourceNeed)) { return false; }
    Resource existingResource = aResourceNeed.getResource();
    boolean isNewResource = existingResource != null && !this.equals(existingResource);
    if (isNewResource)
    {
      aResourceNeed.setResource(this);
    }
    else
    {
      resourceNeeds.add(aResourceNeed);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeResourceNeed(ResourceNeed aResourceNeed)
  {
    boolean wasRemoved = false;
    //Unable to remove aResourceNeed, as it must always have a resource
    if (!this.equals(aResourceNeed.getResource()))
    {
      resourceNeeds.remove(aResourceNeed);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addResourceNeedAt(ResourceNeed aResourceNeed, int index)
  {  
    boolean wasAdded = false;
    if(addResourceNeed(aResourceNeed))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfResourceNeeds()) { index = numberOfResourceNeeds() - 1; }
      resourceNeeds.remove(aResourceNeed);
      resourceNeeds.add(index, aResourceNeed);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveResourceNeedAt(ResourceNeed aResourceNeed, int index)
  {
    boolean wasAdded = false;
    if(resourceNeeds.contains(aResourceNeed))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfResourceNeeds()) { index = numberOfResourceNeeds() - 1; }
      resourceNeeds.remove(aResourceNeed);
      resourceNeeds.add(index, aResourceNeed);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addResourceNeedAt(aResourceNeed, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ERDS placeholderERDS = eRDS;
    this.eRDS = null;
    placeholderERDS.removeResource(this);
    for(int i=resourceAvailabilities.size(); i > 0; i--)
    {
      ResourceAvailability aResourceAvailability = resourceAvailabilities.get(i - 1);
      aResourceAvailability.delete();
    }
    for(int i=resourceNeeds.size(); i > 0; i--)
    {
      ResourceNeed aResourceNeed = resourceNeeds.get(i - 1);
      aResourceNeed.delete();
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "description" + ":" + getDescription()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "eRDS = "+(getERDS()!=null?Integer.toHexString(System.identityHashCode(getERDS())):"null")
     + outputString;
  }
}