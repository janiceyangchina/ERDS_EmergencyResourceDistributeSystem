/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.2.4305 modeling language!*/

package system;

// line 42 "../../../class diagram (1).ump"
// line 223 "../../../class diagram (1).ump"
public class Community
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Community Attributes
  private String name;

  //Community Associations
  private ERDS eRDS;
  private Emergency emergency;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Community(String aName, ERDS aERDS, Emergency aEmergency)
  {
    name = aName;
    boolean didAddERDS = setERDS(aERDS);
    if (!didAddERDS)
    {
      throw new RuntimeException("Unable to create community due to eRDS");
    }
    boolean didAddEmergency = setEmergency(aEmergency);
    if (!didAddEmergency)
    {
      throw new RuntimeException("Unable to create community due to emergency");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    // line 44 "../../../class diagram (1).ump"
    if (aName.length() <= 2 || aName.length() >= 20 ){return false;}
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public ERDS getERDS()
  {
    return eRDS;
  }

  public Emergency getEmergency()
  {
    return emergency;
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
      existingERDS.removeCommunity(this);
    }
    eRDS.addCommunity(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setEmergency(Emergency aEmergency)
  {
    boolean wasSet = false;
    //Must provide emergency to community
    if (aEmergency == null)
    {
      return wasSet;
    }

    if (emergency != null && emergency.numberOfCommunities() <= Emergency.minimumNumberOfCommunities())
    {
      return wasSet;
    }

    Emergency existingEmergency = emergency;
    emergency = aEmergency;
    if (existingEmergency != null && !existingEmergency.equals(aEmergency))
    {
      boolean didRemove = existingEmergency.removeCommunity(this);
      if (!didRemove)
      {
        emergency = existingEmergency;
        return wasSet;
      }
    }
    emergency.addCommunity(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ERDS placeholderERDS = eRDS;
    this.eRDS = null;
    placeholderERDS.removeCommunity(this);
    Emergency placeholderEmergency = emergency;
    this.emergency = null;
    placeholderEmergency.removeCommunity(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "eRDS = "+(getERDS()!=null?Integer.toHexString(System.identityHashCode(getERDS())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "emergency = "+(getEmergency()!=null?Integer.toHexString(System.identityHashCode(getEmergency())):"null")
     + outputString;
  }
}