package JDBC_conector;

import java.sql.*;
import java.util.Scanner;
// import java.util.logging.Level;
// import java.util.logging.Logger;

public class Driver {

    private String username = "root";
    private String password = "#Admin123";
    Scanner scanner = new Scanner(System.in);

    // Getting Info method.
    public void gettingInfo() {

        System.out.println("Welcome to the Trial!!");

        Statement statement; // runs SQL statement
        String output;

        ResultSet result; // Holds output from SQL
        String SQL = "SELECT * FROM residents";

        try {

            // Establishing the connection
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

    public void insertHostel() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbURL = "jdbc:mysql://localhost:3306/hostel_management_system";
            Connection dbConnection = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement prstmt;

            System.out.println("Enter name of Hostel: ");
            String name = scanner.nextLine();
            System.out.println("Enter Hostel location: ");
            String location = scanner.nextLine();

            String query = "INSERT INTO hostel_table (hostel_name, hostel_location, manager_id) VALUES (?, ?, NULL)";

            // Adding the stuff to the DB
            prstmt = dbConnection.prepareStatement(query);

            prstmt.setString(1, name);
            prstmt.setString(2, location);

            int rowsAffected = prstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        Driver trial = new Driver();

        trial.insertHostel();
    }
}
