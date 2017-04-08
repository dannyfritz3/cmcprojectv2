package drivers;
import java.util.ArrayList;

import baseclasses.*;
import interfaces.*;

public class UC13Driver{
	


	public static void main(String[] args) throws Exception{
			AdminInterface AI2 = new AdminInterface();
			System.out.println("Testing UC13 Admin");
			Account a = AI2.viewUser("Avgjohnnie");
			a.setStatus('Y');
			//display user info
			System.out.println("First get of a user\n" + "Username: " + a.getUsername() + '\n' + "Firstname: " + a.getFirstName() + '\n' + "Lastname: " + a.getLastName() + '\n' + "Password: " + a.getPassword() + '\n' + "Type: " + a.getType() + '\n' + "Status: " + a.getStatus() + '\n');
			//change user info
			a.setFirstName("John");a.setPassword("TommiesSuck");a.setLastName("Smith");a.setType('u');a.setStatus('Y');
			//update user info
			AI2.editProfile(a);
			//get userinfo
			a = AI2.viewUser("Matthew");
			System.out.println("Edit of a user\n" + "Username: " + a.getUsername() + '\n' + "Firstname: " + a.getFirstName() + '\n' + "Lastname: " + a.getLastName() + '\n' + "Password: " + a.getPassword() + '\n' + "Type: " + a.getType() + '\n' + "Status: " + a.getStatus() + '\n');
			//set userinfo back
			a.setPassword("Rocks21");a.setLastName("Zent");a.setType('a');a.setStatus('Y');a.setFirstName("Matthew");
			//display user info
			AI2.editProfile(a);
			System.out.println("Second get of a user\n" + "Username: " + a.getUsername() + '\n' + "Firstname: " + a.getFirstName() + '\n' + "Lastname: " + a.getLastName() + '\n' + "Password: " + a.getPassword() + '\n' + "Type: " + a.getType() + '\n' + "Status: " + a.getStatus() + '\n');
			
			System.out.println("Testing UC13 User");
			UserInterface UI2 = new UserInterface();
			if(UI2.login("Avgjohnnie", "TommiesSuck"))
				System.out.println("Login successful\n");		
			//if(UI2.getAccount().getLoggedIn())System.out.println(UI2.getAccount().getUsername() + " is logged in");
			System.out.println(UI2.viewProfile());
			Account acc = UI2.getAccount();
			acc.setLastName("Smith");
			UI2.editProfile(acc);
			System.out.println(UI2.viewProfile());
			acc.setLastName("John");
			acc.setStatus('Y');
			UI2.editProfile(acc);
			System.out.println(UI2.viewProfile());
			
			
			
	}
}



