package Server.VotingManager.CardHolder;

import Server.Shared.CardReader;
public class CardHolder_Main {
    private static CardReader cardReader = new CardReader();
    public CardReader.cardType getAdmin(){
        return CardReader.cardType.ADMIN;
    }

    public CardReader.cardType getVoter(){
        return CardReader.cardType.VOTER;
    }

<<<<<<< HEAD
    public void runInput() throws InterruptedException {
        cardReader.readInput();
    }
    public void sendMessage(String message) throws InterruptedException {
        cardReader.sendMessage(message);
    }
    public void ejectCard(){
        cardReader.eraseEjectCard();
    }
=======
    public void runInput(){
        cardReader.readInput();
    }

>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    /********
     *
     * @return
     */
    public CardReader.cardType getCardType() {
        return cardReader.getCardType();
    }
}
