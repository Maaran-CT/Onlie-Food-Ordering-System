package Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliverDB {
    static DBconnect db = new DBconnect();
    static Connection connection = db.getConnection();
//    static Admin admin=new Admin();
    
    // Create operation
    public static boolean addDelivery(String name, String phone, boolean working, boolean present, String busy) {
        try {
            // Get the last delivery_id in the table
            int lastDeliveryId = getLastDeliveryId();

            // Add a new delivery with the next available ID
            String sql = "INSERT INTO delivery (delivery_id, d_name, d_phone, d_working, d_present, d_busy) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, lastDeliveryId + 1);
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
    

    // Check if delivery with specific ID exists
    public static boolean deliveryExists(int deliveryId) {
        try {
            String sql = "SELECT * FROM delivery WHERE delivery_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, deliveryId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Integer> getDeliveryIdsWithEmptyBusyStatus() {
        List<Integer> deliveryIds = new ArrayList<>();
        try {
            String sql = "SELECT delivery_id FROM delivery WHERE d_busy = ''";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        deliveryIds.add(resultSet.getInt("delivery_id"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveryIds;
    }
    public static List<Integer> getDeliveryIdsWithBusyStatus() {
        List<Integer> deliveryIds = new ArrayList<>();
        try {
            String sql = "SELECT delivery_id FROM delivery WHERE d_busy = 'delivering' ";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        deliveryIds.add(resultSet.getInt("delivery_id"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveryIds;
    }
    // Get the last delivery_id in the table
    private static int getLastDeliveryId() {
        int lastDeliveryId = 0;
        try {
            String sql = "SELECT MAX(delivery_id) as last_id FROM delivery";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        lastDeliveryId = resultSet.getInt("last_id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastDeliveryId;
    }
    
    public static int getTopDeliveryId() {
        int lastDeliveryId = -1;
        try {
            String sql = "SELECT MIN(delivery_id) as last_id FROM delivery where d_busy='' ";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        lastDeliveryId = resultSet.getInt("last_id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastDeliveryId;
    }
    
    public static void setBillIdByDeliverId(int deliverId,int billId) {

        try {
            String sql = "UPDATE delivery SET billId = ? WHERE delivery_id = ?";            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, billId);
                statement.setInt(2, deliverId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        return billId;
    }

    // Remove delivery
    public static boolean removeDelivery(int deliveryId) {
        try {
            if (!deliveryExists(deliveryId)) {
                return false;
            }
            String sql = "DELETE FROM delivery WHERE delivery_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, deliveryId);
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if delivery is present
    public static boolean isDeliveryPresent(int deliveryId) {
        try {
            String sql = "SELECT d_present FROM delivery WHERE delivery_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, deliveryId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next() && resultSet.getBoolean("d_present");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get delivery working status
    public static boolean isDeliveryWorking(int deliveryId) {
        try {
            String sql = "SELECT d_working FROM delivery WHERE delivery_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, deliveryId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next() && resultSet.getBoolean("d_working");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get busy status of delivery
    public static String getDeliveryBusyStatus(int deliveryId) {
        try {
            String sql = "SELECT d_busy FROM delivery WHERE delivery_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, deliveryId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next() ? resultSet.getString("d_busy") : "";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static int getBillIdByDeliveryId(int deliveryId) {
        try {
            String sql = "SELECT billid FROM delivery WHERE delivery_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, deliveryId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("billid");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if no bill found for the given delivery ID
    }

    // Set delivery present status
    public static boolean setDeliveryPresentStatus(int deliveryId, boolean present) {
        try {
            if (!deliveryExists(deliveryId)) {
                return false;
            }
            String sql = "UPDATE delivery SET d_present = ? WHERE delivery_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setBoolean(1, present);
                statement.setInt(2, deliveryId);
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Set delivery busy status
    public static boolean setDeliveryBusyStatus(int deliveryId, String busy) {
        try {
            if (!deliveryExists(deliveryId)) {
                return false;
            }
            String sql = "UPDATE delivery SET d_busy = ? WHERE delivery_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, busy);
                statement.setInt(2, deliveryId);
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Set delivery working status
    public static boolean setDeliveryWorkingStatus(int deliveryId, boolean working) {
        try {
            if (!deliveryExists(deliveryId)) {
                return false;
            }
            String sql = "UPDATE delivery SET d_working = ? WHERE delivery_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setBoolean(1, working);
                statement.setInt(2, deliveryId);
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    // Print all delivery
    public static List<List<String>> printAllDelivery() {
    	List<List<String>> alist = new ArrayList<>();
        try {
            String sql = "SELECT * FROM delivery";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                    	List<String> al = new ArrayList<>();
                        int deliveryId = resultSet.getInt("delivery_id");
                        String name = resultSet.getString("d_name");
                        String phone = resultSet.getString("d_phone");
                        boolean working = resultSet.getBoolean("d_working");
                        boolean present = resultSet.getBoolean("d_present");
                        String busy = resultSet.getString("d_busy");
                        al.add(String.valueOf(deliveryId));al.add(name);al.add(phone);al.add(String.valueOf(working));al.add(String.valueOf(present));al.add(busy);
                        alist.add(al);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return alist;
    }
}
    
