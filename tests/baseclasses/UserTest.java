package baseclasses;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class UserTest {

	private User user1;
	private ArrayList<String> emphasis = new ArrayList<String>();
	private University uni1 = new University("SAINT BENS", "MINNESOTA", "RURAL", "PRIVATE", 3000, 1.00, 0.70, 0.70, 40000.00, 0.60, 1000, 0.80, 0.50, 4, 4, 3, emphasis);
	private University uni2 = new University("UNIVERSITY TWO");
	private University uni3 = new University("UNIVERSITY THREE");
	private ArrayList<University> savedSchools = new ArrayList<University>();
	
	@Before
	public void setup()
	{
		this.emphasis.add("MATH");
		this.savedSchools.add(this.uni1);
		this.savedSchools.add(this.uni2);
		this.user1 = new User("dannyf", "Danny", "Fritz", "password", 'u', 'Y', savedSchools);
	}
	
	@Test
	public void testGetSavedUniversities() {
		assertTrue(user1.getSavedUniversities().equals(savedSchools));
	}

	@Test
	public void testAddUniversities() {
		this.user1.addUniversities(uni3);
		assertTrue(this.savedSchools.contains(uni3));
	}

	@Test
	public void testRemoveSchool() {
		this.user1.removeSchool(uni3);
		assertFalse(this.savedSchools.contains(uni3));
	}

	@Test
	public void testViewSchool() {
		assertTrue(user1.viewSchool(uni1).equals("Name: UNIVERSITY ONE\nState: null\nLocation: null\nControl: null\nNumber of Students: 0\nPercent Female: 0.0\nSAT Verbal: 0.0\nSAT Math: 0.0\nExpenses: 0.0\nPercent FinancialAid: 0.0\nNumber of Applicants: 0\nPercent Enrolled: 0.0\nAcademic Scale: 0\nSocial Scale: 0\nQuality of Life Scale: 0\nEmphasis: null"));
	}

	@Test
	public void testViewSchoolWRec() {
		System.out.println(this.user1.viewSchoolWRec(uni1));
	}

}
