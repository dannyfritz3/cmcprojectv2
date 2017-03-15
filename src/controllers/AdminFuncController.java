/*
 *File: AdminFuncController.java
 *
 */
package controllers;

import java.util.ArrayList;

import baseclasses.Account;
import baseclasses.University;

/**
 * This is a class that controls the admin functions
 * 
 * @author Kyle Becker and Ethan Ferry
 * @version 0.1.1
 */
public class AdminFuncController{
	/**
	 * creates an account controller for this class to use
	 */
	private AccountController acccontrol = new AccountController();
	/**
	 * creates a database controller for this class to use
	 */
	private DBController database = new DBController();
  
 /**
  * This method adds a university to the system
  * 
  * @param name of university
  * @param state of university
  * @param location of university
  * @param numberOfStudents at the university
  * @param percentFemale students at the university
  * @param SATVerbal of the average student at the university
  * @param SATMath of the average student at the university
  * @param expense of the university
  * @param percentFinancialAid of the average student at the university
  * @param numberOfApplicants at the university
  * @param perecentAdmitted at the university
  * @param percentEnrolled at the university
  * @param academicScale of the university
  * @param socialScale of the university
  * @param empases of the university
  * @return true if univerity added successfully
  */
 public boolean addUniversity(String name, String state, String location, String control, int numberOfStudents, double percentFemale, double SATVerbal, double SATMath, double expense, double percentFinancialAid, int numberOfApplicants, double percentAdmitted, double percentEnrolled, int academicScale, int socialScale,int qualityOfLifeScale, String emphasis){
	 University uni = new University(name,state,location,control,numberOfStudents,percentFemale,SATVerbal,SATMath,expense,percentFinancialAid, numberOfApplicants,percentAdmitted,percentEnrolled,academicScale,socialScale,qualityOfLifeScale,emphasis);
	 return database.addUniversity(uni);
 }
  /**
   * This is a method that returns a list of universities in the system
   * 
   * @returns a set of all university objects in the database
   */
  public ArrayList<University> viewAllUniversities(University university){
	  ArrayList<University> universities = database.getUniversities();
	  return universities;
  }
	  
  
  /**
   * This is a method that edits a university's information in the database
   * 
   * @param university the edited university object
   * @returns true if university is updated successfully
   */
  public boolean editUniversity(University university){
    return database.editUniversity(university);
  }
  
    /**
   * This method returns an account to be viewed
   * @param username the username of the account to be viewed
   * @returns a set of accounts in the system
   */
  public Account viewUser(String username){
	  return database.getAccount(username);
  }
  
      /**
   * This method returns an account to be deactivated
   * @param username the username of the account to be deactivated
   */
  public void deactivate(String username){
	  Account acc = database.getAccount(username);
	  acc.setType('N');
	  database.updateUser(acc);
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
  public boolean addUser(String firstname, String lastname, String username, String password, char type, char status){
	  return acccontrol.addAccount(firstname,lastname,username,password,type,status);
 	}
  
  /**
   * This method returns a set of all accounts in the system
   * 
   * @returns an Arraylist set of accounts
   */
  public ArrayList<Account> manageUsers(){
	  ArrayList<Account> accounts= acccontrol.getAccounts();
	  return accounts;
  }
}
