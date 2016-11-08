/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.2.4305 modeling language!*/

package system;

// line 204 "../../../class diagram (1).ump"
// line 298 "../../../class diagram (1).ump"
public class TransportationType
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TransportationType Attributes
  private String air;
  private String sea;
  private String land;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TransportationType(String aAir, String aSea, String aLand)
  {
    air = aAir;
    sea = aSea;
    land = aLand;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAir(String aAir)
  {
    boolean wasSet = false;
    air = aAir;
    wasSet = true;
    return wasSet;
  }

  public boolean setSea(String aSea)
  {
    boolean wasSet = false;
    sea = aSea;
    wasSet = true;
    return wasSet;
  }

  public boolean setLand(String aLand)
  {
    boolean wasSet = false;
    land = aLand;
    wasSet = true;
    return wasSet;
  }

  public String getAir()
  {
    return air;
  }

  public String getSea()
  {
    return sea;
  }

  public String getLand()
  {
    return land;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "air" + ":" + getAir()+ "," +
            "sea" + ":" + getSea()+ "," +
            "land" + ":" + getLand()+ "]"
     + outputString;
  }
}