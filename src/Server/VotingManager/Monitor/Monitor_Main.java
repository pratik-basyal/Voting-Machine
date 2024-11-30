package Server.VotingManager.Monitor;

import Server.Shared.*;

import java.io.IOException;

public class Monitor_Main implements Runnable {

    private CardReader cardD;

    private Latch latchD;

    private Printer printerD;

    private ScreenDriver screenD;

    private SD_Card_R sd_card_r;

    private TamperDriver tamperD;

    private SD_Card_W1 sd_card_w1;

    private SD_Card_W2 sd_card_w2;

    private String message;
    private boolean sysFailed;

    private boolean exit = false;



    public Monitor_Main() throws IOException {
        this.screenD = new ScreenDriver();
        this.sd_card_r = new SD_Card_R();
        this.sd_card_w1 = new SD_Card_W1();
        this.sd_card_w2 = new SD_Card_W2();
        this.tamperD = new TamperDriver();
        this.printerD = new Printer();
        this.latchD = new Latch();
        this.cardD = new CardReader();
        this.message = null;
        this.sysFailed = false;
    }

    /*****
     * @Override
     */
    public void run () {
        while(exit) {
            for (int i = 0; i < 7; i++) {
                if (cardD.checkFail()) {
                    message = "CRFailed";
                    sysFailed = true;
                    exit = true;
                }
                else if (printerD.checkFail()) {
                    message = "PFailed";
                    sysFailed = true;
                    exit = true;
                }
                else if (sd_card_r.isSDCardFailed()) {
                    message = "SDRFailed";
                    sysFailed = true;
                    exit = true;
                }
                else if (tamperD.isTamperedDriverFailed()) {
                    message = "TFailed";
                    sysFailed = true;
                    exit = true;
                }
                else if (sd_card_w1.isCardFailed()) {
                    message = "SDW1Failed";
                    sysFailed = true;
                    exit = true;
                }
                else if (sd_card_w2.sdCardW2Failed()) {
                    message = "SDW2Failed";
                    sysFailed = true;
                    exit = true;
                }
                else if (screenD.screenFailed()) {
                    message = "SCFailed";
                    sysFailed = true;
                    exit = true;
                }
            }
        }
    }
}
