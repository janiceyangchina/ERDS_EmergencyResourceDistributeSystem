/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.2.4305 modeling language!*/

package system;
import java.util.*;

// line 198 "../../../class diagram (1).ump"
// line 293 "../../../class diagram (1).ump"
public class Transportation extends Resource
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Transportation Attributes
  private String cargoCapacity;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Transportation(String aName, String aDescription, ERDS aERDS, String aCargoCapacity)
  {
    super(aName, aDescription, aERDS);
    cargoCapacity = aCargoCapacity;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCargoCapacity(String aCargoCapacity)
  {
    boolean wasSet = false;
    cargoCapacity = aCargoCapacity;
    wasSet = true;
    return wasSet;
  }

  public String getCargoCapacity()
  {
    return cargoCapacity;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "cargoCapacity" + ":" + getCargoCapacity()+ "]"
     + outputString;
  }
}