/*
 *File: SearchController.java
 *
 */
package controllers;


import java.util.ArrayList;

import baseclasses.University;

/**
 * This is a class that holds all the methods related to searching
 * 
 * @author mjzent
 * @version 0.1
 */
public class SearchController {

	private ArrayList<University> searchedUniversities;
	
	/**
	 * This method holds the algorithm for finding related universities to a given university
	 * 
	 * @param university to be compared to
	 * @param (n) number of recommended universities the user would like
	 * @return an array of size n holding the most closely related universities
	 */
	public ArrayList<University> findRelatedUniversities(University university, int n){
		DBController db = new DBController();
		ArrayList<University> Us = db.getUniversities();
		ArrayList<Tuple<University,Integer>> distances = new ArrayList<Tuple<University,Integer>>();//always in sorted order
		for(int i = 0;i < Us.size();i++){
			University temp = Us.get(i);
			int dist = distance(university, temp);
			if(distances.size() == 0){
				distances.add(new Tuple<University,Integer>(temp,dist));
			} else {
				for(int j = 0;j < distances.size();j++){
					if(distances.get(j).i > dist){
						distances.add(j, new Tuple<University,Integer>(temp,dist));
						j = distances.size();
					}
				}
			}
		}
		ArrayList<University> relatedUs = new ArrayList<University>();
		for(int i = 0;i < n;i++){
			relatedUs.add(distances.get(i).university);
		}
		return relatedUs;
	}
	
	private int distance(University u, University c){
		int distance = 0;
		if(!u.getState().equals(c.getState())){
			distance+=1;
		}
		if(!u.getLocation().equals(c.getLocation())){
			distance+=1;
		}
		//numgber of students
		distance+=Math.abs(u.getNumberOfStudents() - c.getNumberOfStudents())/(Integer.MAX_VALUE - 0);
		distance+=Math.abs(u.getPercentFemale() - c.getPercentFemale())/(100-0);
		distance+=Math.abs(u.getSATVerbal() - c.getSATVerbal())/(800-200);
		distance+=Math.abs(u.getSATMath() - c.getSATMath())/(800-200);
		//expenses
		distance+=Math.abs(u.getExpenses() - c.getExpenses())/(Integer.MAX_VALUE - 0);
		distance+=Math.abs(u.getPercentFinancialAid() - c.getPercentFinancialAid())/(100-0);
		//number of applicants
		distance+=Math.abs(u.getNumberOfApplicants() - c.getNumberOfApplicants())/(Integer.MAX_VALUE - 0);
		distance+=Math.abs(u.getPercentAdmitted() - c.getPercentAdmitted())/(100-0);
		distance+=Math.abs(u.getPercentEnrolled() - c.getPercentEnrolled())/(100-0);
		distance+=Math.abs(u.getAcademicScale() - c.getSocialScale())/(5-0);
		distance+=Math.abs(u.getSocialScale() - c.getSocialScale())/(5-0);
		if(!u.getEmphasis().equals(c.getEmphasis())){
			distance+=1;
		}
		return distance;
	}
	
	/**
	 * This method gets a university out of searchedUniversities
	 * 
	 * @param name of the university to be gotten
	 * @return the university object in the list, will ben null if it doesn't not exist
	 */
	public University getUniversity(String name){
		for(int i = 0;i < searchedUniversities.size();i++){
			if(searchedUniversities.get(i).getName() == name){
				return searchedUniversities.get(i);
			}
		}
		return null;
	}
	
