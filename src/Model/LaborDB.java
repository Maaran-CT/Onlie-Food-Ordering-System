package Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaborDB {
    static DBconnect db = new DBconnect();
    static Connection connection = db.getConnection();
//    static Admin admin=new Admin();
    // Create operation
    public static boolean addLabor(String name, String phone, boolean working, boolean present, String busy) {
        try {
            // Get the last labor_id in the table
        	int lastLaborId=getLastLaborId();
            // Add a new labor with the next available ID
            String sql = "INSERT INTO labor (labor_id, l_name, l_phone, l_working, l_present, l_busy) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, lastLaborId + 1);
                statement.setString(2, name);
                statement.setString(3, phone);
                statement.setBoolean(4, working);
                statement.setBoolean(5, present);
                statement.setString(6, busy);
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if labor with specific ID exists
    public static boolean laborExists(int laborId) {
        try {
            String sql = "SELECT * FROM labor WHERE labor_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, laborId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get the last labor_id in the table
    private static int getLastLaborId() {
        int lastLaborId = 0;
        try {
            String sql = "SELECT MAX(labor_id) as last_id FROM labor";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        lastLaborId = resultSet.getInt("last_id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastLaborId;
    }
    public static int getTopIdleLaborId() {
        try {
            String sql = "SELECT labor_id FROM labor WHERE l_busy = '' ORDER BY labor_id ASC LIMIT 1";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("labor_id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if no idle labor found or error occurred
    }
    public static List<Integer> getLaborIdsWithEmptyBusyStatus() {
        List<Integer> laborIds = new ArrayList<>();
        try {
            String sql = "SELECT labor_id FROM labor WHERE l_busy = 'cooking'";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        laborIds.add(resultSet.getInt("labor_id"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return laborIds;
    }
    // Remove labor
    public boolean removeLabor(int laborId) {
        try {
        	if (!laborExists(laborId)) {
        		return false;
        	}
            String sql = "DELETE FROM labor WHERE labor_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, laborId);
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if labor is present
    public static boolean isLaborPresent(int laborId) {
        try {
            String sql = "SELECT l_present FROM labor WHERE labor_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, laborId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next() && resultSet.getBoolean("l_present");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get labor working status
    public static boolean isLaborWorking(int laborId) {
        try {
            String sql = "SELECT l_working FROM labor WHERE labor_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, laborId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next() && resultSet.getBoolean("l_working");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get busy status of labor
    public static String getLaborBusyStatus(int laborId) {
        try {
            String sql = "SELECT l_busy FROM labor WHERE labor_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, laborId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next() ? resultSet.getString("l_busy") : "";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static boolean setLaborPresentStatus(int laborId, boolean present) {
        try {
            if (!laborExists(laborId)) {
                return false;
            }
            String sql = "UPDATE labor SET l_present = ? WHERE labor_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setBoolean(1, present);
                statement.setInt(2, laborId);
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean setLaborBusyStatus(int laborId, String busy) {
        try {
            if (!laborExists(laborId)) {
                return false;
            }
            String sql = "UPDATE labor SET l_busy = ? WHERE labor_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, busy);
                statement.setInt(2, laborId);
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean setLaborWorkingStatus(int laborId, boolean busy) {
        try {
            if (!laborExists(laborId)) {
                return false;
            }
            String sql = "UPDATE labor SET l_working = ? WHERE labor_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setBoolean(1, busy);
                statement.setInt(2, laborId);
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void setBillIdByLaborId(int laborId,int billId) {

        try {
            String sql = "UPDATE labor SET billId = ? WHERE labor_id = ?";            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, billId);
                statement.setInt(2, laborId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        return billId;
    }

    // Print all labor
    public static List<List<String>> printAllLabor() {
    	List<List<String>> alist = new ArrayList<>();
        try {
            String sql = "SELECT * FROM labor";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                    	List<String> al = new ArrayList<>();
                        int laborId = resultSet.getInt("labor_id");
                        String name = resultSet.getString("l_name");
                        String phone = resultSet.getString("l_phone");
                        boolean working = resultSet.getBoolean("l_working");
                        boolean present = resultSet.getBoolean("l_present");
                        String busy = resultSet.getString("l_busy");
                        al.add(String.valueOf(laborId));al.add(name);al.add(phone);al.add(String.valueOf(working));al.add(String.valueOf(present));al.add(busy);
                        alist.add(al);

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alist;
    }
    public static int getBillIdByLaborId(int laborId) {
        int billId = -1; // Initialize with a default value indicating no bill found

        try {
            String sql = "SELECT billId FROM labor WHERE labor_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, laborId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        billId = resultSet.getInt("billId");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billId;
    }
    
 

}
