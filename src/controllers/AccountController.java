/*
 *File: AccountController.java
 *
 */

package controllers;

import java.util.ArrayList;

import baseclasses.Account;

/**
 * This is a class that controls the account functions
 * 
 * @author Kyle Becker and Ethan Ferry
 * @version 0.1.1
 */
public class AccountController{
  
  /**
   * holds the database controller to be used within this controller
   */
  private DBController database = new DBController();
  /**
   * This method updates account info
   * 
   * @param account the updated account that should be changed in our database
   * @param firstname the new firstname, null if to be unchanged
   * @param lastname the new lastname, null if to be unchanged
   * @param password the new password, null if to be unchanged
   * @param type the new account type, null if to be unchanged
   * @param status the new account status, null if to be unchanged
   */
  public void editUserInfo(Account account, String firstname, String lastname, String password, char type, char status){
	  if(firstname!=null)
		  account.setFirstName(firstname);
	  if(lastname!=null)
		  account.setLastName(lastname);
	  if(password!=null)
		  account.setPassword(password);
	  account.setType(type);
	  account.setStatus(status);
	  
	  database.updateUser(account);
  }
  
  /**
   * This method adds an account to the system
   * 
   * @param firstname the fistname of the user represented by the account
   * @param lastname the lastname of the user represented by the account
   * @param username the username of the user represented by the account
   * @param password the password of the user represented by the account
   * @param type the account type of the user represented by the account
   * @param status the account status of the user represented by the account
   * @returns true if account added successfully, otherwise false
   */
  public boolean addAccount(String firstname, String lastname, String username, String password, char type, char status){
	  Account acc = new Account(firstname,lastname,username,password,type,status);
	  return database.addAccount(acc); 
  }
  /**
   * This method returns a set of all the accounts in the system
   * 
   * @returns an ArrayList of accounts in the system
   */
  public ArrayList<Account> getAccounts(){
	  ArrayList<Account> accounts= database.getAccounts();
	  return accounts;
  }
    /**
   * This is a method that logs in the user
   * 
   * @param Username of the user
   * @param Password of the user
   * @returns true if the login is completed
   */
  public boolean login(String username, String password){
	  LogonController lg = new LogonController();
	  return lg.login(username,password);
  }
  
}
