package drivers;

import java.util.ArrayList;

import baseclasses.Account;
import baseclasses.University;
import controllers.AccountController;
import controllers.DBController;
import interfaces.UserInterface;

public class viewAllUserNames {
	public static void main(String[]args){
		DBController.removeUser("Test2");
		DBController.removeUser("Test1");
		DBController.removeUser("Test");
	ArrayList<Account> x = AccountController.getAccounts();
	for(Account a : x){
		System.out.println(a.getUsername());
		//System.out.println(a.getPassword());
		//System.out.println(a.getType());
		}
	UserInterface ui = new UserInterface();
	ArrayList<University> u = ui.viewSchoolWRec(DBController.getUniversity("UNIVERSITY OF MINNESOTA"));
	for(University z: u){
		System.out.println(z.getInformation()+"\n");
		}
	}
	
}
