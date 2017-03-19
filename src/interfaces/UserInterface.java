/**
 * File: UserInterface.java
 */
 package interfaces;
 
 import java.util.*;
 import baseclasses.*;
 import controllers.*;
 /**
 *This is a class that is an interface for the user
 *
 * @author Ethan Ferry
 * @version 0.1
 */

public class UserInterface extends AccountInterface{
	
	/**
	 * UserFuncController object so that we can work with the user
	 */
	private UserFuncController ufc = new UserFuncController((User)this.account);
	/**
	 * SearchController object so that we can work with the search function
	 */
	private SearchController sc = new SearchController();
	
	/**
 	* This is a method to view saved schools
 	* 
 	*/
	public ArrayList<University> viewSavedSchools(){
		return this.ufc.getSavedSchools();
	}
	/**account
 	* This is a method to search for schools
 	*
 	* @param name of school
 	* @param state schools is located
 	* @param locaiton of school (urban, rural...)
 	* @param control of the university (public or private)
 	* @param numberOfStudents currently attending the school
 	* @param percentFemale percentage of females attending the school
 	* @param SATVerbal the average SAT verbal score for enrolled students
 	* @param SATMath the average SAT math score for enrolled students
 	* @param expenses annual expenses or tuition to attend the schools
 	* @param percentFinancialAid %  of enrolled students receiving financial aid
 	* @param numberOfApplicants # of people who apply each year
 	* @param percentAdmitted % of applicants admitted
 	* @param percentEnrolled percent of applicants that decide to enroll
 	* @param academicScale scale 1-5 of academic scale of school
 	* @param socialScale scale 1-5 indicating the social life at school
 	* @param qualityOfLifeScale scale 1-5 indicating the quality of life at school
 	* @param emphases up to five areas of study (all strings) in which the school excels
 	*
 	* @returns an array of universities that meet the criteria
 	*/
	public ArrayList<University> searchSchools(String name, String state, String location, String control, int numberOfStudents, int numberOfStudents2, double percentFemale, double percentFemale2, double SATVerbal, double SATVerbal2, double SATMath, double SATMath2, double expenses, double expenses2, double percentFinancialAid, double percentFinancialAid2, int numberOfApplicants, int numberOfApplicants2, double percentAdmitted, double percentAdmitted2, double percentEnrolled, double percentEnrolled2, int academicScale, int academicScale2, int socialScale, int socialScale2, int qualityOfLifeScale, int qualityOfLifeScale2, String emphasis){
		this.sc.search(name, state, location, control, numberOfStudents, numberOfStudents2, percentFemale, percentFemale2, SATVerbal, SATVerbal2, SATMath, SATMath2, expenses, expenses2, percentFinancialAid, percentFinancialAid2, numberOfApplicants, numberOfApplicants2, percentAdmitted, percentAdmitted2, percentEnrolled, percentEnrolled2, academicScale, academicScale2, socialScale, socialScale2, qualityOfLifeScale, qualityOfLifeScale2, emphasis);
		return this.sc.getSearchedUniversities();
	}

	/**
 	 *  This is a method that lets the user view their profile
 	 *  
 	 *  @returns String
 	 */ 	 
	public String viewProfile(){
		return this.ufc.viewProfile();
	}

	/** 
 	 * This is a method that lets users save universities to a list   
 	 * 
 	 * @param University a university object
 	 */
	public void saveSchool(University uni){
		this.ufc.saveSchool(uni);
	}
	/**
 	 * This method views the searches (??)
 	 *
 	 * @returns an array of University objects
 	 */ 
	public ArrayList<University> viewSearches(){
		return this.sc.getSearchedUniversities();
	}

	/**
	 * This is a method that edits a profile
 	 *
 	 * @param An account of the profile being edited
 	 * @throws IllegalArgumentException if account has a different username
 	 */ 
	public boolean editProfile(Account acc){
		return this.ufc.editProfile(acc);
	}

	/**
	 * A method that views the school's information
	 * 
	 * @returns all of the schools information
	 */
	public String viewSchool(University uni)
	{
		return ufc.viewSchool(uni);
	}
	/**
	 * A method that views the school and the recommended school's information
	 * 
	 * @returns all of the schools information
	 */
	public String viewSchoolWRec(University u)
	{
		return this.ufc.viewSchoolWRec(u);
	}
	/**
	 * A method that removes a university
	 * 
	 * returns true if the university was removed successfully
	 */
	public boolean removeSchool(University uni)
	{
		return this.ufc.removeSchool(uni);
	}
	

}
