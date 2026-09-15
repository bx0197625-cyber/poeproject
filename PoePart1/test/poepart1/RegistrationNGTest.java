 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package poepart1;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Student
 */
public class RegistrationNGTest {

    public RegistrationNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of checkUsername method, of class Registration.
     */
    @Test
    public void testCheckUsername() {
        System.out.println("checkUsername");
        String username = "kf_mo";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.checkUsername(username);
        assertEquals(result, expResult);
    }

    @Test
    public void testCheckUsername_Invalid() {
        System.out.println("checkUsername - invalid");
        String username = "kayla_mokoena";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.checkUsername(username);
        assertEquals(result, expResult);
    }

    /**
     * Test of checkPasswordComplexity method, of class Registration.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        String password = "Ab1!defg";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(result, expResult);
    }

    @Test
    public void testCheckPasswordComplexity_Invalid() {
        System.out.println("checkPasswordComplexity - invalid");
        String password = "password";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(result, expResult);
    }

    /**
     * Test of checkCellPhoneNumber method, of class Registration.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        String cellphone = "+27821234567";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.checkCellPhoneNumber(cellphone);
        assertEquals(result, expResult);
    }

    @Test
    public void testCheckCellPhoneNumber_Invalid() {
        System.out.println("checkCellPhoneNumber - invalid");
        String cellphone = "0821234567";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber(cellphone);
        assertEquals(result, expResult);
    }

    /**
     * Test of registerUser method, of class Registration.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String username = "kf_mo";
        String password = "Ab1!defg";
        String cellphone = "+27821234567";
        Registration instance = new Registration();
        String expResult = "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell phone number successfully added.";
        String result = instance.registerUser(username, password, cellphone);
        assertEquals(result, expResult);
    }

    @Test
    public void testRegisterUser_InvalidUsername() {
        System.out.println("registerUser - invalid username");
        String username = "kayla_mokoena";
        String password = "Ab1!defg";
        String cellphone = "+27821234567";
        Registration instance = new Registration();
        String expResult = "Username is not correctly formatted; please ensure that "
                + "your username contains an underscore and is no more "
                + "than five characters in length.";
        String result = instance.registerUser(username, password, cellphone);
        assertEquals(result, expResult);
    }

    /**
     * Test of registerStatus method, of class Registration.
     */
    @Test
    public void testRegisterStatus() {
        System.out.println("registerStatus");
        String username = "kf_mo";
        String password = "Ab1!defg";
        String cellphone = "+27821234567";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.registerStatus(username, password, cellphone);
        assertEquals(result, expResult);
    }

    @Test
    public void testRegisterStatus_Invalid() {
        System.out.println("registerStatus - invalid");
        String username = "kayla_mokoena";
        String password = "password";
        String cellphone = "0821234567";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.registerStatus(username, password, cellphone);
        assertEquals(result, expResult);
    }
}