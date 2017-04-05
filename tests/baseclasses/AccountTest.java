package baseclasses;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class AccountTest {
	public Account user1, user2, user3;
	@Before
	public void setup()
	{
		user1 = new Account("dannyf", "Danny", "Fritz", "password", 'u', 'Y');
		user2 = new Account("dannyf", "Danny", "Fritz", "password", 'u', 'Y');
		user3 = new Account("dannyd", "Danny", "Fritz", "password", 'u', 'Y');
	}
	
	@Test
	public void testAccountEqualMethod() {
		assertTrue(user1.equals(user2));
	}
	
	@Test
	public void testAccountNotEqualMethod() {
		assertFalse(user1.equals(user3));
	}
}