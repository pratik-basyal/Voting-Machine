package Server.VotingManager.Voting;

import Server.Shared.Printer;
import Server.Shared.SD_Card_W1;
import Server.Shared.SD_Card_W2;

public class Vote_Recording {

    private SD_Card_W1 sd_card_w1;
    private SD_Card_W2 sd_card_w2;

    private Printer printer;
    private String message;

    public Vote_Recording() {
        //if(sd_card_w1.getMessage() != null)  this.message = sd_card_w1.getMessage();

        this.sd_card_w1 = new SD_Card_W1();
        this.sd_card_w2 = new SD_Card_W2();
        this.printer = new Printer();
    }

    /*******
     * TEMPORARY
     * @return
     */
    public boolean isvoteRecorded() {
       if(!message.contains("Failed")) {
            printer.print(message);
            return true;
       }
       return false;
    }
}
