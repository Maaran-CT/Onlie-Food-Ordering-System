package Model;
import java.util.*;
public class Cart {
	 public ArrayList<String> item=new ArrayList<>();
	 public ArrayList<Integer> count=new ArrayList<>();
	 public ArrayList<String> itemTime=new ArrayList<>();
	 public ArrayList<Integer> cost=new ArrayList<>();
	 public int totalCost=0;
	 public int totalTIme=0;
	 public int getTotalCost() {
		 totalCost=0;
		 for(int i=0;i<cost.size();i++) {
			 totalCost+=(cost.get(i)*count.get(i));
		 }
		 return totalCost;
	 }
	 public int getTotalTime() {
		 totalTIme=0;
		 for(int i=0;i<itemTime.size();i++) {
			 totalTIme+=Integer.valueOf(itemTime.get(i));
		 }
		 return totalTIme;
	 }
	 public String getAllItem() {
		 return item.toString();
	 }
//	 public void viewCart() {
//		    System.out.println("Cart Items:");
//		    System.out.println("------------------------------------------");
//		    System.out.printf("%-20s%-10s%-10s%n", "Item", "Count", "Cost");
//		    System.out.println("------------------------------------------");
//		    
//		    for (int i = 0; i < item.size(); i++) {
//		        System.out.printf("%-20s	%-10d%-10d%n", item.get(i), count.get(i), cost.get(i));
//		    }
//		    
//		    System.out.println("------------------------------------------");
//		    System.out.println("Total Cost: " + getTotalCost());
//	}
	 
	 public void addCart(String item,int count,int cost,String time) {
		 this.item.add(item);
		 this.cost.add(cost);
		 this.count.add(count);
		 this.itemTime.add(time);
	 }
	 
	 public void deleteCartItem(int i) {
		 i--;
		 item.remove(i);
	     cost.remove(i);
	     this.count.remove(i);
	     itemTime.remove(i);
	}
	 
}
