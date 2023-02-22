package Components.Booking;

import java.util.Scanner;

import JDBC_conector.Driver;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to HostMe!");

            System.out.println("1. Sign in");
            System.out.println("2. Sign up");

            int answer = scanner.nextInt();
            scanner.nextLine();

            switch (answer) {
                case 1:
                    System.out.println("Enter user name: ");
                    String name = scanner.next();
                    System.out.println("Enter password: ");
                    String password = scanner.next();

                    // Passing the given details to our signIn class for authentication.
                    Driver auth = new Driver();
                    auth.authenticateUSer(name, password);

                    break;

                case 2:
                    System.out.println("SIGN UPP!!");
                    break;

                default:
                    break;
            }
        }

    }

}
