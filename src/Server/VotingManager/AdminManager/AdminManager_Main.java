package Server.VotingManager.AdminManager;
import Server.Shared.ScreenDriver;
import Server.Shared.Latch;

/***********
 * This contains instance of :
 * Monitor, AdminManager, Voting, CardHolder
 */
public class AdminManager_Main implements Runnable{

    private ScreenDriver screen;
    private Latch latch;

    private int currentOfficialID;

    private boolean enableVoting;

    private boolean openVoting;

    private boolean disableVoting;

    private boolean closeVoting;

    String screenMessage;

    public AdminManager_Main() {
        this.enableVoting = false;
        this.openVoting = false;
        this.disableVoting = false;
        this.closeVoting = false;
        this.currentOfficialID = -1;
        this.screen = new ScreenDriver();
        this.latch = new Latch();
        this.screenMessage = screen.getMessage();
    }




    @Override
    public void run() {
        while(!adminDone()) {
            String screenMessage = screen.getMessage();

            if("evs".equalsIgnoreCase(screenMessage)) {
                enableVotingSession();
            }
            else if ("ov".equalsIgnoreCase(screenMessage) && !enableVoting) {
                System.out.println("ENABLE THE VOTING SESSION FIRST");
            }
            else if ("ov".equalsIgnoreCase(screenMessage) && enableVoting) {
                openVoting();
                latch.lock();
            }

            else if ("dvs".equalsIgnoreCase(screenMessage)) {
                disableVotingSession();
            }

            else if ("cv".equalsIgnoreCase(screenMessage)) {
                closeVoting();
            }

            //simulating a dealy to avoid busy-waiting
            try{
                Thread.sleep(100);
            }

            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /********
     * Method to assign currentAdminId to new ID
     * @param ID
     */
    public void newAdmin(int ID) {
        currentOfficialID = ID;
    }

    /******
     *
     */
    public boolean adminDone() {
        return screenMessage.equalsIgnoreCase("DONE");
    }

    /********
     * Method to enable voting for voters
     * @param
     */
    public void openVoting() {
        openVoting = true;
        closeVoting = false;
    }

    /********
     * Method to enable voting session for certain time
     * @param
     */
    public void enableVotingSession() {
            enableVoting = true;
            disableVoting = false;
    }

    /*********
     * Method to disable voting session
     * @param
     */
    public void disableVotingSession () {
        disableVoting = true;
        enableVoting = false;
    }

    /**********
     * Method to close voting for voter.
     * @param
     */
    public void closeVoting() {
        closeVoting = true;
        openVoting = false;
    }
}
