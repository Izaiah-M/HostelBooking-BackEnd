package JDBC_conector;

import java.sql.*;
import java.util.Scanner;
// import java.util.logging.Level;
// import java.util.logging.Logger;

import Components.Booking.SignIn;

public class Driver {

    private String username = "root";
    private String password = "";
    Scanner scanner = new Scanner(System.in);

    // Getting Info method.
    public void authenticateUSer(String resident_username, String resident_password) {

        SignIn user = new SignIn(resident_username, resident_password);

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
            // result = prstmt.executeQuery(SQL); // Holds the output from sql
            result = prstmt.executeQuery();

            if (result.next()) {
                String name = result.getString("resident_name");
                String user_password = result.getString("resident_password");
                System.out.println("Name: " + name);
                System.out.println("Password: " + user_password);
            } else {
                System.out.println("User not found");
            }

            prstmt.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertResident() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbURL = "jdbc:mysql://localhost:3306/hostel_management_system";
            Connection dbConnection = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement prstmt;

            System.out.println("Enter name: ");
            String name = scanner.nextLine();
            System.out.println("Enter : ");
            String password = scanner.nextLine();

            String query = "INSERT INTO residents (resident_name, resident_password) VALUES (?, ?)";

            // Adding the stuff to the DB
            prstmt = dbConnection.prepareStatement(query);

            prstmt.setString(1, name);
            prstmt.setString(2, password);

            int rowsAffected = prstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected.");

            prstmt.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        // Driver trial = new Driver();

        // trial.insertResident();

        // trial.authenticateUSer("njp", "b2gmzkuj");
    }
}
