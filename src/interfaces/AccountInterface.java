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
	
	/**
	 * Creates an account controller for this class to use
	 */
	private AccountController ac = new AccountController();
	/**
	 * Creates a logon controller for this class to use
	 */
	private LogonController lc = new LogonController();
	
	private DBController db = new DBController();
	
	protected Account account;
	
	/**
 	* This is a method to logout
 	*
 	* @param a is the account to be logged out
 	*/
	public void logout(Account a){
		this.lc.logout(a);
	}
	
	/**
 	* This is a method to log in
 	*
 	* @param username the username of the account logging in
 	* @param password the password of the account logging in
	*/ 
	public void login(String username, String password){
		if(this.ac.login(username, password) == false)
		{
			System.err.println("Either your userame or password did not match or the user does not exist");
		}
		else
		{
			this.ac.login(username, password);
			account = db.getAccount(username);
		}
	}
}
