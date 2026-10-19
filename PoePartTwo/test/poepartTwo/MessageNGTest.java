 package poepartTwo;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.testng.annotations.Test;

/**
 * Unit tests for the Messages class (Part 2).
 *
 * @author Student
 */
public class MessageNGTest {

    private final Messages msg = new Messages();

    // ---------- Message length ----------
    @Test
    public void messageLengthSuccess() {
        assertEquals(msg.checkMessageLength("Hi Mike, can you join us for dinner tonight?"),
                "Message ready to send.");
    }

    @Test
    public void messageLengthFailure() {
        String tooLong = "a".repeat(255);
        assertEquals(msg.checkMessageLength(tooLong),
                "Message exceeds 250 characters by 5; please reduce the size.");
    }

    // ---------- Recipient number ----------
    @Test
    public void recipientCorrectlyFormatted() {
        assertEquals(msg.checkRecipientCell("+27718693002"),
                "Cell phone number successfully captured.");
    }

    @Test
    public void recipientIncorrectlyFormatted() {
        assertEquals(msg.checkRecipientCell("08966553"),
                "Cell phone number is incorrectly formatted or does not contain an "
                + "international code. Please correct the number and try again.");
    }

    // ---------- Message hash (looped over the test data) ----------
    @Test
    public void messageHashIsCorrect() {
        String[] ids = {"0012345678", "0187654321"};
        String[] texts = {
            "Hi Mike, can you join us for dinner tonight?",
            "Hi Keegan, did you receive the payment?"
        };
        String[] expected = {"00:0:HITONIGHT", "01:1:HIPAYMENT"};

        for (int i = 0; i < texts.length; i++) {
            assertEquals(msg.createMessageHash(ids[i], i, texts[i]), expected[i]);
        }
    }

    // ---------- Message ID ----------
    @Test
    public void messageIdIsCreated() {
        String id = msg.generateMessageID();
        assertEquals(id.length(), 10);
        assertTrue(msg.checkMessageID(id));
        assertFalse(msg.checkMessageID("12345678901"));
    }

    // ---------- Send / Disregard / Store ----------
    @Test
    public void sentMessageResponses() {
        assertEquals(msg.sentMessageResponse(1), "Message successfully sent.");
        assertEquals(msg.sentMessageResponse(2), "Press 0 to delete the message.");
        assertEquals(msg.sentMessageResponse(3), "Message successfully stored.");
    }

    // ---------- Full run with the brief's test data (send, then discard) ----------
    @Test
    public void totalMessagesSentWithTestData() {
        String simulatedInput = "2\n"
                + "+27718693002\n"
                + "Hi Mike, can you join us for dinner tonight?\n"
                + "1\n"
                + "08575975889\n"
                + "Hi Keegan, did you receive the payment?\n"
                + "2\n"
                + "0\n";

        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
            Messages testMsg = new Messages(); // created after setIn so its Scanner reads the test input
            testMsg.sendingMessages();

            assertEquals(testMsg.returnTotalMessages(), 1);
            assertEquals(testMsg.messages.size(), 1); // the discarded message was deleted
            assertTrue(testMsg.msgHash.get(0).endsWith(":0:HITONIGHT"));
        } finally {
            System.setIn(originalIn);
        }
    }
}