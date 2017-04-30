/**
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
 public static boolean addUniversity(String name, String state, String location, String control, int numberOfStudents, double percentFemale, double SATVerbal, double SATMath, double expense, double percentFinancialAid, int numberOfApplicants, double percentAdmitted, double percentEnrolled, int academicScale, int socialScale,int qualityOfLifeScale, ArrayList<String>emphasis){
	 University uni = new University(name,state,location,control,numberOfStudents,percentFemale,SATVerbal,SATMath,expense,percentFinancialAid, numberOfApplicants,percentAdmitted,percentEnrolled,academicScale,socialScale,qualityOfLifeScale,emphasis);
	 return DBController.addUniversity(uni);
 }
  /**
   * This is a method that returns a list of universities in the system
   * 
   * @returns a set of all university objects in the database
   */
  public static ArrayList<University> viewAllUniversities(){
	  ArrayList<University> universities = DBController.getUniversities();
	  return universities;
  }
	  
  
  /**
   * This is a method that edits a university's information in the database
   * 
   * @param university the edited university object
   * @returns true if university is updated successfully
   */
  public static boolean editUniversity(University university){
    return DBController.editUniversity(university);
  }
  
    /**
   * This method returns an account to be viewed
   * @param username the username of the account to be viewed
   * @returns a set of accounts in the system
   */
  public static Account viewUser(String username){
	  return DBController.getAccount(username);
  }
  
      /**
   * This method returns an account to be deactivated
   * @param username the username of the account to be deactivated
   */
  public static void deactivate(String username){
	  Account acc = DBController.getAccount(username);
	  acc.setStatus('N');
	  DBController.updateUser(acc);
  }
  
  /**
   * This method returns an account to be activated
   * @param username the username of the account ot be activated
   */
  public static void activate(String username){
	  Account acc = DBController.getAccount(username);
	  acc.setStatus('Y');
	  DBController.updateUser(acc);
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
  public static boolean addUser(String firstname, String lastname, String username, String password, char type, char status){
	  return AccountController.addAccount(firstname,lastname,username,password,type,status);
  }
  
  public static void editAccount(Account a){
	  DBController.updateUser(a);
  }
  /**
   * This method returns a set of all accounts in the system
   * 
   * @returns an Arraylist set of accounts
   */
  public static ArrayList<Account> manageUsers(){
	  ArrayList<Account> accounts= AccountController.getAccounts();
	  return accounts;
  }
  
  public static University viewUniversity(String uniName) {
	  return DBController.getUniversity(uniName);
  }
	
  public static boolean deleteUser(Account a){
	  return DBController.removeUser(a.getUsername());
  }
  public static boolean deleteUniversity(String u){
	  return DBController.deleteSchool(u);
  }
}
