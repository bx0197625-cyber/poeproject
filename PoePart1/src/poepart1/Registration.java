 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepart1;

/**
 *
 * @author Student
 */
public class Registration { // start of class

    String userName;
    String passWord;
    String cellPhone;

    // Checks that the username contains an underscore and is no more than
    // five characters long, as required by the POE brief.
    public boolean checkUsername(String username) { // start of username method
        if (username == null) {
            return false;
        }
        return username.contains("_") && username.length() <= 5;
    } // end of username method

    // Checks that the password is at least 8 characters long, contains a
    // capital letter, a number and a special character.
    public boolean checkPasswordComplexity(String password) { // start of password method
        if (password == null) {
            return false;
        }
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[^a-zA-Z0-9].*");
    } // end of password method

    // Checks that the cell number starts with an international dialling
    // code (e.g. +27) followed by digits, with no more than ten digits
    // after the country code.
    // Reference: regex pattern adapted from
    // https://www.baeldung.com/java-regex-validate-phone-numbers
    public boolean checkCellPhoneNumber(String cellphone) { // Start of cellphone method
        if (cellphone == null) {
            return false;
        }
        return cellphone.matches("^\\+\\d{10,11}$");
    } // end of cellphone method

    public String registerUser(String username, String password, String cellphone) {

        if (!checkUsername(username)) {
            return "Username is not correctly formatted; please ensure that "
                    + "your username contains an underscore and is no more "
                    + "than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that "
                    + "the password contains at least eight characters, a "
                    + "capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellphone)) {
            return "Cell phone number incorrectly formatted or does not "
                    + "contain international code.";
        }

        this.userName = username;
        this.passWord = password;
        this.cellPhone = cellphone;

        return "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell phone number successfully added.";
    }

    public boolean registerStatus(String username, String password, String cellphone) {
        return checkUsername(username) &&
                checkPasswordComplexity(password) &&
                checkCellPhoneNumber(cellphone);
    }
}