package Server;

import Server.VotingManager.VotingManager_Main;

import java.io.IOException;

public class Main {

    static VotingManager_Main votingManager;
    static{
        votingManager = new VotingManager_Main();
    }

//    public Main() throws IOException {
//        votingManager = new VotingManager_Main();
//    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while(true) {
                try {
                    votingManager.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
    }
}
