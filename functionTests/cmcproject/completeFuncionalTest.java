package cmcproject;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import baseclasses.Account;
import baseclasses.University;
import controllers.DBController;
import interfaces.AdminInterface;
import interfaces.UserInterface;

public class completeFuncionalTest {
	
	UserInterface ui = new UserInterface();
	AdminInterface ai = new AdminInterface();
	static Account user2 = new Account("user2", "John", "Doe", "user", 'u', 'Y');
	static Account user3 = new Account("user3", "John", "Doe", "user", 'u', 'Y');
	static University uni1 = new University("SAINT KENS", "SOUTH MANKOTA", "SUBURBAN", "PUBLIC", 3000, 1.00, 0.70, 0.70, 40000.00, 0.60, 1000, 0.80, 0.50, 4, 4, 3, new ArrayList<String>());
	static University uni2 = new University("SAINT BENS", "MINNESOTA", "RURAL", "PRIVATE", 3000, 1.00, 0.70, 0.70, 40000.00, 0.60, 1000, 0.80, 0.50, 4, 4, 3, new ArrayList<String>());

	
	
	@BeforeClass
	public static void setup(){
		DBController.getMaxMinValues();
		DBController.addAccount(user2);
		DBController.addAccount(user3);
		DBController.addUniversity(uni1);
		DBController.addUniversity(uni2);
	}
	
	@AfterClass
	public static void teardown(){
		DBController.removeUser("user2");
		DBController.removeUser("user3");
		DBController.deleteSchool(uni1.getName());
		DBController.deleteSchool(uni2.getName());
	}
	
	@Before
	public void ui(){
		ui = new UserInterface();
	}
	
	@Test
	public void testUC1Login() {
		assertTrue(ui.login("Avgjohnnie", "TommiesSuck"));
		
	}
	
	@Test
	public void testUC10ManageUsers(){
		ArrayList<Account> accounts = ai.manageUsers();
		assertTrue(accounts.contains(user2) && accounts.contains(user3));
	}

}
