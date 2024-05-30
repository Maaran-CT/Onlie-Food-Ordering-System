package Main;
import java.sql.SQLException;
import java.util.*;
import Controller.*;
import View.*;
public class Main{  
	public static String inid[]= new String[2];
	public static boolean shopOpen=true;
	public static Scanner in=new Scanner(System.in);
	
	public static void main(String args[]) throws SQLException{  
		UserView l=new UserView();
		Customer customer=new Customer();
		
		Timer timer = new Timer();
    TimerTask task = new TimerTask() {
            @Override
    public void run() {
    try {
					customer.pushFoodQueue();
					customer.checkLabor();
					customer.pushDeliverQueue();
					customer.checkDeliver();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
       };
timer.schedule(task, 0, 60 * 1000);

		
		
		int choice=0;
		int f=0;
		while(f==0) {
			System.out.println("1.Sign In");
			System.out.println("2.Sign Up");
			System.out.println("3.exit");
			System.out.print("Enter Your Choice:");
			choice=in.nextInt();
			switch(choice) {
			case 1:
				l.signIn();
				break;
			case 2:
				l.signUp();
				break;
			case 3:
				l.exit();
				timer.cancel();
				f=1;
				break;
			default:
				System.out.print("Enter A Valid Choice");
				l.user();
				
			}
			System.out.println();
			System.out.println();
			System.exit(0);
		}
		
		
	}  

}
//try{  
////Class.forName("com.mysql.cj.jdbc.Driver");  
////Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/test","root","your_password");
////Statement stmt=con.createStatement();  
////ResultSet rs=stmt.executeQuery("select * from test1");  
////while(rs.next())  
////System.out.println(rs.getInt(1)+"  "+rs.getString(2));  
////con.close();  
////}catch(Exception e){ System.out.println(e);}  




























//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Scanner;
//public class Main{
//	public static void main(String args[]) {
////		Scanner in=new Scanner(System.in);
//////		LoginDB l=new LoginDB();
//////		l.read();
////		DBconnect d=new DBconnect();
////		DBconnect.connect();
//		  // JDBC URL, username, and password of MySQL server
//        String jdbcUrl = "jdbc:mysql://localhost:3000/test";
//        String username = "your_username";
//        String password = "your_password";
//
//        // JDBC variables for opening, closing, and managing the database connection
//        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
//            System.out.println("Connected to the database!");
//
//            // You can perform your database operations here
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//	}
//}