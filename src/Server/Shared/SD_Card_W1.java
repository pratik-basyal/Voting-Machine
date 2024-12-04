package Server.Shared;

import Socket.InputHandler;

<<<<<<< HEAD
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

=======
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
public class SD_Card_W1 {

    InputHandler socket;

<<<<<<< HEAD
    private boolean messageCaptured = false;

    private String fileName = "filledBallot.txt";

=======
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    private String message;

    public SD_Card_W1() {
        this.socket = InputHandler.getInstance();
        this.message = null;
<<<<<<< HEAD

    }

    public static boolean cardFailed = false;

    public void readInput() throws InterruptedException {
        message = socket.getInput();
        if(message.startsWith("SD1")) {
            System.out.println("MESSAGE : " + message);
            writeFile(message);
            socket.clearInput();
        }
=======
    }

    boolean cardFailed = false;

    public void readInput() {
        message = socket.getInput();
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    }

    public String getMessage() {
        return message;
    }

<<<<<<< HEAD
    public void writeFile(String content) {
        messageCaptured = true;
        System.out.println("IS WRITING");
        // Example content: "President = Donald J. Trump\nVicePresident = Kamala Harris"
        String fileName = "filledBallot.txt";

        try (FileWriter myWriter = new FileWriter(fileName, true)) {
            // Write the voter ID
            myWriter.write("Voter_ID: " + content.substring(3,13) + "\n");

            // Write the content
            myWriter.write(content.substring(13)+ "\n");

            // Write a delimiter to separate sections
            myWriter.write("======================\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeFile1(HashMap<String, String> content) {
        // Example content: {President=Donald J. Trump, VicePresident=Kamala Harris}
        String fileName = "output.txt";
        String voterID = "12345";

        try (FileWriter myWriter = new FileWriter(fileName)) {
            // Write the voter ID
            myWriter.write("Voter_ID: " + voterID + "\n");

            // Write each key-value pair
            for (Map.Entry<String, String> mapElement : content.entrySet()) {
                String key = mapElement.getKey();
                String value = mapElement.getValue();
                myWriter.write(key + " = " + value + "\n");
            }

            // Write a delimiter to separate sections
            myWriter.write("======================\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isCaptured() {
        return messageCaptured;
    }
    public boolean checkFail() {
        String str = socket.getInput();
        if(str.equalsIgnoreCase("sdw1fail"))
        {
            cardFailed = true;
        }
        return cardFailed;
    }

=======
    public boolean isCardFailed() {
        return cardFailed;
    }


>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
}
