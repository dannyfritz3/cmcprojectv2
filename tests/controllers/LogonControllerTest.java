package controllers;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import baseclasses.Account;

public class LogonControllerTest {

	@Rule
	public ExpectedException passFail = ExpectedException.none();
	
	@Rule
	public ExpectedException notExistFail = ExpectedException.none();
	
	@Rule
	public ExpectedException deactivatedFail = ExpectedException.none();
	
	@BeforeClass
	public static void setup(){
		DBController.addAccount(new Account("user1", "John", "Doe", "user", 'u', 'Y'));
		DBController.addAccount(new Account("user2", "John", "Doe", "user", 'u', 'Y'));
		DBController.updateUser(new Account("user2", "John", "Doe", "user", 'u', 'N'));
		DBController.addAccount(new Account("admin1", "John", "Doe", "admin", 'a', 'Y'));
		DBController.addAccount(new Account("admin2", "John", "Doe", "admin", 'a', 'Y'));
		DBController.updateUser(new Account("admin2", "John", "Doe", "admin", 'a', 'N'));
	}
	
	@AfterClass
	public static void teardown(){
		DBController.removeUser("user1");
		DBController.removeUser("user2");
		DBController.removeUser("admin1");
		DBController.removeUser("admin2");
	}
	
	@Test
	public void testLoginUser() throws Exception {
		Account a;
		a = LogonController.login("user1", "user");
		assertTrue(a.getLoggedIn());
		
	}
	
	@Test
	public void testLoginUserPasswordFail() throws Exception{
		passFail.expect(Exception.class);
		passFail.expectMessage("Password does not match this user");
		Account a;
		a = LogonController.login("user1", "password");
		
	}
	
	@Test
	public void testLoginUserNotExist() throws Exception {
		notExistFail.expect(Exception.class);
		notExistFail.expectMessage("This account does not exsist");
		Account a;
		a = LogonController.login("user3", "password");

	}
	
	@Test
	public void testLoginUserDeactivated() throws Exception{
		deactivatedFail.expect(Exception.class);
		deactivatedFail.expectMessage("This account is deactivated");
		Account a;
		a = LogonController.login("user2", "user");
		
	}
	
	@Test
	public void testLoginAdmin() throws Exception {
		Account a;
		a = LogonController.login("admin1", "admin");
		assertTrue(a.getLoggedIn());
		
	}

	@Test
	public void testLoginAdminPasswordFail() throws Exception{
		passFail.expect(Exception.class);
		passFail.expectMessage("Password does not match this user");
		Account a;
		a = LogonController.login("admin1", "password");
		
	}
	
	@Test
	public void testLoginAdminNotExist() throws Exception{
		notExistFail.expect(Exception.class);
		notExistFail.expectMessage("This account does not exsist");
		Account a;
		a = LogonController.login("admin3", "password");
		
	}
	
	@Test
	public void testLoginAdminDeactivated() throws Exception{
		deactivatedFail.expect(Exception.class);
		deactivatedFail.expectMessage("This account is deactivated");
		Account a;
		a = LogonController.login("admin2", "admin");
		
	}
	
	@Test
	public void testLogoutUser() throws Exception {
		Account a;
		a = LogonController.login("user1", "user");
		LogonController.logout(a);
		assertFalse(a.getLoggedIn());
	}

	@Test
	public void testLogoutAdmin() throws Exception {
		Account a;
		a = LogonController.login("admin1", "admin");
		LogonController.logout(a);
		assertFalse(a.getLoggedIn());
	}
}
