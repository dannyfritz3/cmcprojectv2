package drivers;
import controllers.*;

import java.util.ArrayList;

import baseclasses.University;

public class dbDriver {

	
		public static void main(String args[]){
		ArrayList<University> a = DBController.getUniversities();
		System.out.println("fe");
		}
}
