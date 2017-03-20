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
		UI.login("Matthew", "Rocks21");

		
	}
}
