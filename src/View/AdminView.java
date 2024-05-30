package View;
import java.util.*;
import Controller.*;
import Main.Main;
public class AdminView {
	static Scanner in=new Scanner(System.in);
	static UserView log=new UserView();
	static Admin admin=new Admin();
	public void admin() {
		int f=0;
		while(f!=1) {
			System.out.println("Welcome "+Main.inid[0]);
			int choice;
			System.out.println("1.Open/Close Shop");
			System.out.println("2.Chef Details");
			System.out.println("3Delivery Detalis.");
			System.out.println("4.Menu card Details");
			System.out.println("5.Distance Details");
			System.out.println("6.Exit");
			System.out.print("Enter Your Choice:");
			choice=in.nextInt();
			switch(choice) {
			case 1:
				openClose();
			case 2:
				viewChef();
				break;
			case 3:
				viewDelivery();
				break;
			case 4:
				viewItem();
				break;
			case 5:
				viewDistance();
				break;
			case 6:
				f=1;
				break;
			default:
				System.out.print("Enter A Valid Choice");
				
			}
			System.out.println();
			System.out.println();
		}
	}
	
	//------------MENU------------
	public void viewItem() {
		int f=0;
		while(f!=1) {
			System.out.println("Menu card Details");
			List<List<String>> result = admin.displayItem();
            System.out.println("Menu Table:");
            System.out.println("--------------------------------------------------");
            System.out.printf("%-5s%-30s%-10s%-5s%n", "ID", "Item Name", "Cost", "Time");
            System.out.println("--------------------------------------------------");
			Iterator<List<String>> itr = result.iterator();
			while(itr.hasNext()) {
				List<String> al = itr.next();
				System.out.printf("%-5s%-30s%-10s%-5s%n", al.get(0), al.get(1), al.get(2), al.get(3));
			}
			System.out.println("--------------------------------------------------");
			

            System.out.println();
            System.out.println();
            System.out.println();
            
			System.out.println("1.Add an Item");
			System.out.println("2.Remove an item");
			System.out.println("3.Update Item cost and time");
			System.out.println("4.Exit");
			System.out.print("Choose the above option:");
			int choice=in.nextInt();
			in.nextLine();
			switch(choice) {
				case 1:
					addItem();
					break;
				case 2:
					deleteItem();
					break;
				case 3:
					changeItemCostandTime();
					break;
				case 4:
					f=1;
					break;
				default:
					System.out.println("Enter a valid Choice");
//					viewItem();
			}
		}
	}
	
	public void addItem() {
		System.out.print("Enter the Item name:");
        String name = in.nextLine(); 

        System.out.print("Enter item cost:");
        int cost = in.nextInt(); 
        
        System.out.print("Enter item making time:");
        String time = in.next(); 

        admin.addItem(name, cost, time);		
	}
	public void deleteItem() {
		System.out.println("Enter the Item name:");
		String name=in.nextLine();
		admin.deleteItem(name);
	}
	
