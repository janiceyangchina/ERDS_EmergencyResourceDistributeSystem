/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.2.4305 modeling language!*/

package system;

// line 80 "../../../class diagram (1).ump"
// line 238 "../../../class diagram (1).ump"
public class ResourceAvailability
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ResourceAvailability Attributes
  private int quantity;

  //ResourceAvailability State Machines
  enum State { Available, Allocated, OnTheWay, Arrived }
  private State state;

  //ResourceAvailability Associations
  private Organization organization;
  private Resource resource;
  private ResourceMatch resourceMatch;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ResourceAvailability(int aQuantity, Organization aOrganization, Resource aResource)
  {
    quantity = aQuantity;
    boolean didAddOrganization = setOrganization(aOrganization);
    if (!didAddOrganization)
    {
      throw new RuntimeException("Unable to create resourceAvailability due to organization");
    }
    boolean didAddResource = setResource(aResource);
    if (!didAddResource)
    {
      throw new RuntimeException("Unable to create resourceAvailability due to resource");
    }
    setState(State.Available);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    // line 94 "../../../class diagram (1).ump"
    if (aQuantity <= 0){return false;}
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public int getQuantity()
  {
    return quantity;
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

  public boolean coordinateResource()
  {
    boolean wasEventProcessed = false;
    
    State aState = state;
    switch (aState)
    {
      case Available:
        setState(State.Allocated);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancel()
  {
    boolean wasEventProcessed = false;
    
    State aState = state;
    switch (aState)
    {
      case Allocated:
        setState(State.Available);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean transportResource()
  {
    boolean wasEventProcessed = false;
    
    State aState = state;
    switch (aState)
    {
      case Allocated:
        setState(State.OnTheWay);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean returnBack()
  {
    boolean wasEventProcessed = false;
    
    State aState = state;
    switch (aState)
    {
      case OnTheWay:
        setState(State.Available);
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
      case OnTheWay:
        setState(State.Arrived);
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

  public Organization getOrganization()
  {
    return organization;
  }

  public Resource getResource()
  {
    return resource;
  }

  public ResourceMatch getResourceMatch()
  {
    return resourceMatch;
  }

  public boolean hasResourceMatch()
  {
    boolean has = resourceMatch != null;
    return has;
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
      existingOrganization.removeResourceAvailability(this);
    }
    organization.addResourceAvailability(this);
    wasSet = true;
    return wasSet;
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
      existingResource.removeResourceAvailability(this);
    }
    resource.addResourceAvailability(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setResourceMatch(ResourceMatch aNewResourceMatch)
  {
    boolean wasSet = false;
    if (resourceMatch != null && !resourceMatch.equals(aNewResourceMatch) && equals(resourceMatch.getResourceAvailability()))
    {
      //Unable to setResourceMatch, as existing resourceMatch would become an orphan
      return wasSet;
    }

    resourceMatch = aNewResourceMatch;
    ResourceAvailability anOldResourceAvailability = aNewResourceMatch != null ? aNewResourceMatch.getResourceAvailability() : null;

    if (!this.equals(anOldResourceAvailability))
    {
      if (anOldResourceAvailability != null)
      {
        anOldResourceAvailability.resourceMatch = null;
      }
      if (resourceMatch != null)
      {
        resourceMatch.setResourceAvailability(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Organization placeholderOrganization = organization;
    this.organization = null;
    placeholderOrganization.removeResourceAvailability(this);
    Resource placeholderResource = resource;
    this.resource = null;
    placeholderResource.removeResourceAvailability(this);
    ResourceMatch existingResourceMatch = resourceMatch;
    resourceMatch = null;
    if (existingResourceMatch != null)
    {
      existingResourceMatch.delete();
    }
  }

  // line 100 "../../../class diagram (1).ump"
   public ResourceAvailability split(int quantity){
    ResourceAvailability ra = new ResourceAvailability( quantity, getOrganization(), getResource() );
	this.quantity = getQuantity()-quantity;
	ra.setQuantity(quantity);
	return ra;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "organization = "+(getOrganization()!=null?Integer.toHexString(System.identityHashCode(getOrganization())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "resource = "+(getResource()!=null?Integer.toHexString(System.identityHashCode(getResource())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "resourceMatch = "+(getResourceMatch()!=null?Integer.toHexString(System.identityHashCode(getResourceMatch())):"null")
     + outputString;
  }
}