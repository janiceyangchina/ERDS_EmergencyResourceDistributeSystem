package Interface;


import java.util.ArrayList;
import java.util.List;

import system.Charity;
import system.Community;
import system.Company;
import system.Emergency;
import system.Resource;
import system.ResourceAvailability;
import system.ResourceMatch;
import system.ResourceNeed;
import system.Schedule;
import system.TransportAvailability;
import system.Transportation;

public class ReportEmergency {
	public static void main(String args[]) {
		MainReportEmergency();
	}
	
	public static void MainReportEmergency() {
		
		try {
		
		System.out.println("Enter the location of the emergency");
		
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		
		String emergencyLocation = Main.getInput();
		
		///Declaring an emergency
	//	EmergencyState state = new EmergencyState();
		
		
		Emergency emergency = new Emergency(emergencyLocation, Main.erdsSystem);
		
		Main.erdsSystem.addEmergency(emergency);
		
		List<Community> communityList = new ArrayList<Community>();
		
		String isThereMoreToEnter = "y";
		
		while(isThereMoreToEnter.equals("y")) {
			System.out.println("Enter the name of community");
			String communityName = Main.getInput();
			
			Community community = checkForCommunity(communityName);
			
			if(community == null) {
				community = new Community(communityName, Main.erdsSystem, emergency);
				Main.erdsSystem.addCommunity(community);
			} else {
				community.setEmergency(emergency);
			}
			communityList.add(community);
			System.out.println("Enter y if there are more communities reporting the same emergency");
			isThereMoreToEnter = Main.getInput();
		}
		
		isThereMoreToEnter = "y";
		
		System.out.println("Enter the resources needed, Dont enter the transporatation resources here");
		
		while(isThereMoreToEnter.equals("y")) {
			try {
			System.out.println("Enter the resource needed");
			String resourceName = Main.getInput();
		//	Resource resource = Main.findResource(resourceName);
	//		if(resource==null) {
//				System.out.println("No such resource exists, Enter a proper name");
	//			continue;
//			}
			
			Resource resource = new Resource(resourceName, "", Main.erdsSystem);
			
			System.out.println("Enter number of resources needed");
			int  number = Integer.parseInt(Main.getInput());
			
			ResourceNeed need = new ResourceNeed(number, resource, emergency);
			emergency.addResourceNeed(need);
			
			System.out.println("If there more number of resources, enter y");
			isThereMoreToEnter = Main.getInput();
			}
			catch(Exception e) {
				System.out.println("No tricks , Enter only numbers");
				continue;
			}
			}
		
		System.out.println("Enter the number of land units required");
		
		int landUnits = Integer.parseInt(Main.getInput());
		
		System.out.println("Enter the number of sea units required");
		
		int seaUnits = Integer.parseInt(Main.getInput());
		
		System.out.println("Enter the number of air units required");
		
		int airUnits = Integer.parseInt(Main.getInput());
		
		System.out.println("Enter the name of Charity company");
		
		String charityName = Main.getInput();
		
		System.out.println("Enter the location of Charity company");
		
		String charityLocation = Main.getInput();
		
		Charity charity = new Charity(charityName, charityLocation, Main.erdsSystem);
		Main.erdsSystem.addOrganization(charity);
		
		isThereMoreToEnter = "y";
		while(isThereMoreToEnter.equals("y")) {
		
			int userInput=0;
			System.out.println("Enter your type of Resource");
			System.out.println("1. People");
			System.out.println("2. Package");
			
			try {
				userInput = Integer.parseInt(Main.getInput());
			}
			catch(Exception e) {
				System.out.println("Invalid input");
				continue;
			}
			
			if(userInput==1){
				ResourceEntry.PeopeEntryNew(charity);
			}
			else {
				ResourceEntry.PackageEntryNew(charity);
			}
			System.out.println("Do you add more resource, press y");
			isThereMoreToEnter = Main.getInput();
		}
		
		System.out.println("Enter the name of transportation company");
		
		String transportName = Main.getInput();
		
		System.out.println("Enter the location of transportation company");
		
		String transportLocation = Main.getInput();
		
		Company company = new Company(transportName, transportLocation, Main.erdsSystem);
		Main.erdsSystem.addOrganization(company);
		
		isThereMoreToEnter = "y";
		
		//Get All the transport things
		ResourceEntry.TransportEntryNew(company);
			
		
		// Scheduling algorithm
		
		List<ResourceNeed> nonTransport = getNonTransport(emergency);
		
		List<ResourceNeed> Transport = getTransport(emergency);
		

		if(getTransport() == 0) {
			System.out.println("Not enough resources / Exiting program");
			return;
		}
		
		for(ResourceNeed eachNeed : nonTransport) {
			if(eachNeed.getRequiredQuantity() > getTotalQuantity(eachNeed.getResource())) {
				System.out.println("Not enough resources");
				return;
			}
		}
		
		List<ResourceMatch> matchedResources = new ArrayList<ResourceMatch>();
		
		Schedule schedule = null;
		
		if(Transport.size() == 0) {
			Transportation transport = getFirstFreeTransport();
			TransportAvailability availability = (TransportAvailability) transport.getResourceAvailability(0);
			 schedule = new Schedule("today", "tomorrow", availability, Main.erdsSystem);
			
		} else  {
			Transportation transport = (Transportation) Transport.get(0).getResource();
			TransportAvailability availability = (TransportAvailability) transport.getResourceAvailability(0);
			 schedule = new Schedule("today", "tomorrow", availability, Main.erdsSystem);
			
		}
		
		scheduleList.add(schedule);
		Main.erdsSystem.addSchedule(schedule);
		
		emergency.finishDeclaring();
		
		
		//Providing resource 
		for(ResourceNeed nontransport : nonTransport) {
			
			emergency.continueProvideResource();
			
			
			int counter = 0;
			int index=0;
			int difference = nontransport.getRequiredQuantity() - counter;
			while (difference >0 ) {
				ResourceAvailability avail = nontransport.getResource().getResourceAvailabilities().get(index);
				if(avail.getStateFullName().equals("Available")){
				
				if(difference > avail.getQuantity()) {
					ResourceAvailability toAllot = avail.split(avail.getQuantity());
					
					// Resource Availiability changing the state. 
					toAllot.coordinateResource();
					
					ResourceMatch match = new ResourceMatch(toAllot, nontransport, schedule);
					match.setResourceAvailability(toAllot);
					matchedResources.add(match);
					index ++;
					counter = counter+ toAllot.getQuantity();

				} else {
					ResourceAvailability toAllot = avail.split(difference);
					
					// Resource Availability changing the state
					toAllot.coordinateResource();
					
					ResourceMatch match = new ResourceMatch(toAllot, nontransport, schedule);
					match.setResourceAvailability(toAllot);
					matchedResources.add(match);
					index ++;
					counter = counter + difference;
				}
				difference = nontransport.getRequiredQuantity() - counter;
			}
				else {
					index++;
				}
			}
		}
		
		List<ResourceMatch> backUpmatchedResources = new ArrayList<ResourceMatch>();
		
		for(ResourceMatch match:matchedResources){
			backUpmatchedResources.add(match);
		}

		
		emergency.coordiniateResource();

		
		while(backUpmatchedResources.size() != 0) {
			int i =1;
			System.out.println("Select one option to match");
			for(ResourceMatch match:backUpmatchedResources){
				System.out.println( (i++) + " " + match.getResourceAvailability().getResource().getName() + " "+match.getResourceAvailability().getQuantity());
			}
			
			int userInput = Integer.parseInt(Main.getInput());
			
			ResourceMatch match = backUpmatchedResources.remove(userInput-1);
			
			i=1;
			
			System.out.println("Select one option to match from transport");
			
			for(ResourceAvailability resourceavailability:company.getResourceAvailabilities()) {
				System.out.println((i++) + " " + resourceavailability.getResource().getName() + "   " +resourceavailability.getQuantity());
			}
			
			userInput = Integer.parseInt(Main.getInput());
			
			TransportAvailability availability = (TransportAvailability) company.getResourceAvailabilities().get(userInput-1);
			
			schedule = 	availability.getSchedule();
			
			if(schedule == null) {
				schedule = new Schedule("today", "tomorrow", availability, Main.erdsSystem);
				Main.erdsSystem.addSchedule(schedule);
			}
				match.setSchedule(schedule);
		 		schedule.addResourceMatch(match);
				emergency.scheduleTransportation();

		}

		
		System.out.println("Scheduling completed");


		// Scheduling
		
		for(int i=1;i<Transport.size();i++) {
			
			
			Transportation transport = (Transportation) Transport.get(i).getResource();
			TransportAvailability availability = (TransportAvailability) transport.getResourceAvailability(0);
			Schedule scheduleNew = new Schedule("today", "tomorrow", availability, Main.erdsSystem);
			ResourceMatch match = new ResourceMatch(availability, Transport.get(i), scheduleNew);
			matchedResources.add(match);
			
			// Resource availability changing state to allocated. 
			availability.coordinateResource();
			
			scheduleNew.addResourceMatch(match);
			Main.erdsSystem.addSchedule(scheduleNew);
		}
		

		System.out.println("Transporting resources");
		
		// Moving the state to on the way
		for (ResourceMatch match:matchedResources) {
			match.getResourceAvailability().transportResource();
		}
		
		System.out.println("Goods arriving to destination");
		
		// Moving the state to arrived
		for (ResourceMatch match:matchedResources) {
			match.getResourceAvailability().transportResourceToDestination();
		}
		
		System.out.println("Communities have informed that emergnecy has been resolved");
		
		
		for(Community community:communityList) {
			Main.erdsSystem.resolvedEmergency(community.getEmergency());
		}
		
		// The goods and people have completed their transition of allocation to transition but however the transportation units will be again changed to available.
		
		for(ResourceMatch match:matchedResources) {
			if(match.getResourceAvailability() instanceof TransportAvailability) {
				TransportAvailability availability = (TransportAvailability) match.getResourceAvailability();
				
			}
		}
		
		
		Main.DisplayMainScreen();
		
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Oops something went horribly wrong. Please start from beginning");
			Main.DisplayMainScreen();
		}
		
	}
	
