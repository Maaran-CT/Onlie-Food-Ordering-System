package Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodQueueDB {
    static DBconnect db = new DBconnect();
    static Connection connection = db.getConnection();

    public static void insertData(int billId, String fTime) throws SQLException {
        String sql = "INSERT INTO foodqueue (billid, f_time) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, billId);
            statement.setString(2, fTime);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
//                System.out.println("A new record has been inserted.");
            }
        }
    }

    // Method to retrieve f_time for a specific billid
    public static String getFTime(int billId) throws SQLException {
        String sql = "SELECT f_time FROM foodqueue WHERE billid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, billId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("f_time");
                }
            }
        }
        return null;
    }
    public static List<Integer> getBillIdsWithEmptyBusyStatus() {
        List<Integer> billIds = new ArrayList<>();
        try {
            String sql = "SELECT billid FROM foodqueue";
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
    // Method to retrieve billid for a specific f_time
    public static int getBillId(String fTime) throws SQLException {
        String sql = "SELECT billid FROM foodqueue WHERE f_time = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, fTime);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("billid");
                }
            }
        }
        return -1;
    }

    // Method to delete data from foodqueue table
    public static void deleteData(int billId) throws SQLException {
        String sql = "DELETE FROM foodqueue WHERE billid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, billId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
//                System.out.println("Record deleted successfully.");
            }
        }
    }
    
    // Method to get the top billid in the table
    public static int getTopBillId() throws SQLException {
        String sql = "SELECT billid FROM foodqueue LIMIT 1";
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
    public static String getTopFTime() throws SQLException {
        String sql = "SELECT f_time FROM foodqueue LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("f_time");
                }
            }
        }
        return null;
    }
}
