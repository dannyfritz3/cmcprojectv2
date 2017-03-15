/**
 * File: UserFuncController.java
 */

package controllers;

import java.util.*;

import baseclasses.Account;
import baseclasses.University;
import baseclasses.User;

/**
 * This is a class that holds all the functionalities of a User
 * 
 * @author mjzent
 * @version 0.1
 */
public class UserFuncController {
	
	private User user;
	private ArrayList<University> savedSchools;
	private DBController db = new DBController();
	/**
	 * This creates a new UserFuncController with the parameter of the user using it
	 */
	public UserFuncController(User user){
		this.user = user;
	}
	/**
	 * This method talks to the User class and removes a university from the users list of saved universities
	 * 
	 * @param u is school to be removed from saved universities
	 * @return whether the remove was successful
	 */
	public boolean removeSchool(University u){
		boolean temp = user.removeSchool(u);
		this.db.updateUser(user);
		return temp;
	}
	
	/**
	 * This method talks to the User class and views the details of a university
	 * 
	 * @param u is the school to be viewed
	 * @return a string representation of university details
	 */
	public String viewSchool(University u){
		String information = db.getUniversity(u.getName()).getInformation();
		return information;
	}
	/**
	 * This method talks to the User class and views the details of a university as well as the details of similar universities
	 * 
	 * @param u is the school to be viewed and to find similar universities to
	 * @return a string representation of university details and details of similar universities
	 */
	
	public String viewSchoolWRec(University u){
		ArrayList<University> unis = db.getUniversityRecomendations(u);
		return unis.get(0).getInformation();
	}
	
	/**
	 * This method talks to the User class and saves a university in their saved schools
	 * 
	 * @param u is the university to be saved
	 * @return whether or not the university could be saved
	 */
	public boolean saveSchool(University u){
		boolean temp = user.addUniversities(u);
		this.db.updateUser(user);
		return temp;
	}
	
	/**
	 * This method views all the saved schools that are saved by the user
	 * 
	 * @return an array list of universities saved by the user
	 */
	public ArrayList<University> getSavedSchools()
	{
		return user.getSavedUniversities();
	}
	/**
	 * This method talks to the User class and gets the details of that user
	 * 
	 * @return a string representation of the users account details
	 */
	public String viewProfile(){
		return "\nFirst Name: " + user.getFirstName() + "\nLast Name: " + user.getLastName() + "\nType: " + user.getType() + "\nStatus: " + user.getStatus();
	}
	
	/**
	 * This method changes the account details of this user
	 * 
	 * @param a is an account contains the new details
	 * @throws IllegalArgumentException if the new account has a different username that old account details
	 * @return
	 */
	public boolean editProfile(Account a){
		this.user.setFirstName(a.getFirstName());
		this.user.setLastName(a.getLastName());
		this.user.setPassword(a.getPassword());
		
		return true;
	}
}
