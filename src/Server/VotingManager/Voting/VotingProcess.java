package Server.VotingManager.Voting;

import Server.Shared.ScreenDriver;
public class VotingProcess {

    private ScreenDriver screenDriver;

    private String message;

    public VotingProcess() {
        this.screenDriver = new ScreenDriver();
        this.message = null;
    }

    public void setMessage() {
        message = screenDriver.getMessage();
    }

    public String getMessage(){
        return message;
    }

    public void startVote(String blankBallot) {
        screenDriver.sendMessage(blankBallot);
    }
}
