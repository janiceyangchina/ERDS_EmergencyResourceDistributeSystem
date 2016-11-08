/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.2.4305 modeling language!*/

package system;
import java.util.*;

// line 15 "../../../class diagram (1).ump"
// line 218 "../../../class diagram (1).ump"
public class Emergency
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Emergency Attributes
  private String location;

  //Emergency State Machines
  enum State { Declared, Responded, Scheduled, Resolved }
  private State state;

  //Emergency Associations
  private List<ResourceNeed> resourceNeeds;
  private List<Community> communities;
  private ERDS eRDS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Emergency(String aLocation, ERDS aERDS)
  {
    location = aLocation;
    resourceNeeds = new ArrayList<ResourceNeed>();
    communities = new ArrayList<Community>();
    boolean didAddERDS = setERDS(aERDS);
    if (!didAddERDS)
    {
      throw new RuntimeException("Unable to create emergency due to eRDS");
    }
    setState(State.Declared);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLocation(String aLocation)
  {
    boolean wasSet = false;
    // line 35 "../../../class diagram (1).ump"
    if (aLocation.length() <= 2 || aLocation.length() >= 20 ){return false;}
    location = aLocation;
    wasSet = true;
    return wasSet;
  }

  public String getLocation()
  {
    return location;
  }

  public String getStateFullName()
  {
    String answer = state.toString();
    return answer;
  }

  public State getState()
  {
    return state;
  }

  public boolean finishDeclaring()
  {
    boolean wasEventProcessed = false;
    
    State aState = state;
    switch (aState)
    {
      case Declared:
        setState(State.Responded);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean continueProvideResource()
  {
    boolean wasEventProcessed = false;
    
    State aState = state;
    switch (aState)
    {
      case Responded:
        setState(State.Responded);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean coordiniateResource()
  {
    boolean wasEventProcessed = false;
    
    State aState = state;
    switch (aState)
    {
      case Responded:
        setState(State.Scheduled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean scheduleTransportation()
  {
    boolean wasEventProcessed = false;
    
    State aState = state;
    switch (aState)
    {
      case Scheduled:
        setState(State.Scheduled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean transportResourceToDestination()
  {
    boolean wasEventProcessed = false;
    
    State aState = state;
    switch (aState)
    {
      case Scheduled:
        setState(State.Resolved);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setState(State aState)
  {
    state = aState;
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

  public Community getCommunity(int index)
  {
    Community aCommunity = communities.get(index);
    return aCommunity;
  }

  public List<Community> getCommunities()
  {
    List<Community> newCommunities = Collections.unmodifiableList(communities);
    return newCommunities;
  }

  public int numberOfCommunities()
  {
    int number = communities.size();
    return number;
  }

  public boolean hasCommunities()
  {
    boolean has = communities.size() > 0;
    return has;
  }

  public int indexOfCommunity(Community aCommunity)
  {
    int index = communities.indexOf(aCommunity);
    return index;
  }

  public ERDS getERDS()
  {
    return eRDS;
  }

  public static int minimumNumberOfResourceNeeds()
  {
    return 0;
  }

  public ResourceNeed addResourceNeed(int aRequiredQuantity, Resource aResource)
  {
    return new ResourceNeed(aRequiredQuantity, aResource, this);
  }

  public boolean addResourceNeed(ResourceNeed aResourceNeed)
  {
    boolean wasAdded = false;
    if (resourceNeeds.contains(aResourceNeed)) { return false; }
    Emergency existingEmergency = aResourceNeed.getEmergency();
    boolean isNewEmergency = existingEmergency != null && !this.equals(existingEmergency);
    if (isNewEmergency)
    {
      aResourceNeed.setEmergency(this);
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
    //Unable to remove aResourceNeed, as it must always have a emergency
    if (!this.equals(aResourceNeed.getEmergency()))
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

  public boolean isNumberOfCommunitiesValid()
  {
    boolean isValid = numberOfCommunities() >= minimumNumberOfCommunities();
    return isValid;
  }

  public static int minimumNumberOfCommunities()
  {
    return 1;
  }

  public Community addCommunity(String aName, ERDS aERDS)
  {
    Community aNewCommunity = new Community(aName, aERDS, this);
    return aNewCommunity;
  }

  public boolean addCommunity(Community aCommunity)
  {
    boolean wasAdded = false;
    if (communities.contains(aCommunity)) { return false; }
    Emergency existingEmergency = aCommunity.getEmergency();
    boolean isNewEmergency = existingEmergency != null && !this.equals(existingEmergency);

    if (isNewEmergency && existingEmergency.numberOfCommunities() <= minimumNumberOfCommunities())
    {
      return wasAdded;
    }
    if (isNewEmergency)
    {
      aCommunity.setEmergency(this);
    }
    else
    {
      communities.add(aCommunity);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCommunity(Community aCommunity)
  {
    boolean wasRemoved = false;
    //Unable to remove aCommunity, as it must always have a emergency
    if (this.equals(aCommunity.getEmergency()))
    {
      return wasRemoved;
    }

    //emergency already at minimum (1)
    if (numberOfCommunities() <= minimumNumberOfCommunities())
    {
      return wasRemoved;
    }

    communities.remove(aCommunity);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean addCommunityAt(Community aCommunity, int index)
  {  
    boolean wasAdded = false;
    if(addCommunity(aCommunity))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCommunities()) { index = numberOfCommunities() - 1; }
      communities.remove(aCommunity);
      communities.add(index, aCommunity);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCommunityAt(Community aCommunity, int index)
  {
    boolean wasAdded = false;
    if(communities.contains(aCommunity))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCommunities()) { index = numberOfCommunities() - 1; }
      communities.remove(aCommunity);
      communities.add(index, aCommunity);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCommunityAt(aCommunity, index);
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
      existingERDS.removeEmergency(this);
    }
    eRDS.addEmergency(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=resourceNeeds.size(); i > 0; i--)
    {
      ResourceNeed aResourceNeed = resourceNeeds.get(i - 1);
      aResourceNeed.delete();
    }
    for(int i=communities.size(); i > 0; i--)
    {
      Community aCommunity = communities.get(i - 1);
      aCommunity.delete();
    }
    ERDS placeholderERDS = eRDS;
    this.eRDS = null;
    placeholderERDS.removeEmergency(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "location" + ":" + getLocation()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "eRDS = "+(getERDS()!=null?Integer.toHexString(System.identityHashCode(getERDS())):"null")
     + outputString;
  }
}