package controllers;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import baseclasses.Account;
import baseclasses.University;

public class DBControllerTest {
	static University columbiaClone, columbiaTwo, columbiaClone2;
	static Account user1, user2, user3;
	@BeforeClass
	public static void setup(){
		DBController.getMaxMinValues();
		columbiaClone = DBController.getUniversity("COLUMBIA");
		columbiaClone2 = DBController.getUniversity("COLUMBIA");
		columbiaClone2.setLocation("SOMEWHERENICE");
		columbiaTwo = new University("COLUMBIA2", "NEW YORK", "URBAN", "PRIVATE", 10000, 30.0, 625.0, 650, 35196.0, 60.0, 5500, 30.0, 50.0, 1, 3, 5, null);
		user1 = new Account("user1", "John", "Doe2", "user", 'u', 'Y');
		DBController.saveSchool("user1", "COLUMBIA");
		user2 = new Account("user2", "John", "Doe", "user", 'u', 'Y');
		user3 = new Account("user3", "John", "Doe", "user", 'u', 'Y');
		DBController.addAccount(new Account("user1", "John", "Doe", "user", 'u', 'Y'));

		DBController.addAccount(user3);
	}
	
	@AfterClass
	public static void teardown(){
		DBController.removeUser("user1");
		DBController.removeUser("user2");
		DBController.removeSchool("user3", "COLUMBIA");
		DBController.removeUser("user3");
	}
	@Test
	public void testGetUniversity() {
		assertEquals(columbiaClone, DBController.getUniversity("COLUMBIA"));
	}
	@Test
	public void testEmphasis(){
		assertFalse(DBController.getUniversity("COLUMBIA").getEmphasis().isEmpty());
	}
	@Test
	public void testGetUniversityFails() {
		assertEquals(DBController.getUniversity(""), null);
		assertEquals(DBController.getUniversity("notaschool"), null);
	}

	@Test
	public void testAddUniversity() {
		DBController.addUniversity(columbiaTwo);
		assertTrue(DBController.getUniversities().contains(columbiaTwo));
	}
	
	@Test
	public void testAddUniversityFailsWhenUniversityIsAlreadyThere() {
		assertFalse(DBController.addUniversity(columbiaClone));

	}

	@Test
	public void testEditUniversity() {
		DBController.editUniversity(columbiaClone2);
		assertEquals(columbiaClone2, DBController.getUniversity("COLUMBIA"));
		DBController.editUniversity(columbiaClone);
		assertEquals(columbiaClone, DBController.getUniversity("COLUMBIA"));
	}

	//@Test
	public void testGetUniversities() {
		assertFalse(DBController.getUniversities().isEmpty());
	}

	@Test
	public void testUpdateUser() {
		DBController.updateUser(user1);
		assertEquals(user1.equals(DBController.getAccount("user1")), true);
	}

	@Test
	public void testGetAccounts() {
		assertFalse(DBController.getUniversities().isEmpty());
	}

	@Test
	public void testAddAccount() {
		assertEquals(true, DBController.addAccount(user2));
	}

	@Test
	public void testGetAccount() {
		assertEquals(user3.equals(DBController.getAccount("user3")), true);
	}
	@Test
	public void testGetAccountFails() {
		assertEquals(DBController.getAccount("userikul3"), null);
	}

	@Test
	public void testRemoveSchool() {
		assertEquals(true, DBController.removeSchool("user1", "COLUMBIA"));
	}

	@Test
	public void testSaveSchool() {
		assertTrue(DBController.saveSchool("user3", "COLUMBIA"));
	}
	@Test
	public void testSaveSchoolFails() {
		assertFalse(DBController.saveSchool("user3", "COLUMBI434A"));
	}
	
}
