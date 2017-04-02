package baseclasses;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class UniversityTest {

	private University uni1;
	private University uni2;
	private University uni3;
	private University uni4;
	private University uni5;
	private University uni6;
	private University uni7;
	private University uni8;
	private University uni9;
	private ArrayList<String> emphasis1 = new ArrayList<String>();
	private ArrayList<String> emphasis2 = new ArrayList<String>();
	@Before
	public void setup()
	{
		this.emphasis1.add("MATH");
		this.emphasis2.add("EDUCATION");
		this.uni1 = new University("SAINT JOHNS");
		this.uni2 = new University("SAINT BENS", "MINNESOTA", "RURAL", "PRIVATE", 3000, 1.00, 0.70, 0.70, 40000.00, 0.60, 1000, 0.80, 0.50, 4, 4, 3, emphasis1);
		this.uni3 = new University("CONCORDIA", "MINNESOTA", "RURAL", "PRIVATE", 5000, 0.46, 0.75, 0.70, 25000.00, 0.55, 1000, 0.80, 0.50, 3, 2, 4, emphasis2);
	}
	
	@Test
	public void testDefaultConstructor() {
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void testUniversitySecondConstrucotrForIAEAS1() {
		this.uni4 = new University("CONCORDIA", "MINNESOTA", "RURAL", "PRIVATE", 5000, 0.46, 0.75, 0.70, 25000.00, 0.55, 1000, 0.80, 0.50, 0, 2, 4, emphasis1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUniversitySecondConstrucotrForIAEAS2() {
		this.uni7 = new University("CONCORDIA", "MINNESOTA", "RURAL", "PRIVATE", 5000, 0.46, 0.75, 0.70, 25000.00, 0.55, 1000, 0.80, 0.50, 6, 2, 4, emphasis1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUniversitySecondConstrucotrForIAESS1() {
		this.uni5 = new University("CONCORDIA", "MINNESOTA", "RURAL", "PRIVATE", 5000, 0.46, 0.75, 0.70, 25000.00, 0.55, 1000, 0.80, 0.50, 3, 0, 4, emphasis1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUniversitySecondConstrucotrForIAESS2() {
		this.uni8 = new University("CONCORDIA", "MINNESOTA", "RURAL", "PRIVATE", 5000, 0.46, 0.75, 0.70, 25000.00, 0.55, 1000, 0.80, 0.50, 3, 6, 4, emphasis1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUniversitySecondConstrucotrForIAEQOL1() {
		this.uni6 = new University("CONCORDIA", "MINNESOTA", "RURAL", "PRIVATE", 5000, 0.46, 0.75, 0.70, 25000.00, 0.55, 1000, 0.80, 0.50, 3, 2, 0, emphasis1);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testUniversitySecondConstrucotrForIAEQOL2() {
		this.uni9 = new University("CONCORDIA", "MINNESOTA", "RURAL", "PRIVATE", 5000, 0.46, 0.75, 0.70, 25000.00, 0.55, 1000, 0.80, 0.50, 3, 2, 6, emphasis1);
	}

	@Test
	public void testGetName() {
		assertTrue(uni1.getName().equals("SAINT JOHNS"));
		assertTrue(uni2.getName().equals("SAINT BENS"));
	}

	@Test
	public void testGetState() {
		assertTrue(uni2.getState().equals("MINNESOTA"));
	}

	@Test
	public void testGetLocation() {
		assertTrue(uni2.getLocation().equals("RURAL"));
	}

	@Test
	public void testGetControl() {
		assertTrue(uni2.getControl().equals("PRIVATE"));
	}

	@Test
	public void testGetNumberOfStudents() {
		assertTrue(uni2.getNumberOfStudents() == 3000);
	}

	@Test
	public void testGetPercentFemale() {
		assertTrue(uni2.getPercentFemale() == 1.00);
	}

	@Test
	public void testGetSATVerbal() {
		assertTrue(uni2.getSATVerbal() == 0.7);
	}

	@Test
	public void testGetSATMath() {
		assertTrue(uni2.getSATMath() == 0.7);
	}

	@Test
	public void testGetExpenses() {
		assertTrue(uni2.getExpenses() == 40000.00);
	}

	@Test
	public void testGetPercentFinancialAid() {
		assertTrue(uni2.getPercentFinancialAid() == 0.60);
	}

	@Test
	public void testGetNumberOfApplicants() {
		assertTrue(uni2.getNumberOfApplicants() == 1000);
	}

	@Test
	public void testGetPercentAdmitted() {
		assertTrue(uni2.getPercentAdmitted() == 0.80);
	}

	@Test
	public void testGetPercentEnrolled() {
		assertTrue(uni2.getPercentEnrolled() == 0.50);
	}

	@Test
	public void testGetAcademicScale() {
		assertTrue(uni2.getAcademicScale() == 4);
	}

	@Test
	public void testGetSocialScale() {
		assertTrue(uni2.getSocialScale() == 4);
	}

	@Test
	public void testGetQualityOfLifeScale() {
		assertTrue(uni2.getQualityOfLifeScale() == 3);
	}

	@Test
	public void testGetEmphasis() {
		assertTrue(uni2.getEmphasis().contains("MATH"));
	}
	
	@Test
	public void testSetName() {
		this.uni1.setName("SAINT JOHN'S");
		assertTrue(uni1.getName().equals("SAINT JOHN'S"));
	}

	@Test
	public void testSetState() {
		this.uni2.setState("SOUTH DAKOTA");
		assertTrue(uni2.getState().equals("SOUTH DAKOTA"));
	}

	@Test
	public void testSetLocation() {
		this.uni2.setLocation("URBAN");
		assertTrue(uni2.getLocation().equals("URBAN"));
	}

	@Test
	public void testSetControl() {
		this.uni2.setControl("PUBLIC");
		assertTrue(uni2.getControl().equals("PUBLIC"));
	}

	@Test
	public void testSetNumberOfStudents() {
		this.uni2.setNumberOfStudents(5000);
		assertTrue(uni2.getNumberOfStudents() == 5000);
	}

	@Test
	public void testSetPercentFemale() {
		this.uni2.setPercentFemale(0.99);
		assertTrue(uni2.getPercentFemale() == 0.99);
	}

	@Test
	public void testSetSATVerbal() {
		this.uni2.setSATVerbal(0.6);
		assertTrue(uni2.getSATVerbal() == 0.6);
	}

	@Test
	public void testSetSATMath() {
		this.uni2.setSATMath(0.6);
		assertTrue(uni2.getSATMath() == 0.6);
	}

	@Test
	public void testSetExpenses() {
		this.uni2.setExpenses(41000.00);
		assertTrue(uni2.getExpenses() == 41000.00);
	}

	@Test
	public void testSetPercentFinancialAid() {
		this.uni2.setPercentFinancialAid(0.80);
		assertTrue(uni2.getPercentFinancialAid() == 0.80);
	}

	@Test
	public void testSetNumberOfApplicants() {
		this.uni2.setNumberOfApplicants(1500);
		assertTrue(uni2.getNumberOfApplicants() == 1500);
	}

	@Test
	public void testSetPercentAdmitted() {
		this.uni2.setPercentAdmitted(0.85);
		assertTrue(uni2.getPercentAdmitted() == 0.85);
	}

	@Test
	public void testSetPercentEnrolled() {
		this.uni2.setPercentEnrolled(0.60);
		assertTrue(uni2.getPercentEnrolled() == 0.60);
	}

	@Test
	public void testSetAcademicScale() {
		this.uni2.setAcademicScale(3);
		assertTrue(uni2.getAcademicScale() == 3);
	}

	@Test
	public void testSetSocialScale() {
		this.uni2.setSocialScale(3);
		assertTrue(uni2.getSocialScale() == 3);
	}

	@Test
	public void testSetQualityOfLifeScale() {
		this.uni2.setQualityOfLifeScale(4);
		assertTrue(uni2.getQualityOfLifeScale() == 4);
	}

	@Test
	public void testAddEmphasis() {
		this.uni2.addEmphasis("LITERATURE");
		assertTrue(uni2.getEmphasis().contains("LITERATURE"));
	}
}
