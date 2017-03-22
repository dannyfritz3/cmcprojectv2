package drivers;

import interfaces.*;
import baseclasses.*;
import java.util.*;

public class UC3Driver {

	public static void main(String[] args) {
		UserInterface ui = new UserInterface();
		University dannyUniversity = new University("Danny University");
		ArrayList<University> savedSchools = new ArrayList<University>();
		savedSchools.add(dannyUniversity);
		
		User danny = new User("dannyf", "Danny", "Fritz", "password", 'u', 'Y', savedSchools);
		savedSchools = danny.getSavedUniversities();
		System.out.println("Saved Universities:");
		for(University uni : savedSchools)
		{
			System.out.println(uni.getName());
		}
		
		if(danny.removeSchool(dannyUniversity))
		{
			System.out.println("School removed");
		}
		else
		{
			System.out.println("School failed to remove");
		}
		
		System.out.println("Saved Universities:");
		for(University uni : savedSchools)
		{
			System.out.println(uni.getName());
		}
	}
}
