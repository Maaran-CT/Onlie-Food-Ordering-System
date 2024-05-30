package Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DistanceDB {
    static DBconnect db = new DBconnect();
    static Connection connection = db.getConnection();

    // Create operation
    public static boolean addDistance(String pincode, String deliveryTime, int cost) {
        try {
            if (distanceExists(pincode)) {
                // Distance already exists, update cost and time
//                updateDistance(pincode, deliveryTime, cost);
            	return false;
            } else {
                // Distance does not exist, add a new distance
                String sql = "INSERT INTO distance (pincode, time, cost) VALUES (?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, pincode);
                    statement.setString(2, deliveryTime);
                    statement.setInt(3, cost);
                    statement.executeUpdate();
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Read operation
    public static ArrayList<ArrayList<String>> printDistanceTable() {
    	ArrayList<ArrayList<String>> alist = new ArrayList<>();
        try {
            String sql = "SELECT * FROM distance";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {


                    while (resultSet.next()) {
                    	ArrayList<String> al =new ArrayList<>();
                        String pincode = resultSet.getString("pincode");
                        String deliveryTime = resultSet.getString("time");
                        int cost = resultSet.getInt("cost");
                        al.add(pincode);al.add(deliveryTime);al.add(String.valueOf(cost));
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
    public static boolean updateDistance(String pincode, String newDeliveryTime, int newCost) {
        try {
        	if(distanceExists(pincode)) {
	            String sql = "UPDATE distance SET time = ?, cost = ? WHERE pincode = ?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setString(1, newDeliveryTime);
	                statement.setInt(2, newCost);
	                statement.setString(3, pincode);
	                statement.executeUpdate();
	            }
	            return true;
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if distance exists
    public static boolean distanceExists(String pincode) {
        try {
            String sql = "SELECT * FROM distance WHERE pincode = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, pincode);
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
    public static boolean deleteDistance(String pincode) {
        try {
        	if(distanceExists(pincode)) {
        		String sql = "DELETE FROM distance WHERE pincode = ?";
            	try (PreparedStatement statement = connection.prepareStatement(sql)) {
            		statement.setString(1, pincode);
            		statement.executeUpdate();
            	}
            	return true;
        	}
        	else {
        		return false;
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static String getDeliveryTimeByPincode(String pincode) {
        try {
            String sql = "SELECT time FROM distance WHERE pincode = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, pincode);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("time");
                    } else {
                        System.out.println("Delivery time for pincode " + pincode + " not found.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int getCostByPincode(String pincode) {
        try {
            String sql = "SELECT cost FROM distance WHERE pincode = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, pincode);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("cost");
                    } else {
                        System.out.println("Cost for pincode " + pincode + " not found.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return a default value or handle the case where cost is not found
        return -1; // Return -1 if cost is not found, you can change this accordingly
    }

}
