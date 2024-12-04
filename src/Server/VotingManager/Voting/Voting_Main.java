package Server.VotingManager.Voting;

import java.io.IOException;

public class Voting_Main implements Runnable{

    private Blank_Ballot blankBallot;

    private String message;
<<<<<<< HEAD

    public static boolean msg = true;

    private boolean isVotingCompleted = false;
=======
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    private VotingProcess votingProcess;

    private Vote_Recording voteRecording;

    public Voting_Main () throws IOException {
        this.blankBallot = new Blank_Ballot();
        this.votingProcess = new VotingProcess();
        this.voteRecording = new Vote_Recording();
        this.message = null;
<<<<<<< HEAD
        votingProcess.startVote();
=======
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    }
    @Override
    public void run() {
        do {
<<<<<<< HEAD
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
=======
            blankBallot.fetchBlankBallot();
            message = votingProcess.getMessage();
            votingProcess.startVote(blankBallot.getBlankBallot());

            if(voteRecording.isvoteRecorded()) message = "DONE";
        }
        while(!message.equalsIgnoreCase("DONE"));
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    }
}
