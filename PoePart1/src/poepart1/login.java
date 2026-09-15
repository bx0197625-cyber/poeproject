 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepart1;

/**
 *
 * @author Student
 */
public class login extends Registration {

    boolean loginStatus;

    public boolean loginUser(String name, String password) {
        loginStatus = name.equals(userName) && password.equals(passWord);
        return loginStatus;
    }

    public String returnLoginStatus(boolean loginStatus, String firstName, String lastName) {
        if (loginStatus) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
