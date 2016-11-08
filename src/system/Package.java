/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.2.4305 modeling language!*/

package system;
import java.util.*;

// line 181 "../../../class diagram (1).ump"
// line 288 "../../../class diagram (1).ump"
public class Package extends Resource
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Package Attributes
  private int quantityUnits;
  private int dimension;
  private int weight;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Package(String aName, String aDescription, ERDS aERDS, int aQuantityUnits, int aDimension, int aWeight)
  {
    super(aName, aDescription, aERDS);
    quantityUnits = aQuantityUnits;
    dimension = aDimension;
    weight = aWeight;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setQuantityUnits(int aQuantityUnits)
  {
    boolean wasSet = false;
    // line 185 "../../../class diagram (1).ump"
    if (aQuantityUnits <= 0){return false;}
    quantityUnits = aQuantityUnits;
    wasSet = true;
    return wasSet;
  }

  public boolean setDimension(int aDimension)
  {
    boolean wasSet = false;
    // line 188 "../../../class diagram (1).ump"
    if (aDimension <= 0){return false;}
    dimension = aDimension;
    wasSet = true;
    return wasSet;
  }

  public boolean setWeight(int aWeight)
  {
    boolean wasSet = false;
    // line 191 "../../../class diagram (1).ump"
    if (aWeight <= 0){return false;}
    weight = aWeight;
    wasSet = true;
    return wasSet;
  }

  public int getQuantityUnits()
  {
    return quantityUnits;
  }

  public int getDimension()
  {
    return dimension;
  }

  public int getWeight()
  {
    return weight;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "quantityUnits" + ":" + getQuantityUnits()+ "," +
            "dimension" + ":" + getDimension()+ "," +
            "weight" + ":" + getWeight()+ "]"
     + outputString;
  }
}