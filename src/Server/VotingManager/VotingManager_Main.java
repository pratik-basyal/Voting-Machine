package Server.VotingManager;


import Server.VotingManager.CardHolder.CardHolder_Main;
import Server.VotingManager.Monitor.Monitor_Main;
import Server.VotingManager.AdminManager.AdminManager_Main;
import Server.VotingManager.Voting.Voting_Main;

import java.io.IOException;

public class VotingManager_Main {
    private Monitor_Main monitor;
    private AdminManager_Main admin;

    private Voting_Main voter;

    private boolean showTemplate = true;

    private static CardHolder_Main cardHolder = new CardHolder_Main();;

    public void start() throws IOException {
        cardHolder.runInput();

        if (cardHolder.getCardType() == cardHolder.getAdmin() && showTemplate) {
            this.monitor = new Monitor_Main();
            this.admin = new AdminManager_Main();
            admin.run();
            monitor.run();
            showTemplate = false;
        }

        else if (cardHolder.getCardType() == cardHolder.getVoter() && showTemplate) {
            this.voter = new Voting_Main();
            this.monitor = new Monitor_Main();
            voter.run();
            monitor.run();
            showTemplate = false;
        }
    }
}
