
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
  public static Account login(String username, String password) throws Exception{
    Account user = DBController.getAccount(username);
    if(user == null){
    	throw new Exception("This account does not exsist");
    } else {
    	if(!user.getPassword().equals(password)){
    		throw new Exception("Password does not match this user");
    	} else {
    		if(user.getStatus() == 'N')
    		{
    			throw new Exception("This account is deactivated");
    		}
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
