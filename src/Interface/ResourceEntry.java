package Interface;

import system.Charity;
import system.Company;
import system.GovermentalOrganization;
import system.Organization;
import system.Package;
import system.Person;
import system.Resource;
import system.ResourceAvailability;
import system.Skill;
import system.TransportAvailability;
import system.Transportation;
import system.TransportationType;

public class ResourceEntry {

	
public static void main(String args[]) {
		
		System.out.println("Welcome to WHO");
		
		MainResourceEntry();
	}

public static void MainResourceEntry() {
	int userInput =0;
	
	System.out.println("Enter the type of organization");
	System.out.println("1. Charity ");
	System.out.println("2. Government");
	System.out.println("3. Transportation Company");
	System.out.println("4. Go back to main screen");
	
	try {
		userInput = Integer.parseInt(Main.getInput());
	}
	catch(Exception e) {
		System.out.println("Invalid input");
		Main.DisplayMainScreen();
	}
	
	switch(userInput) {
		case 1: PeopleGoodsSelection();
			break;
		case 2:PeopleGoodsSelection();
			break;
		case 3: TransportEntry();
			break;
		default : Main.DisplayMainScreen();
		break;
		}
	}

	public  static void TransportEntry() {
		System.out.println("Enter the organization name");
		String organizationName = Main.getInput();
		
		Organization organization = findOrganization(organizationName,2);
		
		if(organization == null) {
			System.out.println("Invalid input");
			Main.DisplayMainScreen();
		}
		String userInput ="y";
		while(userInput.equals("y")) {
		try {
			System.out.println("Enter name of unit");
			String name = Main.getInput();
			Transportation unit = (Transportation) Main.findResource(name);
			if(unit == null) {
				System.out.println("Resource doesnt exist .. Creating");
				
	
				System.out.println("Eneter description");
				String description = Main.getInput();
				System.out.println("Enter capacity");
				String capacity = Main.getInput();
				
				unit = new Transportation(name, description, Main.erdsSystem, capacity);

			
				
				Main.erdsSystem.addResource(unit);

			}
			
			System.out.println("Enter geographical range");
			String range = Main.getInput();
			
			System.out.println("Enter the mode of transportation");
			System.out.println("1. Land \n2. Air\n3. Sea");
			int mode = Integer.parseInt(Main.getInput());
			
			TransportationType transporttype = new TransportationType("","", "");
			if(mode ==1) {
				transporttype.setLand("land");
			} else if (mode ==2 ){
				transporttype.setAir("air");
			} else if(mode ==3) {
				transporttype.setSea("sea");
			
			}
			
			System.out.println("Enter the number of units");
			int number = Integer.parseInt(Main.getInput());
			
		//	ResourceState state = new ResourceState();
			
			
			TransportAvailability availability = new TransportAvailability(number, organization, unit, transporttype, range);

			unit.addResourceAvailability(availability);
			organization.addResourceAvailability(availability);
			System.out.println("Do you want to continue entering more resources for same company, press y else any other key");
			userInput = Main.getInput();
			
		}
		catch(Exception e) {
			System.out.println("Invalid input");
			Main.DisplayMainScreen();
		}
		}
		Main.DisplayMainScreen();
}

	public static void PeopleGoodsSelection() {
		System.out.println("Enter the organization name");
		String organizationName = Main.getInput();
		
		Organization organization = findOrganization(organizationName,1);
		
		if(organization == null) {
			System.out.println("Invalid input");
			Main.DisplayMainScreen();
		}
		
		int userInput=0;
		System.out.println("Enter your type of Resource");
		System.out.println("1. People");
		System.out.println("2. Package");
		
		try {
			userInput = Integer.parseInt(Main.getInput());
		}
		catch(Exception e) {
			System.out.println("Invalid input");
			Main.DisplayMainScreen();
		}
		
		switch(userInput) {
			case 1: PeopeEntry(organization);
				break;
			case 2:
				PackageEntry(organization);
				break;
			default : Main.DisplayMainScreen();
			break;
			}
		}
		
	
	public static void PeopeEntry(Organization organization) {
		try  {
		System.out.println("You can enter infomation about only one person at a time");
		System.out.println("Enter name");
		String userName = Main.getInput();
		System.out.println("Enter age");
		int age = Integer.parseInt(Main.getInput());
		System.out.println("Enter gender");
		String gender = Main.getInput();
		Person person = new Person(userName, age, gender, Main.erdsSystem,organization);
		organization.addPerson(person);
		String userInput = "y";
		while(userInput.equals("y")) {
			System.out.println("Enter the name of skill");
			String skill = Main.getInput();
			System.out.println("Enter the description of the skill");
			String desc = Main.getInput();
			Skill skillN = new Skill(skill,desc,Main.erdsSystem);
			person.addSkill(skillN);
			skillN.addPerson(person);
			Main.erdsSystem.addResource(skillN);
			System.out.println("Enter number of units available");
			int number = Integer.parseInt(Main.getInput());
		//	ResourceState state= new ResourceState();
			ResourceAvailability resourceAvailability = new ResourceAvailability( number, organization, skillN);
		//	resourceAvailability.setState(state);
			skillN.addResourceAvailability(resourceAvailability);
			organization.addResourceAvailability(resourceAvailability);
			System.out.println("Do you want to continue entering more skills  for same person, press y else any other key");
			userInput = Main.getInput();
		}
		}
		catch(Exception e){
			System.out.println("Invalid input");
			Main.DisplayMainScreen();
		}
		Main.DisplayMainScreen();
	}

