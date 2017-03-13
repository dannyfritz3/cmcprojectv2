/**
 * File: University.java
 */

package baseclasses;

/**
 * A class that defines the attributes and functionalities of a university
 * 
 * @authors Danny Fritz, Matt Zent, Ethan Ferry, and Kyle Becker
 * @version 0.1
 */
public class University
{
  private String name, state, location, emphasis;
  private int numberOfStudents, percentFemale, satVerbal, satMath,
    expenses, percentFinancialAid, numberOfApplicants, percentAdmitted,
    percentEnrolled, academicScale, socialScale;
  
  /**
   * A contructor that creates a university object with the minimum requirements being just the name
   * 
   * @param the name of the university
   */
  public University(String name)
  {
    this.name = name;
  }
  
  /**
   * A constructor that creates a University object with all of the requirements
   * 
   * @param name of the university
   * @param state in which university is located
   * @param type of location university is located (urban, rural, suburban, etc.)
   * @param subjects that are emhasized at the university
   * @param number of students at the university
   * @param percent of female students at the university
   * @param average SAT Verbal score for enrolled students
   * @param average SAT Math score for enrolled students
   * @param anual expenses or tuition to attend university
   * @param percent of financial aid given to students at university
   * @param number of students that applied to university
   * @param percent of students admitted to university
   * @param percent of students that decide to enroll at university
   * @param academic scale indicating the academic scale at university (1-5)
   * @param social scale indicating quality of life at university (1-5)
   * @throws IllegalArgumentsException if the academicScale parameter is not between 1 and 5
   * @throws IllegalArgumentsException if the socialScale parameter is not between 1 and 5
   */
  public University(String name, String state, String location, int numberOfStudents,
		  			int percentFemale, int satVerbal, int satMath,
                    int expenses, int percentFinancialAid, int numberOfapplicants,
                    int percentAdmitted, int percentEnrolled, int academicScale,
                    int socialScale, String emphasis,) throws IllegalArgumentException
  {
	  if((academicScale < 1 || acdemicScale > 5))
	  {
		  throw new IllegalArgumentException("Parameter for 'Academic Scale' is not a valid number. Must be an integer 1 - 5");
	  }
	  else if((socialScale < 1 || socialScale > 5))
	  {
		  throw new IllegalArgumentException("Parameter for 'Social Scale' is not a valid number. Must be an integer 1 - 5");
	  }
	  else
	  {
	    this.name = name;
	    this.state = state;
	    this.location = location;
	    this.numberOfStudents = numberOfStudents;
	    this.percentFemale = percentFemale;
	    this.satVerbal = satVerbal;
	    this.satMath = satMath;
	    this.expenses = expenses;
	    this.percentFinancialAid = percentFinancialAid;
	    this.numberOfApplicants = numberOfApplicants;
	    this.percentAdmitted = percentAdmitted;
	    this.percentEnrolled = percentEnrolled;
	    this.academicScale = academicScale;
	    this.socialScale = socialScale;
	    this.emphasis = emphasis;
	  }
  }
  /**
   * A method that gets the name of the University
   * 
   * @returns the name of the University
   */
  public String getName()
  {
    return this.name;
  }
  
  /**
   * A method that gets the state of the University
   * 
   * @returns the state of the University
   */
  public String getState()
  {
    return this.state;
  }
  
  /**
   * A method that gets the location of the University
   * 
   * @returns the location of the University
   */
  public String getLocation()
  {
    return this.location;
  }
  
  /**
   * A method that gets the number of studnets at the University
   * 
   * @returns the number of students at the University
   */
  public int getNumberOfStudents()
  {
    return this.numberOfStudents;
  }
  
  /**
   * A method that gets the percentage of female students in the University
   * 
   * @returns the percentage of female students in the University
   */
  public int getPercentFemale()
  {
    return this.percentFemale;
  }
  
  /**
   * A method that gets the SAT Verbal of the University
   * 
   * @returns the SAT Verbal of the University
   */
  public int getSATVerbal()
  {
    return this.satVerbal;
  }
  
  /**
   * A method that gets the SAT Math of the University
   * 
   * @returns the SAT Math of the University
   */
  public int getSATMath()
  {
    return this.satMath;
  }
  
  /**
   * A method that gets the expenses of the University
   * 
   * @returns the expenses of the University
   */
  public int getExpenses()
  {
    return this.expenses;
  }
  
  /**
   * A method that gets the percent of financial aid from the University
   * 
   * @returns the percent of financial aid from the University
   */
  public int getPercentFinancialAid()
  {
    return this.percentFinancialAid;
  }
  
  /**
   * A method that gets the number of applicants from the University
   * 
   * @returns the number of applicants from the University
   */
  public int getNumberOfApplicants()
  {
    return this.numberOfApplicants;
  }
  
