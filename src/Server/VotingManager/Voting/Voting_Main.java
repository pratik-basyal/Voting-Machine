package Server.VotingManager.Voting;

import java.io.IOException;

public class Voting_Main implements Runnable{

    private Blank_Ballot blankBallot;

    private String message;

    public static boolean msg = true;

    private boolean isVotingCompleted = false;
    private VotingProcess votingProcess;

    private Vote_Recording voteRecording;

    public Voting_Main () throws IOException {
        this.blankBallot = new Blank_Ballot();
        this.votingProcess = new VotingProcess();
        this.voteRecording = new Vote_Recording();
        this.message = null;
        votingProcess.startVote();
    }
    @Override
    public void run() {
        do {
            votingProcess.readInput();
            votingProcess.setMessage();
            message = votingProcess.getMessage();
            System.out.println("Voting Main");
            System.out.println(message);
            try {
                System.out.println("This is boolean for voteRecording.isvoteRecorded:"+ voteRecording.isvoteRecorded());
                System.out.println("Message: "+ message.equalsIgnoreCase("DONE"));
                if(voteRecording.isvoteRecorded() && message.equalsIgnoreCase("DONE")) {
                    isVotingCompleted = true;
                    msg = true;
                }


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        while(!isVotingCompleted);

    }

    public boolean isVotingCompleted() {
        return isVotingCompleted;
    }

    public boolean getCardEject(boolean ejected) {
        return ejected;
    }
}
