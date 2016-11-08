/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.2.4305 modeling language!*/

package system;
import java.util.*;

// line 59 "../../../class diagram (1).ump"
// line 228 "../../../class diagram (1).ump"
public class Schedule
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Schedule Attributes
  private String transportationStartDate;
  private String transportationArriveDate;
  private SimpleDateFormat df;

  //Schedule Associations
  private List<ResourceMatch> resourceMatchs;
  private TransportAvailability transportAvailability;
  private ERDS eRDS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Schedule(String aTransportationStartDate, String aTransportationArriveDate, TransportAvailability aTransportAvailability, ERDS aERDS)
  {
    transportationStartDate = aTransportationStartDate;
    transportationArriveDate = aTransportationArriveDate;
    df = new SimpleDateFormat("dd-MMM-yyyy");
    resourceMatchs = new ArrayList<ResourceMatch>();
    boolean didAddTransportAvailability = setTransportAvailability(aTransportAvailability);
    if (!didAddTransportAvailability)
    {
      throw new RuntimeException("Unable to create schedule due to transportAvailability");
    }
    boolean didAddERDS = setERDS(aERDS);
    if (!didAddERDS)
    {
      throw new RuntimeException("Unable to create schedule due to eRDS");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTransportationStartDate(String aTransportationStartDate)
  {
    boolean wasSet = false;
    // line 63 "../../../class diagram (1).ump"
    if (aTransportationStartDate != df.format(aTransportationStartDate)){return false;}
    transportationStartDate = aTransportationStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setTransportationArriveDate(String aTransportationArriveDate)
  {
    boolean wasSet = false;
    // line 66 "../../../class diagram (1).ump"
    if (aTransportationArriveDate != df.format(aTransportationArriveDate)){return false;}
          if (transportationStartDate.compareTo(aTransportationArriveDate) != -1){return false;}
    transportationArriveDate = aTransportationArriveDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setDf(SimpleDateFormat aDf)
  {
    boolean wasSet = false;
    df = aDf;
    wasSet = true;
    return wasSet;
  }

  public String getTransportationStartDate()
  {
    return transportationStartDate;
  }

  public String getTransportationArriveDate()
  {
    return transportationArriveDate;
  }

  public SimpleDateFormat getDf()
  {
    return df;
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

  public TransportAvailability getTransportAvailability()
  {
    return transportAvailability;
  }

  public ERDS getERDS()
  {
    return eRDS;
  }

  public static int minimumNumberOfResourceMatchs()
  {
    return 0;
  }

  public ResourceMatch addResourceMatch(ResourceAvailability aResourceAvailability, ResourceNeed aResourceNeed)
  {
    return new ResourceMatch(aResourceAvailability, aResourceNeed, this);
  }

  public boolean addResourceMatch(ResourceMatch aResourceMatch)
  {
    boolean wasAdded = false;
    if (resourceMatchs.contains(aResourceMatch)) { return false; }
    Schedule existingSchedule = aResourceMatch.getSchedule();
    boolean isNewSchedule = existingSchedule != null && !this.equals(existingSchedule);
    if (isNewSchedule)
    {
      aResourceMatch.setSchedule(this);
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
    //Unable to remove aResourceMatch, as it must always have a schedule
    if (!this.equals(aResourceMatch.getSchedule()))
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

  public boolean setTransportAvailability(TransportAvailability aNewTransportAvailability)
  {
    boolean wasSet = false;
    if (aNewTransportAvailability == null)
    {
      //Unable to setTransportAvailability to null, as schedule must always be associated to a transportAvailability
      return wasSet;
    }
    
    Schedule existingSchedule = aNewTransportAvailability.getSchedule();
    if (existingSchedule != null && !equals(existingSchedule))
    {
      //Unable to setTransportAvailability, the current transportAvailability already has a schedule, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    TransportAvailability anOldTransportAvailability = transportAvailability;
    transportAvailability = aNewTransportAvailability;
    transportAvailability.setSchedule(this);

    if (anOldTransportAvailability != null)
    {
      anOldTransportAvailability.setSchedule(null);
    }
    wasSet = true;
    return wasSet;
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
      existingERDS.removeSchedule(this);
    }
    eRDS.addSchedule(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=resourceMatchs.size(); i > 0; i--)
    {
      ResourceMatch aResourceMatch = resourceMatchs.get(i - 1);
      aResourceMatch.delete();
    }
    TransportAvailability existingTransportAvailability = transportAvailability;
    transportAvailability = null;
    if (existingTransportAvailability != null)
    {
      existingTransportAvailability.setSchedule(null);
    }
    ERDS placeholderERDS = eRDS;
    this.eRDS = null;
    placeholderERDS.removeSchedule(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "transportationStartDate" + ":" + getTransportationStartDate()+ "," +
            "transportationArriveDate" + ":" + getTransportationArriveDate()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "df" + "=" + (getDf() != null ? !getDf().equals(this)  ? getDf().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "transportAvailability = "+(getTransportAvailability()!=null?Integer.toHexString(System.identityHashCode(getTransportAvailability())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "eRDS = "+(getERDS()!=null?Integer.toHexString(System.identityHashCode(getERDS())):"null")
     + outputString;
  }
}