/**
 * File: AdminInterface.java
 */

package interfaces;

/*
 *This is a class that is an interface for the admin
 *
 *@author Ethan Ferry
 *@version 0.1
 */


public class AdminInterface{
	
	/*
 	* 
 	* This method is to view all schools
 	*
 	* @returns an array of universities
 	*/ 
	public ArrayList<University> viewAllSchools(){
		return null;
	}

	/*
 	* This method manages universities
 	*
 	*/
	public void manageUniversities(){

	}
	/*
 	* This is a method to deactivate an account
 	*
 	* @param the account that is being deactivated
	*/
	public void deactivate(Account a){

	}
	/*
 	 * This is a method to add a user
 	 *
 	 * @param an account object of the user being added
 	 */ 
	public void addUser(Account a){
	
	}

	/*
 	* This is a method to edit a university
	* 
	* @param the string title of the university
	*/ 
	public void editUniversity(String university){

	}
	
	/*
	 * This is a method to edit a profile
	 *
	 * @param the account being edited
	 * @returns a boolean for a succesful edit or not
 	*/
	public boolean editProfile(Account a){
		return false;
	}
	
	/*
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
	public boolean addUniversity(String name, String state, String location, int numberOfStudents, int percentFemale, int SATVerbal, int SATMath, int expenses, int percentFinancialAid, int numberOfApplicants, int percentAdmitted, int percentEnrolled, int academicScale, int socialScale, String emphases){
		return false;
	}
	
	/*
 	 * This is a method to view a user
 	 * 
 	 * @param username the username of the user
 	 * @returns the account of the user being viewed
 	 */   

	public Account viewUser(String username){
		return null;
	}
}
