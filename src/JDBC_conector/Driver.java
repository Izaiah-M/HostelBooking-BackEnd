package JDBC_conector;

import java.sql.*;
// import java.util.logging.Level;
// import java.util.logging.Logger;

public class Driver {
    public static void main(String[] args) {

        System.out.println("Welcome to the Trial!!");

        String username = "root";
        String password = "#Admin123";

        Statement statement; // runs SQL statement
        // String useSQL = new String("USE Hostel_Management_System"); // We are picking
        // the db to use

        String output;

        ResultSet result; // Holds output from SQL
        String SQL = "SELECT * FROM residents";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/hostel_management_system";
            Connection dbConnection = DriverManager.getConnection(dbURL, username, password);

            statement = dbConnection.createStatement(); // Allows SQL to be executed

            // Running the query
            result = statement.executeQuery(SQL); // Holds the output from sql

            while (result.next()) {
                output = result.getString("resident_id") + " " + result.getString("resident_name") + " "
                        + result.getString("resident_contact") + " " + result.getString("hostel_id") + " "
                        + result.getString("room_no");
                System.out.println(output);
            }

            statement.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
