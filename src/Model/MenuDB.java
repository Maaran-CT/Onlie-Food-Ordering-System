package Model;
import java.sql.Connection;import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import Controller.*;
public class MenuDB {
    static DBconnect db = new DBconnect();
    static Connection connection = db.getConnection();
//    static Admin admin=new Admin();
    static int id=getId()+1;
    // Create operation
    public static boolean addItem(String itemName, int cost, String time) {
        try {
            if (itemExists(itemName)) {
//                admin.addMenuError();
                return false;
                
            } else {
                // Item does not exist, add a new item
                String sql = "INSERT INTO menu (id, itemname, cost, time) VALUES (?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                	statement.setInt(1, id++);
                    statement.setString(2, itemName);
                    statement.setInt(3, cost);
                    statement.setString(4, time);
                    statement.executeUpdate();
                }
            }
//            admin.addMenusuccess();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static int getId() {
    	try{
    		String sql="SELECT id FROM menu ORDER BY id desc LIMIT 1";
    			try(PreparedStatement statement=connection.prepareStatement(sql)){
    				try(ResultSet rs=statement.executeQuery(sql)){
    					while(rs.next()) {
    						return rs.getInt("id");
    					}
    				}
    			}
    		}catch(Exception er) {
    		er.printStackTrace();
    	}
    	return id;
    }
    // Read operation
    public static List<List<String>> printMenu() {
    	List<List<String>> alist = new ArrayList<>();
        try {
            String sql = "SELECT * FROM menu";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                    	List<String> al = new ArrayList<>();
                        int id = resultSet.getInt("id");
                        String itemName = resultSet.getString("itemname");
                        int cost = resultSet.getInt("cost");
                        String itemTime = resultSet.getString("time");
                        al.add(String.valueOf(id));al.add(itemName);al.add(String.valueOf(cost));al.add(itemTime);
                        alist.add(al);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }

    // Update operation
    public static boolean updateItem(String itemName, int newCost, String newTime) {
        try {
        	if (!itemExists(itemName)) {
//                admin.deleteMenuError();
                return false;
                
            }
        	else {
	            String sql = "UPDATE menu SET cost = ?, time = ? WHERE itemname = ?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setInt(1, newCost);
	                statement.setString(2, newTime);
	                statement.setString(3, itemName);
	                statement.executeUpdate();
	            }
        	}
//        	admin.changeItemSuccessfull();
        	return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if item exists
    public static boolean itemExists(String itemName) {
        try {
	            String sql = "SELECT * FROM menu WHERE itemname = ?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setString(1, itemName);
	                try (ResultSet resultSet = statement.executeQuery()) {
	                    return resultSet.next();
	                }
	            }
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete operation
    public static boolean deleteItem(String itemName) {
        try {
        	if (!itemExists(itemName)) {
//                admin.deleteMenuError();
        		return false;
                
            }
        	else {
	            String sql = "DELETE FROM menu WHERE itemname = ?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setString(1, itemName);
	                statement.executeUpdate();
	            }
            }
//        	admin.deleteMenusuccess();
        	return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static String getItemNameById(int itemId) {
        try {
            String sql = "SELECT itemname FROM menu WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, itemId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("itemname");
                    } else {
//                        System.out.println("Item with ID " + itemId + " not found.");
                    	  return ("Item with ID " + itemId + " not found.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getItemCostById(int itemId) {
        try {
            String sql = "SELECT cost FROM menu WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, itemId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("cost");
                    } else {
//                        System.out.println("Item with ID " + itemId + " not found.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return a default value or handle the case where cost is not found
        return -1; // Return -1 if cost is not found, you can change this accordingly
    }

    public static String getItemTimeById(int itemId) {
        try {
            String sql = "SELECT time FROM menu WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, itemId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("time");
                    } else {
//                        System.out.println("Item with ID " + itemId + " not found.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
