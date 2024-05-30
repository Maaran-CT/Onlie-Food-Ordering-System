package Controller;

import Model.*;
import View.*;

import java.util.ArrayList;
import java.util.List;

import Main.Main;

public class Admin {
	AdminView adminView=new AdminView();
	LaborDB laborDB=new LaborDB();
	DistanceDB distanceDB=new DistanceDB();
	DeliverDB deliverDB=new DeliverDB();
	MenuDB menuDB=new MenuDB();
	public List<List<String>> displayItem() {
		return menuDB.printMenu();
	}
	public void addItem(String itemName,int cost,String time) {
		if(menuDB.addItem(itemName, cost, time)) {
			adminView.addItemSuccess();
		}
		else {
			adminView.addItemError();
		}
	}
	public void deleteItem(String name) {
		if(menuDB.deleteItem(name)) {
			adminView.deleteItemSuccess();
		}
		else {
			adminView.deleteItemError();
		}
	}
	public void openCloseShop(boolean open){
		Main.shopOpen=open;
	}
	public void changeItemCost(String name,int cost,String time) {
		if(menuDB.updateItem(name, cost, time)) {
			adminView.updateItemSucessfully();
		}
		else {
			adminView.deleteItemError();
		}
	}
	
	
	
	
	//chef details
	public void addlabor(String name, String phone, boolean working, boolean present, String busy) {
		if(laborDB.addLabor( name, phone, working,present,busy)) {
			adminView.addChefSuccess();
			
		}
		else {
			adminView.addChefError();
		}
	}
	public void deleteLabor(int laborID) {
		if(laborDB.removeLabor(laborID)) {
			adminView.deleteChefSuccess();
		}
		else {
			adminView.deleteChefError();
		}
	}
	public void setLaborWorking(int id,boolean status) {
		if(laborDB.setLaborWorkingStatus(id, status)) {
			adminView.updateChefSucessfully();
		}
		else {
			adminView.deleteChefError();
		}
	}
	public void setLaborPresent(int id,boolean status) {
		if(laborDB.setLaborPresentStatus(id, status)) {
			adminView.updateChefSucessfully();
		}
		else {
			adminView.deleteChefError();
		}
	}
	public void setLaborstatus(int id,String busy) {
		if(laborDB.setLaborBusyStatus(id, busy)) {
			adminView.updateChefSucessfully();
		}
		else {
			adminView.deleteChefError();
		}
	}
	public List<List<String>> diplayChef() {
		return laborDB.printAllLabor();
	}
	//delivey details
	public void addDelivery(String name, String phone, boolean working, boolean present, String busy) {
	    if(DeliverDB.addDelivery(name, phone, working, present, busy)) {
	        adminView.addChefSuccess();
	    } else {
	        adminView.addChefError();
	    }
	}

	public void deleteDelivery(int deliveryID) {
	    if(DeliverDB.removeDelivery(deliveryID)) {
	        adminView.deleteChefSuccess();
	    } else {
	        adminView.deleteChefError();
	    }
	}

	public void setDeliveryWorking(int id, boolean status) {
	    if(DeliverDB.setDeliveryWorkingStatus(id, status)) {
	        adminView.updateChefSucessfully();
	    } else {
	        adminView.deleteChefError();
	    }
	}

	public void setDeliveryPresent(int id, boolean status) {
	    if(DeliverDB.setDeliveryPresentStatus(id, status)) {
	        adminView.updateChefSucessfully();
	    } else {
	        adminView.deleteChefError();
	    }
	}

	public void setDeliveryStatus(int id, String busy) {
	    if(DeliverDB.setDeliveryBusyStatus(id, busy)) {
	        adminView.updateChefSucessfully();
	    } else {
	        adminView.deleteChefError();
	    }
	}
	
	public List<List<String>> displayDelivery() {
	    return DeliverDB.printAllDelivery();
	}
	
	public ArrayList<ArrayList<String>> displayDistance() {
		return distanceDB.printDistanceTable();
	}
	public void addDistance(String pincode,int cost,String Time) {
		
		if(distanceDB.addDistance(pincode, Time, cost)) {
			adminView.addDistanceSuccess();
		}
		else {
			adminView.addDistanceError();
		}
	}
	public void deleteDistance(String pincode) {
		if(distanceDB.deleteDistance(pincode)) {
			adminView.deleteDistanceSuccess();
		}
		else {
			adminView.deleteDistanceError();
		}
	}
	public void updateDistance(String pincode,int cost,String time) {
		if(distanceDB.updateDistance(pincode, time, cost)) {
			adminView.updateDistanceSuccess();
		}
		else {
			adminView.deleteDistanceError();
		}
	}
	
	
	
}
