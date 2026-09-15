 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepart1;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class PoePart1 {

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

        System.out.println("Enter username: ");
        String loginUsername = scan.nextLine();

        System.out.println("Enter password: ");
        String loginPassword = scan.nextLine();

        boolean loginResult = register.loginUser(loginUsername, loginPassword);
        System.out.println(register.returnLoginStatus(loginResult, firstname, lastName));
    }

}