/**
 *File: DBController.java
 *
 */
package controllers;


import java.util.ArrayList;
import baseclasses.Account;
import baseclasses.University;
import baseclasses.User;
import dblibrary.project.csci230.UniversityDBLibrary;


/**
 * This is a class that controls the database functions
 * 
 * @author Kyle Becker
 * @version 0.1
 */
public class DBController{
  
  private static UniversityDBLibrary lib = new UniversityDBLibrary("nullpm", "nullpm", "csci230");
  
  /**
   * This is a method that returns a university object from info from the database
   * 
   * @param name name of the university
   * @returns univeristy object of university with specified name
   */
  public static University getUniversity(String name){
	  String[][] unis = lib.university_getUniversities();
	  for(String[] info : unis){
		  if(info[0].equals(name)){
			  return new University(info[0], info[1], info[2], info[3], Integer.parseInt(info[4]), Double.parseDouble(info[5]), Double.parseDouble(info[6]), Double.parseDouble(info[7]), Double.parseDouble(info[8]), Double.parseDouble(info[9]), Integer.parseInt(info[10]), Double.parseDouble(info[11]), Double.parseDouble(info[12]),Integer.parseInt(info[13]),Integer.parseInt(info[14]),Integer.parseInt(info[15]), null);
		  }
		  
	  }
	  return null;
    
  }
  
    /**
   * This is a method the adds a university to the database
   * 
   * @param university the university object to be added
   * @returns true if addition is successful
   */
  public static boolean addUniversity(University university){
    return lib.university_addUniversity(university.getName(), university.getState(), university.getLocation(), university.getControl(), university.getNumberOfStudents(), university.getPercentFemale(), university.getSATVerbal(), university.getSATMath(), university.getExpenses(), university.getPercentFinancialAid(), university.getNumberOfApplicants(), university.getPercentAdmitted(), university.getPercentEnrolled(), university.getAcademicScale(), university.getSocialScale(), university.getQualityOfLifeScale()) != -1;
  }
  /**
   * This is a method that edits a university's information in the database
   * 
   * @param university the edited university object
   * @returns true if university is updated successfully
   */
  public static boolean editUniversity(University university){
	  return lib.university_editUniversity(university.getName() , university.getState(), university.getLocation(), university.getControl(), university.getNumberOfStudents(), university.getPercentFemale(), university.getSATVerbal(), university.getSATMath(), university.getExpenses(), university.getPercentFinancialAid(), university.getNumberOfApplicants(), university.getPercentAdmitted(), university.getPercentEnrolled(), university.getAcademicScale(), university.getSocialScale(), university.getQualityOfLifeScale()) != -1;
  }

  /**
   * This is a method that returns a list of universities in the database
   * 
   * @returns a set of all university objects in the database
   */
  public static ArrayList<University> getUniversities(){
	  ArrayList<University> retlist = new ArrayList<University>();
	  String[][] unis = lib.university_getUniversities();
	  for(String[] info : unis){
			  retlist.add(new University(info[0], info[1], info[2], info[3], Integer.parseInt(info[4]), Double.parseDouble(info[5]), Double.parseDouble(info[6]), Double.parseDouble(info[7]), Double.parseDouble(info[8]), Double.parseDouble(info[9]), Integer.parseInt(info[10]), Double.parseDouble(info[11]), Double.parseDouble(info[12]),Integer.parseInt(info[13]),Integer.parseInt(info[14]),Integer.parseInt(info[15]), null));
		  }
	  return retlist;
  }
  /**
   * This method updates a user's information in the database
   * 
   * @param a the account to be updated
   */
  public static boolean updateUser(Account account){
	  
	  return lib.user_editUser(account.getUsername(), account.getFirstName(), account.getLastName(), account.getPassword(), account.getType(), account.getStatus()) != -1;
  }
  
  /**
   * This method returns a set of all the accounts in the database
   * 
   * @returns a set of accounts in the database
   */
  public static ArrayList<Account> getAccounts(){
    ArrayList<Account> retlist = new ArrayList<Account>();
	String[][] users = lib.user_getUsers();
	  for(String[] info : users){
			  retlist.add(new Account(info[2], info[0], info[1], info[3], info[4].charAt(0), info[5].charAt(0)));		  
	  }
	  return retlist;
  } 
    /**
   * This method adds an account to the system
   * 
   * @param account the account to be added to the database
   * @returns true if account added successfully, otherwise false
   */
  public static boolean addAccount(Account account){
    return lib.user_addUser(account.getFirstName(), account.getLastName(), account.getUsername(), account.getPassword(), account.getType()) != -1;
  }
  /**
   * This method gets an account that matches the specified username, sets users saved schools
   * 
   * @param username the username of the account to be retrieved
   * @returns the account object that matches the specified unsername
   */
  public static Account getAccount(String username){
	  String[][] users = lib.user_getUsers();
	  ArrayList<University> unis = new ArrayList<University>();
	  for(String[] info : users){
		  if(info[2].equals(username)){
			  String[][] schools = lib.user_getUsernamesWithSavedSchools();
			  if(schools != null){
				  for(String[] user: schools){
				  	if(user[0].equals(username)){
				  		for(int i = 1; i < user.length; i++) unis.add(getUniversity(info[i]));
				  	}
				  }
			  }
			  if(info[4].charAt(0) == 'a'){
				  return new Account(info[2], info[0], info[1], info[3], info[4].charAt(0), info[5].charAt(0));
			  } else {
				  return new User(info[2], info[0], info[1], info[3], info[4].charAt(0), info[5].charAt(0), unis);
			  }
			 
		  }  
	  }
	  return null;
  }
  /**
   * Removes saved school from the user in the database
   * 
   * @param name of the user
   * @param school name of school to be removed
   * @return true if successfully removed
   */
  public static boolean removeSchool(String name, String school){
	  return lib.user_removeSchool(name, school) != -1;
  }  
  /**
   * Adds saved school from the user in the database
   * 
   * @param name of the user
   * @param school name of school to be added
   * @return true if successfully added
   */
  public static boolean saveSchool(String name, String school){
	  return lib.user_saveSchool(name, school) != -1;
  }
  
 
}
