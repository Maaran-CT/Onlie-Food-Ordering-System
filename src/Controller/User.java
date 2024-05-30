package Controller;
import java.sql.SQLException;
import Model.*;
import View.*;
import Main.Main;
public class User {
	UserDB l=new UserDB();
	UserView homePage=new UserView();
	AdminView adminView=new AdminView();
	CustomerView customerView=new CustomerView();
//	public void printuserTable() {
//		l.printuserTable();
//	}
	public void checkUser(String phone,String pass) throws SQLException {
		if(l.authenticateUser(phone, pass)) {
			String a[]=l.getStatusAndPhone(phone, pass);
			Main.inid[0]= a[0];
			Main.inid[1]=a[1];
			if(Main.inid[0].equals("admin")) {
//				System.out.println("Welcome "+Main.inid[0]);
				adminView.admin();
			}
			else{
				System.out.println("User");
				customerView.customer();
				homePage.user();
			}
			
		}
		else {
			homePage.userError();
			homePage.user();
		}
	}
	public String getPin(String phone) {
			return UserDB.getPincode(phone);
		
	}
	public void createAcc(String phone,String user,String pass,String address,String pincode) {
		try {
			if(!l.checkphone(phone)) {
				l.createUser(phone, user, pass,address,pincode);
//				homePage.home();
			}
			else {
				homePage.singUpError();
				homePage.user();
			}
		}
		catch(Exception e){
			System.out.print(e);
		}
	}
}
