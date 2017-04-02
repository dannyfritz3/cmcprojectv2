package controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import baseclasses.Account;
import baseclasses.University;

public class AdminFuncControllerTest {
	static University test;
	static University test1;
	static University test2;
	static Account atest;
	static Account atest1;
	static Account atest2;
	@BeforeClass
	public static void setup(){
		DBController.getMaxMinValues();
		test = new University("TEST", "MINNESOTA", "URBAN", "PRIVATE", 5000, 50, 250, 250, 10000, 80, 2500, 60, 90, 2, 2, 2, null);
		test1 = new University("TEST1", "MINNESOTA", "URBAN", "PRIVATE", 5000, 50, 250, 250, 10000, 80, 2500, 60, 90, 1, 2, 2, null);
		test2 = new University("TEST2", "MINNESOTA", "URBAN", "PRIVATE", 5000, 50, 250, 250, 50000, 80, 2500, 60, 90, 2, 2, 2, null);
		atest = new Account("Test","Ethan","Ferry","123qwe",'u','Y');
		atest1 = new Account("Test1","John","Smith","password",'a','Y');
		atest2 = new Account("Test2","Matt","Zent","12345",'u','Y');
		

		DBController.addAccount(atest);
		DBController.addAccount(atest1);

		
		for(Account acc : DBController.getAccounts()){
			System.out.println("\t" + acc.getUsername());
		}

		DBController.addUniversity(test);


		for(University uni : DBController.getUniversities()){
			System.out.println("\t" + uni.getName());
		}
	}
	
	
	@AfterClass
	public static void teardown(){
		DBController.deleteSchool("TEST");
		DBController.deleteSchool("TEST1");
		DBController.deleteSchool("TEST2");
		DBController.removeUser("test");
		DBController.removeUser("test1");
		DBController.removeUser("test2");

	}
	@Test
	public void testAddUniversityPass(){
		assertTrue(AdminFuncController.addUniversity("TEST1", "MINNESOTA", "URBAN", "PRIVATE", 5000, 50, 250, 250, 10000, 80, 2500, 60, 90, 1, 2, 2, null));
	}
	@Test
	public void testAddUniversityFail() {
		assertTrue(!AdminFuncController.addUniversity("TEST", "MINNESOTA", "URBAN", "PRIVATE", 5000, 50, 250, 250, 10000, 80, 2500, 60, 90, 2, 2, 2, null));
	}
	@Test
	public void testViewAllUniversitiesPass() {
		ArrayList<University>test = AdminFuncController.viewAllUniversities();
		assertTrue(test!=null);
	}
	@Test
	public void testEditUniversityPass() {
		assertTrue(AdminFuncController.editUniversity(test));
	}

	@Test
	public void testEditUniversityFail() {
		assertTrue(AdminFuncController.editUniversity(test2));
	}
	
	@Test
	public void testViewUserUser() {
		assertTrue(AdminFuncController.viewUser("Test1").equals(atest1));
	}
	@Test
	public void testViewUserAdmin() {
		assertTrue(AdminFuncController.viewUser("Test2").equals(atest2));
	}
		
	@Test
	public void testDeactivate() {
		AdminFuncController.deactivate("Test1");
		assertTrue(AdminFuncController.viewUser("Test1").getStatus()=='N');
	}
	
	@Test
	public void testAddUserPass() {
		assertTrue(AdminFuncController.addUser("Test2","Ethan","Ferry","123qwe",'u','Y'));
	}
	@Test
	public void testAddUserFail() {
		assertTrue(!AdminFuncController.addUser("Test1","Ethan","Ferry","123qwe",'u','Y'));
	}

	
	@Test
	public void testManageUsers() {
		ArrayList<Account>test = AdminFuncController.manageUsers();
		assertTrue(test!=null);
	}

}
