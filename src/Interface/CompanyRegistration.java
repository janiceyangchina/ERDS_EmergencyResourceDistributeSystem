package Interface;

import system.Charity;
import system.Company;
import system.GovermentalOrganization;

public class CompanyRegistration {
	
	public static void main(String args[]){
		MainCompanyRegistration();
	}
	
	public static void MainCompanyRegistration() {
		int userInput=0;
		System.out.println("Enter the type of company you want to register");
		System.out.println("1. Charity organization");
		System.out.println("2. Government organization");
		System.out.println("3. Transport organization");
		
		try {
			userInput = Integer.parseInt(Main.getInput());
		}
		catch(Exception e) {
			System.out.println("Invalid input");
			Main.DisplayMainScreen();
		}
		switch(userInput) {
		case 1: registerCompany(1);
			break;
		case 2: registerCompany(2);
			break;
		case 3: registerCompany(3);
			break;
		default : Main.DisplayMainScreen();
			break;
		}
		
	}
	
	public static void registerCompany(int option){
		System.out.println("Enter name");
		String name = Main.getInput();
		System.out.println("Enter location");
		String location = Main.getInput();
		if(option ==1) {
			Charity charity = new Charity(name, location, Main.erdsSystem);
			Main.erdsSystem.addOrganization(charity);
		} else if(option ==2) {
			GovermentalOrganization gorganization = new GovermentalOrganization(name, location, Main.erdsSystem);
			Main.erdsSystem.addOrganization(gorganization);
		} else {
			Company company = new Company(name, location, Main.erdsSystem);
			Main.erdsSystem.addOrganization(company);
		}
		Main.DisplayMainScreen();
	}
}
