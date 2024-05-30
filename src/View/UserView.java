package View;
import java.sql.SQLException;
import java.util.Scanner;
import Controller.*;
import Main.Main;
import Model.Cart;
public class UserView {
	public static Scanner in=new Scanner(System.in);

	public static User log=new User();
	
	public static void user() throws SQLException {
		int flag=0;
		while(flag!=1) {
			int choice;
			System.out.println("1.Sign In");
			System.out.println("2.Sign Up");
			System.out.println("3.exit");
			System.out.print("Enter Your Choice:");
			choice=in.nextInt();
			switch(choice) {
			case 1:
				signIn();
				break;
			case 2:
				signUp();
				break;
			case 3:
				exit();
				flag=1;
				break;
			default:
				System.out.println("Enter A Valid Choice");
//				User();
				
			}
			System.out.println();
			System.out.println();
		}
	}
	
public static void signIn() throws SQLException {
	System.out.println("Enter Your phone and Password");
	
	System.out.println("phone:");
	String phone=in.next();
	
	System.out.println("PassWord:");
	String PassWord=in.next();
	
	log.checkUser(phone, PassWord);
//	User();
	System.out.println();
	System.out.println();
	
}
public static void userError() {
	System.out.print("Enter Correct phone and Password");
	System.out.println();
	System.out.println();
	
}
public static void singUpError() {
	System.out.print("Account Already Exist Give Different phone");
	System.out.println();
	System.out.println();
	
}
//
//public static void home() {
//	System.out.print("Welcome");
//	log.printUserTable();
//}
public static void signUp() {
	System.out.println("Sign Up page");
	System.out.println("Enter Your phone and Password");
	System.out.println("phone:");
	String phone=in.next();
	System.out.println("UserName:");
	String user=in.next();
	System.out.println("PassWord:");
	String PassWord=in.next();
	System.out.println("Enter Your Address");
	in.nextLine();
	String address=in.nextLine();
	System.out.print("Enter Pincode");
	String pincode=in.next();
	log.createAcc(phone, user, PassWord,address,pincode);
	System.out.println();
	System.out.println();
	
}
public static void exit() {
	System.out.print("Thank You");
}

}
