package drivers;

import interfaces.AdminInterface;
import java.util.ArrayList;

import baseclasses.*;
import controllers.DBController;
import dblibrary.project.csci230.UniversityDBLibrary;
public class adminFunctionalityDriver {

	
	public static void main(String args[]){
		AdminInterface AI = new AdminInterface();
		UniversityDBLibrary DB = new UniversityDBLibrary("nullpm", "nullpm", "csci230");
		DBController DBC = new DBController();
		System.out.println("Testing UC 18: View All Schools");
		for(University uni : AI.viewAllSchools()){
			System.out.println("\t" + uni.getName());
		}
		System.out.println("\n Testing UC 15: Add University");
		AI.addUniversity("COLLEGE OF SAINT BENEDICT", "Minnesota", "SMALL-CITY", "PRIVATE", 2000, 100.0, 600.0, 600.0, 52000.00, 80, 3000, 70, 25, 4, 2, 1, null);
		for(University uni : AI.viewAllSchools()){
			System.out.println("\t" + uni.getName());
		}
		
		System.out.println("Removing COLLEGE OF SAINT BENEDICT");
		DB.university_deleteUniversity("COLLEGE OF SAINT BENEDICT");
		
		System.out.println("\n Testing UC 16: Edit University");
		University uni = DBC.getUniversity("COLUMBIA");
		System.out.println(uni.getInformation());
		uni.setAcademicScale(1);
		AI.editUniversity(uni);
		System.out.println("\n Infomation after setting Acedemic scale to 1: \n");
		System.out.println(uni.getInformation());
		uni.setAcademicScale(1);
		AI.editUniversity(uni);
		
		System.out.println("\n Testing UC 17: View User");
		Account a = AI.viewUser("Avgjohnnie");
		System.out.println("Username: " + a.getUsername() + '\n' + "Firstname: " + a.getFirstName() + '\n' + "Lastname: " + a.getLastName() + '\n' + "Password: " + a.getPassword() + '\n' + "Type: " + a.getType() + '\n' + "Status: " + a.getStatus() + '\n');
	}
}
