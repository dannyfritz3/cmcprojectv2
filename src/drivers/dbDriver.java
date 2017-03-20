package drivers;
import controllers.*;
public class dbDriver {

	
	public static void main(String args[]){
		System.out.println(DBController.getAccount("Matthew").getPassword());
	}
}
