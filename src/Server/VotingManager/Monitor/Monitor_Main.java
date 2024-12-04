package Server.VotingManager.Monitor;

import Server.Shared.*;

import java.io.IOException;

public class Monitor_Main implements Runnable {
<<<<<<< HEAD


    public static volatile String message = "OK";
    Printer printer = new Printer();
    CardReader cardReader = new CardReader();

    SD_Card_W1 sd_card_w1 = new SD_Card_W1();

    SD_Card_R sd_card_r = new SD_Card_R();

    TamperSensor ts = new TamperSensor();

    ScreenDriver scd = new ScreenDriver();

    TamperDriver td = new TamperDriver();
    private boolean printerCheck;
    private boolean cardReaderCheck;
    private boolean sd_card_rCheck;

    private boolean sd_card_w1Check;

    private boolean tamperSCheck;

    private boolean tamperDCheck;

    private boolean scdCheck;
    private boolean exit = false;

    public Monitor_Main() throws IOException {}

    @Override
    public void run() {
        System.out.println("Monitor thread started");
        while (!exit) {
                cardReaderCheck = cardReader.checkFail();
                if (cardReaderCheck) {
                    System.out.println("Card fail");
                    message = "CRFailed";
                    scd.sendMessage("crFail");
                    exit = true;
                    System.out.println("Monitor detected failure: " + message);
                    break;
                }

                printerCheck = printer.checkFail();
                if (printerCheck) {
                    message = "PFailed";
                    scd.sendMessage("pFail");
                    exit = true;
                    System.out.println("Monitor detected failure: " + message);
                    break;
                }

                sd_card_rCheck = sd_card_r.checkFail();
                if (sd_card_rCheck) {
                    message = "SDRFailed";
                    scd.sendMessage("sdrFail");
                    exit = true;
                    System.out.println("Monitor detected failure: " + message);
                    break;
                }

                tamperSCheck = ts.checkFail();
                if (tamperSCheck) {
                    message = "tmp";
                    scd.sendMessage("tmp");
                    exit = true;
                    System.out.println("Tamper event detected : ");
                    break;
                }

                sd_card_w1Check = sd_card_w1.checkFail();
                if (sd_card_w1Check) {
                    message = "SDW1Failed";
                    scd.sendMessage("sdw1fail");
                    exit = true;
                    System.out.println("Monitor detected failure: " + message);
                    break;
                }

                tamperDCheck = td.checkFail();
                if (tamperDCheck) {
                    message = "tamperedDriverFailure";
                    scd.sendMessage("tfail");
                    exit = true;
                    System.out.println("Monitor detected failure : " + message);
                    break;
                }

                scdCheck = scd.checkFail();
                if (scdCheck) {
                    message = "SCFailed";
                    scd.sendMessage("scfail");
                    exit = true;
                    System.out.println("Monitor detected failure: " + message);
                    break;
                }

            // Simulate periodic monitoring
            try {
                Thread.sleep(500); // Check every 500ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Monitor thread exiting " + message);
    }
    public boolean isExit(){
        return exit;
    }
    public String getMessage(){
        return message;
=======

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
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    }
}
