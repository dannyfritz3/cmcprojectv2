package drivers;

import java.util.ArrayList;

import baseclasses.*;
import controllers.DBController;
import dblibrary.project.csci230.UniversityDBLibrary;
import interfaces.*;

public class functionalityDriver {
	public static Thread t = new Thread();
	
	public static void main(String[] args){
		ArrayList<String> emphasis1 = new ArrayList<String>();
		ArrayList<String> emphasis2 = new ArrayList<String>();
		emphasis1.add("MATH");
		emphasis1.add("LITERATURE");
		emphasis2.add("EDUCATION");
		University uni1 = new University("SAINT JOHNS");
		University uni2 = new University("SAINT BENS", "MINNESOTA", "RURAL", "PRIVATE", 3000, 1.00, 0.70, 0.70, 40000.00, 0.60, 1000, 0.80, 0.50, 4, 4, 3, emphasis1);
		System.out.println(uni2.getInformation());
		//System.out.println("Adding User: ");
		AdminInterface AI = new AdminInterface();
		System.out.println("Testing UC11:Add User");
		if(AI.addUser(new Account("Avgjohnnie","Johnnie","John","TommiesSuck",'u','Y')))System.out.println("Added Avgjohnnie");
		//System.out.println(AI.addUser(new Account("Matthew","Zent","Matthew","Rocks21",'a','Y')));
		
		System.out.println("\nUsing Account: " + AI.viewUser("Matthew").getUsername());
		
		UserInterface UI = new UserInterface();
		System.out.println("\nTesting UC1(Alt1):Login ");
		try {
			if(!AI.login("Matthew", "password"))System.out.println("Login Failed");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			t.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\nTesting UC1:Login ");
		try {
			if(AI.login("Matthew", "Rocks21"))System.out.println("Login successful");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		if(AI.getAccount().getLoggedIn())System.out.println(AI.getAccount().getUsername() + " is logged in");
		
		System.out.println("\nTesting UC14:Logout ");
		if(AI.logout())System.out.println("Logout successful");
		if(!AI.getAccount().getLoggedIn())System.out.println(AI.getAccount().getUsername() + " is logged out");
		
		System.out.println("\nUsing Account: " + AI.viewUser("Avgjohnnie").getUsername());
		try {
			UI.login("Avgjohnnie", "TommiesSuck");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nTesting UC8:Save University ");
		if(UI.saveSchool(DBController.getUniversity("COLUMBIA")))System.out.println("COLUMBIA added");
		System.out.println("\nTesting UC2:View Saved Schools ");
		System.out.print("Saved Schools: ");
		ArrayList<University> savedSchools = UI.viewSavedSchools();
		for(University u : savedSchools){
			System.out.print(u.getName() + " ");
		}
		System.out.println("\n\nTesting UC3:Remove School ");
		System.out.print("Saved Schools: ");
		if(UI.removeSchool(DBController.getUniversity("COLUMBIA")))System.out.println("\nCOLUMBIA removed");
		savedSchools = UI.viewSavedSchools();
		for(University u : savedSchools){
			System.out.print(u.getName() + " ");
		}
		System.out.println("\n\nTesting UC4:View University");
		System.out.println("Viewing COLUMBIA\n");
		System.out.println(UI.viewSchool(DBController.getUniversity("COLUMBIA")));
		
		System.out.println("\nTesting UC5:Search Schools, Testing UC6:View Search Results");
		System.out.println("Searching for schools in MINNESOTA\nResults:");
		UI.searchSchools(null, "MINNESOTA", null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		
		for(University school : UI.viewSearches()){
			System.out.println(school.getName());
		}
		
		System.out.println("\nTesting UC7:View University with recommendations");
		System.out.println("Showing COLUMBIA and its 5 most related universities");
		for(University school : UI.viewSchoolWRec(DBController.getUniversity("COLUMBIA"))){
			System.out.println(school.getName());
		}
		
		System.out.println("\nTesting UC9:View Profile");
		System.out.println(UI.viewProfile());
		
		System.out.println("\nTesting UC10:Manage Users");
		System.out.println("Showing all usernames");
		ArrayList<Account> accounts = AI.manageUsers();
		for(Account a :accounts){
			System.out.println(a.getUsername());
		}
		
		System.out.println("\nTesting UC12:Deactivate");
		System.out.println("Deactivating Zent");
		AI.deactivate(accounts.get(6));
		accounts = AI.manageUsers();
		System.out.println("Status of Zent: " + accounts.get(6).getStatus());
		
		System.out.println("\nTesting UC13:Edit User info(Admin)");
		AdminInterface AI2 = new AdminInterface();
		Account a = AI2.viewUser("Matthew");
		//display user info
		System.out.println("First get of a admin\n" + "Username: " + a.getUsername() + '\n' + "Firstname: " + a.getFirstName() + '\n' + "Lastname: " + a.getLastName() + '\n' + "Password: " + a.getPassword() + '\n' + "Type: " + a.getType() + '\n' + "Status: " + a.getStatus() + '\n');
		//change user info
		a.setFirstName("John");a.setPassword("123qwe");a.setLastName("Smith");a.setType('u');a.setStatus('N');
		//update user info
		AI2.editProfile(a);
		//get userinfo
		a = AI2.viewUser("Matthew");
		System.out.println("Edit of a user\n" + "Username: " + a.getUsername() + '\n' + "Firstname: " + a.getFirstName() + '\n' + "Lastname: " + a.getLastName() + '\n' + "Password: " + a.getPassword() + '\n' + "Type: " + a.getType() + '\n' + "Status: " + a.getStatus() + '\n');
		//set userinfo back
		a.setPassword("Rocks21");a.setLastName("Zent");a.setType('a');a.setStatus('Y');a.setFirstName("Matthew");
		//display user info
		AI2.editProfile(a);
		System.out.println("Revert back to original\n" + "Username: " + a.getUsername() + '\n' + "Firstname: " + a.getFirstName() + '\n' + "Lastname: " + a.getLastName() + '\n' + "Password: " + a.getPassword() + '\n' + "Type: " + a.getType() + '\n' + "Status: " + a.getStatus() + '\n');
		
		System.out.println("Testing UC13:Edit User info(User)");
		UserInterface UI2 = new UserInterface();
		try {
			if(UI2.login("Avgjohnnie", "TommiesSuck"))
				System.out.println("Login successful\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		if(UI2.getAccount().getLoggedIn())System.out.println(UI2.getAccount().getUsername() + " is logged in");
		System.out.println("\nFirst get of user");
		System.out.println(UI2.viewProfile());
		Account acc = UI2.getAccount();
		acc.setLastName("Smith");
		UI2.editProfile(acc);
		System.out.println("\nEdit of user");
		System.out.println(UI2.viewProfile());
		acc.setLastName("John");
		UI2.editProfile(acc);
		System.out.println("\nRevert back to original");
		System.out.println(UI2.viewProfile());
		
		System.out.println("\nTesting UC15:Add University");
		System.out.println("Adding adding COLLEGE OF SAINT BENEDICT");
		AI.addUniversity("COLLEGE OF SAINT BENEDICT", "Minnesota", "SMALL-CITY", "PRIVATE", 2000, 100.0, 600.0, 600.0, 52000.00, 80, 3000, 70, 25, 4, 2, 1, new ArrayList<String>());
		for(University uni : AI.viewAllSchools()){
			System.out.println("\t" + uni.getName());
		}
		UniversityDBLibrary DB = new UniversityDBLibrary("nullpm", "nullpm", "csci230");
		System.out.println("\nRemoving COLLEGE OF SAINT BENEDICT");
		DB.university_deleteUniversity("COLLEGE OF SAINT BENEDICT");
		DBController DBC = new DBController();
		System.out.println("\nTesting UC16:Edit University");
		University uni = DBC.getUniversity("COLUMBIA");
		System.out.println(uni.getInformation());
		uni.setAcademicScale(1);
		AI.editUniversity(uni);
		System.out.println("\nInfomation after setting Acedemic scale to 1: \n");
		System.out.println(uni.getInformation());
		uni.setAcademicScale(5);
		AI.editUniversity(uni);
		
		
		System.out.println("\n Testing UC17:View User");
		Account a2 = AI.viewUser("Avgjohnnie");
		System.out.println("Username: " + a2.getUsername() + '\n' + "Firstname: " + a2.getFirstName() + '\n' + "Lastname: " + a2.getLastName() + '\n' + "Password: " + a2.getPassword() + '\n' + "Type: " + a2.getType() + '\n' + "Status: " + a2.getStatus() + '\n');
	
		System.out.println("\nTesting UC18:View All Schools");
		for(University u : AI.viewAllSchools()){
			System.out.println("\t" + u.getName());
		}
		
	}
}
