package JDBC_conector;

import java.sql.*;
import java.util.Scanner;
// import java.util.logging.Level;
// import java.util.logging.Logger;

import Components.Booking.SignIn;

public class Driver {

    private String username = "root";
    private String password = "#Admin123";
    Scanner scanner = new Scanner(System.in);

    // Getting Info method.
    public void authenticateUSer() {

        SignIn user = new SignIn();

        Statement statement; // runs SQL statement
        String output;

        ResultSet result; // Holds output from SQL
        String SQL = "SELECT * FROM residents WHERE resident_name = ? AND resident_password = ?";

        try {

            // Establishing the connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/hostel_management_system";
            Connection dbConnection = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement prstmt = dbConnection.prepareStatement(SQL); // Allows SQL to be executed

            prstmt.setString(1, user.getUsername());
            prstmt.setString(2, user.getPassword());
            // Running the query
            result = prstmt.executeQuery(SQL); // Holds the output from sql

            // if (rs.next()) {
            // String email = rs.getString("email");
            // String password = rs.getString("password");
            // System.out.println("Email: " + email);
            // System.out.println("Password: " + password);
            // } else {
            // System.out.println("User not found");
            // }

            prstmt.close();

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

            prstmt.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        Driver trial = new Driver();

        trial.insertHostel();
    }
}
