package DbConnenction;

import java.sql.*;

public class DBHelper {
    private static final int ERROR = -1;
    private final static String connectionString = "jdbc:mysql://localhost/i_do_weddings";
    private static boolean connectionOpen = false;
    public static Connection dbConnection;

    public DBHelper() {

    }

    public static boolean OpenConnection() {
        if (connectionOpen) {
            return true;
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                dbConnection = DriverManager.getConnection(connectionString, "root", "123456");
                connectionOpen = true;
                return true;
            } catch (SQLException e) {
                System.out.println("SQL problem: " + e.getMessage());
                System.out.println("SQL state: " + e.getSQLState());
                System.out.println("Vendor error: " + e.getErrorCode());
                return false;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return  false;
            }
        }

    }


    public static void CloseConnection() {
        if (connectionOpen) {
            try {
                dbConnection.close();
                connectionOpen = false;
            } catch (SQLException e) {
                System.out.println("SQL problem: " + e.getMessage());
                System.out.println("SQL state: " + e.getSQLState());
                System.out.println("Vendor error: " + e.getErrorCode());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static ResultSet ReadData(String sql) {
        try {
            OpenConnection();
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            System.out.println("SQL problem: " + e.getMessage());
            System.out.println("SQL state: " + e.getSQLState());
            System.out.println("Vendor error: " + e.getErrorCode());
            return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static ResultSet ReadDataAndInputFromUserInt(String sql, int tableIndexInput, int num) {
        try {
            OpenConnection();
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setInt(tableIndexInput,num);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            System.out.println("SQL problem: " + e.getMessage());
            System.out.println("SQL state: " + e.getSQLState());
            System.out.println("Vendor error: " + e.getErrorCode());
            return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static ResultSet ReadDataAndInputFromUser(String sql, int tableIndexInput, String string) {
        try {
            OpenConnection();
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(tableIndexInput,string);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            System.out.println("SQL problem: " + e.getMessage());
            System.out.println("SQL state: " + e.getSQLState());
            System.out.println("Vendor error: " + e.getErrorCode());
            return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static int WriteData(String sql) {
        try {
            OpenConnection();
            Statement statement = dbConnection.createStatement();
            int affectedRows;
            affectedRows = statement.executeUpdate(sql);
            CloseConnection();
            return affectedRows;
        } catch (SQLException e) {
            System.out.println("SQL problem: " + e.getMessage());
            System.out.println("SQL state: " + e.getSQLState());
            System.out.println("Vendor error: " + e.getErrorCode());
            return ERROR;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  ERROR;
        }
    }
}