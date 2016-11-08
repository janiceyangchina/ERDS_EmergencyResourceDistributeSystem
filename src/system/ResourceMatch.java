/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.2.4305 modeling language!*/

package system;

// line 75 "../../../class diagram (1).ump"
// line 233 "../../../class diagram (1).ump"
public class ResourceMatch
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ResourceMatch Associations
  private ResourceAvailability resourceAvailability;
  private ResourceNeed resourceNeed;
  private Schedule schedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ResourceMatch(ResourceAvailability aResourceAvailability, ResourceNeed aResourceNeed, Schedule aSchedule)
  {
    boolean didAddResourceAvailability = setResourceAvailability(aResourceAvailability);
    if (!didAddResourceAvailability)
    {
      throw new RuntimeException("Unable to create resourceMatch due to resourceAvailability");
    }
    boolean didAddResourceNeed = setResourceNeed(aResourceNeed);
    if (!didAddResourceNeed)
    {
      throw new RuntimeException("Unable to create resourceMatch due to resourceNeed");
    }
    boolean didAddSchedule = setSchedule(aSchedule);
    if (!didAddSchedule)
    {
      throw new RuntimeException("Unable to create resourceMatch due to schedule");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public ResourceAvailability getResourceAvailability()
  {
    return resourceAvailability;
  }

  public ResourceNeed getResourceNeed()
  {
    return resourceNeed;
  }

  public Schedule getSchedule()
  {
    return schedule;
  }

  public boolean setResourceAvailability(ResourceAvailability aNewResourceAvailability)
  {
    boolean wasSet = false;
    if (aNewResourceAvailability == null)
    {
      //Unable to setResourceAvailability to null, as resourceMatch must always be associated to a resourceAvailability
      return wasSet;
    }
    
    ResourceMatch existingResourceMatch = aNewResourceAvailability.getResourceMatch();
    if (existingResourceMatch != null && !equals(existingResourceMatch))
    {
      //Unable to setResourceAvailability, the current resourceAvailability already has a resourceMatch, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    ResourceAvailability anOldResourceAvailability = resourceAvailability;
    resourceAvailability = aNewResourceAvailability;
    resourceAvailability.setResourceMatch(this);

    if (anOldResourceAvailability != null)
    {
      anOldResourceAvailability.setResourceMatch(null);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean setResourceNeed(ResourceNeed aResourceNeed)
  {
    boolean wasSet = false;
    if (aResourceNeed == null)
    {
      return wasSet;
    }

    ResourceNeed existingResourceNeed = resourceNeed;
    resourceNeed = aResourceNeed;
    if (existingResourceNeed != null && !existingResourceNeed.equals(aResourceNeed))
    {
      existingResourceNeed.removeResourceMatch(this);
    }
    resourceNeed.addResourceMatch(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setSchedule(Schedule aSchedule)
  {
    boolean wasSet = false;
    if (aSchedule == null)
    {
      return wasSet;
    }

    Schedule existingSchedule = schedule;
    schedule = aSchedule;
    if (existingSchedule != null && !existingSchedule.equals(aSchedule))
    {
      existingSchedule.removeResourceMatch(this);
    }
    schedule.addResourceMatch(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ResourceAvailability existingResourceAvailability = resourceAvailability;
    resourceAvailability = null;
    if (existingResourceAvailability != null)
    {
      existingResourceAvailability.setResourceMatch(null);
    }
    ResourceNeed placeholderResourceNeed = resourceNeed;
    this.resourceNeed = null;
    placeholderResourceNeed.removeResourceMatch(this);
    Schedule placeholderSchedule = schedule;
    this.schedule = null;
    placeholderSchedule.removeResourceMatch(this);
  }

}