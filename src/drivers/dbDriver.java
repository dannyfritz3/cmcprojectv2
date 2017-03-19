package drivers;
import controllers.*;
public class dbDriver {

	
	public static void main(String args[]){
		DBController db = new DBController();
		System.out.println(db.getUniversity("YALE"));
	}
}
