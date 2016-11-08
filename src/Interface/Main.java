package Interface;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import system.ERDS;
import system.Organization;
import system.Resource;

public class Main {
	
	public static ERDS erdsSystem = new ERDS();
	
	public static void main(String args[]) {
		
		System.out.println("Welcome to WHO");
		
		DisplayMainScreen();
	}
	
	public static void DisplayMainScreen() {
		int userInput = 0;
		
		ReportEmergency.MainReportEmergency();
		
		/*System.out.println("Following are your options");
		System.out.println("1 . Report Emergency");
		System.out.println("2.  Add Resouces");
		System.out.println("3.  Register Organization");
		System.out.println("4 . Display All");
		
		try {
			userInput = Integer.parseInt(getInput());
		}
		catch(Exception e) {
			System.out.println("Invalid input");
			DisplayMainScreen();
		}
		switch(userInput) {
		case 1:ReportEmergency.MainReportEmergency();
			break;
		case 2:ResourceEntry.main(null);
			break;
		case 3: CompanyRegistration.main(null);
			break;
		case 4:DisplayAll();
			break;
		default : DisplayMainScreen();
			break;
		}*/
		}
	
	public static Resource findResource(String resourceName) {
		for(Resource resource : erdsSystem.getResources()) {
			if (resourceName.equals(resource.getName())){
				return resource;
			}
		}
		return null;
	}
	
	public static String getInput() {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
	      String userName = null;
	 
	      //  read the username from the command-line; need to use try/catch with the
	      //  readLine() method
	      try {
	         userName = br.readLine();
	      } catch (IOException ioe) {
	         System.out.println("IO error trying to read your name!");
	         System.exit(1);
	      }
	      
	      return userName;
	}
	
	public static void DisplayAll() {
		for(Organization organization : erdsSystem.getOrganizations() ) {
			System.out.println("organization " + organization);
		}
		for(Resource resource:erdsSystem.getResources()) {
			System.out.println("Resources remaining "+ReportEmergency.getTotalQuantity(resource));
			
		}
		DisplayMainScreen();
	}
}
