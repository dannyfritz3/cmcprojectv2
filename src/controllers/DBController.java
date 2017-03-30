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
  private static double[][] maxMinValues;//[0]=min,[1]=max{numberOfStudents,numberOfApplicants,academicScale,socialScale,qualityOfLifeScale,percentFemale,satVerbal,satMath,expenses,percentFinancialAid,percentAdmitted,percentEnrolled}
  
  
  /**
   * this method gets the private variable MaxMinValues, if not already initialized it sets them
   * 
   * @return an array of all max and min values of all universities in the database
   */
  public static double[][] getMaxMinValues(){
	  if(maxMinValues == null){
		  maxMinValues = new double[2][12];
		  ArrayList<University> us = getUniversities();
		  for(University u : us){
			  if(u.getNumberOfStudents()<maxMinValues[0][0]){
				  maxMinValues[0][0] = u.getNumberOfStudents();
			  } else if(u.getNumberOfStudents()>maxMinValues[1][0]){
				  maxMinValues[1][0] = u.getNumberOfStudents();
			  }
			  if(u.getNumberOfApplicants()<maxMinValues[0][1]){
				  maxMinValues[0][1] = u.getNumberOfApplicants();
			  } else if(u.getNumberOfApplicants()>maxMinValues[1][1]){
				  maxMinValues[1][1] = u.getNumberOfApplicants();
			  }
			  if(u.getAcademicScale()<maxMinValues[0][2]){
				  maxMinValues[0][2] = u.getAcademicScale();
			  } else if(u.getAcademicScale()>maxMinValues[1][2]){
				  maxMinValues[1][2] = u.getAcademicScale();
			  }
			  if(u.getSocialScale()<maxMinValues[0][3]){
				  maxMinValues[0][3] = u.getSocialScale();
			  } else if(u.getSocialScale()>maxMinValues[1][3]){
				  maxMinValues[1][3] = u.getSocialScale();
			  }
			  if(u.getQualityOfLifeScale()<maxMinValues[0][4]){
				  maxMinValues[0][4] = u.getQualityOfLifeScale();
			  } else if(u.getQualityOfLifeScale()>maxMinValues[1][4]){
				  maxMinValues[1][4] = u.getQualityOfLifeScale();
			  }
			  if(u.getPercentFemale()<maxMinValues[0][5]){
				  maxMinValues[0][5] = u.getPercentFemale();
			  } else if(u.getPercentFemale()>maxMinValues[1][5]){
				  maxMinValues[1][5] = u.getPercentFemale();
			  }
			  if(u.getSATVerbal()<maxMinValues[0][6]){
				  maxMinValues[0][6] = u.getSATVerbal();
			  } else if(u.getSATVerbal()>maxMinValues[1][6]){
				  maxMinValues[1][6] = u.getSATVerbal();
			  }
			  if(u.getSATMath()<maxMinValues[0][7]){
				  maxMinValues[0][7] = u.getSATMath();
			  } else if(u.getSATMath()>maxMinValues[1][7]){
				  maxMinValues[1][7] = u.getSATMath();
			  }
			  if(u.getExpenses()<maxMinValues[0][8]){
				  maxMinValues[0][8] = u.getExpenses();
			  } else if(u.getExpenses()>maxMinValues[1][8]){
				  maxMinValues[1][8] = u.getExpenses();
			  }
			  if(u.getPercentFinancialAid()<maxMinValues[0][9]){
				  maxMinValues[0][9] = u.getPercentFinancialAid();
			  } else if(u.getPercentFinancialAid()>maxMinValues[1][9]){
				  maxMinValues[1][9] = u.getPercentFinancialAid();
			  }
			  if(u.getPercentAdmitted()<maxMinValues[0][10]){
				  maxMinValues[0][10] = u.getPercentAdmitted();
			  } else if(u.getPercentAdmitted()>maxMinValues[1][10]){
				  maxMinValues[1][10] = u.getPercentAdmitted();
			  }
			  if(u.getPercentEnrolled()<maxMinValues[0][11]){
				  maxMinValues[0][11] = u.getPercentEnrolled();
			  } else if(u.getPercentEnrolled()>maxMinValues[1][11]){
				  maxMinValues[1][11] = u.getPercentEnrolled();
			  }
		  }
	  }
	  return maxMinValues;
	  
  }
  
  
  
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
   * @param u the university object to be added
   * @returns true if addition is successful
   */
  public static boolean addUniversity(University u){
	  if(u.getNumberOfStudents()<maxMinValues[0][0]){
		  maxMinValues[0][0] = u.getNumberOfStudents();
	  } else if(u.getNumberOfStudents()>maxMinValues[1][0]){
		  maxMinValues[1][0] = u.getNumberOfStudents();
	  }
	  if(u.getNumberOfApplicants()<maxMinValues[0][1]){
		  maxMinValues[0][1] = u.getNumberOfApplicants();
	  } else if(u.getNumberOfApplicants()>maxMinValues[1][1]){
		  maxMinValues[1][1] = u.getNumberOfApplicants();
	  }
	  if(u.getAcademicScale()<maxMinValues[0][2]){
		  maxMinValues[0][2] = u.getAcademicScale();
	  } else if(u.getAcademicScale()>maxMinValues[1][2]){
		  maxMinValues[1][2] = u.getAcademicScale();
	  }
	  if(u.getSocialScale()<maxMinValues[0][3]){
		  maxMinValues[0][3] = u.getSocialScale();
	  } else if(u.getSocialScale()>maxMinValues[1][3]){
		  maxMinValues[1][3] = u.getSocialScale();
	  }
	  if(u.getQualityOfLifeScale()<maxMinValues[0][4]){
		  maxMinValues[0][4] = u.getQualityOfLifeScale();
	  } else if(u.getQualityOfLifeScale()>maxMinValues[1][4]){
		  maxMinValues[1][4] = u.getQualityOfLifeScale();
	  }
	  if(u.getPercentFemale()<maxMinValues[0][5]){
		  maxMinValues[0][5] = u.getPercentFemale();
	  } else if(u.getPercentFemale()>maxMinValues[1][5]){
		  maxMinValues[1][5] = u.getPercentFemale();
	  }
	  if(u.getSATVerbal()<maxMinValues[0][6]){
		  maxMinValues[0][6] = u.getSATVerbal();
	  } else if(u.getSATVerbal()>maxMinValues[1][6]){
		  maxMinValues[1][6] = u.getSATVerbal();
	  }
	  if(u.getSATMath()<maxMinValues[0][7]){
		  maxMinValues[0][7] = u.getSATMath();
	  } else if(u.getSATMath()>maxMinValues[1][7]){
		  maxMinValues[1][7] = u.getSATMath();
	  }
	  if(u.getExpenses()<maxMinValues[0][8]){
		  maxMinValues[0][8] = u.getExpenses();
	  } else if(u.getExpenses()>maxMinValues[1][8]){
		  maxMinValues[1][8] = u.getExpenses();
	  }
	  if(u.getPercentFinancialAid()<maxMinValues[0][9]){
		  maxMinValues[0][9] = u.getPercentFinancialAid();
	  } else if(u.getPercentFinancialAid()>maxMinValues[1][9]){
		  maxMinValues[1][9] = u.getPercentFinancialAid();
	  }
	  if(u.getPercentAdmitted()<maxMinValues[0][10]){
		  maxMinValues[0][10] = u.getPercentAdmitted();
	  } else if(u.getPercentAdmitted()>maxMinValues[1][10]){
		  maxMinValues[1][10] = u.getPercentAdmitted();
	  }
	  if(u.getPercentEnrolled()<maxMinValues[0][11]){
		  maxMinValues[0][11] = u.getPercentEnrolled();
	  } else if(u.getPercentEnrolled()>maxMinValues[1][11]){
		  maxMinValues[1][11] = u.getPercentEnrolled();
	  }
	  return lib.university_addUniversity(u.getName(), u.getState(), u.getLocation(), u.getControl(), u.getNumberOfStudents(), u.getPercentFemale(), u.getSATVerbal(), u.getSATMath(), u.getExpenses(), u.getPercentFinancialAid(), u.getNumberOfApplicants(), u.getPercentAdmitted(), u.getPercentEnrolled(), u.getAcademicScale(), u.getSocialScale(), u.getQualityOfLifeScale()) != -1;
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
				  		for(int i = 1; i < user.length; i++) unis.add(getUniversity(user[i]));
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
  /**
   * Removes a user from the user in the database
   * 
   * @param name of the user
   * @return true if successfully removed
   */
  public static boolean removeUser(String username){
	  return lib.user_deleteUser(username) != -1;
  }  
  
  /**
   * Deletes a school
   * @param name of school to be deleted
   * @return if the school was deleted
   */
  public static boolean deleteSchool(String name){
	  return lib.university_deleteUniversity(name) != -1;
  }
 
}
