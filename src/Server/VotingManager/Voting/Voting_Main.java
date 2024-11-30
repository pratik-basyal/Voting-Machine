package Server.VotingManager.Voting;

import java.io.IOException;

public class Voting_Main implements Runnable{

    private Blank_Ballot blankBallot;

    private String message;
    private VotingProcess votingProcess;

    private Vote_Recording voteRecording;

    public Voting_Main () throws IOException {
        this.blankBallot = new Blank_Ballot();
        this.votingProcess = new VotingProcess();
        this.voteRecording = new Vote_Recording();
        this.message = null;
    }
    @Override
    public void run() {
        do {
            blankBallot.fetchBlankBallot();
            message = votingProcess.getMessage();
            votingProcess.startVote(blankBallot.getBlankBallot());

            if(voteRecording.isvoteRecorded()) message = "DONE";
        }
        while(!message.equalsIgnoreCase("DONE"));
    }
}
