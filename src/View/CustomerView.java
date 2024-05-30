package View;
import java.util.*;
import Controller.*;
import Main.Main;
import Model.Cart;
import java.sql.*;
public class CustomerView {
	Scanner in=new Scanner(System.in);
	Customer customer=new Customer();
	Cart cart=new Cart();
	public void customer() throws SQLException {
		int f=0;
		while(f!=1) {
			System.out.println("Welcome "+Main.inid[0]);
			int choice;
			System.out.println("1.Order Food");
			System.out.println("2.View Cart");
			System.out.println("3.See Orders");
			System.out.println("4.Exit");
			System.out.print("Enter Your Choice:");
			choice=in.nextInt();
			switch(choice) {
			case 1:
				makeOrder();
				break;
			case 2:
				viewCart();
				break;
			case 3:
				viewOrder();
				break;
			case 4:
				f=1;
				break;
			default:
				System.out.print("Enter A Valid Choice");
				
			}
//			in.nextLine();
			System.out.println();
			System.out.println();
		}
	}
	void viewOrder() {
		ArrayList<ArrayList<String>> list=customer.viewBills(Main.inid[1]);
		Iterator<ArrayList<String>> i=list.iterator();
        System.out.println("Bill Table for Login ID: " + Main.inid[1]);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-8s%-10s%-60s%-15s%-15s%-10s%-12s%-10s%-12s%-12s%-12s%n","Bill ID", "Login ID", "Food", "Delivery Cost", "Food Cost", "Food Time", "Delivery Time", "Book Time", "Total Time", "Total Cost", "Status");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
		while(i.hasNext()){
			ArrayList<String> al=i.next();
            System.out.printf("%-8s%-10s%-60s%-15s%-15s%-10s%-12s%-10s%-12s%-12s%-12s%n",al.get(0), al.get(1), al.get(2), al.get(3), al.get(4), al.get(5), al.get(6), al.get(7), al.get(8), al.get(9), al.get(10));
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	void makeOrder() throws SQLException {
		int f=0;
		while(f!=1) {
			System.out.println("Menu");
			customer.printMenu();
			System.out.println("Your Cart");
			displayCart();
			System.out.println("Welcome "+Main.inid[0]);
			int choice;
			System.out.println("1.Add Item");
			System.out.println("2.Remove Item");
			System.out.println("3.Place Order");
			System.out.println("4.Exit");
			System.out.print("Enter Your Choice:");
			choice=in.nextInt();
			switch(choice) {
			case 1:
				addItem();
				break;
			case 2:
				removeItem();
				break;
			case 3:
				book();
				f=1;
				break;
			case 4:
				f=1;
				break;
			default:
				System.out.print("Enter A Valid Choice");
				
			}
			in.nextLine();
			System.out.println();
			System.out.println();
		}
	}
	void addItem() {
		System.out.println("Enter id of the Dish to Add:");
		int id=in.nextInt();
		System.out.println("Enter Count:");
		int count=in.nextInt();
		customer.addCart(cart, id, count);
		
	}
	void removeItem() {
		System.out.println("Enter id of the Dish to delete:");
		int id=in.nextInt();
		customer.deletCart(cart, id);		
	}
	void book() throws SQLException {
		customer.BookOrder(cart);
	}
	void viewCart() throws SQLException{
//		cart.viewCart();
		System.out.println("Cart Items:");
	    System.out.println("------------------------------------------");
	    System.out.printf("%-20s%-10s%-10s%n", "Item", "Count", "Cost");
	    System.out.println("------------------------------------------");
	    
	    for (int i = 0; i < cart.item.size(); i++) {
	        System.out.printf("%-20s	%-10d%-10d%n", cart.item.get(i), cart.count.get(i), cart.cost.get(i));
	    }
	    
	    System.out.println("------------------------------------------");
	    System.out.println("Total Cost: " + cart.getTotalCost());
		System.out.println("1.Book");
		System.out.println("2.Exit");
		System.out.println("Enter Your Choice");
		int n=in.nextInt();
		if(n==1) {
			book();
		}
		else {
			return;
		}
	}
	void displayCart() throws SQLException{
//		cart.viewCart();
		System.out.println("Cart Items:");
	    System.out.println("------------------------------------------");
	    System.out.printf("%-20s%-10s%-10s%n", "Item", "Count", "Cost");
	    System.out.println("------------------------------------------");
	    
	    for (int i = 0; i < cart.item.size(); i++) {
	        System.out.printf("%-20s	%-10d%-10d%n", cart.item.get(i), cart.count.get(i), cart.cost.get(i));
	    }
	    
	    System.out.println("------------------------------------------");
	    System.out.println("Total Cost: " + cart.getTotalCost());

	}
}