	public static Transportation getFirstFreeTransport() {
		for(Resource resource : Main.erdsSystem.getResources()) {
			if(resource instanceof Transportation) {
				return (Transportation) resource;
			}
		}
		return null;
	}

	public static int getTotalQuantity(Resource resource) {
		int count = 0;
		for(ResourceAvailability avail : resource.getResourceAvailabilities() ) {
			if(avail.getStateFullName().equals("Available")) {
				count = count +avail.getQuantity();
			}
		}
		return count;
	}

	public static Community checkForCommunity(String name) {
		for(Community community : Main.erdsSystem.getCommunities()) {
			if(community.getName().equals(name)) {
				return community;
			}
		}
		return null;
	}
	
	public static List<ResourceNeed> getNonTransport(Emergency emergency) {
		List<ResourceNeed> returnList = new ArrayList<ResourceNeed>();
		for(ResourceNeed need:emergency.getResourceNeeds()) {
			if(!(need.getResource() instanceof Transportation)) {
				returnList.add(need);
			}
		}
		return returnList;
	}
	
	public static List<ResourceNeed> getTransport(Emergency emergency) {
		List<ResourceNeed> returnList = new ArrayList<ResourceNeed>();
		for(ResourceNeed need:emergency.getResourceNeeds()) {
			if(need.getResource() instanceof Transportation) {
				returnList.add(need);
			}
		}
		return returnList;
	}
	
	public static int getTransport() {
		int count =0;
		List<Resource> returnList = new ArrayList<Resource>();
		for(Resource transport : Main.erdsSystem.getResources()) {
			if(transport instanceof Transportation) {
				returnList.add(transport);
			}
		}
		for(Resource resource:returnList){
			count = count + getTotalQuantity(resource);
		}
		return count;
		
	}
	
	
}
