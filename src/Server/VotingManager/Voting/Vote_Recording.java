package Server.VotingManager.Voting;

import Server.Shared.Printer;
import Server.Shared.SD_Card_W1;
import Server.Shared.SD_Card_W2;

<<<<<<< HEAD
import java.io.IOException;

public class Vote_Recording {

    static SD_Card_W1 sd_card_w1 = new SD_Card_W1();
    private SD_Card_W2 sd_card_w2 = new SD_Card_W2();

    private Printer printer =  new Printer();;
    private String message ="Recording In Progress";
=======
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
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34

    /*******
     * TEMPORARY
     * @return
     */
<<<<<<< HEAD
    public boolean isvoteRecorded() throws InterruptedException, IOException {
        Thread.sleep(1000);
        sd_card_w1.readInput();
        Thread.sleep(1000);
        printer.printInput();
        return (sd_card_w1.isCaptured() && printer.printedConfirmed());
=======
    public boolean isvoteRecorded() {
       if(!message.contains("Failed")) {
            printer.print(message);
            return true;
       }
       return false;
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    }
}
