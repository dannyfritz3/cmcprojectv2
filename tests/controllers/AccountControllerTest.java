package controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import baseclasses.Account;
import baseclasses.University;
import interfaces.AdminInterface;

public class AccountControllerTest {
	static Account test;
	static Account test1;
	static Account test2;


	
	@BeforeClass
	public static void setup(){
		DBController.getMaxMinValues();
		test = new Account("Test","Ethan","Ferry","123qwe",'u','Y');
		test1 = new Account("Test1","Ethan","Ferry","123qwe",'u','Y');
		test2 = new Account("Test2","Ethan","Ferry","123qwe",'u','Y');
		

		DBController.addAccount(test);
		DBController.addAccount(test1);

		
		for(Account acc : DBController.getAccounts()){
			System.out.println("\t" + acc.getUsername());
		}
	}
	
	@AfterClass
	public static void teardown(){
		DBController.removeUser("test");
		DBController.removeUser("test1");
		DBController.removeUser("test2");
	}
	

	@Test
	public void testEditUserInfoFirstName(){
		AccountController.editUserInfo(test, "John", null, "Test", null, 'x','x');
		assertTrue(DBController.getAccount("Test").getFirstName().equals("John"));
	}
	
	@Test
	public void testEditUserInfoLastName(){
		AccountController.editUserInfo(test, null, "Smith", "Test", null, 'x','x');
		assertTrue(DBController.getAccount("Test").getLastName().equals("Smith"));
	}
	@Test
	public void testEditUserInfoPassword(){
		AccountController.editUserInfo(test, null, null, "Test", "Password", 'x','x');
		assertTrue(DBController.getAccount("Test").getPassword().equals("Password"));
	}
	@Test
	public void testEditUserInfoTypeUser(){
		AccountController.editUserInfo(test, null, null, "Test", null, 'u','x');
		assertTrue(DBController.getAccount("Test").getType()=='u');
	}
	
	@Test
	public void testEditUserInfoTypeAdmin(){
		AccountController.editUserInfo(test, null, null, "Test", null, 'a','x');
		assertTrue(DBController.getAccount("Test").getType()=='a');
	}
	
	@Test
	public void testEditUserInfoStatusActive(){
		AccountController.editUserInfo(test, null, null, "Test", null, 'x','Y');
		assertTrue(DBController.getAccount("Test").getStatus()=='Y');
	}
	@Test
	public void testEditUserInfoStatusDeactive(){
		AccountController.editUserInfo(test, null, null, "Test", null, 'x','N');
		assertTrue(DBController.getAccount("Test").getStatus()=='N');
	}
	
	@Test
	public void testAddAccountPass() {
		assertTrue(DBController.addAccount(test2));
		
	}

	@Test
	public void testAddAccountFail() {
		assertTrue(DBController.addAccount(test1));
	}
	

	@Test
	public void testGetAccounts() {
		ArrayList<Account> temp = AccountController.getAccounts();
		assertTrue(temp.contains(test));
	}

	@Test
	public void testGetAccountsFail() {
		ArrayList<Account> test = AccountController.getAccounts();
		assertTrue(!test.contains(test2));
	}

	
}
