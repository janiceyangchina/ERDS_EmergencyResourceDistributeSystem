/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.2.4305 modeling language!*/

package system;

// line 50 "../../../class diagram (1).ump"
public class SimpleDateFormat
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SimpleDateFormat Attributes
  private String date;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SimpleDateFormat(String aDate)
  {
    date = aDate;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(String aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public String getDate()
  {
    return date;
  }

  public void delete()
  {}

  // line 53 "../../../class diagram (1).ump"
   public String format(String datePased){
    // Assumes we do the formatting and send back the formatted date
	return date;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "date" + ":" + getDate()+ "]"
     + outputString;
  }
}