package controllers;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import baseclasses.University;

public class SearchControllerTest {
	SearchController search;
	static University test;
	
	@BeforeClass
	public static void setup(){
		DBController.getMaxMinValues();
		test = new University("TEST", "MINNESOTA", "URBAN", "PRIVATE", 5000, 50, 250, 250, 10000, 80, 2500, 60, 90, 2, 2, 2, null);
		DBController.addUniversity(test);
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
	}
	
//	@Test
//	public void testFindRelatedUniversities() {
//		fail("Not yet implemented");
//	}
//
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
		search.search(null, null, null, null, -1, -1, -1, -1, 249, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchMathPassUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, 250, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchMathPassMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, 249, 251, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchMathFailLower() {
		search.search(null, null, null, null, -1, -1, -1, -1, 251, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	
	@Test
	public void testSearchMathFailUpper() {
		search.search(null, null, null, null, -1, -1, -1, -1, -1, 240, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}
	@Test
	public void testSearchMathFailMid() {
		search.search(null, null, null, null, -1, -1, -1, -1, 200, 249, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertFalse(search.getSearchedUniversities().contains(test));
	}

}
