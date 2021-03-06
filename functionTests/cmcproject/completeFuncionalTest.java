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
	static Account user3 = new Account("user3", "John", "Doe", "user", 'u', 'Y');
	static University uni1 = new University("SAINT KENS", "SOUTH MANKOTA", "SUBURBAN", "PUBLIC", 3000, 1.00, 0.70, 0.70, 40000.00, 0.60, 1000, 0.80, 0.50, 4, 4, 3, new ArrayList<String>());
	static University uni2 = new University("SAINT BENS", "MINNESOTA", "RURAL", "PRIVATE", 3000, 1.00, 0.70, 0.70, 40000.00, 0.60, 1000, 0.80, 0.50, 4, 4, 3, new ArrayList<String>());
	//static User user4 = new User("user4", "Danny", "Fritz", "password", 'u', 'Y', new ArrayList<University>());
	static Account user5 = new Account("admin1", "Admin", "Ad", "password", 'a', 'Y');
	static Account user6 = new Account("user6", "John", "Doe", "user", 'u', 'Y');
	
	@BeforeClass
	public static void setup(){
		DBController.getMaxMinValues();
		DBController.addAccount(user2);
		DBController.addAccount(user1);
		DBController.addUniversity(uni1);
		DBController.addUniversity(uni2);
		DBController.saveSchool("user2", "SAINT KENS");
		DBController.addAccount(user5);
		DBController.addAccount(user6);
	}
	
	@AfterClass
	public static void teardown(){
		DBController.removeUser("user3");
		DBController.removeUser("user1");
		DBController.removeUser("admin1");
		DBController.deleteSchool(uni1.getName());
		DBController.deleteSchool(uni2.getName());
		DBController.removeSchool("user2", "SAINT KENS");
		DBController.removeUser("user2");
		DBController.removeUser("user6");
		
	}
	
	@Before
	public void ui(){
		ui = new UserInterface();
		ai.deactivate(user1);
	}
	
	@Test
	public void testUC1Login() throws Exception {
		assertTrue(ui.login("user2", "user"));
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
		ui.login("user2", "password");		
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

	
	/*
	 * Danny's Tests: UC3, UC4, UC5, and UC12
	 */
	
	@Test
	public void testUC3RemoveSavedSchool() throws Exception
	{
		//user4.addUniversities(uni1);
		//user4.removeSchool(uni1);
		//assertFalse(user4.getSavedUniversities().contains(uni1));
		ui.login("user2", "user");
		assertTrue(ui.removeSchool(uni2));
		
	}
	
	@Test
	public void testUC4ViewSchool()
	{
		assertTrue(ui.viewSchool(uni1).equals("Name: SAINT KENS\nState: SOUTH MANKOTA\nLocation: SUBURBAN\nControl: PUBLIC\nNumber of Students: 3000\nPercent Female: 1.0\nSAT Verbal: 0.7\nSAT Math: 0.7\nExpenses: 40000.0\nPercent FinancialAid: 0.6\nNumber of Applicants: 1000\nPercent Enrolled: 0.5\nPercent Admitted0.8\nAcademic Scale: 4\nSocial Scale: 4\nQuality of Life Scale: 3\nEmphasis: "));
		
		
	}
	
	@Test
	public void testUC5SearchSchools()
	{
		ArrayList<University> searchedSchools = ui.searchSchools(null, "MINNESOTA", null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		assertTrue(searchedSchools!=null);
	}
	
	@Test
	public void testUC12DeactivateAccount()
	{
		ai.deactivate(user5);
		assertTrue(DBController.getAccount("admin1").getStatus() == 'N');
	}
	
	@Test
	public void testUC6Results()
	{
		ArrayList<University> searchedSchools = ui.searchSchools(null, "MINNESOTA", null, null, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, null);
		University u = DBController.getUniversity("UNIVERSITY OF MINNESOTA");
		assertTrue(searchedSchools.contains(u));
	}
	
	@Test
	public void testUC7ViewSchoolsWithRecommendation()
	{
		ArrayList<University> u = ui.viewSchoolWRec(DBController.getUniversity("UNIVERSITY OF MINNESOTA"));
		University uni = DBController.getUniversity("UNIVERSITY OF WASHINGTON");
		assertTrue(u.contains(uni));
	}
	
	@Test
	public void testUC9ViewProfile() throws Exception
	{
		ui.login("user6","user");
		System.out.println(ui.viewProfile());
		assertTrue(ui.viewProfile().equals("First Name: John\nLast Name: Doe\nUsername: user6\nPassword: user\nType: u"));
	}
	
	@Test
	public void testUC13EditUserInfoA()
	{	
		user1.setFirstName("user1");
		user1.setLastName("Smith");
		user1.setPassword("user");
		user1.setStatus('Y');
		user1.setType('a');
		ai.editProfile(user1);
		assertTrue(user1.getFirstName().equals("user1")&& user1.getLastName().equals("Smith")&& user1.getPassword().equals("user") && user1.getStatus()== 'Y' && user1.getType()=='a');
	}
	
	@Test
	public void testUC13EditUserInfoU() throws Exception
	{	
		AdminFuncController.activate("user2");
		ui.login("user2", "user");
		User a = new User("user2", "user2", "Smith", "user", 'u', 'Y',new ArrayList<University>());
		ui.editProfile(a);
		user2 = DBController.getAccount("user2");
		assertTrue(user2.getFirstName().equals("user2") && user2.getLastName().equals("Smith")&& user2.getPassword().equals("user"));
	}
	
	public void testUC15AddUniversity(){
		assertTrue(ai.addUniversity("COLLEGE OF SAINT BENEDICT", "Minnesota", "SMALL-CITY", "PRIVATE", 2000, 100.0, 600.0, 600.0, 52000.00, 80, 3000, 70, 25, 4, 2, 1, new ArrayList<String>()));
		DBController.deleteSchool("COLLEGE OF SAINT BENEDICT");
	}
	
	public void testUC16EditUniversity(){
		University uni = DBController.getUniversity("COLUMBIA");
		uni.setAcademicScale(1);
		ai.editUniversity(uni);
		assertEquals(uni.getAcademicScale(), 1);
		uni.setAcademicScale(5);
		ai.editUniversity(uni);
	}
	
	public void testUC17viewUser(){
		Account a2 = ai.viewUser("Avgjohnnie");
		assertEquals(a2.getUsername(), "Avgjohnnie");
	}
	
	public void testUC18viewAllSchools(){
		assertFalse(ai.viewAllSchools().isEmpty());
	}
}
