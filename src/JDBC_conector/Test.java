// package JDBC_conector;

// import java.sql.*;
// import javax.sql.DataSource;
// // import org.apache.commons.dbcp2.BasicDataSource;

// public class Test {
// private static final String DB_URL =
// "jdbc:mysql://localhost:3306/hostel_management_system";
// private static final String USERNAME = "root";
// private static final String PASSWORD = "#Admin123";
// private static final int MAX_TOTAL_CONNECTIONS = 10;

// // Create a DataSource object to manage the connection pool
// private static final DataSource dataSource = createDataSource();

// private static DataSource createDataSource() {
// BasicDataSource ds = new BasicDataSource();
// ds.setUrl(DB_URL);
// ds.setUsername(USERNAME);
// ds.setPassword(PASSWORD);
// ds.setMaxTotal(MAX_TOTAL_CONNECTIONS);
// return ds;
// }

// public static void main(String[] args) {

// System.out.println("Welcome to the Trial!!");

// Connection connection = null;
// Statement statement = null;
// ResultSet result = null;

// String SQL = "SELECT * FROM residents";

// try {
// // Get a connection from the connection pool
// connection = dataSource.getConnection();
// statement = connection.createStatement();
// result = statement.executeQuery(SQL);

// while (result.next()) {
// String output = result.getString("resident_id") + " " +
// result.getString("resident_name") + " "
// + result.getString("resident_contact") + " " + result.getString("hostel_id")
// + " "
// + result.getString("room_no");
// System.out.println(output);
// }

// } catch (SQLException e) {
// System.out.println("Error: " + e.getMessage());
// } finally {
// // Close database resources in reverse order of creation to ensure proper
// cleanup
// try {
// if (result != null) {
// result.close();
// }
// if (statement != null) {
// statement.close();
// }
// if (connection != null) {
// connection.close();
// }
// } catch (SQLException e) {
// System.out.println("Error: " + e.getMessage());
// }
// }
// }

// public void insertHostel(String name, String location, String mgr_id) {
// Connection connection = null;
// PreparedStatement statement = null;

// try {
// // Get a connection from the connection pool
// connection = dataSource.getConnection();

// // Define the SQL statement for inserting data into the hostel table
// String sql = "INSERT INTO hostel (hostel_name, location, manager_id) VALUES
// (?, ?, ?)";

// // Create a PreparedStatement object for inserting data
// statement = connection.prepareStatement(sql);
// statement.setString(1, name);
// statement.setString(2, location);
// statement.setString(3, mgr_id);

// // Execute the insert statement and print the number of rows affected
// int rowsAffected = statement.executeUpdate();
// System.out.println(rowsAffected + " row(s) affected.");

// } catch (SQLException e) {
// System.out.println("Error: " + e.getMessage());
// } finally {
// // Close database resources in reverse order of creation to ensure proper
// cleanup
// try {
// if (statement != null) {
// statement.close();
// }
// if (connection != null) {
// connection.close();
// }
// } catch (SQLException e) {
// System.out.println("Error: " + e.getMessage());
// }
// }
// }
// }
