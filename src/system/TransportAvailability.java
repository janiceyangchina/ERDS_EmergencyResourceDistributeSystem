/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.2.4305 modeling language!*/

package system;

// line 116 "../../../class diagram (1).ump"
// line 248 "../../../class diagram (1).ump"
public class TransportAvailability extends ResourceAvailability
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TransportAvailability Attributes
  private TransportationType type;
  private String geographicRange;

  //TransportAvailability Associations
  private Schedule schedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TransportAvailability(int aQuantity, Organization aOrganization, Resource aResource, TransportationType aType, String aGeographicRange)
  {
    super(aQuantity, aOrganization, aResource);
    type = aType;
    geographicRange = aGeographicRange;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setType(TransportationType aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public boolean setGeographicRange(String aGeographicRange)
  {
    boolean wasSet = false;
    // line 119 "../../../class diagram (1).ump"
    if (aGeographicRange.length() <= 2 || aGeographicRange.length() >= 20 ){return false;}
    geographicRange = aGeographicRange;
    wasSet = true;
    return wasSet;
  }

  public TransportationType getType()
  {
    return type;
  }

  public String getGeographicRange()
  {
    return geographicRange;
  }

  public Schedule getSchedule()
  {
    return schedule;
  }

  public boolean hasSchedule()
  {
    boolean has = schedule != null;
    return has;
  }

  public boolean setSchedule(Schedule aNewSchedule)
  {
    boolean wasSet = false;
    if (schedule != null && !schedule.equals(aNewSchedule) && equals(schedule.getTransportAvailability()))
    {
      //Unable to setSchedule, as existing schedule would become an orphan
      return wasSet;
    }

    schedule = aNewSchedule;
    TransportAvailability anOldTransportAvailability = aNewSchedule != null ? aNewSchedule.getTransportAvailability() : null;

    if (!this.equals(anOldTransportAvailability))
    {
      if (anOldTransportAvailability != null)
      {
        anOldTransportAvailability.schedule = null;
      }
      if (schedule != null)
      {
        schedule.setTransportAvailability(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Schedule existingSchedule = schedule;
    schedule = null;
    if (existingSchedule != null)
    {
      existingSchedule.delete();
    }
    super.delete();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "geographicRange" + ":" + getGeographicRange()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "schedule = "+(getSchedule()!=null?Integer.toHexString(System.identityHashCode(getSchedule())):"null")
     + outputString;
  }
}