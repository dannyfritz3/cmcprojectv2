/**
 * File: AccountInterface.java
 */

package interfaces;

import baseclasses.*;
import controllers.*;
/**
 * This is an abstract class that is interface for accounts
 *
 * @author Ethan Ferry
 * @version 0.1
 *
 */


public abstract class AccountInterface{
	


	
	protected Account account;
	
	/**
 	* This is a method to logout
 	*
 	* @param a is the account to be logged out
 	*/
	public boolean logout(){
		if(account == null) return false;
		LogonController.logout(account);
		return true;
	}
	
	/**
 	* This is a method to log in
 	*
 	* @param username the username of the account logging in
 	* @param password the password of the account logging in
	*/ 
	public boolean login(String username, String password){
		Account temp = AccountController.login(username, password);
		if(temp == null)
		{
			System.err.println("Either your userame or password did not match or the user does not exist");
			return false;
		}
		else
		{
			//AccountController.login(username, password);
			account = temp;
			return true;
		}
	}
	
	public Account getAccount(){
		return account;
	}
}
