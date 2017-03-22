package drivers;

import baseclasses.*;
import interfaces.*;

public class functionalityDriver {
	public static void main(String[] args){
		//System.out.println("Adding User: ");
		AdminInterface AI = new AdminInterface();
		//System.out.println(AI.addUser(new Account("Matthew","Zent","Matthew","Rocks21",'a','Y')));
		
		System.out.println(AI.viewUser("Matthew").getUsername());
		
		System.out.println("Login: ");
		UserInterface UI = new UserInterface();
		if(UI.login("Matthew", "Rocks21"))System.out.println("Successful");		
		if(UI.getAccount().getLoggedIn())System.out.println(UI.getAccount().getUsername() + " is logged in");
		System.out.println("Logout: ");
		if(UI.logout())System.out.println("Successful");
		
	}
}
