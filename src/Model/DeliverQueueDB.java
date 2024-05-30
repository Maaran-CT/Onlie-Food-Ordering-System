package Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliverQueueDB {
	static DBconnect db = new DBconnect();
    static Connection connection = db.getConnection();
	 public static void insertData(int billId, String dTime) throws SQLException {
	        String sql = "INSERT INTO deliveryqueue (billid, d_time) VALUES (?, ?)";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, billId);
	            statement.setString(2, dTime);
	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
//	                System.out.println("A new record has been inserted.");
	            }
	            
	        }
	    }
	    
	    // Method to retrieve d_time for a specific billid
	    public static String getDTime(int billId) throws SQLException {
	        String sql = "SELECT d_time FROM deliveryqueue WHERE billid = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, billId);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    return resultSet.getString("d_time");
	                }
	            }
	        }
	        return null;
	    }
	    
	    // Method to retrieve billid for a specific d_time
	    public static int getBillId(String dTime) throws SQLException {
	        String sql = "SELECT billid FROM deliveryqueue WHERE d_time = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, dTime);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    return resultSet.getInt("billid");
	                }
	            }
	        }
	        return -1;
	    }
	    public static List<Integer> getBillIdsWithEmptyBusyStatus() {
	        List<Integer> billIds = new ArrayList<>();
	        try {
	            String sql = "SELECT billid FROM deliveryqueue";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                try (ResultSet resultSet = statement.executeQuery()) {
	                    while (resultSet.next()) {
	                        billIds.add(resultSet.getInt("billid"));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return billIds;
	    }
	    
	    // Method to delete data from deliveryqueue table
	    public static void deleteData(int billId) throws SQLException {
	        String sql = "DELETE FROM deliveryqueue WHERE billid = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, billId);
	            int rowsDeleted = statement.executeUpdate();
	            if (rowsDeleted > 0) {
//	                System.out.println("Record deleted successfully.");
	            }
	        }
	    }
	    public static int getTopBillId() throws SQLException {
	        String sql = "SELECT billid FROM deliveryqueue LIMIT 1";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    return resultSet.getInt("billid");
	                }
	            }
	        }
	        return -1;
	    }

	    // Method to get the top f_time in the table
	    public static String getTopDTime() throws SQLException {
	        String sql = "SELECT d_time FROM deliveryqueue LIMIT 1";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    return resultSet.getString("d_time");
	                }
	            }
	        }
	        return null;
	    }
	    
}
