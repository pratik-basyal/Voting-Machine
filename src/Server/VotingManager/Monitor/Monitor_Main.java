package Server.VotingManager.Monitor;

import Server.Shared.*;

public class Monitor_Main {

    private CardReaderDriver cardD;

    private LatchDriver latchD;

    private PrinterDriver printerD;

    private ScreenDriver screenD;

    private SD_Card_R sd_card_r;

    private TamperDriver tamperD;

    private SD_Card_W1 sd_card_w1;

    private SD_Card_W2 sd_card_w2;

    public Monitor_Main (CardReaderDriver cardD, LatchDriver latchD, PrinterDriver printerD,
                         ScreenDriver screenD, SD_Card_R sd_card_r, TamperDriver tamperD,
                         SD_Card_W1 sd_card_w1, SD_Card_W2 sd_card_w2) {

        this.cardD = cardD;
        this.latchD = latchD;
        this.printerD = printerD;
        this.screenD = screenD;
        this.sd_card_r = sd_card_r;
        this.sd_card_w1 = sd_card_w1;
        this.sd_card_w2 = sd_card_w2;
        this.tamperD = tamperD;
    }


    /*******
     * method to notify system is failed or not
     * @return
     */
    public String systemFailed() {

        for (int i = 0; i < 4; i++) {
            if (cardD.isCardReaderFailed()) return "CardReaderFailed";
            if (printerD.isPrinterFailed()) return "PrinterFailed";
            if (sd_card_r.isSDCardFailed()) return "SDCardRFailed";
            if (tamperD.isTamperedDriverFailed()) return "TamperDFailed";
        }
        return "NoFailure";
    }
}
