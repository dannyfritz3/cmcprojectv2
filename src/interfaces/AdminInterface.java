/**
 * File: AdminInterface.java
 */
package interfaces;


import controllers.AdminFuncController;
import controllers.AccountController;
import baseclasses.University;
import baseclasses.Account;

import java.util.ArrayList;
/**
 *This is a class that is an interface for the admin
 *
 *@author Ethan Ferry
 *@version 0.1
 */


public class AdminInterface extends AccountInterface{
	/**
 	* 
 	* This method is to view all schools
 	*
 	* @returns an array of universities
 	*/ 
	public ArrayList<University> viewAllSchools(){
		return AdminFuncController.viewAllUniversities();
	}
	/**
 	* This is a method to deactivate an account
 	*
 	* @param the account that is being deactivated
	*/
	public void deactivate(Account a){
		AdminFuncController.deactivate(a.getUsername());
	}
	/**
 	 * This is a method to add a user
 	 *
 	 * @param an account object of the user being added
 	 */ 
	public boolean addUser(Account a){
		return (AdminFuncController.addUser(a.getFirstName(), a.getLastName(), a.getUsername(), a.getPassword(), a.getType(), a.getStatus()));
	}

	/**
 	* This is a method to edit a university
	* 
	* @param the university object
	*/ 
	public void editUniversity(University university){
		AdminFuncController.editUniversity(university); 
	}
	
	/**
	 * This is a method to edit a profile
	 *
	 * @param the account being edited
 	*/
	public void editProfile(Account a){
		AccountController.editUserInfo(a, a.getFirstName(), a.getLastName(), a.getUsername(),a.getPassword(), a.getType(), a.getStatus());
	}
	
	/**
   	* This is a method to search for schools
  	*
  	* @param name of school
  	* @param state schools is located
  	* @param locaiton of school (urban, rural...)
  	* @param numberOfStudents currently attending the school
  	* @param percentFemale percentage of females attending the school
  	* @param SATVerbal the average SAT verbal score for enrolled students
  	* @param SATMath the average SAT math score for enrolled students
  	* @param expenses annual expenses or tuition to attend the schools
 	* @param percentFinancialAid %  of enrolled students recieveing financial aid
 	* @param numberOfApplicants # of people who apply each year
 	* @param percentAdmitted % of applicants admitted
  	* @param percentEnrolled percent of applicants that decide to enroll
 	* @param academicScale scale 1-5 of academic scale of school
 	* @param socialScale scale 1-5 idicating quality of life at school
 	* @param emphases up to five areas of study (all strings) in which the school excells
 	* @returns an array of universities that meet the criteria
 	*/
	public boolean addUniversity(String name, String state, String location, String control, int numberOfStudents, double percentFemale, double SATVerbal, double SATMath, double expense, double percentFinancialAid, int numberOfApplicants, double percentAdmitted, double percentEnrolled, int academicScale, int socialScale,int qualityOfLifeScale, ArrayList<String> emphasis){
		return AdminFuncController.addUniversity(name,state,location,control, numberOfStudents,percentFemale,SATVerbal,SATMath,expense,percentFinancialAid, numberOfApplicants,percentAdmitted,percentEnrolled,academicScale,socialScale,qualityOfLifeScale,emphasis);
	}
	
	/**
 	 * This is a method to view a user
 	 * 
 	 * @param username the username of the user
 	 * @returns the account of the user being viewed
 	 */   

	public Account viewUser(String username){
		return AdminFuncController.viewUser(username);
	}
	
	public ArrayList<Account> manageUsers(){
		return AdminFuncController.manageUsers();
	}
}