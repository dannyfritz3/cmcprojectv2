package cmcproject;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import baseclasses.*;
import controllers.AdminFuncController;
import controllers.DBController;
import controllers.SearchController;
import controllers.UserFuncController;
import interfaces.AdminInterface;
import interfaces.UserInterface;

public class completeFuncionalTest {
	
	
	@Rule
	public ExpectedException passFail = ExpectedException.none();
	
	@Rule
	public ExpectedException notExistFail = ExpectedException.none();
	
	@Rule
	public ExpectedException deactivatedFail = ExpectedException.none();
	
	UserInterface ui = new UserInterface();
	static AdminInterface ai = new AdminInterface();
	DBController db = new DBController();
	SearchController sc = new SearchController();
	static Account user2 = new Account("user2", "John", "Doe", "user", 'u', 'Y');
	static Account user1 = new Account("user1", "John", "Doe", "user", 'u', 'Y');
	static Account user3 = new Account("user2", "John", "Doe", "user", 'u', 'Y');
	static University uni1 = new University("SAINT KENS", "SOUTH MANKOTA", "SUBURBAN", "PUBLIC", 3000, 1.00, 0.70, 0.70, 40000.00, 0.60, 1000, 0.80, 0.50, 4, 4, 3, new ArrayList<String>());
	static University uni2 = new University("SAINT BENS", "MINNESOTA", "RURAL", "PRIVATE", 3000, 1.00, 0.70, 0.70, 40000.00, 0.60, 1000, 0.80, 0.50, 4, 4, 3, new ArrayList<String>());
	static User user4 = new User("user3", "Danny", "Fritz", "password", 'u', 'Y', new ArrayList<University>());
	static Account user5 = new Account("admin1", "Admin", "Ad", "password", 'a', 'Y');
	static Account userAct = new Account("admin1", "Admin", "Ad", "password", 'a', 'Y');
	
	@BeforeClass
	public static void setup(){
		DBController.getMaxMinValues();
		DBController.addAccount(user2);
		DBController.addAccount(user1);
		DBController.addUniversity(uni1);
		DBController.addUniversity(uni2);
		DBController.saveSchool("user2", "SAINT KENS");
	}
	
	@AfterClass
	public static void teardown(){
		DBController.removeUser("user3");
		DBController.removeUser("user2");
		DBController.removeUser("user1");
		DBController.deleteSchool(uni1.getName());
		DBController.deleteSchool(uni2.getName());
		DBController.removeSchool("user2", "SAINT KENS");
		ai.editProfile(userAct);
		
	}
	
	@Before
	public void ui(){
		ui = new UserInterface();
		ai.deactivate(user1);
	}
	
	@Test
	public void testUC1Login() throws Exception {
		assertTrue(ui.login("Avgjohnnie", "TommiesSuck"));
	}
	
	@Test
	public void testUC1LoginAlt1InvalidUsername() throws Exception{
		notExistFail.expect(Exception.class);
		notExistFail.expectMessage("This account does not exsist");
		ui.login("Avgbennie", "TommiesSuck");
	}
	
	@Test
	public void testUC1LoginAlt2Password() throws Exception{
		passFail.expect(Exception.class);
		passFail.expectMessage("Password does not match this user");
		ui.login("Avgjohnnie", "password");		
	}
	
	@Test
	public void testUC1LoginAlt3Deactivated() throws Exception{
		deactivatedFail.expect(Exception.class);
		deactivatedFail.expectMessage("This account is deactivated");	
		ui.login("user1", "user");
		
	}
	
	@Test
	public void testUC2ViewSavedSchools() throws Exception{
		ui.login("user2", "user");
		assertTrue(ui.viewSavedSchools().contains(uni1));
	}
	
	@Test
	public void testUC8SaveSchool() throws Exception{
		ui.login("user2", "user");
		ui.saveSchool(uni2);
		assertTrue(ui.viewSavedSchools().contains(uni2));
	}
	
	@Test
	public void testUC11AddUniversity() throws Exception{
		ai.addUser(user3);
		assertTrue(ai.manageUsers().contains(user3));
	}
	
	@Test
	public void testUC14Logout() throws Exception{
		ui.login("user2", "user");
		assertTrue(ui.logout());
	}
	
	@Test
	public void testUC10ManageUsers(){
		ArrayList<Account> accounts = ai.manageUsers();
		assertTrue(accounts.contains(user2) && accounts.contains(user1));
	}

	
	/**
	 * Danny's Tests: UC3, UC4, UC5, and UC12
	 */
	
	@Test
	public void testUC3RemoveSavedSchool()
	{
		user4.addUniversities(uni1);
		user4.removeSchool(uni1);
		assertFalse(user4.getSavedUniversities().contains(uni1));
	}
	
	@Test
	public void testUC4ViewSchool()
	{
		assertTrue(user4.viewSchool(uni1).equals(uni1.getInformation()));
	}
	
	@Test
	public void testUC5SearchSchools()
	{
		ArrayList<University> searchedSchools = ui.searchSchools(null, "MINNESOTA", null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
	}
	
	@Test
	public void testUC12DeactivateAccount()
	{
		ai.deactivate(user5);
		assertTrue(user5.getStatus() == 'N');
	}
	
	@Test
	public void testUC6Results()
	{
		ArrayList<University> searchedSchools = ui.searchSchools(null, "MINNESOTA", null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		University u = DBController.getUniversity("UNIVERSITY OF MINNESOTA");
		assertTrue(searchedSchools.contains(u));
	}
	/*
	@Test
	public void testUC7ViewSchoolsWithRecommendation()
	{
		
	}*/
	
	@Test
	public void testUC9ViewProfile() throws Exception
	{
		AdminFuncController.activate("user2");
		ui.login("user2","user");
		assertTrue(ui.viewProfile().equals("First Name: John\nLast Name: Doe\nUsername: user2\nPassword: user\nType: u"));
	}
	
	@Test
	public void testUC13EditUserInfoA()
	{	
		Account a = user1;
		user1.setFirstName("Jane");
		user1.setLastName("Smith");
		user1.setPassword("12345");
		user1.setStatus('N');
		user1.setType('a');
		ai.editProfile(user1);
		assertEquals(user1.getFirstName(),"Jane");
		assertEquals(user1.getLastName(),"Smith");
		assertEquals(user1.getPassword(),"12345");
		assertEquals(user1.getStatus(),'N');
		assertEquals(user1.getType(),'a');
		//set it back to what it was before the change
		user1 = a;
		ai.editProfile(user1);
		
	}
	
	@Test
	public void testUC13EditUserInfoU() throws Exception
	{	
		ui.login("user2", "user");
		User a = new User("user2", "Jane", "Smith", "steve", 'a', 'N',new ArrayList<University>());
		ui.editProfile(a);
		assertEquals(user2.getFirstName(),"Jane");
		assertEquals(a.getLastName(),"Smith");
		assertEquals(a.getPassword(),"steve");
		assertEquals(a.getType(),'a');
		assertEquals(a.getStatus(),'N');
		//set it back
		Account b = new Account("user2", "John", "Doe", "user", 'u', 'Y');
		user2 = b;
		ai.editProfile(user2);
		
	}
}
