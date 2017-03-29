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
		Account temp;
		try {
			temp = AccountController.login(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
		//AccountController.login(username, password);
		account = temp;
		return true;
		
	}
	
	public Account getAccount(){
		return account;
	}
}
