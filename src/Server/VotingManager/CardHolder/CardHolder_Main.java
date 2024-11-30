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

    public void runInput(){
        cardReader.readInput();
    }

    /********
     *
     * @return
     */
    public CardReader.cardType getCardType() {
        return cardReader.getCardType();
    }
}
