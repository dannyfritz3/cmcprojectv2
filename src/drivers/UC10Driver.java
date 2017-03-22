package drivers;

import controllers.AdminFuncController;
import baseclasses.Account;
import java.util.*;

public class UC10Driver {
	
	public static void main(String[] args) {
		AdminFuncController afc = new AdminFuncController();
		ArrayList<Account> accounts = afc.manageUsers();;
		
		for(Account acc : accounts)
		{
			System.out.println(acc.getUsername());
		}
	}
}