  /**
   * A method that gets the percent admitted from the University
   * 
   * @returns the percent admitted from the University
   */
  public int getPercentAdmitted()
  {
    return this.percentAdmitted;
  }
  
  /**
   * A method that gets the percent enrolled from the University
   * 
   * @returns the percent enrolled from the University
   */
  public int getPercentEnrolled()
  {
    return this.percentEnrolled;
  }
  
  /**
   * A method that gets the academic scale from the University
   * 
   * @returns the academic scale from the University
   */
  public int getAcademicScale()
  {
    return this.academicScale;
  }
  
  /**
   * A method that gets the social scale from the University
   * 
   * @returns the social scale from the University
   */
  public int getSocialScale()
  {
    return this.socialScale;
  }
  
  /**
   * A method that gets the emphasis from the University
   * 
   * @returns the emphasis from the University
   */
  public String getEmphasis()
  {
    return this.emphasis;
  }
  
  /**
   * A method that gets all of the information about the University
   * 
   * @returns all of the information about the University
   */
  public String getInformation()
  {
    return "Name: " + name + "\nState: " + state + "\nLocation: " + location
      + "\nNumber of Students: " + numberOfStudents + "\nPercent Female: " + percentFemale
      + "\nSAT Verbal: " + satVerbal + "\nSAT Math: " + satMath + "\nExpenses: " + expenses
      + "\nPercent FinancialAid: " + percentFinancialAid + "\nNumber of Applicants: " + numberOfApplicants
      + "\nPercent Enrolled: " + percentEnrolled + "Academic Scale: " + academicScale + "\nSocial Scale: " + socialScale
      + "\nEmphasis: " + emphasis;
  }
  
  /**
   * A method that sets the name of the University
   * 
   * @param the name of University
   */
  public void setName(String s)
  {
    this.name = s;
  }
  
  /**
   * A method that sets the state of the University
   * 
   * @param the state of University
   */
  public void setState(String s)
  {
    this.state = s;
  }
  
  /**
   * A method that sets the location of the University
   * 
   * @param the location of University
   */
  public void setLocation(String s)
  {
    this.location = s;
  }
  
  /**
   * A method that sets the number of students at the University
   * 
   * @param the number of students at the University
   */
  public void setNumberOfStudents(int i)
  {
    this.numberOfStudents = i;
  }
  
  /**
   * A method that sets the percent of female students at the University
   * 
   * @param the percent of female students at the University
   */
  public void setPercentFemale(int i)
  {
    this.percentFemale = i;
  }
  
  /**
   * A method that sets the SAT Verbal at the University
   * 
   * @param the SAT Verbal at the University
   */
  public void setSATVerbal(int i)
  {
    this.satVerbal = i;
  }
  
  /**
   * A method that sets the SAT Math at the University
   * 
   * @param the SAT Math at the University
   */
  public void setSATMath(int i)
  {
    this.satMath = i;
  }
  
  /**
   * A method that sets the expenses of the University
   * 
   * @param the expenses of the University
   */
  public void setExpenses(int i)
  {
    this.expenses = i;
  }
  
  /**
   * A method that sets the percent financial aid of the University
   * 
   * @param the percent financial aid of the University
   */
  public void setPercentFinancialAid(int i)
  {
    this.percentFinancialAid = i;
  }
  
  /**
   * A method that sets the number of applicants to the University
   * 
   * @param the number of applicants to the University
   */
  public void setNumberOfApplicants(int i)
  {
    this.numberOfApplicants = i;
  }
  
  /**
   * A method that sets the percent admitted at the University
   * 
   * @param the percent admitted at the University
   */
  public void setPercentAdmitted(int i)
  {
    this.percentAdmitted = i;
  }
  
  /**
   * A method that sets the percent enrolled at the University
   * 
   * @param the percent enrolled at the University
   */
  public void setPercentEnrolled(int i)
  {
    this.percentEnrolled = i;
  }
  
  /**
   * A method that sets the academic scale of the University
   * 
   * @param the academic scale of the University
   */
  public void setAcademicScale(int i)
  {
    this.academicScale = i;
  }
  
  /**
   * A method that sets the social scale of the University
   * 
   * @param the social scale of the University
   */
  public void setSocialScale(int i)
  {
    this.socialScale = i;
  }
  
  /**
   * A method that sets the emphasis of the University
   * 
   * @param the emphasis of the University
   */
  public void setEmphasis(String s)
  {
    this.emphasis = s;
  }
  
  /**
   * A method that compares one University to another
   * 
   * @param a University to compare
   * @returns an integer representing the number of comparisons
   */
  public int compareto(Object o)
  {
    return 0;
  }
}