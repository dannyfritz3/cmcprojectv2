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
	 * This method talks to the dbcontroller to search the database based on given conditions, it will put all searches in searchedUniversities
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
	public boolean search(String name, String state, String location, int numberOfStudents, int numberOfStudents2, int percentFemale, int percentFemale2, int SATVerbal, int SATVerbal2, int SATMath, int SATMath2, int expense, int expense2, int percentFinancialAid, int percentFinancialAid2, int numberOfApplicants, int numberOfApplicants2, int percentAdmitted, int percentAdmitted2, int percentEnrolled, int percentEnrolled2, int academicScale, int academicScale2, int socialScale, int socialScale2, String emphasis){
		DBController db = new DBController();
		ArrayList<University> Us = db.getUniversities();
		for(int i = 0;i < Us.size();i++){
			University u = Us.get(i);
			if(name == null || u.getName().contains(name)){
				if(state == null || u.getState().contains(state)){
					if(location == null || u.getLocation().equals(location)){
						if(numberOfStudents == null && numberOfStudents2 == null || numberOfStudents == null && u.getNumberOfStudents() <= numberOfStudents2 || numberOfStudents2 == null && u.getNumberOfStudents() >= numberOfStudents || u.getNumberOfStudents() >= numberOfStudents && u.getNumberOfStudents() <= numberOfStudents2){
							if(percentFemale == null && percentFemale2 == null || percentFemale == null && u.getPercentFemale() <= percentFemale2 || percentFemale2 == null && u.getPercentFemale() >= percentFemale || u.getPercentFemale() >= percentFemale && u.getPercentFemale() <= percentFemale2){
								if(SATVerbal == null && SATVerbal2 == null || SATVerbal == null && u.getSATVerbal() <= SATVerbal2 || SATVerbal2 == null && u.getSATVerbal() >= SATVerbal || u.getSATVerbal() >= SATVerbal && u.getSATVerbal() <= SATVerbal2){
									if(SATMath == null && SATMath2 == null || SATMath == null && u.getSATMath() <= SATMath2 || SATMath2 == null && u.getSATMath() >= SATMath || u.getSATMath() >= SATMath && u.getSATMath() <= SATMath2){
										if(expense == null && expense2 == null || expense == null && u.getExpenses() <= expense2 || expense2 == null && u.getExpenses() >= expense || u.getExpenses() >= expense && u.getExpenses() <= expense2){
											if(percentFinancialAid == null && percentFinancialAid2 == null || percentFinancialAid == null && u.getPercentFinancialAid() <= percentFinancialAid2 || percentFinancialAid2 == null && u.getPercentFinancialAid() >= percentFinancialAid || u.getPercentFinancialAid() >= percentFinancialAid && u.getPercentFinancialAid() <= percentFinancialAid2){
												if(numberOfApplicants == null && numberOfApplicants2 == null || numberOfApplicants == null && u.getNumberOfApplicants() <= numberOfApplicants2 || numberOfApplicants2 == null && u.getNumberOfApplicants() >= numberOfApplicants || u.getNumberOfApplicants() >= numberOfApplicants && u.getNumberOfApplicants() <= numberOfApplicants2){
													if(percentAdmitted == null && percentAdmitted2 == null || percentAdmitted == null && u.getPercentAdmitted() <= percentAdmitted2 || percentAdmitted2 == null && u.getPercentAdmitted() >= percentAdmitted || u.getPercentAdmitted() >= percentAdmitted && u.getPercentAdmitted() <= percentAdmitted2){
														if(percentEnrolled == null && percentEnrolled2 == null || percentEnrolled == null && u.getPercentEnrolled() <= percentEnrolled2 || percentEnrolled2 == null && u.getPercentEnrolled() >= percentEnrolled || u.getPercentEnrolled() >= percentEnrolled && u.getPercentEnrolled() <= percentEnrolled2){
															if(academicScale == null && academicScale2 == null || academicScale == null && u.getAcademicScale() <= academicScale2 || academicScale2 == null && u.getAcademicScale() >= academicScale || u.getAcademicScale() >= academicScale && u.getAcademicScale() <= academicScale2){
																if(socialScale == null && socialScale2 == null || socialScale == null && u.getSocialScale() <= socialScale2 || socialScale2 == null && u.getSocialScale() >= socialScale || u.getSocialScale() >= socialScale && u.getSocialScale() <= socialScale2){
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

