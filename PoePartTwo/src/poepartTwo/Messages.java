 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepartTwo;

import java.io.FileWriter;   // ADDED: needed to write the JSON file
import java.io.IOException;  // ADDED: needed to handle file errors
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Messages {
    
 ArrayList<String> messageIds = new ArrayList<>();
     ArrayList<String>recipientNumber = new ArrayList<>();
     ArrayList<String>messages = new ArrayList<>();
     ArrayList<String>msgHash = new ArrayList<>();
     ArrayList<String>messageStatus = new ArrayList<>();
     ArrayList<String>storedJson = new ArrayList<>(); // ADDED: JSON objects of stored messages
    
    
    Scanner input;
    Random random = new Random();
    
    // ADDED: lets the main class share its Scanner so two Scanners never fight over System.in
    public Messages(Scanner sharedInput){
        this.input = sharedInput;
    }
    
    // Default constructor (used by the unit tests): makes its own Scanner
    public Messages(){
        this(new Scanner(System.in));
    }
    
    int numMessageSent = 0;
    
    public void sendingMessages(){
        
        System.out.println("How many messages do you want to send");
        int numberOfMesages = input.nextInt();
                input.nextLine();
        
        for(int i = 1; i <= numberOfMesages; i++){
            
            System.out.println("MESSAGE: " + i);
            
            System.out.println("Enter recipient cell number!!");
            String recipient = input.nextLine();
            
            String recipientResult = checkRecipientCell(recipient);
            
            // FIXED: now compares to the required success text and prints the real result
            if(!recipientResult.equals("Cell phone number successfully captured.")){
                System.out.println(recipientResult);
                i = i - 1;
                continue;
            }
            
            System.out.println("Enter your message!!");
            String messageText = input.nextLine();
            
            // CHANGED: uses the new checkMessageLength() so the required messages are shown
            String lengthResult = checkMessageLength(messageText);
            
            if(!lengthResult.equals("Message ready to send.")){
                
                System.out.println(lengthResult);
                i--;
                continue;
            }
            
            // MOVED: everything is only added once the recipient and message are both valid,
            // so the lists never get out of sync when the user has to retry
            recipientNumber.add(recipient);
            messages.add(messageText);
            
            String messageID = generateMessageID();
            System.out.println("Message ID generated: " + messageID); // ADDED
            
            String messageHash = createMessageHash(messageID, i - 1, messageText); // CHANGED: loop counter, starts at 0
            
            msgHash.add(messageHash);
            
            String status =sentMessage();
            
            // CHANGED: a deleted message has been removed from every list, so no status is stored for it
            if(!status.equals("Deleted")){
                messageStatus.add(status);
            }
            
            if(status.equals("Sent")){
                
                numMessageSent++;
            }
         
        }
        
        printMessages();
        
        System.out.println("Total messages sent: " + returnTotalMessages());
    }
    
    public int returnTotalMessages(){
        return numMessageSent;
    }
    
    // ADDED: required checkMessageID() method - ID must not be more than ten characters
    public boolean checkMessageID(String messageID){
        return messageID != null && messageID.length() <= 10;
    }
    
    // ADDED: checks the 250 character limit and returns the required messages
    public String checkMessageLength(String messageText){
        
        if(messageText.trim().isEmpty()){
            return "Please enter a message between 1 and 250 characters.";
        }
        
        if(messageText.length() > 250){
            return "Message exceeds 250 characters by " + (messageText.length() - 250)
                    + "; please reduce the size.";
        }
        
        return "Message ready to send.";
    }
    
        public String generateMessageID(){
            
        
        String messageID;
        
        do{
            long number = 1000000000L + (long) (random.nextDouble() * 9000000000L); // works on every Java version
            messageID = String.valueOf(number);
        }while(messageIds.contains(messageID) || !checkMessageID(messageID)); // CHANGED: also uses checkMessageID
     
        messageIds.add(messageID);
        
        return messageID;
        }
    
public String checkRecipientCell(String cellphone) {

        // CHANGED: local numbers may be 10 or 11 digits (the brief's test data 08575975889 has 11)
        if (cellphone.matches("^\\+27[0-9]{9}$")
                || cellphone.matches("^0[0-9]{9,10}$")) {

            return "Cell phone number successfully captured."; // CHANGED: required message

        } else {

            // CHANGED: required message
            return "Cell phone number is incorrectly formatted or does not contain an international code. "
                    + "Please correct the number and try again.";
        }
        
}
public String createMessageHash(String messageID, int messageNumber, String messageText){
    
                
            
            String firstTwoNumbers =
                    messageID.substring(0, 2);
            String trimmedMessage = messageText.trim();
            String[] words = trimmedMessage.split("\\s+");
            // CHANGED: punctuation removed so "tonight?" gives TONIGHT as in the brief (00:0:HITONIGHT)
            String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
            String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");
            
            String hash = firstTwoNumbers
                    +":"
                    + messageNumber
                    + ":"
                    + firstWord
                    + lastWord;
            
            return hash.toUpperCase();
        }

// ADDED: returns the required text for each menu choice (also makes this testable)
public String sentMessageResponse(int choice){
    return switch(choice){
        case 1 -> "Message successfully sent.";
        case 2 -> "Press 0 to delete the message.";
        case 3 -> "Message successfully stored.";
        default -> "Invalid option";
    };
}

public String sentMessage(){
    
    System.out.println("\nchoose what you want to do with this messge!");
    
    System.out.println("1. Send Message");
    System.out.println("2. Disregard Message");
    System.out.println("3. Store Message to send later");
    
    System.out.println("Enter your choice: ");
    int choice = input.nextInt();
    input.nextLine(); // ADDED: clears the Enter key so the next recipient is not read as empty
    
    System.out.println(sentMessageResponse(choice)); // CHANGED: required messages
    
    switch(choice){
        
        case 1 -> {
            return "Sent"; // FIXED: was "sent", but the loop checks for "Sent"
        }
        
        case 2 -> {
            int deleteChoice = input.nextInt();
            
            input.nextLine();
            
            if(deleteChoice == 0){
                System.out.println("Message deleted");
                
                int lastIndex = messages.size() - 1;
                
                messageIds.remove(lastIndex);
                recipientNumber.remove(lastIndex);
                messages.remove(lastIndex);
                msgHash.remove(lastIndex);
                
                return "Deleted"; // CHANGED: tells the loop not to add a status for a removed message
            }else{
                System.out.println("Invalid option!");
                return "Disregarded";
                
            }
                
            }
        
        case 3 -> {
            int lastIndex = messages.size() - 1;
            // ADDED: save the message to the JSON file
            storeMessage(messageIds.get(lastIndex), msgHash.get(lastIndex),
                    recipientNumber.get(lastIndex), messages.get(lastIndex));
            return "Stored";
        }
        
        default -> {
            return "Disregarded";
        }
        }
}

/*
 * ADDED: storeMessage() - stores the message in a JSON file (storedMessages.json).
 * The JSON is built by hand so no extra library is needed.
 * References:
 * Oracle (n.d.) FileWriter (Java Platform SE 8). Available at:
 *   https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html
 * JSON.org (n.d.) Introducing JSON. Available at: https://www.json.org/json-en.html
 */
public void storeMessage(String messageID, String hash, String recipient, String message){
    
    String entry = "  {\n"
            + "    \"messageID\": \"" + escapeJson(messageID) + "\",\n"
            + "    \"messageHash\": \"" + escapeJson(hash) + "\",\n"
            + "    \"recipient\": \"" + escapeJson(recipient) + "\",\n"
            + "    \"message\": \"" + escapeJson(message) + "\"\n"
            + "  }";
    
    storedJson.add(entry);
    
    try (FileWriter writer = new FileWriter("storedMessages.json")) {
        writer.write("[\n" + String.join(",\n", storedJson) + "\n]");
    } catch (IOException e) {
        System.out.println("Could not store the message: " + e.getMessage());
    }
}

// ADDED: makes sure quotes and backslashes in a message do not break the JSON
private String escapeJson(String text){
    return text.replace("\\", "\\\\").replace("\"", "\\\"");
}

public String printMessages(){
    
    StringBuilder allMessages = new StringBuilder();
    
    System.out.println("\n===== All Messages =====");
    
    // CHANGED: details are now in the order the brief asks for:
    // Message ID, Message Hash, Recipient, Message
    for(int i = 0; i < messages.size(); i++){
        allMessages.append("\n==============================");
        allMessages.append("\nMessages Number: ").append(i + 1);
        allMessages.append("\nMessage ID: ").append(messageIds.get(i));
        allMessages.append("\nMessage Hash: ").append(msgHash.get(i));
        allMessages.append("\nRecipient: ").append(recipientNumber.get(i));
        allMessages.append("\nMessage: ").append(messages.get(i));
        allMessages.append("\nStatus: ").append(messageStatus.get(i));
    }
    
    System.out.println(allMessages); // CHANGED: prints the same text instead of repeating every line
    
    return allMessages.toString();
}

}// end of the class mesage