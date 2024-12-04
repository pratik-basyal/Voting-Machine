package Server.Shared;

import Server.VotingManager.AdminManager.AdminManager_Main;
import Socket.Socket_Handler_Main;

import java.util.Scanner;

import Socket.InputHandler;

public class CardReader {


    private boolean cardIn = false;
    public static boolean fail = false;
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
        String idtype = socket.getInput().toLowerCase();
        if (idtype.equalsIgnoreCase("crfail")) {
            setFail();
        }
        return fail;
    }


    public void readInput() {
            String idtype = socket.getInput().toLowerCase();
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
        sendMessage("ID:"+ID);
    }

    public void sendMessage(String message){
        socket.sendToClient(message);
    }


}

