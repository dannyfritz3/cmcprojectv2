package controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import baseclasses.University;

public class SearchControllerTest {
	SearchController search;
	static University test;
	static University test1;
	static University test2;
	static University test3;
	static University test4;
	static University test5;
	@BeforeClass
	public static void setup(){
		DBController.getMaxMinValues();
		test = new University("TEST", "MINNESOTA", "URBAN", "PRIVATE", 5000, 50, 250, 250, 10000, 80, 2500, 60, 90, 2, 2, 2, null);
		test1 = new University("TEST1", "MINNESOTA", "URBAN", "PRIVATE", 5000, 50, 250, 250, 10000, 80, 2500, 60, 90, 1, 2, 2, null);
		test2 = new University("TEST2", "MINNESOTA", "URBAN", "PRIVATE", 5000, 50, 250, 250, 50000, 80, 2500, 60, 90, 2, 2, 2, null);
		test3 = new University("TEST3", "MINNESOTA", "URBAN", "PRIVATE", 5000, 50, 250, 250, 10000, 10, 2500, 60, 90, 2, 2, 2, null);
		test4 = new University("TEST4", "MINNESOTA", "SUBURBAN", "PRIVATE", 5000, 50, 250, 250, 10000, 80, 2500, 60, 90, 2, 2, 2, null);
		test5 = new University("TEST5", "MINNESOTA", "URBAN", "PRIVATE", 5001, 51, 251, 251, 10000, 80, 2500, 60, 90, 2, 2, 2, null);

		DBController.addUniversity(test);
		DBController.addUniversity(test1);
		DBController.addUniversity(test2);
		DBController.addUniversity(test3);
		DBController.addUniversity(test4);
		DBController.addUniversity(test5);
		for(University uni : DBController.getUniversities()){
			System.out.println("\t" + uni.getName());
		}
	}
	
	@Before
	public void searchSetup(){
		search = new SearchController();
	}
	
	@AfterClass
	public static void teardown(){
		DBController.deleteSchool("TEST");
		DBController.deleteSchool("TEST1");
		DBController.deleteSchool("TEST2");
		DBController.deleteSchool("TEST3");
		DBController.deleteSchool("TEST4");
		DBController.deleteSchool("TEST5");
	}
	@Test
	public void testFindRelatedUniversities1() {
		ArrayList<University> unis = search.findRelatedUniversities(test, 6);
		assertTrue(unis.get(0).equals(test) && unis.get(1).equals(test5) && unis.get(2).equals(test1) && unis.get(3).equals(test2) && unis.get(4).equals(test3) && unis.get(5).equals(test4));
	}
	
	
	@Test
	public void testFindNRelatedUniversities1() {
		assertTrue(search.findRelatedUniversities(test, 1).size() == 1);
	}
	@Test
	public void testFindNRelatedUniversities2() {
		assertTrue(search.findRelatedUniversities(test, 2).size() == 2);
	}
	@Test
	public void testFindNRelatedUniversities5() {
		assertTrue(search.findRelatedUniversities(test, 5).size() == 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFindNRelatedUniversitiesNeg() throws IllegalArgumentException {
		search.findRelatedUniversities(test, -1);
	}
	
	

//	@Test
//	public void testGetUniversity() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetSearchedUniversities() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testSearchNamePass() {
		search.search("TEST",null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchNameFail() {
		search.search("TESTTEST",null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().isEmpty());
	}
	
	@Test
	public void testSearchStatePass() {
		search.search(null, "MINNESOTA", null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchStateFail() {
		search.search(null, "ATOSENNIM", null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().isEmpty());
	}
	
	@Test
	public void testSearchLocationPass() {
		search.search(null, null, "URBAN", null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchLocationFail() {
		search.search(null, null, "SUBURBAN", null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchControlPass() {
		search.search(null, null, null, "PRIVATE", -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchControlFail() {
		search.search(null, null, null, "CITY", -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchNumStudentsPassLower() {
		search.search(null, null, null, null, 5000, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchNumStudentsPassUpper() {
		search.search(null, null, null, null, -1, 6000, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchNumStudentsPassMid() {
		search.search(null, null, null, null, 4999, 5001, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchNumStudentsFailLower() {
		search.search(null, null, null, null, 5001, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchNumStudentsFailUpper() {
		search.search(null, null, null, null, -1, 4999, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchNumStudentsFailMid() {
		search.search(null, null, null, null, 6000, 7000, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchPercentFemalePassLower() {
		search.search(null, null, null, null, -1, -1, 50, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchPercentFemalePassUpper() {
		search.search(null, null, null, null, -1, -1, -1, 60, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchPercentFemalePassMid() {
		search.search(null, null, null, null, -1, -1, 49, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchPercentFemaleFailLower() {
		search.search(null, null, null, null, -1, -1, 59, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchPercentFemaleFailUpper() {
		search.search(null, null, null, null, -1, -1, -1, 49, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchPercentFemaleFailMid() {
		search.search(null, null, null, null, -1, -1, 70, 80, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchVerbalPassLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, 249, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchVerbalPassUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, 250, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchVerbalPassMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, 249, 251, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchVerbalFailLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, 251, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchVerbalFailUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, 240, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchVerbalFailMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, 200, 249, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchMathPassLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, 249, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchMathPassUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, 250, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchMathPassMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, 249, 251, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchMathFailLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, 251, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchMathFailUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, 240, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchMathFailMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, 200, 249, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchExpensePassLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, 10000, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchExpensePassUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10001, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchExpensePassMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, 9999, 15000, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchExpenseFailLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, 10001, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchExpenseFailUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, 9000, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchExpenseFailMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, 100, 9999, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}

	@Test
	public void testSearchFinancialAidPassLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 79, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchFinancialAidPassUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 80, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchFinancialAidPassMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 80, 81, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchFinancialAidFailLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 81, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchFinancialAidFailUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 79, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchFinancialAidFailMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 60, 70, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchApplicantsPassLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2499, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchApplicantsPassUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2500, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchApplicantsPassMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2500, 2501, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchApplicantsFailLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2501, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchApplicantsFailUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2499, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchApplicantsFailMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 200, 2000, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchAdmittedPassLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 60, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchAdmittedPassUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 61, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchAdmittedPassMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 59, 60, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchAdmittedFailLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 61, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchAdmittedFailUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 59, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchAdmittedFailMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 70, 78, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchEnrolledPassLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 90, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchEnrolledPassUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 91, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchEnrolledPassMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 89, 90, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchEnrolledFailLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 91, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchEnrolledFailUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 89, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchEnrolledFailMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 60, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchAcademicdPassLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchAcademicPassUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchAcademicPassMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 2, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchAcademicFailLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchAcademicFailUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchacAcademicFailMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, 4, 5, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchSocialPassLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchSocialPassUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchSocialPassMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 2, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchSocialFailLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchSocialFailUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchacSocialFailMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, 4, 5, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchLifePassLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchLifePassUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchLifePassMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 2, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchLifeFailLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchLifeFailUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchacLifeFailMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, 4, 5, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
}
