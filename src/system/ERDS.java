/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.2.4305 modeling language!*/

package system;
import java.util.*;

// line 2 "../../../class diagram (1).ump"
// line 213 "../../../class diagram (1).ump"
public class ERDS
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ERDS Associations
  private List<Person> persons;
  private List<Organization> organizations;
  private List<Resource> resources;
  private List<Schedule> schedules;
  private List<Emergency> emergencies;
  private List<Community> communities;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ERDS()
  {
    persons = new ArrayList<Person>();
    organizations = new ArrayList<Organization>();
    resources = new ArrayList<Resource>();
    schedules = new ArrayList<Schedule>();
    emergencies = new ArrayList<Emergency>();
    communities = new ArrayList<Community>();
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public Organization getOrganization(int index)
  {
    Organization aOrganization = organizations.get(index);
    return aOrganization;
  }

  public List<Organization> getOrganizations()
  {
    List<Organization> newOrganizations = Collections.unmodifiableList(organizations);
    return newOrganizations;
  }

  public int numberOfOrganizations()
  {
    int number = organizations.size();
    return number;
  }

  public boolean hasOrganizations()
  {
    boolean has = organizations.size() > 0;
    return has;
  }

  public int indexOfOrganization(Organization aOrganization)
  {
    int index = organizations.indexOf(aOrganization);
    return index;
  }

  public Resource getResource(int index)
  {
    Resource aResource = resources.get(index);
    return aResource;
  }

  public List<Resource> getResources()
  {
    List<Resource> newResources = Collections.unmodifiableList(resources);
    return newResources;
  }

  public int numberOfResources()
  {
    int number = resources.size();
    return number;
  }

  public boolean hasResources()
  {
    boolean has = resources.size() > 0;
    return has;
  }

  public int indexOfResource(Resource aResource)
  {
    int index = resources.indexOf(aResource);
    return index;
  }

  public Schedule getSchedule(int index)
  {
    Schedule aSchedule = schedules.get(index);
    return aSchedule;
  }

  public List<Schedule> getSchedules()
  {
    List<Schedule> newSchedules = Collections.unmodifiableList(schedules);
    return newSchedules;
  }

  public int numberOfSchedules()
  {
    int number = schedules.size();
    return number;
  }

  public boolean hasSchedules()
  {
    boolean has = schedules.size() > 0;
    return has;
  }

  public int indexOfSchedule(Schedule aSchedule)
  {
    int index = schedules.indexOf(aSchedule);
    return index;
  }

  public Emergency getEmergency(int index)
  {
    Emergency aEmergency = emergencies.get(index);
    return aEmergency;
  }

  public List<Emergency> getEmergencies()
  {
    List<Emergency> newEmergencies = Collections.unmodifiableList(emergencies);
    return newEmergencies;
  }

  public int numberOfEmergencies()
  {
    int number = emergencies.size();
    return number;
  }

  public boolean hasEmergencies()
  {
    boolean has = emergencies.size() > 0;
    return has;
  }

  public int indexOfEmergency(Emergency aEmergency)
  {
    int index = emergencies.indexOf(aEmergency);
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

  public static int minimumNumberOfPersons()
  {
    return 0;
  }

  public Person addPerson(String aName, int aAge, String aGender, Organization aOrganization)
  {
    return new Person(aName, aAge, aGender, this, aOrganization);
  }

  public boolean addPerson(Person aPerson)
  {
    boolean wasAdded = false;
    if (persons.contains(aPerson)) { return false; }
    ERDS existingERDS = aPerson.getERDS();
    boolean isNewERDS = existingERDS != null && !this.equals(existingERDS);
    if (isNewERDS)
    {
      aPerson.setERDS(this);
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
    //Unable to remove aPerson, as it must always have a eRDS
    if (!this.equals(aPerson.getERDS()))
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

  public static int minimumNumberOfOrganizations()
  {
    return 0;
  }

  public Organization addOrganization(String aName, String aLocation)
  {
    return new Organization(aName, aLocation, this);
  }

  public boolean addOrganization(Organization aOrganization)
  {
    boolean wasAdded = false;
    if (organizations.contains(aOrganization)) { return false; }
    ERDS existingERDS = aOrganization.getERDS();
    boolean isNewERDS = existingERDS != null && !this.equals(existingERDS);
    if (isNewERDS)
    {
      aOrganization.setERDS(this);
    }
    else
    {
      organizations.add(aOrganization);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrganization(Organization aOrganization)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrganization, as it must always have a eRDS
    if (!this.equals(aOrganization.getERDS()))
    {
      organizations.remove(aOrganization);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addOrganizationAt(Organization aOrganization, int index)
  {  
    boolean wasAdded = false;
    if(addOrganization(aOrganization))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrganizations()) { index = numberOfOrganizations() - 1; }
      organizations.remove(aOrganization);
      organizations.add(index, aOrganization);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrganizationAt(Organization aOrganization, int index)
  {
    boolean wasAdded = false;
    if(organizations.contains(aOrganization))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrganizations()) { index = numberOfOrganizations() - 1; }
      organizations.remove(aOrganization);
      organizations.add(index, aOrganization);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrganizationAt(aOrganization, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfResources()
  {
    return 0;
  }

  public Resource addResource(String aName, String aDescription)
  {
    return new Resource(aName, aDescription, this);
  }

  public boolean addResource(Resource aResource)
  {
    boolean wasAdded = false;
    if (resources.contains(aResource)) { return false; }
    ERDS existingERDS = aResource.getERDS();
    boolean isNewERDS = existingERDS != null && !this.equals(existingERDS);
    if (isNewERDS)
    {
      aResource.setERDS(this);
    }
    else
    {
      resources.add(aResource);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeResource(Resource aResource)
  {
    boolean wasRemoved = false;
    //Unable to remove aResource, as it must always have a eRDS
    if (!this.equals(aResource.getERDS()))
    {
      resources.remove(aResource);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addResourceAt(Resource aResource, int index)
  {  
    boolean wasAdded = false;
    if(addResource(aResource))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfResources()) { index = numberOfResources() - 1; }
      resources.remove(aResource);
      resources.add(index, aResource);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveResourceAt(Resource aResource, int index)
  {
    boolean wasAdded = false;
    if(resources.contains(aResource))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfResources()) { index = numberOfResources() - 1; }
      resources.remove(aResource);
      resources.add(index, aResource);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addResourceAt(aResource, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSchedules()
  {
    return 0;
  }

  public Schedule addSchedule(String aTransportationStartDate, String aTransportationArriveDate, TransportAvailability aTransportAvailability)
  {
    return new Schedule(aTransportationStartDate, aTransportationArriveDate, aTransportAvailability, this);
  }

  public boolean addSchedule(Schedule aSchedule)
  {
    boolean wasAdded = false;
    if (schedules.contains(aSchedule)) { return false; }
    ERDS existingERDS = aSchedule.getERDS();
    boolean isNewERDS = existingERDS != null && !this.equals(existingERDS);
    if (isNewERDS)
    {
      aSchedule.setERDS(this);
    }
    else
    {
      schedules.add(aSchedule);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSchedule(Schedule aSchedule)
  {
    boolean wasRemoved = false;
    //Unable to remove aSchedule, as it must always have a eRDS
    if (!this.equals(aSchedule.getERDS()))
    {
      schedules.remove(aSchedule);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addScheduleAt(Schedule aSchedule, int index)
  {  
    boolean wasAdded = false;
    if(addSchedule(aSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSchedules()) { index = numberOfSchedules() - 1; }
      schedules.remove(aSchedule);
      schedules.add(index, aSchedule);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveScheduleAt(Schedule aSchedule, int index)
  {
    boolean wasAdded = false;
    if(schedules.contains(aSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSchedules()) { index = numberOfSchedules() - 1; }
      schedules.remove(aSchedule);
      schedules.add(index, aSchedule);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addScheduleAt(aSchedule, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfEmergencies()
  {
    return 0;
  }

  public Emergency addEmergency(String aLocation)
  {
    return new Emergency(aLocation, this);
  }

  public boolean addEmergency(Emergency aEmergency)
  {
    boolean wasAdded = false;
    if (emergencies.contains(aEmergency)) { return false; }
    ERDS existingERDS = aEmergency.getERDS();
    boolean isNewERDS = existingERDS != null && !this.equals(existingERDS);
    if (isNewERDS)
    {
      aEmergency.setERDS(this);
    }
    else
    {
      emergencies.add(aEmergency);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEmergency(Emergency aEmergency)
  {
    boolean wasRemoved = false;
    //Unable to remove aEmergency, as it must always have a eRDS
    if (!this.equals(aEmergency.getERDS()))
    {
      emergencies.remove(aEmergency);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEmergencyAt(Emergency aEmergency, int index)
  {  
    boolean wasAdded = false;
    if(addEmergency(aEmergency))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmergencies()) { index = numberOfEmergencies() - 1; }
      emergencies.remove(aEmergency);
      emergencies.add(index, aEmergency);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEmergencyAt(Emergency aEmergency, int index)
  {
    boolean wasAdded = false;
    if(emergencies.contains(aEmergency))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmergencies()) { index = numberOfEmergencies() - 1; }
      emergencies.remove(aEmergency);
      emergencies.add(index, aEmergency);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEmergencyAt(aEmergency, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfCommunities()
  {
    return 0;
  }

  public Community addCommunity(String aName, Emergency aEmergency)
  {
    return new Community(aName, this, aEmergency);
  }

  public boolean addCommunity(Community aCommunity)
  {
    boolean wasAdded = false;
    if (communities.contains(aCommunity)) { return false; }
    ERDS existingERDS = aCommunity.getERDS();
    boolean isNewERDS = existingERDS != null && !this.equals(existingERDS);
    if (isNewERDS)
    {
      aCommunity.setERDS(this);
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
    //Unable to remove aCommunity, as it must always have a eRDS
    if (!this.equals(aCommunity.getERDS()))
    {
      communities.remove(aCommunity);
      wasRemoved = true;
    }
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

  public void delete()
  {
    for(int i=persons.size(); i > 0; i--)
    {
      Person aPerson = persons.get(i - 1);
      aPerson.delete();
    }
    for(int i=organizations.size(); i > 0; i--)
    {
      Organization aOrganization = organizations.get(i - 1);
      aOrganization.delete();
    }
    for(int i=resources.size(); i > 0; i--)
    {
      Resource aResource = resources.get(i - 1);
      aResource.delete();
    }
    for(int i=schedules.size(); i > 0; i--)
    {
      Schedule aSchedule = schedules.get(i - 1);
      aSchedule.delete();
    }
    for(int i=emergencies.size(); i > 0; i--)
    {
      Emergency aEmergency = emergencies.get(i - 1);
      aEmergency.delete();
    }
    for(int i=communities.size(); i > 0; i--)
    {
      Community aCommunity = communities.get(i - 1);
      aCommunity.delete();
    }
  }

  // line 11 "../../../class diagram (1).ump"
   public void resolvedEmergency(Emergency emergency){
    emergency.transportResourceToDestination();
  }

}