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

    // for signing in the user.
    public void authenticateUSer(String resident_username, String resident_password) {

        SignIn user = new SignIn(resident_username, resident_password);

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
                System.out.println("");
                System.out.println("Welcome back " + name + "!");
                System.out.println("");

                Driver hostels = new Driver();
                hostels.getHostels();
            } else {
                System.out.println("User not found");
            }

            prstmt.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // For inserting a new user/resident to the database.
    public void insertResident() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbURL = "jdbc:mysql://localhost:3306/hostel_management_system";
            Connection dbConnection = DriverManager.getConnection(dbURL, username, password);
            PreparedStatement prstmt;

            System.out.println("Enter full name: ");
            String name = scanner.nextLine();
            System.out.println("Enter password: ");
            String password = scanner.nextLine();
            System.out.println("Enter email: ");
            String email = scanner.nextLine();

            String query = "INSERT INTO residents (resident_name, resident_password, resident_email) VALUES (?, ?, ?)";

            // Adding the stuff to the DB
            prstmt = dbConnection.prepareStatement(query);

            prstmt.setString(1, name);
            prstmt.setString(2, password);
            prstmt.setString(3, email);

            // int rowsAffected = prstmt.executeUpdate();
            // System.out.println(rowsAffected + " row(s) affected.");
            System.out.println("");
            System.out.println("Welcome " + name + "!");

            Driver hosts = new Driver();
            hosts.getHostels();

            prstmt.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // For getting Hostels in our db
    public void getHostels() {
        System.out.println("");
        System.out.println("Which hostel are you interested in?");
        System.out.println("");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String dbURL = "jdbc:mysql://localhost:3306/hostel_management_system";
            Connection dbConnection = DriverManager.getConnection(dbURL, username, password);
            Statement stmt = dbConnection.createStatement();

            String query = "SELECT hostel_id, hostel_name FROM hostels";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("hostel_id");
                String name = rs.getString("hostel_name");
                System.out.println(id + ". " + name);
            }
            System.out.println("");

            // For when a choice is made.
            String chosenHostel = scanner.nextLine();

            // String HostelStatus = rs.getString("hostel_status");

            String newSQL = "SELECT * FROM rooms WHERE hostel_id = ? AND room_status != 'Booked' AND room_status != 'Under maintenance'";

            PreparedStatement prstmt = dbConnection.prepareStatement(newSQL);

            prstmt.setString(1, chosenHostel);

            ResultSet result = prstmt.executeQuery();

            if (result.next()) {
                while (result.next()) {

                    int room_no = result.getInt("room_id");
                    String room_type = result.getString("room_type");
                    int price = result.getInt("price");

                    System.out.println("********************************");
                    System.out.println("Room number: " + room_no);
                    System.out.println("Room type: " + room_type);
                    System.out.println("Room price: " + price);
                    System.out.println("*********************************");
                    System.out.println("");
                }

                // continue from in here...after they choose the room.

            } else {
                System.out.println("Sorry, this hostel is Fully Booked!");
            }

            rs.close();
            stmt.close();
            dbConnection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void selectedRoom(String Choice) {

    }

    public static void main(String[] args) {
        // Driver trial = new Driver();

        // trial.getHostels();

        // trial.insertResident();

        // trial.authenticateUSer("njp", "b2gmzkuj");
    }
}
