package Server.Shared;
import Socket.Socket_Handler_Main;

import java.io.IOException;

public class CardReaderDriver {
    Socket_Handler_Main socket;
    private String cardData;
    private boolean cardInserted;

    private boolean cardReaderFailed;

    /***********
     * Method to return the string form card
     */
    public CardReaderDriver () throws IOException {
        this.socket = Socket_Handler_Main.getInstance("localhost", 1234);
        this.cardData = null;
        this.cardInserted = false;
        cardReaderFailed = false;
    }

    /**********
     * method to check if the card is inserted inside the machine or not
     * @return
     */
    public void insertCard() {
        cardInserted = true;
    }

    /*******
     * method to return a string of a card
     */
    public String getCardNum() {
        return cardData;
    }

    /******
     * method to eject the card
     */

    public void ejectsCard () {
        if(!cardInserted) throw new IllegalArgumentException("No card is inserted!");

        cardInserted = false;
        System.out.println("Card Ejected");
    }

    /*******
     * method to erase the data of ejected card
     */
    public void eraseEjectCard() {
        cardData = null;
        cardInserted = false;
    }

    /********
     * method to declare CardReader Failed
     */
    public void cardFailed() {
        cardReaderFailed = true;
    }

    /******
     * method to return if CardReader fails
     */
    public boolean isCardReaderFailed() {
        return cardReaderFailed;
    }
}
