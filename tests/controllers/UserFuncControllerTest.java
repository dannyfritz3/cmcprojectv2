package controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import baseclasses.*;
import java.util.*;

public class UserFuncControllerTest {

	private University uni1, uni2, uni3;
	private ArrayList<String> emphasis = new ArrayList<String>();
	private ArrayList<University> savedSchools = new ArrayList<University>();
	private UserFuncController ufc;
	private User user = new User("dannyf", "Danny", "Fritz", "password", 'u', 'Y', savedSchools);
	@Before
	public void setup()
	{
		this.emphasis.add("MATH");
		this.uni1 = new University("UNIVERSITY ONE");
		this.uni2 = new University("SAINT BENS", "MINNESOTA", "RURAL", "PRIVATE", 3000, 1.00, 0.70, 0.70, 40000.00, 0.60, 1000, 0.80, 0.50, 4, 4, 3, emphasis);
		this.uni3 = new University("UNIVERSITY THREE");
		this.savedSchools.add(uni1);
		this.savedSchools.add(uni2);
		this.ufc = new UserFuncController(user);
	}

	@Test
	public void testRemoveSchool() {
		this.ufc.removeSchool(this.uni1);
		assertFalse(this.ufc.getSavedSchools().contains(uni1));
	}
	
	@Test
	public void testSaveSchool() {
		this.ufc.saveSchool(uni3);
		assertTrue(this.ufc.getSavedSchools().contains(uni3));
	}

	@Test
	public void testGetSavedSchools() {
		assertTrue(this.ufc.getSavedSchools().equals(savedSchools));
	}

	@Test
	public void testViewProfile() {
		assertTrue(this.ufc.viewProfile().equals("First Name: " + user.getFirstName() + "\nLast Name: " + user.getLastName() + "\nUsername: " + user.getUsername() + "\nPassword: " + user.getPassword() +  "\nType: " + user.getType()));
	}

	@Test
	public void testEditProfile() {
		User newUser = new User("dannyf", "Danny", "Fritz", "password1", 'u', 'Y', savedSchools);
		this.ufc.editProfile(newUser);
		assertTrue(this.user.getPassword().equals("password1"));
	}

}
