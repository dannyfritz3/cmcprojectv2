
/**
 * File: LoggonController.java
 */

package controllers;

import baseclasses.Account;

/**
 * This is a class that controls the logon functions
 * 
 * @author Danny Fritz, Ethan Ferry, Matt Zent, and Kyle Becker
 * @version 0.1
 */
public class LogonController{
  
 /**
   * This is a method that logs in the user
   * 
   * @param Username of the user
   * @param Password of the user
   * @returns true if the login is completed
   */
  public static Account login(String username, String password){
    Account user = DBController.getAccount(username);
    if(user == null){
    	return null;
    } else {
    	if(!user.getPassword().equals(password)){
    		return null;
    	} else {
    		user.login();
    		return user;
    	}
    }
  }
  
  /**
   * This is a method that logs out the user
   *
   * 
   * @param An account of the user being logged out
   */
  public static void logout(Account a){
    a.logout();
  }
}
