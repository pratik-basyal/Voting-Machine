package Server.VotingManager.Voting;

import Server.Shared.ScreenDriver;
public class VotingProcess {

    private ScreenDriver screenDriver =  new ScreenDriver();;

    private String message = "SCDVScreen";

    public VotingProcess() {
    }

    public void startVote() {
        screenDriver.sendMessage(message);
    }
    public void setMessage() {
        message = screenDriver.getMessage();
    }

    public String getMessage(){
        message = screenDriver.getMessage();
        return message;
    }
    public void readInput(){
        screenDriver.readInput();
    }
}
