package Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDB {
	public static boolean isAdmin=false;
	static DBconnect db=new DBconnect();
	static Connection connection =db.getConnection();
    // Create operation
    public static void createUser(String phone, String username, String password,String address,String pincode) {
        try  {
            String sql = "INSERT INTO user (phone, password,username, status,address,pincode) VALUES (?, ?, ?, ?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, phone);
                statement.setString(3, password);
                statement.setString(2, username);
                statement.setString(4, "user");
                statement.setString(5, address);
                statement.setString(6,pincode );
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Authenticate user based on phone and password
    public static boolean authenticateUser(String phone, String password) {
        try  {
            String sql = "SELECT * FROM user WHERE phone = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, phone);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next(); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
    public static boolean checkphone(String phone) {
        try  {
            String sql = "SELECT * FROM user WHERE phone = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, phone);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next(); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }

    
    public static String[] getStatusAndPhone(String phone, String password) {
        String[] result = new String[2];

        try {
            String sql = "SELECT status, phone FROM user WHERE phone = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, phone);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        result[0] = resultSet.getString("status");
                        result[1] = resultSet.getString("phone");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    public static String getPincode(String phone) {
        String result = "";

        try {
            String sql = "SELECT pincode FROM user WHERE phone = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, phone);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        result= resultSet.getString("pincode");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
