package Controller;
import Model.*;
import java.util.*;
import View.*;
import Main.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;
public class Customer {
	static AdminView adminView=new AdminView();
	static LaborDB laborDB=new LaborDB();
	static DistanceDB distanceDB=new DistanceDB();
	static DeliverDB deliverDB=new DeliverDB();
	static MenuDB menuDB=new MenuDB();
	static BillDB billDB=new BillDB();
	static FoodQueueDB foodqueue=new FoodQueueDB();
	static DeliverQueueDB deliverqueue=new DeliverQueueDB();
	
	public ArrayList<ArrayList<String>> viewBills(String phone) {
		return billDB.displayTable(phone);
	}
	public void printMenu() {
		MenuDB.printMenu();
	}
	public void addCart(Cart c,int id,int count) {
		c.addCart(MenuDB.getItemNameById(id), count, MenuDB.getItemCostById(id), MenuDB.getItemTimeById(id));
	}
	public void deletCart(Cart c,int id) {
		c.deleteCartItem(id);
	}
	public void BookOrder(Cart c) throws SQLException {
		String totFoodTime=""+c.totalTIme;
		String totalTime=""+(Integer.valueOf(distanceDB.getDeliveryTimeByPincode(UserDB.getPincode(Main.inid[1])))+c.getTotalTime());
		Bill bill=new Bill(Main.inid[1], c.getAllItem(), DistanceDB.getCostByPincode(UserDB.getPincode(Main.inid[1])), c.getTotalCost(), ""+c.totalTIme, distanceDB.getDeliveryTimeByPincode(UserDB.getPincode(Main.inid[1])), totalTime, c.getTotalCost()+DistanceDB.getCostByPincode(UserDB.getPincode(Main.inid[1])), "waiting");
		billDB.insertBill(bill);
//		billDB.insertBill(Main.inid[1], c.getAllItem(), DistanceDB.getCostByPincode(UserDB.getPincode(Main.inid[1])), c.getTotalCost(), ""+c.totalTIme, distanceDB.getDeliveryTimeByPincode(UserDB.getPincode(Main.inid[1])), totalTime, c.getTotalCost()+DistanceDB.getCostByPincode(UserDB.getPincode(Main.inid[1])), "waiting");
		foodqueue.insertData(billDB.getLastBillId(),totFoodTime);
	}
	public void checkLabor() throws SQLException {
		ArrayList<Integer> lid=new ArrayList<>(laborDB.getLaborIdsWithEmptyBusyStatus());
		for(int i=0;i<lid.size();i++) {
			int billid=laborDB.getBillIdByLaborId(lid.get(i));
			int timeDiff=billDB.getTotalMinutes(Time.valueOf(LocalTime.now()).toString())-billDB.getTotalMinutes(billDB.getBookTime(billid).toString());
//			System.out.println(timeDiff);
			if(Integer.valueOf(billDB.getFoodTime(billid))-timeDiff<=0) {
				foodqueue.deleteData(billid);
				billDB.setBillStatus(billid,"cooked");
				laborDB.setLaborBusyStatus(lid.get(i),"");
				deliverqueue.insertData(billid, billDB.getDeliveryTime(billid));
			}
//			System.out.println("Check Labor time diff:"+timeDiff+" BillID "+billid);
		}
	}
	public void checkDeliver() throws SQLException {
		ArrayList<Integer> did=new ArrayList<>(deliverDB.getDeliveryIdsWithBusyStatus());
		for(int i=0;i<did.size();i++) {
			int billid=deliverDB.getBillIdByDeliveryId(did.get(i));
//			System.out.println("Check Delivery Queue bill ID:"+billid);
			int timeDiff=billDB.getTotalMinutes(Time.valueOf(LocalTime.now()).toString())-billDB.getTotalMinutes(billDB.getBookTime(billid).toString());
			timeDiff+=Integer.valueOf(billDB.getFoodTime(billid).toString());
			System.out.println("Check Deliver time diff:"+timeDiff+" bill id"+billid);
			if(Integer.valueOf(billDB.getDeliveryTime(billid))-timeDiff<=0) {
				billDB.setBillStatus(billid,"delivered");
				deliverDB.setDeliveryBusyStatus(did.get(i),"");
				deliverqueue.deleteData(billid);
			}
		}
	}
	public void pushFoodQueue() throws SQLException{
		ArrayList<Integer> qid=new ArrayList<>(foodqueue.getBillIdsWithEmptyBusyStatus());
		ArrayList<Integer> bid=new ArrayList<>(billDB.getWaitingBillIds());
		bid.retainAll(qid);
		for(int i=0;i<bid.size();i++) {
			int lid=laborDB.getTopIdleLaborId();
			if(lid==-1) {
				break;
			}
			else {
				laborDB.setBillIdByLaborId(lid, bid.get(i));
				laborDB.setLaborBusyStatus(lid, "cooking");
				billDB.setBillStatus(bid.get(i),"cooking");
			}
			
		}
		 ArrayList<Integer> addbid=new ArrayList<>(billDB.getWaitingBillIds());
		 for(int i=0;i<addbid.size();i++) {
			 foodqueue.insertData(addbid.get(i), billDB.getFoodTime(addbid.get(i)));
		 }
		 
	
	}
	public void pushDeliverQueue() throws SQLException{
		ArrayList<Integer> qid=new ArrayList<>(deliverqueue.getBillIdsWithEmptyBusyStatus());
		ArrayList<Integer> bid=new ArrayList<>(billDB.getCookedBillIds());
		bid.retainAll(qid);
		for(int i=0;i<bid.size();i++) {
			System.out.println("Push Delivery Queue bill ID:"+bid.get(i));
			int did=deliverDB.getTopDeliveryId();
			if(did==-1) {
				break;
			}
			else {
				deliverDB.setBillIdByDeliverId(did, bid.get(i));
				deliverDB.setDeliveryBusyStatus(did, "delivering");
				billDB.setBillStatus(bid.get(i),"delivering");
			}
		}
	}
	 
	 
	
}
