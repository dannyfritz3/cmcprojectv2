package drivers;

import java.util.ArrayList;

import baseclasses.Account;
import controllers.AccountController;

public class viewAllUserNames {
	public static void main(String[]args){
	ArrayList<Account> x = AccountController.getAccounts();
	for(Account a : x){
		System.out.println(a.getUsername());
		System.out.println(a.getPassword());
		System.out.println(a.getType());
		}
	}
}
