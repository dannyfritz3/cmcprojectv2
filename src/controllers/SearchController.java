/**
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
	private ArrayList<String> searchParams;
	
	/**
	 * This method holds the algorithm for finding related universities to a given university
	 * 
	 * @param university to be compared to
	 * @param (n) number of recommended universities the user would like
	 * @return an array of size n holding the most closely related universities
	 */
	public ArrayList<University> findRelatedUniversities(University university, int n){
		if(n < 0){
			throw new IllegalArgumentException("n cannot be negative");
		}
		ArrayList<University> Us = DBController.getUniversities();
		double[][] maxMin = DBController.getMaxMinValues();
		ArrayList<Tuple<University,Double>> distances = new ArrayList<Tuple<University,Double>>();//always in sorted order
		for(int i = 0;i < Us.size();i++){
			University temp = Us.get(i);
			double dist = distance(university, temp, maxMin);
			if(distances.size() == 0){
				distances.add(new Tuple<University,Double>(temp,dist));
			} else {
				for(int j = 0;j < distances.size();j++){
					if(distances.get(j).i > dist){
						distances.add(j, new Tuple<University,Double>(temp,dist));
						j = distances.size();
					}
				}
			}
		}
		ArrayList<University> relatedUs = new ArrayList<University>();
		for(int i = 1;i < n;i++){//could possibly cause problems with recommending the same university
			relatedUs.add(distances.get(i).university);
		}
		return relatedUs;
	}
	
	/**
	 * helper function to find the distances between two universities
	 * 
	 * @param u university to be compared to
	 * @param c university to compare to u
	 * @return the distance between the two
	 */
	private double distance(University u, University c,double[][] maxMin){
		double distance = 0;
		if(u.getState() != null && !u.getState().equalsIgnoreCase(c.getState())){
			distance+=1;
		}
		if(u.getState() != null && !u.getLocation().equalsIgnoreCase(c.getLocation())){
			distance+=1;
		}
		if(u.getState() != null && !u.getControl().equalsIgnoreCase(c.getControl())){
			distance+=1;
		}
		//number of students
		distance+=Math.abs(u.getNumberOfStudents() - c.getNumberOfStudents())/(maxMin[1][0] - maxMin[0][0]);
		distance+=Math.abs(u.getPercentFemale() - c.getPercentFemale())/(maxMin[1][5] - maxMin[0][5]);
		distance+=Math.abs(u.getSATVerbal() - c.getSATVerbal())/(maxMin[1][6] - maxMin[0][6]);
		distance+=Math.abs(u.getSATMath() - c.getSATMath())/(maxMin[1][7] - maxMin[0][7]);
		//expenses
		distance+=Math.abs(u.getExpenses() - c.getExpenses())/(maxMin[1][8] - maxMin[0][8]);
		distance+=Math.abs(u.getPercentFinancialAid() - c.getPercentFinancialAid())/(maxMin[1][9] - maxMin[0][9]);
		//number of applicants

		distance+=Math.abs(u.getNumberOfApplicants() - c.getNumberOfApplicants())/(maxMin[1][1] - maxMin[0][1]);
		distance+=Math.abs(u.getPercentAdmitted() - c.getPercentAdmitted())/(maxMin[1][10] - maxMin[0][10]);
		distance+=Math.abs(u.getPercentEnrolled() - c.getPercentEnrolled())/(maxMin[1][11] - maxMin[0][11]);
		distance+=Math.abs(u.getAcademicScale() - c.getAcademicScale())/(maxMin[1][2] - maxMin[0][2]);
		distance+=Math.abs(u.getSocialScale() - c.getSocialScale())/(maxMin[1][3] - maxMin[0][3]);
		distance+=Math.abs(u.getQualityOfLifeScale() - c.getQualityOfLifeScale())/(maxMin[1][4] - maxMin[0][4]);
		int numE = 0;
		double eDist = 0;
		for(String e : u.getEmphasis()){
			numE ++;
			if(!c.getEmphasis().contains(e)){
				eDist+=1;
			}
		}
		if(numE != 0){
			distance+=eDist/numE;
		}

		return distance;
	}
	
	/**
	 * This method gets a university out of searchedUniversities
	 * 
	 * @param name of the university to be gotten
	 * @return the university object in the list, will be null if it doesn't not exist
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
	 * This method gets this search objects parameters used to search
	 * 
	 * @return an array list of strings containing all parameters
	 */
	public String getParameters()
	{
		String ret = "";
		int cas = 0;
		while(ret.length() < 100 && cas < 29){
			switch(cas){
			case 0:
				if(searchParams.get(cas) != null){
					ret = ret + "School Name: " + searchParams.get(cas);
					ret = ret + ", ";
				}
				
				cas++;
				break;
			case 1:
				if(searchParams.get(cas) != null){
					ret = ret + "State: " + searchParams.get(cas);
					ret = ret + ", ";
				}
				
				cas++;
				break;
			
			case 2:
				if(searchParams.get(cas) != null){
					ret = ret + "Location: " + searchParams.get(cas);
					ret = ret + ", ";
				}
				cas++;
			break;
			case 3:
				if(searchParams.get(cas) != null){
					ret = ret + "Control: " + searchParams.get(cas);
					ret = ret + ", ";
				}
				cas++;
			break;
			case 4:
				if(!searchParams.get(cas).equals("-1") || !searchParams.get(cas+1).equals("-1")){
					ret = ret + "# of Students: "; 
					if(searchParams.get(cas).equals("-1")){
						ret = ret + "0";
					} else {
						ret = ret + searchParams.get(cas);
					}
					if(searchParams.get(cas+1).equals("-1")){
						ret = ret + "<";
					} else {
						ret = ret + "-" + searchParams.get(cas+1);
					}
					ret = ret + ", ";
				}
				cas+=2;
			break;
			case 6:
				if(!searchParams.get(cas).equals("-1.0") || !searchParams.get(cas+1).equals("-1.0")){
					ret = ret + "% Female: "; 
					if(searchParams.get(cas).equals("-1.0")){
						ret = ret + "0";
					} else {
						ret = ret + searchParams.get(cas);
					}
					if(searchParams.get(cas+1).equals("-1.0")){
						ret = ret + "<";
					} else {
						ret = ret + "-" + searchParams.get(cas+1);
					}
					ret = ret + ", ";
				}
				cas+=2;
			break;
			case 8:
				if(!searchParams.get(cas).equals("-1.0") || !searchParams.get(cas+1).equals("-1.0")){
					ret = ret + "SAT Verbal: "; 
					if(searchParams.get(cas).equals("-1.0")){
						ret = ret + "0";
					} else {
						ret = ret + searchParams.get(cas);
					}
					if(searchParams.get(cas+1).equals("-1.0")){
						ret = ret + "<";
					} else {
						ret = ret + "-" + searchParams.get(cas+1);
					}
					ret = ret + ", ";
				}
				cas+=2;
			break;
			case 10:
				if(!searchParams.get(cas).equals("-1.0") || !searchParams.get(cas+1).equals("-1.0")){
					ret = ret + "SAT Math: "; 
					if(searchParams.get(cas).equals("-1.0")){
						ret = ret + "0";
					} else {
						ret = ret + searchParams.get(cas);
					}
					if(searchParams.get(cas+1).equals("-1.0")){
						ret = ret + "<";
					} else {
						ret = ret + "-" + searchParams.get(cas+1);
					}
					ret = ret + ", ";
				}
				cas+=2;
			break;
			case 12:
				if(!searchParams.get(cas).equals("-1.0") || !searchParams.get(cas+1).equals("-1.0")){
					ret = ret + "Expenses: "; 
					if(searchParams.get(cas).equals("-1.0")){
						ret = ret + "0";
					} else {
						ret = ret + searchParams.get(cas);
					}
					if(searchParams.get(cas+1).equals("-1.0")){
						ret = ret + "<";
					} else {
						ret = ret + "-" + searchParams.get(cas+1);
					}
					ret = ret + ", ";
				}
				cas+=2;
			break;
			case 14:
				if(!searchParams.get(cas).equals("-1.0") || !searchParams.get(cas+1).equals("-1.0")){
					ret = ret + "% Financial Aid: "; 
					if(searchParams.get(cas).equals("-1.0")){
						ret = ret + "0";
					} else {
						ret = ret + searchParams.get(cas);
					}
					if(searchParams.get(cas+1).equals("-1.0")){
						ret = ret + "<";
					} else {
						ret = ret + "-" + searchParams.get(cas+1);
					}
					ret = ret + ", ";
				}
				cas+=2;
			break;
			case 16:
				if(!searchParams.get(cas).equals("-1") || !searchParams.get(cas+1).equals("-1")){
					ret = ret + "# of Applicants : "; 
					if(searchParams.get(cas).equals("-1")){
						ret = ret + "0";
					} else {
						ret = ret + searchParams.get(cas);
					}
					if(searchParams.get(cas+1).equals("-1")){
						ret = ret + "<";
					} else {
						ret = ret + "-" + searchParams.get(cas+1);
					}
					ret = ret + ", ";
				}
				cas+=2;
			break;
			case 18:
				if(!searchParams.get(cas).equals("-1.0") || !searchParams.get(cas+1).equals("-1.0")){
					ret = ret + "% Admitted: "; 
					if(searchParams.get(cas).equals("-1.0")){
						ret = ret + "0";
					} else {
						ret = ret + searchParams.get(cas);
					}
					if(searchParams.get(cas+1).equals("-1.0")){
						ret = ret + "<";
					} else {
						ret = ret + "-" + searchParams.get(cas+1);
					}
					ret = ret + ", ";
				}
				cas+=2;
			break;
			case 20:
				if(!searchParams.get(cas).equals("-1.0") || !searchParams.get(cas+1).equals("-1.0")){
					ret = ret + "% Enrolled: "; 
					if(searchParams.get(cas).equals("-1.0")){
						ret = ret + "0";
					} else {
						ret = ret + searchParams.get(cas);
					}
					if(searchParams.get(cas+1).equals("-1.0")){
						ret = ret + "<";
					} else {
						ret = ret + "-" + searchParams.get(cas+1);
					}
					ret = ret + ", ";
				}
				cas+=2;
			break;
			case 22:
				if(!searchParams.get(cas).equals("-1") || !searchParams.get(cas+1).equals("-1")){
					ret = ret + "Academic Scale: "; 
					if(searchParams.get(cas).equals("-1")){
						ret = ret + "0";
					} else {
						ret = ret + searchParams.get(cas);
					}
					if(searchParams.get(cas+1).equals("-1")){
						ret = ret + "<";
					} else {
						ret = ret + "-" + searchParams.get(cas+1);
					}
					ret = ret + ", ";
				}
				cas+=2;
			break;
			case 24:
				if(!searchParams.get(cas).equals("-1") || !searchParams.get(cas+1).equals("-1")){
					ret = ret + "Social Scale: "; 
					if(searchParams.get(cas).equals("-1")){
						ret = ret + "0";
					} else {
						ret = ret + searchParams.get(cas);
					}
					if(searchParams.get(cas+1).equals("-1")){
						ret = ret + "<";
					} else {
						ret = ret + "-" + searchParams.get(cas+1);
					}
					ret = ret + ", ";
				}
				cas+=2;
			break;
			case 26:
				if(!searchParams.get(cas).equals("-1") || !searchParams.get(cas+1).equals("-1")){
					ret = ret + "Quality of Life Scale: "; 
					if(searchParams.get(cas).equals("-1")){
						ret = ret + "0";
					} else {
						ret = ret + searchParams.get(cas);
					}
					if(searchParams.get(cas+1).equals("-1")){
						ret = ret + "<";
					} else {
						ret = ret + "-" + searchParams.get(cas+1);
					}
					ret = ret + ", ";
				}
				cas+=2;
			break;
			case 28:
				int emphs = searchParams.size()-28;
				if(emphs > 0){
					ret = ret + "Emphasis: [";
					for(int i = 0; i < emphs;i++){
						ret = ret + searchParams.get(28 + i);
						if(i != emphs-1){
							ret = ret + ",";
						}
					}
					ret = ret + "], ";
				}
				cas+=1;
			break;
			}
		
		}
		if(ret.length() >= 2)ret = ret.substring(0,ret.length()-2);
		if(ret.length()>100){
			ret = ret.substring(0, 97) + "...";
		}
		return ret;
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
	public boolean search(String name, String state, String location, String control, int numberOfStudents, int numberOfStudents2, double percentFemale, double percentFemale2, double SATVerbal, double SATVerbal2, double SATMath, double SATMath2, double expense, double expense2, double percentFinancialAid, double percentFinancialAid2, int numberOfApplicants, int numberOfApplicants2, double percentAdmitted, double percentAdmitted2, double percentEnrolled, double percentEnrolled2, int academicScale, int academicScale2, int socialScale, int socialScale2, int qualityOfLifeScale, int qualityOfLifeScale2, ArrayList<String> emphasis){
		searchedUniversities = new ArrayList<University>();
		searchParams = new ArrayList<String>();
		addParams(name, state, location, control, numberOfStudents, numberOfStudents2, percentFemale, percentFemale2, SATVerbal, SATVerbal2, SATMath, SATMath2, expense, expense2, percentFinancialAid, percentFinancialAid2, numberOfApplicants, numberOfApplicants2, percentAdmitted, percentAdmitted2, percentEnrolled, percentEnrolled2, academicScale, academicScale2, socialScale, socialScale2, qualityOfLifeScale, qualityOfLifeScale2, emphasis);
		ArrayList<University> Us = DBController.getUniversities();
		for(int i = 0;i < Us.size();i++){
			University u = Us.get(i);
			if(name == null || u.getName().contains(name)){
				if(state == null || u.getState().contains(state)){
					if(location == null || u.getLocation().equals(location)){
						if(control == null || u.getControl().equals(control)){
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
																		if(qualityOfLifeScale == -1 && qualityOfLifeScale2 == -1 || qualityOfLifeScale == -1 && u.getQualityOfLifeScale() <= qualityOfLifeScale2 || qualityOfLifeScale2 == -1 && u.getQualityOfLifeScale() >= qualityOfLifeScale || u.getQualityOfLifeScale() >= qualityOfLifeScale && u.getQualityOfLifeScale() <= qualityOfLifeScale2){
																			if(emphasis.size() == 0){
																				searchedUniversities.add(u);
																			} else {
																				for(String emph : emphasis){
																					if(u.getEmphasis().contains(emph)){
																						searchedUniversities.add(u);
																						break;
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
					}
				}
			}
		}
		
		if(searchedUniversities.isEmpty()){
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Helper class to save parameters used to search
	 * 
	 * @param name
	 * @param state
	 * @param location
	 * @param control
	 * @param numberOfStudents
	 * @param numberOfStudents2
	 * @param percentFemale
	 * @param percentFemale2
	 * @param SATVerbal
	 * @param SATVerbal2
	 * @param SATMath
	 * @param SATMath2
	 * @param expense
	 * @param expense2
	 * @param percentFinancialAid
	 * @param percentFinancialAid2
	 * @param numberOfApplicants
	 * @param numberOfApplicants2
	 * @param percentAdmitted
	 * @param percentAdmitted2
	 * @param percentEnrolled
	 * @param percentEnrolled2
	 * @param academicScale
	 * @param academicScale2
	 * @param socialScale
	 * @param socialScale2
	 * @param qualityOfLifeScale
	 * @param qualityOfLifeScale2
	 * @param emphasis
	 */
	private void addParams(String name, String state, String location, String control, int numberOfStudents, int numberOfStudents2, double percentFemale, double percentFemale2, double SATVerbal, double SATVerbal2, double SATMath, double SATMath2, double expense, double expense2, double percentFinancialAid, double percentFinancialAid2, int numberOfApplicants, int numberOfApplicants2, double percentAdmitted, double percentAdmitted2, double percentEnrolled, double percentEnrolled2, int academicScale, int academicScale2, int socialScale, int socialScale2, int qualityOfLifeScale, int qualityOfLifeScale2, ArrayList<String> emphasis){
		searchParams.add(name);
		searchParams.add(state);
		searchParams.add(location);
		searchParams.add(control);
		searchParams.add(numberOfStudents+"");
		searchParams.add(numberOfStudents2+"");
		searchParams.add(percentFemale+"");
		searchParams.add(percentFemale2+"");
		searchParams.add(SATVerbal+"");
		searchParams.add(SATVerbal2+"");
		searchParams.add(SATMath+"");
		searchParams.add(SATMath2+"");
		searchParams.add(expense+"");
		searchParams.add(expense2+"");
		searchParams.add(percentFinancialAid+"");
		searchParams.add(percentFinancialAid2+"");
		searchParams.add(numberOfApplicants+"");
		searchParams.add(numberOfApplicants2+"");
		searchParams.add(percentAdmitted+"");
		searchParams.add(percentAdmitted2+"");
		searchParams.add(percentEnrolled+"");
		searchParams.add(percentEnrolled2+"");
		searchParams.add(academicScale+"");
		searchParams.add(academicScale2+"");
		searchParams.add(socialScale+"");
		searchParams.add(socialScale2+"");
		searchParams.add(qualityOfLifeScale+"");
		searchParams.add(qualityOfLifeScale2+"");
		for(String emph : emphasis){
			searchParams.add(emph);
		}
		System.out.println();
	}
	
	
	/**
	 * Tuple helper class
	 * 
	 * @author mjzent
	 *
	 * @param <X> is a University object
	 * @param <Y> is a distance
	 */
	private class Tuple<X, Y> { 
		  public final X university; 
		  public final Y i; 
		  public Tuple(X university, Y i) { 
		    this.university = university; 
		    this.i = i; 
		  } 
		} 
}

