package Server.VotingManager;


import Client.Screen;
import Client.Template;
import Server.Shared.CardReader;
import Server.VotingManager.CardHolder.CardHolder_Main;
import Server.VotingManager.Monitor.Monitor_Main;
import Server.VotingManager.AdminManager.AdminManager_Main;
import Server.VotingManager.Voting.Voting_Main;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VotingManager_Main {
    private Monitor_Main monitor = new Monitor_Main();
    private boolean showTemplate = true;
    private AdminManager_Main admin = new AdminManager_Main(false,false,false,false);
    private Screen screen = new Screen();;

    private Voting_Main voter;


    private boolean cardEject = false;


    public String message = Monitor_Main.message;
    private static CardHolder_Main cardHolder = new CardHolder_Main();;
    private ExecutorService executorService = Executors.newFixedThreadPool(1); // Manages threads
    private ExecutorService executorService1 = Executors.newFixedThreadPool(1); // Manages threads
    private ExecutorService executorService2 = Executors.newFixedThreadPool(1); // Manages threads

    public VotingManager_Main() throws IOException {}


    public void start() throws IOException, InterruptedException {
        if(!message.equalsIgnoreCase("ok")) System.out.println("VOTING_MANAGER : " + message);
        // Run monitor.run() in a separate thread
        executorService.submit(() -> {
            if(!monitor.isExit()) {
                monitor.run(); // This thread runs continuously
            }
            else{
                message = monitor.getMessage();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        cardHolder.runInput();
//        System.out.println(AdminManager_Main.message);
            executorService1.submit(() -> {
                if (cardHolder.getCardType() == cardHolder.getAdmin() && AdminManager_Main.message) {
                    AdminManager_Main.message = false;
                    System.out.println(AdminManager_Main.message);
                    this.admin = new AdminManager_Main(admin.isEnableVoting(), admin.isOpenVoting(), admin.isCloseVoting(), admin.isDisableVoting());
                    //            this.monitor = new Monitor_Main();;
                    admin.sendMessage("at");
                    admin.run();
                }
            });



        if (cardHolder.getCardType() == cardHolder.getVoter() && admin!=null && Voting_Main.msg) {
            executorService2.submit(() -> {
                if(admin.isOpenVoting()) {
                    Voting_Main.msg = false;
                    try {
                        this.voter = new Voting_Main();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                        System.out.println("Checking VotingManager_Main37");
                        voter.run();
                        cardHolder.ejectCard();
                }
                else{
                    admin.sendMessage("rejected");
                    cardHolder.ejectCard();
                }
            });

        }


    }

    public String getMessage() {
        return message;
    }

    public void sendEjected() {
        voter.getCardEject(cardEject);
    }
}
