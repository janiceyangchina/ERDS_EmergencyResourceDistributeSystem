/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.2.4305 modeling language!*/

package system;
import java.util.*;

// line 126 "../../../class diagram (1).ump"
// line 253 "../../../class diagram (1).ump"
public class ResourceNeed
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ResourceNeed Attributes
  private int requiredQuantity;

  //ResourceNeed Associations
  private Resource resource;
  private Emergency emergency;
  private List<ResourceMatch> resourceMatchs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ResourceNeed(int aRequiredQuantity, Resource aResource, Emergency aEmergency)
  {
    requiredQuantity = aRequiredQuantity;
    boolean didAddResource = setResource(aResource);
    if (!didAddResource)
    {
      throw new RuntimeException("Unable to create resourceNeed due to resource");
    }
    boolean didAddEmergency = setEmergency(aEmergency);
    if (!didAddEmergency)
    {
      throw new RuntimeException("Unable to create resourceNeed due to emergency");
    }
    resourceMatchs = new ArrayList<ResourceMatch>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRequiredQuantity(int aRequiredQuantity)
  {
    boolean wasSet = false;
    // line 128 "../../../class diagram (1).ump"
    if (aRequiredQuantity <= 0){return false;}
    requiredQuantity = aRequiredQuantity;
    wasSet = true;
    return wasSet;
  }

  public int getRequiredQuantity()
  {
    return requiredQuantity;
  }

  public Resource getResource()
  {
    return resource;
  }

  public Emergency getEmergency()
  {
    return emergency;
  }

  public ResourceMatch getResourceMatch(int index)
  {
    ResourceMatch aResourceMatch = resourceMatchs.get(index);
    return aResourceMatch;
  }

  public List<ResourceMatch> getResourceMatchs()
  {
    List<ResourceMatch> newResourceMatchs = Collections.unmodifiableList(resourceMatchs);
    return newResourceMatchs;
  }

  public int numberOfResourceMatchs()
  {
    int number = resourceMatchs.size();
    return number;
  }

  public boolean hasResourceMatchs()
  {
    boolean has = resourceMatchs.size() > 0;
    return has;
  }

  public int indexOfResourceMatch(ResourceMatch aResourceMatch)
  {
    int index = resourceMatchs.indexOf(aResourceMatch);
    return index;
  }

  public boolean setResource(Resource aResource)
  {
    boolean wasSet = false;
    if (aResource == null)
    {
      return wasSet;
    }

    Resource existingResource = resource;
    resource = aResource;
    if (existingResource != null && !existingResource.equals(aResource))
    {
      existingResource.removeResourceNeed(this);
    }
    resource.addResourceNeed(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setEmergency(Emergency aEmergency)
  {
    boolean wasSet = false;
    if (aEmergency == null)
    {
      return wasSet;
    }

    Emergency existingEmergency = emergency;
    emergency = aEmergency;
    if (existingEmergency != null && !existingEmergency.equals(aEmergency))
    {
      existingEmergency.removeResourceNeed(this);
    }
    emergency.addResourceNeed(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfResourceMatchs()
  {
    return 0;
  }

  public ResourceMatch addResourceMatch(ResourceAvailability aResourceAvailability, Schedule aSchedule)
  {
    return new ResourceMatch(aResourceAvailability, this, aSchedule);
  }

  public boolean addResourceMatch(ResourceMatch aResourceMatch)
  {
    boolean wasAdded = false;
    if (resourceMatchs.contains(aResourceMatch)) { return false; }
    ResourceNeed existingResourceNeed = aResourceMatch.getResourceNeed();
    boolean isNewResourceNeed = existingResourceNeed != null && !this.equals(existingResourceNeed);
    if (isNewResourceNeed)
    {
      aResourceMatch.setResourceNeed(this);
    }
    else
    {
      resourceMatchs.add(aResourceMatch);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeResourceMatch(ResourceMatch aResourceMatch)
  {
    boolean wasRemoved = false;
    //Unable to remove aResourceMatch, as it must always have a resourceNeed
    if (!this.equals(aResourceMatch.getResourceNeed()))
    {
      resourceMatchs.remove(aResourceMatch);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addResourceMatchAt(ResourceMatch aResourceMatch, int index)
  {  
    boolean wasAdded = false;
    if(addResourceMatch(aResourceMatch))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfResourceMatchs()) { index = numberOfResourceMatchs() - 1; }
      resourceMatchs.remove(aResourceMatch);
      resourceMatchs.add(index, aResourceMatch);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveResourceMatchAt(ResourceMatch aResourceMatch, int index)
  {
    boolean wasAdded = false;
    if(resourceMatchs.contains(aResourceMatch))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfResourceMatchs()) { index = numberOfResourceMatchs() - 1; }
      resourceMatchs.remove(aResourceMatch);
      resourceMatchs.add(index, aResourceMatch);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addResourceMatchAt(aResourceMatch, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Resource placeholderResource = resource;
    this.resource = null;
    placeholderResource.removeResourceNeed(this);
    Emergency placeholderEmergency = emergency;
    this.emergency = null;
    placeholderEmergency.removeResourceNeed(this);
    for(int i=resourceMatchs.size(); i > 0; i--)
    {
      ResourceMatch aResourceMatch = resourceMatchs.get(i - 1);
      aResourceMatch.delete();
    }
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "requiredQuantity" + ":" + getRequiredQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "resource = "+(getResource()!=null?Integer.toHexString(System.identityHashCode(getResource())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "emergency = "+(getEmergency()!=null?Integer.toHexString(System.identityHashCode(getEmergency())):"null")
     + outputString;
  }
}