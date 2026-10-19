 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepartTwo;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class poepart2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        login register = new login();
        Scanner scan = new Scanner(System.in);

        System.out.println("\n=====Register=====");
        System.out.println("Please enter username");
        String name = scan.nextLine();

        System.out.println("Enter password");
        String password = scan.nextLine();

        System.out.println("Enter cellphone number");
        String cellphone = scan.nextLine();

        System.out.println(register.registerUser(name, password, cellphone));

        // Stop here if any of the registration checks failed.
        if (!register.registerStatus(name, password, cellphone)) {
            return;
        }

        System.out.println("\n=====Login=====");

        System.out.println("Please enter first name: ");
        String firstname = scan.nextLine();

        System.out.println("Please enter surname: ");
        String lastName = scan.nextLine();

        // ADDED: the user gets 3 attempts to log in, then the program exits
        final int maxAttempts = 3;
        boolean loginResult = false;

        for (int attempt = 1; attempt <= maxAttempts && !loginResult; attempt++) {
            System.out.println("Enter username: ");
            String loginUsername = scan.nextLine();

            System.out.println("Enter password: ");
            String loginPassword = scan.nextLine();

            loginResult = register.loginUser(loginUsername, loginPassword);
            System.out.println(register.returnLoginStatus(loginResult, firstname, lastName));

            if (!loginResult) {
                System.out.println("Attempts left: " + (maxAttempts - attempt));
            }
        }

        // ADDED: messages can only be sent after a successful login
        if (!loginResult) {
            System.out.println("Too many failed attempts. Exiting.");
            return;
        }

        System.out.println("\nWelcome to QuickChat."); // ADDED: required welcome message

        Messages msg = new Messages();
       

        int option;

        do {
            System.out.println("\n===== select any of the options below =====");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");

            option = scan.nextInt();

            switch (option) {
                
                case 1 -> msg.sendingMessages(); // ADDED: option 1 was missing
                case 2 -> System.out.println("Coming Soon.");
                case 3 -> System.out.println("Good bye!!");                 
                default -> System.out.println("Invalid option");
                    
            }

        } while (option != 3);
    }
    

}