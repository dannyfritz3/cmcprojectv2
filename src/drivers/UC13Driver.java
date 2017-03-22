package drivers;
import baseclasses.*;
import interfaces.*;

public class UC13Driver{
	


	public static void main(String[] args){
			AdminInterface AI = new AdminInterface();
		
			Account a = AI.viewUser("Matthew");
			
			System.out.println("Username: " + a.getUsername() + '\n' + "Firstname: " + a.getFirstName() + '\n' + "Lastname: " + a.getLastName() + '\n' + "Password: " + a.getPassword() + '\n' + "Type: " + a.getType() + '\n' + "Status: " + a.getStatus() + '\n');
			
			a.setFirstName("John");
			a.setPassword("123qwe");
			a.setLastName("Smith");
			a.setType('u');
			a.setStatus('N');
			
			AI.editProfile(a);
			
			a = AI.viewUser("Matthew");
			System.out.println("Username: " + a.getUsername() + '\n' + "Firstname: " + a.getFirstName() + '\n' + "Lastname: " + a.getLastName() + '\n' + "Password: " + a.getPassword() + '\n' + "Type: " + a.getType() + '\n' + "Status: " + a.getStatus() + '\n');
			
			a.setPassword("Rocks21");
			a.setLastName("Zent");
			a.setType('a');
			a.setStatus('Y');
			a.setFirstName("Matthew");
			
			AI.editProfile(a);
			System.out.println("Username: " + a.getUsername() + '\n' + "Firstname: " + a.getFirstName() + '\n' + "Lastname: " + a.getLastName() + '\n' + "Password: " + a.getPassword() + '\n' + "Type: " + a.getType() + '\n' + "Status: " + a.getStatus() + '\n');
			
	}
}



