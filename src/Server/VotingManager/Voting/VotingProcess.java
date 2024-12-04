package Server.VotingManager.Voting;

import Server.Shared.ScreenDriver;
public class VotingProcess {

<<<<<<< HEAD
    private ScreenDriver screenDriver =  new ScreenDriver();;

    private String message = "SCDVScreen";

    public VotingProcess() {
    }

    public void startVote() {
        screenDriver.sendMessage(message);
    }
=======
    private ScreenDriver screenDriver;

    private String message;

    public VotingProcess() {
        this.screenDriver = new ScreenDriver();
        this.message = null;
    }

>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    public void setMessage() {
        message = screenDriver.getMessage();
    }

    public String getMessage(){
<<<<<<< HEAD
        message = screenDriver.getMessage();
        return message;
    }
    public void readInput(){
        screenDriver.readInput();
=======
        return message;
    }

    public void startVote(String blankBallot) {
        screenDriver.sendMessage(blankBallot);
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    }
}
