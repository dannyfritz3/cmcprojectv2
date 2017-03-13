/**
 * File: UserFuncController.java
 */

package controllers;
/**
 * This is a class that holds all the functionalities of a User
 * 
 * @author mjzent
 * @version 0.1
 */
public class UserFuncController {
	
	private User user;
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
		return false;
	}
	
	/**
	 * This method talks to the User class and views the details of a university
	 * 
	 * @param u is the school to be viewed
	 * @return a string representation of university details
	 */
	public String viewSchool(University u){
		return null;
	}
	/**
	 * This method talks to the User class and views the details of a university as well as the details of similar universities
	 * 
	 * @param u is the school to be viewed and to find similar universities to
	 * @return a string representation of university details and details of similar universities
	 */
	
	public String viewSchoolWRec(University u){
		return null;
	}
	
	/**
	 * This method talks to the User class and saves a university in their saved schools
	 * 
	 * @param u is the university to be saved
	 * @return whether or not the university could be saved
	 */
	public boolean saveSchool(University u){
		return false;
	}
	
	/**
	 * This method talks to the User class and gets the details of that user
	 * 
	 * @return a string representation of the users account details
	 */
	public String viewProfile(){
		return null;
	}
	
	/**
	 * This method changes the account details of this user
	 * 
	 * @param a is an account contains the new details
	 * @throws IllegalArgumentException if the new account has a different username that old account details
	 * @return
	 */
	public boolean editProfile(Account a) throws IllegalArgumentException{
		return false;
	}
}
