package Model;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Time;
    import java.time.LocalTime;
import java.util.ArrayList;
import java.util.*;
import Controller.*;

    public class BillDB {
        static DBconnect db = new DBconnect();
        static Customer customer=new Customer();
        static Connection connection = db.getConnection();
        static int billId = 0;

        // Function to insert a bill into the database
        public static boolean insertBill(Bill b) {
            try {
                String sql = "INSERT INTO bill (billid, loginid, food, deliverycost, foodcost, foodtime, delivertime, booktime, totaltime, totalcost, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, getLastBillId()+1);
                    statement.setString(2, loginId);
                    statement.setString(3, food);
                    statement.setInt(4, deliveryCost);
                    statement.setInt(5, foodCost);
                    statement.setString(6, foodTime);
                    statement.setString(7, deliveryTime);
                    statement.setTime(8, Time.valueOf(LocalTime.now())); // Book time as current time
                    statement.setString(9, totalTime);
                    statement.setInt(10, totalCost);
                    statement.setString(11, status);
                    statement.executeUpdate();
                }
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false; 
        }
//        public static boolean insertBill(String loginId, String food, int deliveryCost, int foodCost, String foodTime, String deliveryTime, String totalTime, int totalCost, String status) {
//            try {
//                String sql = "INSERT INTO bill (billid, loginid, food, deliverycost, foodcost, foodtime, delivertime, booktime, totaltime, totalcost, status) " +
//                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//                try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                    statement.setInt(1, getLastBillId()+1);
//                    statement.setString(2, loginId);
//                    statement.setString(3, food);
//                    statement.setInt(4, deliveryCost);
//                    statement.setInt(5, foodCost);
//                    statement.setString(6, foodTime);
//                    statement.setString(7, deliveryTime);
//                    statement.setTime(8, Time.valueOf(LocalTime.now())); // Book time as current time
//                    statement.setString(9, totalTime);
//                    statement.setInt(10, totalCost);
//                    statement.setString(11, status);
//                    statement.executeUpdate();
//                }
//                return true;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return false; 
//        }
        public static int getFoodCost(int billId) {
            try {
                String sql = "SELECT foodcost FROM bill WHERE billid = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, billId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            return resultSet.getInt("foodcost");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0; // Return empty string if food time is not found
        }
        
        public static int getDeliveryCost(int billId) {
            try {
                String sql = "SELECT deliverycost FROM bill WHERE billid = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, billId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            return resultSet.getInt("delivertime");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0; // Return empty string if delivery time is not found
        }
        
        // Function to get the bill ID with login ID and status not equal to 'delivered'
        public static int getBillId(String loginId) {
            try {
                String sql = "SELECT billid FROM bill WHERE loginid = ? AND status != 'delivered' AND booktime = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, loginId);
                    statement.setTime(2, Time.valueOf(LocalTime.now())); // Current time
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            return resultSet.getInt("billid");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return -1; // Return -1 if no bill found
        }

        // Function to display the table with login ID in a neat way
        public static ArrayList<ArrayList<String>> displayTable(String loginId) {
        	ArrayList<ArrayList<String>> list=new ArrayList<>();
            try {
                String sql = "SELECT * FROM bill WHERE loginid = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, loginId);
                    try (ResultSet resultSet = statement.executeQuery()) {
//                        System.out.println("Bill Table for Login ID: " + loginId);
//                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
//                        System.out.printf("%-8s%-10s%-60s%-15s%-15s%-10s%-12s%-10s%-12s%-12s%-12s%n",
//                                "Bill ID", "Login ID", "Food", "Delivery Cost", "Food Cost", "Food Time", "Delivery Time", "Book Time", "Total Time", "Total Cost", "Status");
//                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");

                        while (resultSet.next()) {
                        	ArrayList<String> al=new ArrayList<>();
                            int billId = resultSet.getInt("billid");
                            al.add(String.valueOf(billId));
//                            String loginIds = resultSet.getString("loginid");
                            al.add(String.valueOf(loginId));
                            String food = resultSet.getString("food");
                            al.add(String.valueOf(food));
                            int deliveryCost = resultSet.getInt("deliverycost");
                            al.add(String.valueOf(deliveryCost));
                            int foodCost = resultSet.getInt("foodcost");
                            al.add(String.valueOf(foodCost));
                            String foodTime = resultSet.getString("foodtime");
                            al.add(String.valueOf(foodTime));
                            String deliveryTime = resultSet.getString("delivertime");
                            al.add(String.valueOf(deliveryTime));
                            Time bookTime = resultSet.getTime("booktime");
                            al.add(String.valueOf(bookTime));
                            String totalTime = resultSet.getString("totaltime");
                            al.add(String.valueOf(totalTime));
                            int totalCost = resultSet.getInt("totalcost");
                            al.add(String.valueOf(totalCost));
                            String status = resultSet.getString("status");
                            al.add(String.valueOf(status));
                            list.add(al);
//                            System.out.printf("%-8d%-10s%-60s%-15d%-15d%-10s%-12s%-10s%-12s%-12d%-12s%n",
//                                    billId, loginId, food, deliveryCost, foodCost, foodTime, deliveryTime, bookTime.toString(), totalTime, totalCost, status);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }

        // Function to display all bills
//        public static ArrayList<ArrayList<String>> displayAllBills() {
//        	ArrayList<ArrayList<String>> list=new ArrayList<>();
//            try {
//                String sql = "SELECT * FROM bill";
//                try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                    try (ResultSet resultSet = statement.executeQuery()) {
//                        System.out.println("All Bills");
//                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
//                        System.out.printf("%-8s%-10s%-60s%-15s%-15s%-10s%-12s%-10s%-12s%-12s%-12s%n",
//                                "Bill ID", "Login ID", "Food", "Delivery Cost", "Food Cost", "Food Time", "Delivery Time", "Book Time", "Total Time", "Total Cost", "Status");
//                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
//
//                        while (resultSet.next()) {
//                        	ArrayList<String> al=new ArrayList<>();
//                            int billId = resultSet.getInt("billid");
//                            al.add(String.valueOf(billId));
//                            String loginId = resultSet.getString("loginid");
//                            al.add(String.valueOf(loginId));
//                            String food = resultSet.getString("food");
//                            al.add(String.valueOf(food));
//                            int deliveryCost = resultSet.getInt("deliverycost");
//                            al.add(String.valueOf(deliveryCost));
//                            int foodCost = resultSet.getInt("foodcost");
//                            al.add(String.valueOf(foodCost));
//                            String foodTime = resultSet.getString("foodtime");
//                            al.add(String.valueOf(foodTime));
//                            String deliveryTime = resultSet.getString("delivertime");
//                            al.add(String.valueOf(deliveryTime));
//                            Time bookTime = resultSet.getTime("booktime");
//                            al.add(String.valueOf(bookTime));
//                            String totalTime = resultSet.getString("totaltime");
//                            al.add(String.valueOf(totalTime));
//                            int totalCost = resultSet.getInt("totalcost");
//                            al.add(String.valueOf(totalCost));
//                            String status = resultSet.getString("status");
//                            al.add(String.valueOf(status));
//                            list.add(al);
//                            System.out.printf("%-8d%-10s%-60s%-15d%-15d%-10s%-12s%-10s%-12s%-12d%-12s%n",
//                                    billId, loginId, food, deliveryCost, foodCost, foodTime, deliveryTime, bookTime.toString(), totalTime, totalCost, status);
//                        }
//                    }
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return list;
//        }
        public static int getLastBillId() {
            try {
                String sql = "SELECT MAX(billid) AS last_bill_id FROM bill";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            return resultSet.getInt("last_bill_id");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return -1; // Return -1 if there are no bills in the database
        }
        public static int getTotalMinutes(String timeString) {
            // Split the timeString into hours, minutes, and seconds
        	if(timeString.equals("")) {
        		return 0;
        	}
            String[] parts = timeString.split(":");
            
//             Convert hours, minutes, and seconds to integers
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            int seconds = Integer.parseInt(parts[2]);
            
            // Calculate total minutes
            int totalMinutes = hours * 60 + minutes + (seconds > 0 ? 1 : 0); // Add 1 minute if there are remaining seconds
            
            return totalMinutes;
//            return 0;
        }
        
        
        public static String getDeliveryTime(int billId) {
            try {
                String sql = "SELECT delivertime FROM bill WHERE billid = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, billId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            return resultSet.getString("delivertime");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "0"; // Return empty string if delivery time is not found
        }
        
        public static String getFoodTime(int billId) {
            try {
                String sql = "SELECT foodtime FROM bill WHERE billid = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, billId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            return resultSet.getString("foodtime");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "0"; // Return empty string if food time is not found
        }
        public static String getBookTime(int billId) {
            try {
                String sql = "SELECT booktime FROM bill WHERE billid = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, billId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            return resultSet.getTime("booktime").toString();
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ""; // Return empty string if book time is not found
        }
        public static boolean setBillStatus(int billId, String status) {
            try {
                String sql = "UPDATE bill SET status = ? WHERE billid = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, status);
                    statement.setInt(2, billId);
                    statement.executeUpdate();
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        public static ArrayList<Integer> getWaitingBillIds() {
            ArrayList<Integer> waitingBillIds = new ArrayList<>();
            try {
                String sql = "SELECT billid FROM bill WHERE status = 'waiting'";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            waitingBillIds.add(resultSet.getInt("billid"));
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return waitingBillIds;
        }
        public static ArrayList<Integer> getCookedBillIds() {
            ArrayList<Integer> cookedBillIds = new ArrayList<>();
            try {
                String sql = "SELECT billid FROM bill WHERE status = 'cooked'";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            cookedBillIds.add(resultSet.getInt("billid"));
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return cookedBillIds;
        }

    
}
    
  //import java.sql.Connection;
  //import java.sql.PreparedStatement;
  //import java.sql.ResultSet;
  //import java.sql.SQLException;
  //import java.sql.Time;
  //import java.time.LocalTime;
  //
  //public class BillDB {
//      static DBconnect db = new DBconnect();
//      static Customer customer=new Customer();
//      static Connection connection = db.getConnection();
//      static int billId = 0;
  //
//      // Function to insert a bill into the database
//      public static boolean insertBill(String loginId, String food, int deliveryCost, int foodCost, String foodTime, String deliveryTime, String totalTime, int totalCost, String status) {
//          try {
//              String sql = "INSERT INTO bill (billid, loginid, food, deliverycost, foodcost, foodtime, delivertime, booktime, totaltime, totalcost, status) " +
//                      "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//              try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                  statement.setInt(1, ++billId);
//                  statement.setString(2, loginId);
//                  statement.setString(3, food);
//                  statement.setInt(4, deliveryCost);
//                  statement.setInt(5, foodCost);
//                  statement.setString(6, foodTime);
//                  statement.setString(7, deliveryTime);
//                  statement.setTime(8, Time.valueOf(LocalTime.now())); // Book time as current time
//                  statement.setString(9, totalTime);
//                  statement.setInt(10, totalCost);
//                  statement.setString(11, status);
//                  statement.executeUpdate();
//              }
//              return true;
//          } catch (SQLException e) {
//              e.printStackTrace();
//          }
//          return false; 
//      }
  //
//      // Function to get the bill ID with login ID and status not equal to 'delivered'
//      public static int getBillId(String loginId) {
//          try {
//              String sql = "SELECT billid FROM bill WHERE loginid = ? AND status != 'delivered' AND booktime = ?";
//              try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                  statement.setString(1, loginId);
//                  statement.setTime(2, Time.valueOf(LocalTime.now())); // Current time
//                  try (ResultSet resultSet = statement.executeQuery()) {
//                      if (resultSet.next()) {
//                          return resultSet.getInt("billid");
//                      }
//                  }
//              }
//          } catch (SQLException e) {
//              e.printStackTrace();
//          }
//          return -1; // Return -1 if no bill found
//      }
  //
//      // Function to display the table with login ID in a neat way
//      public static void displayTable(String loginId) {
//          try {
//              String sql = "SELECT * FROM bill WHERE loginid = ?";
//              try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                  statement.setString(1, loginId);
//                  try (ResultSet resultSet = statement.executeQuery()) {
//                      System.out.println("Bill Table for Login ID: " + loginId);
//                      System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
//                      System.out.printf("%-8s%-10s%-60s%-15s%-15s%-10s%-12s%-10s%-12s%-12s%-12s%n",
//                              "Bill ID", "Login ID", "Food", "Delivery Cost", "Food Cost", "Food Time", "Delivery Time", "Book Time", "Total Time", "Total Cost", "Status");
//                      System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
  //
//                      while (resultSet.next()) {
//                          int billId = resultSet.getInt("billid");
//                          String food = resultSet.getString("food");
//                          int deliveryCost = resultSet.getInt("deliverycost");
//                          int foodCost = resultSet.getInt("foodcost");
//                          String foodTime = resultSet.getString("foodtime");
//                          String deliveryTime = resultSet.getString("delivertime");
//                          Time bookTime = resultSet.getTime("booktime");
//                          String totalTime = resultSet.getString("totaltime");
//                          int totalCost = resultSet.getInt("totalcost");
//                          String status = resultSet.getString("status");
  //
//                          System.out.printf("%-8d%-10s%-60s%-15d%-15d%-10s%-12s%-10s%-12s%-12d%-12s%n",
//                                  billId, loginId, food, deliveryCost, foodCost, foodTime, deliveryTime, bookTime.toString(), totalTime, totalCost, status);
//                      }
//                  }
//              }
//          } catch (SQLException e) {
//              e.printStackTrace();
//          }
//      }
  //
//      // Function to display all bills
//      public static void displayAllBills() {
//          try {
//              String sql = "SELECT * FROM bill";
//              try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                  try (ResultSet resultSet = statement.executeQuery()) {
//                      System.out.println("All Bills");
//                      System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
//                      System.out.printf("%-8s%-10s%-60s%-15s%-15s%-10s%-12s%-10s%-12s%-12s%-12s%n",
//                              "Bill ID", "Login ID", "Food", "Delivery Cost", "Food Cost", "Food Time", "Delivery Time", "Book Time", "Total Time", "Total Cost", "Status");
//                      System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
  //
//                      while (resultSet.next()) {
//                          int billId = resultSet.getInt("billid");
//                          String loginId = resultSet.getString("loginid");
//                          String food = resultSet.getString("food");
//                          int deliveryCost = resultSet.getInt("deliverycost");
//                          int foodCost = resultSet.getInt("foodcost");
//                          String foodTime = resultSet.getString("foodtime");
//                          String deliveryTime = resultSet.getString("delivertime");
//                          Time bookTime = resultSet.getTime("booktime");
//                          String totalTime = resultSet.getString("totaltime");
//                          int totalCost = resultSet.getInt("totalcost");
//                          String status = resultSet.getString("status");
  //
//                          System.out.printf("%-8d%-10s%-60s%-15d%-15d%-10s%-12s%-10s%-12s%-12d%-12s%n",
//                                  billId, loginId, food, deliveryCost, foodCost, foodTime, deliveryTime, bookTime.toString(), totalTime, totalCost, status);
//                      }
//                  }
//              }
//          } catch (SQLException e) {
//              e.printStackTrace();
//          }
//      }
//      public static int getLastBillId() {
//          try {
//              String sql = "SELECT MAX(billid) AS last_bill_id FROM bill";
//              try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                  try (ResultSet resultSet = statement.executeQuery()) {
//                      if (resultSet.next()) {
//                          return resultSet.getInt("last_bill_id");
//                      }
//                  }
//              }
//          } catch (SQLException e) {
//              e.printStackTrace();
//          }
//          return -1; // Return -1 if there are no bills in the database
//      }
//      public static int getTotalMinutes(String timeString) {
//          // Split the timeString into hours, minutes, and seconds
//      	if(timeString.equals("")) {
//      		return 0;
//      	}
//          String[] parts = timeString.split(":");
//          
//          // Convert hours, minutes, and seconds to integers
//          int hours = Integer.parseInt(parts[0]);
//          int minutes = Integer.parseInt(parts[1]);
//          int seconds = Integer.parseInt(parts[2]);
//          
//          // Calculate total minutes
//          int totalMinutes = hours * 60 + minutes + (seconds > 0 ? 1 : 0); // Add 1 minute if there are remaining seconds
//          
//          return totalMinutes;
//      }
  //    
  //    
//      public static String getDeliveryTime(int billId) {
//          try {
//              String sql = "SELECT delivertime FROM bill WHERE billid = ?";
//              try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                  statement.setInt(1, billId);
//                  try (ResultSet resultSet = statement.executeQuery()) {
//                      if (resultSet.next()) {
//                          return resultSet.getString("delivertime");
//                      }
//                  }
//              }
//          } catch (SQLException e) {
//              e.printStackTrace();
//          }
//          return ""; // Return empty string if delivery time is not found
//      }
  //    
//      public static String getFoodTime(int billId) {
//          try {
//              String sql = "SELECT foodtime FROM bill WHERE billid = ?";
//              try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                  statement.setInt(1, billId);
//                  try (ResultSet resultSet = statement.executeQuery()) {
//                      if (resultSet.next()) {
//                          return resultSet.getString("foodtime");
//                      }
//                  }
//              }
//          } catch (SQLException e) {
//              e.printStackTrace();
//          }
//          return ""; // Return empty string if food time is not found
//      }