	private static Organization findOrganization(String organizationName, int i) {
		if(i==1) {
			for(Organization org : Main.erdsSystem.getOrganizations()) {
				if(org.getName().equals(organizationName) && (org instanceof Charity || org instanceof GovermentalOrganization)) {
					return org;
				}
			}
		}
		else if (i==2) {
			for(Organization org : Main.erdsSystem.getOrganizations()) {
				if(org.getName().equals(organizationName) && (org instanceof Company)) {
					return org;
				}
			}
		}
		return null;
	}

	public static void PackageEntry(Organization organizationName) {
		String userReply = "y";
		while (userReply.equals("y")){
			try {
				String userInput="";
				System.out.println("Enter the resource name");
				userInput = Main.getInput();
				// Find the resource else create the resource
				Package pack =  null;
				
				Package resource = (Package) Main.findResource(userInput);
				if(resource == null){
					System.out.println("No such resources exists");
					System.out.println("Adding one now");
					System.out.println("Enter description");
					String userDescription = Main.getInput();
					System.out.println("Enter name");
					String userName = Main.getInput();
					System.out.println("Enter Dimension");
					int userDimension = Integer.parseInt(Main.getInput());
					System.out.println("Enter Weight");
					int userWeight = Integer.parseInt(Main.getInput());
					System.out.println("Enter number of units in each package");
					int userUnits = Integer.parseInt(Main.getInput());
					resource =new Package(userName,userDescription,Main.erdsSystem,userUnits,userDimension,userWeight);
				} 
				System.out.println("Enter the quantity of units");
				int number = Integer.parseInt(Main.getInput());
			//	ResourceState state= new ResourceState();
				ResourceAvailability resourceAvailability = new ResourceAvailability( number, organizationName, resource);
		//		resourceAvailability.setState(state);
				resource.addResourceAvailability(resourceAvailability);
				organizationName.addResourceAvailability(resourceAvailability);
				System.out.println("Do you want to continue entering more resources for same company, press y else any other key");
				userReply = Main.getInput();
			} catch(Exception e) {
				System.out.println("Invalid user input");
				Main.DisplayMainScreen();
			}
		}
		Main.DisplayMainScreen();
	}

	public static void PeopeEntryNew(Organization organization) {
	
		System.out.println("You can enter infomation about only one person at a time");
		System.out.println("Enter name");
		String userName = Main.getInput();
		Person person = new Person(userName, 18, "Male", Main.erdsSystem,organization);
		organization.addPerson(person);
		String userInput = "y";
		while(userInput.equals("y")) {
			try  {
			System.out.println("Enter the name of skill");
			String skill = Main.getInput();
			Skill skillN = new Skill(skill,"",Main.erdsSystem);
			person.addSkill(skillN);
			skillN.addPerson(person);
			Main.erdsSystem.addResource(skillN);
			System.out.println("Enter number of units available");
			int number = Integer.parseInt(Main.getInput());
//			ResourceState state= new ResourceState();
			ResourceAvailability resourceAvailability = new ResourceAvailability( number, organization, skillN);
	//		resourceAvailability.setState(state);
			skillN.addResourceAvailability(resourceAvailability);
			organization.addResourceAvailability(resourceAvailability);
			System.out.println("Do you want to continue entering more skills  for same person, press y else any other key");
			userInput = Main.getInput();
		}
			catch(Exception e){
				System.out.println("Invalid input");
				continue;
			}
		}
		
	}
	
	public static void PackageEntryNew(Organization organizationName) {
		String userReply = "y";
		while (userReply.equals("y")){
			try {
				String userInput="";
				System.out.println("Enter the resource name");
				userInput = Main.getInput();
				
				Resource resource = (Resource) Main.findResource(userInput);
				if(resource == null){
					resource =new Package(userInput,"",Main.erdsSystem,1,1,1);
					Main.erdsSystem.addResource(resource);
				} 
				System.out.println("Enter the quantity of units");
				int number = Integer.parseInt(Main.getInput());
			//	ResourceState state= new ResourceState();
				ResourceAvailability resourceAvailability = new ResourceAvailability( number, organizationName, resource);
		//		resourceAvailability.setState(state);
				resource.addResourceAvailability(resourceAvailability);
				organizationName.addResourceAvailability(resourceAvailability);
				System.out.println("Do you want to continue entering more Package resources for same company, press y else any other key");
				userReply = Main.getInput();
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("Invalid user input");
				continue;
			}
		}
	}
	
	public static void TransportEntryNew(Organization organizationName) {
		String userReply = "y";
		while (userReply.equals("y")){
			try {
				String userInput="";
				System.out.println("Enter the resource name");
				userInput = Main.getInput();
				
				Transportation unit = (Transportation) Main.findResource(userInput);
				if(unit == null){
					unit = new Transportation(userInput, "", Main.erdsSystem, "12");
					Main.erdsSystem.addResource(unit);
				} 
				System.out.println("Enter the quantity of units");
				int number = Integer.parseInt(Main.getInput());
				
				System.out.println("Enter the mode of transportation");
				System.out.println("1. Land \n2. Air\n3. Sea");
				int mode = Integer.parseInt(Main.getInput());
				
				TransportationType transporttype = new TransportationType("","", "");
				if(mode ==1) {
					transporttype.setLand("land");
				} else if (mode ==2 ){
					transporttype.setAir("air");
				} else if(mode ==3) {
					transporttype.setSea("sea");
				
				}
				

			//	ResourceState state= new ResourceState();
				TransportAvailability availability = new TransportAvailability( number, organizationName, unit, transporttype, "12");
		//		availability.setState(state);
				unit.addResourceAvailability(availability);
				organizationName.addResourceAvailability(availability);
				System.out.println("Do you want to continue entering more Transport resources for same company, press y else any other key");
				userReply = Main.getInput();
			} catch(Exception e) {
				System.out.println("Invalid user input");
				continue;
			}
		}
	}
	
}
