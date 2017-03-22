package drivers;

import java.util.ArrayList;

import baseclasses.*;
import controllers.DBController;
import interfaces.*;

public class functionalityDriver {
	public static void main(String[] args){
		//System.out.println("Adding User: ");
		AdminInterface AI = new AdminInterface();
		System.out.println("Testing UC11:Add User");
		if(AI.addUser(new Account("Avgjohnnie","Johnnie","John","TommiesSuck",'u','Y')))System.out.println("Added Avgjohnnie");
		//System.out.println(AI.addUser(new Account("Matthew","Zent","Matthew","Rocks21",'a','Y')));
		
		System.out.println("\nUsing Account: " + AI.viewUser("Matthew").getUsername());
		
		UserInterface UI = new UserInterface();
		System.out.println("\nTesting UC1(Alt1):Login ");
		if(!UI.login("Matthew", "password"))System.out.println("Login Failed");
		
		System.out.println("\nTesting UC1:Login ");
		if(UI.login("Matthew", "Rocks21"))System.out.println("Login successful");		
		if(UI.getAccount().getLoggedIn())System.out.println(UI.getAccount().getUsername() + " is logged in");
		System.out.println("\nTesting UC14:Logout ");
		if(UI.logout())System.out.println("Logout successful");
		if(!UI.getAccount().getLoggedIn())System.out.println(UI.getAccount().getUsername() + " is logged out");
		System.out.println("\nUsing Account: " + AI.viewUser("Avgjohnnie").getUsername());
		UI.login("Avgjohnnie", "TommiesSuck");
		System.out.println("\nTesting UC8:Save University ");
		UI.saveSchool(DBController.getUniversity("COLUMBIA"));
		System.out.println("COLUMBIA added");
		System.out.println("\nTesting UC2:View Saved Schools ");
		System.out.print("Saved Schools: ");
		ArrayList<University> savedSchools = UI.viewSavedSchools();
		for(University u : savedSchools){
			System.out.print(u.getName() + " ");
		}
		
	}
}
