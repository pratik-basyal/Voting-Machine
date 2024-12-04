package Server;

import Server.VotingManager.VotingManager_Main;

import java.io.IOException;

public class Main {

    static VotingManager_Main votingManager;
    static{
        try {
            votingManager = new VotingManager_Main();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Main() throws IOException {}

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                String currentMessage = votingManager.message;
                if (!currentMessage.equalsIgnoreCase("ok")) {
                    System.out.println("Exiting: message = " + currentMessage);
                    break;
                }
                System.out.println("MAIN THREAD IS RUNNING");

                try {
                    votingManager.start();
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("THREAD HAS STOPPED");
            System.exit(0);
        });
        thread.start();
    }
}
