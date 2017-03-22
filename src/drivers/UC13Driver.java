package drivers;
import baseclasses.*;
import interfaces.*;

public class UC13Driver{
	


	public static void main(String[] args){
			AdminInterface AI = new AdminInterface();
			System.out.println("Testing UC13 Admin");
			Account a = AI.viewUser("Matthew");
			//display user info
			//System.out.println("First get of a user\n" + "Username: " + a.getUsername() + '\n' + "Firstname: " + a.getFirstName() + '\n' + "Lastname: " + a.getLastName() + '\n' + "Password: " + a.getPassword() + '\n' + "Type: " + a.getType() + '\n' + "Status: " + a.getStatus() + '\n');
			//change user info
			a.setFirstName("John");a.setPassword("123qwe");a.setLastName("Smith");a.setType('u');a.setStatus('N');
			//update user info
			AI.editProfile(a);
			//get userinfo
			a = AI.viewUser("Matthew");
			//System.out.println("Edit of a user\n" + "Username: " + a.getUsername() + '\n' + "Firstname: " + a.getFirstName() + '\n' + "Lastname: " + a.getLastName() + '\n' + "Password: " + a.getPassword() + '\n' + "Type: " + a.getType() + '\n' + "Status: " + a.getStatus() + '\n');
			//set userinfo back
			a.setPassword("Rocks21");a.setLastName("Zent");a.setType('a');a.setStatus('Y');a.setFirstName("Matthew");
			//display user info
			AI.editProfile(a);
			//System.out.println("Second get of a user\n" + "Username: " + a.getUsername() + '\n' + "Firstname: " + a.getFirstName() + '\n' + "Lastname: " + a.getLastName() + '\n' + "Password: " + a.getPassword() + '\n' + "Type: " + a.getType() + '\n' + "Status: " + a.getStatus() + '\n');
			
			System.out.println("Testing UC13 User");
			UserInterface UI = new UserInterface();
			if(UI.login("Matthew", "Rocks21"))System.out.println("Login successful\n");		
			if(UI.getAccount().getLoggedIn())System.out.println(UI.getAccount().getUsername() + " is logged in");
			//System.out.println("Username: " + a.getUsername() + '\n' + "Firstname: " + a.getFirstName() + '\n' + "Lastname: " + a.getLastName() + '\n' + "Password: " + a.getPassword() + '\n' + "Type: " + a.getType() + '\n' + "Status: " + a.getStatus() + '\n');
			System.out.println(UI.viewProfile());
			
	}
}



