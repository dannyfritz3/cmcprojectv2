package drivers;

import interfaces.*;
import baseclasses.*;
import controllers.*;
import java.util.*;

public class UC12Driver {

	public static void main(String[] args) {
		AdminInterface ai = new AdminInterface();
		AccountController ac = new AccountController();
		ArrayList<Account> accounts = ac.getAccounts();
		
		ai.deactivate(accounts.get(0));
		
		System.out.println(accounts.get(0).getStatus());
	}

}