	public void changeItemCostandTime() {
		System.out.print("Enter item name:");
		String name=in.nextLine();
		System.out.print("Enter item new Cost:");
		int cost=in.nextInt();
		System.out.println("Enter new item preparation time");
		String time=in.next();
		admin.changeItemCost(name, cost, time);
	}
	public void addItemError() {
		System.out.println("Item Already Exist");
	}
	public void deleteItemError() {
		System.out.println("Item Does not exist Exist");
	}
	public void updateItemSucessfully() {
		System.out.println("Item updated  Successfully");
	}
	public void addItemSuccess() {
		System.out.println("Item Added Successfully");
	}
	public void deleteItemSuccess() {
		System.out.println("Item Removed Sucess fully");
	}
	
	
	//chef details
	public void viewChef() {
		int f=0;
		while(f!=1) {
			System.out.println("Chef card Details");
			List<List<String>> result = admin.diplayChef();

            System.out.println("Labor Table:");
            System.out.println("---------------------------------------------------------------");
            System.out.printf("%-10s%-30s%-15s%-15s%-15s%-10s%n", "Labor ID", "Name", "Phone", "Working", "Present", "");
            System.out.println("---------------------------------------------------------------");
            
            Iterator<List<String>> itr = result.iterator();
            while(itr.hasNext()) {
            	List<String> al = itr.next();
                System.out.printf("%-10s%-30s%-15s%-15s%-15s%-10s%n", al.get(0), al.get(1), al.get(2), al.get(3), al.get(4), al.get(5));
            }
            
            System.out.println("---------------------------------------------------------------");
            System.out.println();
			System.out.println("1.Add Chef");
			System.out.println("2.Remove Chef");
			System.out.println("3.Set Chef Working");
			System.out.println("4.Set Chef Present");
			System.out.println("5.Set Chef Busy");
			System.out.println("6.Exit");
			System.out.print("Choose the above option:");
			int choice=in.nextInt();
			in.nextLine();
			switch(choice) {
				case 1:
					addChef();
					break;
				case 2:
					deleteChef();
					break;
				case 3:
					changeChefWorking();
					break;
				case 4:
					changeChefPresent();
					break;
				case 5:
					 ChangeChefBusy();
					break;
				case 6:
					f=1;
					break;
				default:
					System.out.println("Enter a valid Choice");
//					viewItem();
			}
		}
		
	}
	public void addChef() {
		System.out.println("Enter Chef name:");
		String name=in.nextLine();
		System.out.println("Enter chef number:");
		String phone=in.nextLine();
		System.out.println("Enter Chef Working Status:");
		boolean working=in.nextBoolean();
		System.out.println("Enter Chef Present Status:");
		boolean present=in.nextBoolean();
		in.nextLine();
		System.out.println("Enter chef Busy Time:");
		String busy=in.nextLine();
		admin.addlabor(name, phone, working, present, busy);
		
	}
	public void deleteChef() {
		System.out.print("Enter Chef id to Delete:");
		int id=in.nextInt();
		admin.deleteLabor(id);
	}
	public void changeChefWorking() {
		System.out.print("Enter Chef id:");
		int id=in.nextInt();
		System.out.println("Enter Chef Working Status:");
		boolean working=in.nextBoolean();
		admin.setLaborWorking(id, working);
	}
	public void changeChefPresent() {
		System.out.print("Enter Chef id:");
		int id=in.nextInt();
		System.out.println("Enter Chef Present Status:");
		boolean Present=in.nextBoolean();
		admin.setLaborPresent(id, Present);
	}
	public void ChangeChefBusy() {
		System.out.print("Enter Chef id:");
		int id=in.nextInt();
		System.out.println("Enter Chef Busy Time:");
		String busy=in.next();
		admin.setLaborstatus(id, busy);
	}
	
	public void addChefError() {
		System.out.println("Chef Already Exist");
	}
	public void deleteChefError() {
		System.out.println("ChefId Does not exist Exist");
	}
	public void updateChefSucessfully() {
		System.out.println("Chef updated  Successfully");
	}
	public void addChefSuccess() {
		System.out.println("Chef Added Successfully");
	}
	public void deleteChefSuccess() {
		System.out.println("Chef Removed Sucess fully");
	}
	
	
	
//delivery details
	public void viewDelivery() {
	    int f = 0;
	    while (f != 1) {
	        System.out.println("Delivery Details");
	        List<List<String>> result=admin.displayDelivery();
	        Iterator<List<String>> itr = result.iterator();
            System.out.println("Delivery Table:");
            System.out.println("---------------------------------------------------------------");
            System.out.printf("%-12s%-30s%-15s%-15s%-15s%-10s%n", "Delivery ID", "Name", "Phone", "Working", "Present","");
            System.out.println("---------------------------------------------------------------");
	        while(itr.hasNext()) {
	        	List<String> al =itr.next();
                System.out.printf("%-12s%-30s%-15s%-15s%-15s%-10s%n", al.get(0), al.get(1), al.get(2), al.get(3), al.get(4), al.get(5));
	        }
	        System.out.println("---------------------------------------------------------------");
	        System.out.println("1. Add Delivery");
	        System.out.println("2. Remove Delivery");
	        System.out.println("3. Set Delivery Working");
	        System.out.println("4. Set Delivery Present");
	        System.out.println("5. Set Delivery Busy");
	        System.out.println("6. Exit");
	        System.out.print("Choose the above option: ");
	        int choice = in.nextInt();
	        in.nextLine();
	        switch (choice) {
	            case 1:
	                addDelivery();
	                break;
	            case 2:
	                deleteDelivery();
	                break;
	            case 3:
	                changeDeliveryWorking();
	                break;
	            case 4:
	                changeDeliveryPresent();
	                break;
	            case 5:
	                changeDeliveryBusy();
	                break;
	            case 6:
	                f = 1;
	                break;
	            default:
	                System.out.println("Enter a valid Choice");
	        }
	    }
	}

	public void addDelivery() {
	    System.out.println("Enter Delivery name:");
	    String name = in.nextLine();
	    System.out.println("Enter Delivery number:");
	    String phone = in.nextLine();
	    System.out.println("Enter Delivery Working Status:");
	    boolean working = in.nextBoolean();
	    System.out.println("Enter Delivery Present Status:");
	    boolean present = in.nextBoolean();
	    in.nextLine();
	    System.out.println("Enter Delivery Busy Time:");
	    String busy = in.nextLine();
	    admin.addDelivery(name, phone, working, present, busy);
	}

