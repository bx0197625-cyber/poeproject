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
        // TODO code application logic here
       login register = new login();
        Scanner scan = new Scanner(System.in);
       
        System.out.println("\n=====Register===== ");
        System.out.println("Please enter username");
        String name = scan.nextLine();
        
        System.out.println("Enter password");
        String  password = scan.nextLine();

        System.out.println("Enter cellphone number");
        String cellphone = scan.nextLine();

        System.out.println(register.registerUser(name, password, cellphone));

        if(!register.registerStatus(name, password, cellphone)){
            return;
        }
        
        System.out.println("\n=====login=====");
   }

    private static class login {

        private final Registration registration = new Registration();

        public login() {
        }

        private String registerUser(String name, String password, String cellphone) {
            return registration.registerUser(name, password, cellphone);
        }

        private boolean registerStatus(String name, String password, String cellphone) {
            return registration.registerStatus(name, password, cellphone);
        }
    }
    
}
