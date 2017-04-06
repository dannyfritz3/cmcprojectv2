package cmcproject;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import baseclasses.Account;
import baseclasses.University;
import controllers.DBController;
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
	AdminInterface ai = new AdminInterface();
	static Account user2 = new Account("user2", "John", "Doe", "user", 'u', 'Y');
	static Account user1 = new Account("user1", "John", "Doe", "user", 'u', 'Y');
	static Account user3 = new Account("user2", "John", "Doe", "user", 'u', 'Y');
	static University uni1 = new University("SAINT KENS", "SOUTH MANKOTA", "SUBURBAN", "PUBLIC", 3000, 1.00, 0.70, 0.70, 40000.00, 0.60, 1000, 0.80, 0.50, 4, 4, 3, new ArrayList<String>());
	static University uni2 = new University("SAINT BENS", "MINNESOTA", "RURAL", "PRIVATE", 3000, 1.00, 0.70, 0.70, 40000.00, 0.60, 1000, 0.80, 0.50, 4, 4, 3, new ArrayList<String>());

	
	
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

}