	public void deleteDelivery() {
	    System.out.print("Enter Delivery id to Delete:");
	    int id = in.nextInt();
	    admin.deleteDelivery(id);
	}

	public void changeDeliveryWorking() {
	    System.out.print("Enter Delivery id:");
	    int id = in.nextInt();
	    System.out.println("Enter Delivery Working Status:");
	    boolean working = in.nextBoolean();
	    admin.setDeliveryWorking(id, working);
	}

	public void changeDeliveryPresent() {
	    System.out.print("Enter Delivery id:");
	    int id = in.nextInt();
	    System.out.println("Enter Delivery Present Status:");
	    boolean present = in.nextBoolean();
	    admin.setDeliveryPresent(id, present);
	}

	public void changeDeliveryBusy() {
	    System.out.print("Enter Delivery id:");
	    int id = in.nextInt();
	    System.out.println("Enter Delivery Busy Time:");
	    String busy = in.next();
	    admin.setDeliveryStatus(id, busy);
	}

	public void addDeliveryError() {
	    System.out.println("Delivery Already Exists");
	}

	public void deleteDeliveryError() {
	    System.out.println("Delivery ID Does not exist");
	}

	public void updateDeliverySuccessfully() {
	    System.out.println("Delivery Updated Successfully");
	}

	public void addDeliverySuccess() {
	    System.out.println("Delivery Added Successfully");
	}

	public void deleteDeliverySuccess() {
	    System.out.println("Delivery Removed Successfully");
	}

	
	
	
	
	
	
	
	
	public void viewDistance() {
		int f = 0;
	    while (f != 1) {
	        System.out.println("Delivery Details");
	        ArrayList<ArrayList<String>> result = admin.displayDistance();
	        System.out.println("Distance Table:");
            System.out.println("--------------------------------------------------");
            System.out.printf("%-10s%-10s%-5s%n", "Pincode", "Time", "Cost");
            System.out.println("--------------------------------------------------");
            Iterator<ArrayList<String>> itr = result.iterator();
            while(itr.hasNext()) {
            	ArrayList<String> al = itr.next();
                System.out.printf("%-10s%-10s%-5s%n", al.get(0), al.get(1), al.get(2));
            }
            System.out.println("--------------------------------------------------");
	        System.out.println("1. Add Distance");
	        System.out.println("2. Remove Distance");
	        System.out.println("3. Edit Distance");
	        System.out.println("4. Exit");
	        System.out.print("Choose the above option: ");
	        int choice = in.nextInt();
	        in.nextLine();
	        switch (choice) {
	            case 1:
	                addDistance();
	                break;
	            case 2:
	                deleteDistance();
	                break;
	            case 3:
	                changeDistanceTime();
	                break;
	            case 4:
	                f = 1;
	                break;
	            default:
	                System.out.println("Enter a valid Choice");
	        }
	    }
	}

	
	public void addDistance() {
		System.out.println("Enter Pincode:");
		String pincode=in.nextLine();
		System.out.println("Enter Cost:");
		int cost=in.nextInt();in.nextLine();
		System.out.println("Enter Time:");
		String time=in.nextLine();
		admin.addDistance(pincode, cost, time);
	}
	public void deleteDistance() {
		System.out.println("Enter Pincode:");
		String pincode=in.nextLine();
		admin.deleteDistance(pincode);
	}
	public void changeDistanceTime() {
		System.out.println("Enter Pincode:");
		String pincode=in.nextLine();
		System.out.println("Enter Cost:");
		int cost=in.nextInt();in.nextLine();
		System.out.println("Enter Time:");
		String time=in.nextLine();
		admin.updateDistance(pincode, cost, time);
	}
	public void addDistanceSuccess() {
		System.out.println("Distance Added Successfully");
	}
	public void deleteDistanceSuccess() {
		System.out.println("Distance deleted Successfully");
	}
	public void deleteDistanceError() {
		System.out.println("Pincode Does not Exist");
	}
	public void addDistanceError() {
		System.out.println("Pincode Already Exists");
	}
	public void updateDistanceSuccess() {
		System.out.println("Distance Edited Successfully");
	}
	public void openClose() {
		int f=0;
		while(f!=1) {
			System.out.println("Open/Close Shop");
			System.out.print("Currently The shop Is ");
			System.out.println(Main.shopOpen?"Open":"CLosed");
			System.out.println("1.Open Shop");
			System.out.println("2.Close Shop");
			System.out.println("3.Exit");
			System.out.print("Choose the above option: ");
			int choice=in.nextInt();
			in.nextLine();
			switch(choice) {
				case 1:
					admin.openCloseShop(true);
					break;
				case 2:
					admin.openCloseShop(false);
					break;
				case 3:
					f=1;
					break;
				default:
					System.out.println("Enter a valid Choice");
//					viewItem();
			}
		}
	}
}
