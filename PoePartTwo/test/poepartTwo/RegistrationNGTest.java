 package poepartTwo;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

/**
 * Unit tests for the Registration and login classes (Part 1).
 *
 * @author Student
 */
public class RegistrationNGTest {

    // ---------- Username ----------
    @Test
    public void testCheckUsername() {
        Registration instance = new Registration();
        assertTrue(instance.checkUsername("kyl_1"));
    }

    @Test
    public void testCheckUsername_Invalid() {
        Registration instance = new Registration();
        assertFalse(instance.checkUsername("kyle!!!!!!!"));
    }

    // ---------- Password ----------
    @Test
    public void testCheckPasswordComplexity() {
        Registration instance = new Registration();
        assertTrue(instance.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexity_Invalid() {
        Registration instance = new Registration();
        assertFalse(instance.checkPasswordComplexity("password"));
    }

    // ---------- Cell phone ----------
    @Test
    public void testCheckCellPhoneNumber() {
        Registration instance = new Registration();
        assertTrue(instance.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumber_Invalid() {
        Registration instance = new Registration();
        assertFalse(instance.checkCellPhoneNumber("08966553"));
    }

    // ---------- registerUser messages ----------
    @Test
    public void testRegisterUser() {
        Registration instance = new Registration();
        String expResult = "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell phone number successfully added.";
        assertEquals(instance.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976"), expResult);
    }

    @Test
    public void testRegisterUser_InvalidUsername() {
        Registration instance = new Registration();
        String expResult = "Username is not correctly formatted; please ensure that "
                + "your username contains an underscore and is no more "
                + "than five characters in length.";
        assertEquals(instance.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976"), expResult);
    }

    @Test
    public void testRegisterUser_InvalidPassword() {
        Registration instance = new Registration();
        String expResult = "Password is not correctly formatted; please ensure that "
                + "the password contains at least eight characters, a "
                + "capital letter, a number, and a special character.";
        assertEquals(instance.registerUser("kyl_1", "password", "+27838968976"), expResult);
    }

    @Test
    public void testRegisterUser_InvalidCell() {
        Registration instance = new Registration();
        String expResult = "Cell phone number incorrectly formatted or does not "
                + "contain international code.";
        assertEquals(instance.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553"), expResult);
    }

    // ---------- registerStatus ----------
    @Test
    public void testRegisterStatus() {
        Registration instance = new Registration();
        assertTrue(instance.registerStatus("kyl_1", "Ch&&sec@ke99!", "+27838968976"));
    }

    @Test
    public void testRegisterStatus_Invalid() {
        Registration instance = new Registration();
        assertFalse(instance.registerStatus("kyle!!!!!!!", "password", "08966553"));
    }

    // ---------- login ----------
    @Test
    public void testLoginSuccessful() {
        login instance = new login();
        instance.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(instance.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        login instance = new login();
        instance.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(instance.loginUser("kyl_1", "wrongPass1!"));
    }

    @Test
    public void testReturnLoginStatus() {
        login instance = new login();
        assertEquals(instance.returnLoginStatus(true, "Kyle", "Smith"),
                "Welcome Kyle, Smith it is great to see you again.");
        assertEquals(instance.returnLoginStatus(false, "Kyle", "Smith"),
                "Username or password incorrect, please try again.");
    }
}