/*
 *File: AdminFuncController.java
 *
 */
package controllers;

/**
 * This is a class that controls the admin functions
 * 
 * @author Kyle Becker and Ethan Ferry
 * @version 0.1.1
 */
public class AdminFuncController{
	
	private AccountController acccontrol = new AccountController();
	private DBController database = new DBController();
  
 /**
  * This method adds a university to the system
  * 
  * @param name of university
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
 public boolean addUniversity(String name, String location, int numberOfStudents, int percentFemale, int SATVerbal, int SATMath, int expense, int percentFinancialAid, int numberOfApplicants, int perecentAdmitted, int percentEnrolled, int academicScale, int socialScale, String emphasis){
	 University uni = new University(name,location,numberOfStudents,percentFemale,SATVerbal,SATMath,expense,percentFinancialAid, numberOfApplicants,percentAdmitted,percentEnrolled,academicScale,socialScale,emphasis);
	 return database.addUniversity(uni);
 }
  /**
   * This is a method that returns a list of universities in the system
   * 
   * @returns a set of all university objects in the database
   */
  public ArrayList<University> viewAllUniversities(University university){
	  ArrayList<University> universities = new database.getUniveristies();
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
	  Account acc = new database.getAccount(username);
	  acc.setType('N');
	  acccontrol.updateUser(acc);
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
  public boolean addUser(String firstname, String lastname, String username, String password, Char type, Char status){
	  return acccontrol.addAccount(firstname,lastname,username,password,type,status);
 	}
  
  /**
   * This method returns a set of all accounts in the system
   * 
   * @returns an Arraylist set of accounts
   */
  public ArrayList<Account> manageUsers(){
	  ArrayList<Account> accounts= new acccontrol.getAccounts();
	  return accounts;
  }
}
