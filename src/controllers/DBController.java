/*
 *File: DBController.java
 *
 */
package controllers;


import java.util.ArrayList;
import java.util.Arrays;

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
  
  private UniversityDBLibrary lib = new UniversityDBLibrary("nullpm", "nullpm", "csci230");
  
  /**
   * This is a method that returns a university object from info from the database
   * 
   * @param name name of the university
   * @returns univeristy object of university with specified name
   */
  public University getUniversity(String name){
	  String[][] unis = lib.university_getUniversities();
	  for(String[] info : unis){
		  if(info[0].equals(name)){
			  System.out.println(Arrays.toString(info));
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
  public boolean addUniversity(University university){
    return lib.university_addUniversity(university.getName(), university.getState(), university.getLocation(), university.getControl(), university.getNumberOfStudents(), university.getPercentFemale(), university.getSATVerbal(), university.getSATMath(), university.getExpenses(), university.getPercentFinancialAid(), university.getNumberOfApplicants(), university.getPercentAdmitted(), university.getPercentEnrolled(), university.getAcademicScale(), university.getSocialScale(), university.getQualityOfLifeScale()) != -1;
  }
  /**
   * This is a method that edits a university's information in the database
   * 
   * @param university the edited university object
   * @returns true if university is updated successfully
   */
  public boolean editUniversity(University university){
	  return lib.university_editUniversity(university.getName() , university.getState(), university.getLocation(), university.getControl(), university.getNumberOfStudents(), university.getPercentFemale(), university.getSATVerbal(), university.getSATMath(), university.getExpenses(), university.getPercentFinancialAid(), university.getNumberOfApplicants(), university.getPercentAdmitted(), university.getPercentEnrolled(), university.getAcademicScale(), university.getSocialScale(), university.getQualityOfLifeScale()) != -1;
  }

  /**
   * This is a method that returns a list of universities in the database
   * 
   * @returns a set of all university objects in the database
   */
  public String[][] getUniversities(){
    return lib.university_getUniversities();
  }
  /**
   * This method updates a user's information in the database
   * 
   * @param a the account to be updated
   */
  public boolean updateUser(Account account){
	  return lib.user_editUser(account.getUsername(), account.getFirstName(), account.getLastName(), account.getPassword(), account.getType(), account.getStatus()) != -1;
  }
  
  /**
   * This method returns a set of all the accounts in the database
   * 
   * @returns a set of accounts in the database
   */
  public ArrayList<Account> getAccounts(){
    return null;
  } 
    /**
   * This method adds an account to the system
   * 
   * @param account the account to be added to the database
   * @returns true if account added successfully, otherwise false
   */
  public boolean addAccount(Account account){
    return lib.user_addUser(account.getFirstName(), account.getLastName(), account.getUsername(), account.getPassword(), account.getType()) != -1;
  }
  /**
   * This method gets an account that matches the specified username
   * 
   * @param username the username of the account to be retrieved
   * @returns the account object that matches the specified unsername
   */
  public User getAccount(String username){
    return null;
  }
  
 
}
