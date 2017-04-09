package drivers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import baseclasses.Account;
import baseclasses.University;
import controllers.AccountController;
import controllers.DBController;
import interfaces.AdminInterface;
import interfaces.UserInterface;

public class viewAllUserNames {
	public static void main(String[]args){
		Account user1 = new Account("user1", "John", "Doe", "user", 'u', 'Y');
		AdminInterface ai = new AdminInterface();
		user1.setType('u');
		ai.editProfile(user1);
		DBController.removeSchool("user1", "HARVARD");
		DBController.removeSchool("user2", "SAINT BENS");
		
		DBController.removeUser("user2");

	ArrayList<Account> x = AccountController.getAccounts();
	for(Account a : x){
		System.out.println("Username: \t"+ a.getUsername()+ "\t\tFirstname: " + a.getFirstName());
		
		
		//System.out.println(a.getPassword());
		//System.out.println(a.getType());
		}


	//set it back to what it was before the change

	//UserInterface ui = new UserInterface();
	//ArrayList<University> u = ui.viewSchoolWRec(DBController.getUniversity("UNIVERSITY OF MINNESOTA"));
	//for(University z: u){
	//	System.out.println(z.getInformation()+"\n");
	//	}
	}
	
}