	/**
	 * This method gets all of the searchedUniversities
	 * 
	 * @return all of the searchedUniversities
	 */
	public ArrayList<University> getSearchedUniversities()
	{
		return this.searchedUniversities;
	}
	/**
	 * This method talks to the DBcontroller to search the database based on given conditions, it will put all searches in searchedUniversities
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
	 * @param percentAdmitted at the university
	 * @param percentEnrolled at the university
	 * @param academicScale of the university
	 * @param socialScale of the university
	 * @param emphasis of the university
	 * @return the condition attached to whether or not any matches were found to the search
	 */
	public boolean search(String name, String state, String location, String control, int numberOfStudents, int numberOfStudents2, double percentFemale, double percentFemale2, double SATVerbal, double SATVerbal2, double SATMath, double SATMath2, double expense, double expense2, double percentFinancialAid, double percentFinancialAid2, int numberOfApplicants, int numberOfApplicants2, double percentAdmitted, double percentAdmitted2, double percentEnrolled, double percentEnrolled2, int academicScale, int academicScale2, int socialScale, int socialScale2, int qualityOfLifeScale, int qualityOfLifeScale2, String emphasis){
		DBController db = new DBController();
		ArrayList<University> Us = db.getUniversities();
		for(int i = 0;i < Us.size();i++){
			University u = Us.get(i);
			if(name == null || u.getName().contains(name)){
				if(state == null || u.getState().contains(state)){
					if(location == null || u.getLocation().equals(location)){
						if(numberOfStudents == -1 && numberOfStudents2 == -1 || numberOfStudents == -1 && u.getNumberOfStudents() <= numberOfStudents2 || numberOfStudents2 == -1 && u.getNumberOfStudents() >= numberOfStudents || u.getNumberOfStudents() >= numberOfStudents && u.getNumberOfStudents() <= numberOfStudents2){
							if(percentFemale == -1 && percentFemale2 == -1 || percentFemale == -1 && u.getPercentFemale() <= percentFemale2 || percentFemale2 == -1 && u.getPercentFemale() >= percentFemale || u.getPercentFemale() >= percentFemale && u.getPercentFemale() <= percentFemale2){
								if(SATVerbal == -1 && SATVerbal2 == -1 || SATVerbal == -1 && u.getSATVerbal() <= SATVerbal2 || SATVerbal2 == -1 && u.getSATVerbal() >= SATVerbal || u.getSATVerbal() >= SATVerbal && u.getSATVerbal() <= SATVerbal2){
									if(SATMath == -1 && SATMath2 == -1 || SATMath == -1 && u.getSATMath() <= SATMath2 || SATMath2 == -1 && u.getSATMath() >= SATMath || u.getSATMath() >= SATMath && u.getSATMath() <= SATMath2){
										if(expense == -1 && expense2 == -1 || expense == -1 && u.getExpenses() <= expense2 || expense2 == -1 && u.getExpenses() >= expense || u.getExpenses() >= expense && u.getExpenses() <= expense2){
											if(percentFinancialAid == -1 && percentFinancialAid2 == -1 || percentFinancialAid == -1 && u.getPercentFinancialAid() <= percentFinancialAid2 || percentFinancialAid2 == -1 && u.getPercentFinancialAid() >= percentFinancialAid || u.getPercentFinancialAid() >= percentFinancialAid && u.getPercentFinancialAid() <= percentFinancialAid2){
												if(numberOfApplicants == -1 && numberOfApplicants2 == -1 || numberOfApplicants == -1 && u.getNumberOfApplicants() <= numberOfApplicants2 || numberOfApplicants2 == -1 && u.getNumberOfApplicants() >= numberOfApplicants || u.getNumberOfApplicants() >= numberOfApplicants && u.getNumberOfApplicants() <= numberOfApplicants2){
													if(percentAdmitted == -1 && percentAdmitted2 == -1 || percentAdmitted == -1 && u.getPercentAdmitted() <= percentAdmitted2 || percentAdmitted2 == -1 && u.getPercentAdmitted() >= percentAdmitted || u.getPercentAdmitted() >= percentAdmitted && u.getPercentAdmitted() <= percentAdmitted2){
														if(percentEnrolled == -1 && percentEnrolled2 == -1 || percentEnrolled == -1 && u.getPercentEnrolled() <= percentEnrolled2 || percentEnrolled2 == -1 && u.getPercentEnrolled() >= percentEnrolled || u.getPercentEnrolled() >= percentEnrolled && u.getPercentEnrolled() <= percentEnrolled2){
															if(academicScale == -1 && academicScale2 == -1 || academicScale == -1 && u.getAcademicScale() <= academicScale2 || academicScale2 == -1 && u.getAcademicScale() >= academicScale || u.getAcademicScale() >= academicScale && u.getAcademicScale() <= academicScale2){
																if(socialScale == -1 && socialScale2 == -1 || socialScale == -1 && u.getSocialScale() <= socialScale2 || socialScale2 == -1 && u.getSocialScale() >= socialScale || u.getSocialScale() >= socialScale && u.getSocialScale() <= socialScale2){
																	if(emphasis == null || u.getEmphasis().contains(emphasis)){
																		Us.add(u);
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		if(Us.isEmpty()){
			return false;
		} else {
			return true;
		}
	}
	
	private class Tuple<X, Y> { 
		  public final X university; 
		  public final Y i; 
		  public Tuple(X university, Y i) { 
		    this.university = university; 
		    this.i = i; 
		  } 
		} 
}

