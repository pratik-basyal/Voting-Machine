package Server.Shared;

<<<<<<< HEAD
import Server.VotingManager.AdminManager.AdminManager_Main;
=======
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
import Socket.Socket_Handler_Main;

import java.util.Scanner;

import Socket.InputHandler;

public class CardReader {


    private boolean cardIn = false;
<<<<<<< HEAD
    public static boolean fail = false;
=======
    private boolean fail = false;
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    private String ID = null;
    private cardType type = null;
    private InputHandler socket = InputHandler.getInstance();

    public enum cardType {
        ADMIN, VOTER
    }
    public CardReader() {

//        System.out.println("Check");
//        readInput();
    }

    public Boolean cardPresent (){
        return cardIn;
    }

    public cardType getCardType() {
        return type;
    }

    public String getCardNumber() {
        return ID;
    }


    public void ejectCard() {
        if (cardIn) {
            cardIn = false;
        } else {

        }
    }
    public void eraseEjectCard() {
        ejectCard();
        type = null;
        ID = null;
        socket.sendToClient("ejected");


    }

    public boolean checkFail(){
<<<<<<< HEAD
        String idtype = socket.getInput().toLowerCase();
        if (idtype.equalsIgnoreCase("crfail")) {
            setFail();
        }
=======
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
        return fail;
    }


    public void readInput() {
            String idtype = socket.getInput().toLowerCase();
<<<<<<< HEAD
        System.out.println("Card Reader is listening: " + idtype);
            if(idtype.startsWith("cr")) {
                switch (idtype.substring(2)) {
                    case "id":
                        //socket.sendToClient("Current Card ID: " + (ID != null ? ID : "No Card Inserted"));
                        break;
                    case "reader:a":
                        readCard("a");
                        break;
                    case "reader:v":
                        readCard("v");
                        break;
                    case "type":
                        //socket.sendToClient("Card Type: " + (type != null ? type : "No Card Inserted"));
                        break;
                    case "eject":
                        eraseEjectCard();
                        break;
                    default:

                }socket.clearInput();
            }
=======
            System.out.println("Received command: " + idtype); // Log received commands
            switch (idtype) {
                case "id":
                    //socket.sendToClient("Current Card ID: " + (ID != null ? ID : "No Card Inserted"));
                    break;
                case "reader:a":
                    readCard("a");

                    break;
                case "reader:v":
                    readCard("v");
                    break;
                case "type":
                    //socket.sendToClient("Card Type: " + (type != null ? type : "No Card Inserted"));
                    break;
                case "eject":
                    eraseEjectCard();
                    break;
                case "read_fail":
                    setFail();
                    break;
                default:
            }
            socket.clearInput();
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    }
    private String readCard(String idtype){
        if(!fail) {
            if (cardIn) {
                return null;
            } else {

                cardIn = true;
                if (idtype.equalsIgnoreCase("A")) {
                    type = cardType.ADMIN;
                    generateID();
                    socket.sendToClient("reader:"+idtype +" "+ getCardNumber());
                    return ID;

                } else if (idtype.equalsIgnoreCase("V")) {
                    type = cardType.VOTER;
                    generateID();
                    socket.sendToClient("reader:"+idtype +" "+ getCardNumber());
                    return ID;

                } else {
                    cardIn = false;
                    return null;
                }
            }
        }
        return null;
    }
    private void setFail(){
        eraseEjectCard();
        fail = true;
<<<<<<< HEAD
=======

>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    }
    private void generateID(){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(11);
        for (int i = 0; i < 10; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        ID = sb.toString();
<<<<<<< HEAD
        sendMessage("ID:"+ID);
    }

    public void sendMessage(String message){
        socket.sendToClient(message);
=======
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    }


}

