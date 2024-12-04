package Server.VotingManager.Voting;

import Server.Shared.Printer;
import Server.Shared.SD_Card_W1;
import Server.Shared.SD_Card_W2;

import java.io.IOException;

public class Vote_Recording {

    static SD_Card_W1 sd_card_w1 = new SD_Card_W1();
    private SD_Card_W2 sd_card_w2 = new SD_Card_W2();

    private Printer printer =  new Printer();;
    private String message ="Recording In Progress";

    /*******
     * TEMPORARY
     * @return
     */
    public boolean isvoteRecorded() throws InterruptedException, IOException {
        Thread.sleep(1000);
        sd_card_w1.readInput();
        Thread.sleep(1000);
        printer.printInput();
        return (sd_card_w1.isCaptured() && printer.printedConfirmed());
    }
}
