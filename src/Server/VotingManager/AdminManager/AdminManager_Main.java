package Server.VotingManager.AdminManager;
import Server.Shared.ScreenDriver;
import Server.Shared.Latch;

/***********
 * This contains instance of :
 * Monitor, AdminManager, Voting, CardHolder
 */
public class AdminManager_Main implements Runnable{

    private ScreenDriver screen = new ScreenDriver();
    private Latch latch = new Latch();

    public int currentOfficialID=-1;

    public static boolean enableVoting;

    public static  boolean openVoting;

    public static boolean disableVoting;

    private static boolean closeVoting;
    String screenMessage= screen.getMessage();

    public static boolean message=true;


    public AdminManager_Main(boolean setting, boolean setting1, boolean setting2, boolean setting3) {
        this.enableVoting = setting;
        this.openVoting=setting1;
        this.closeVoting=setting2;
        this.disableVoting=setting3;
    }




    @Override
    public synchronized void run() {
        while(!adminDone()) {
//            System.out.println("ADMINCHECK ");
//            screen.readInput();
            screenMessage= screen.getMessage();

            if("evs".equalsIgnoreCase(screenMessage)) {
                enableVotingSession();
            }

            else if ("ovs".equalsIgnoreCase(screenMessage) && !enableVoting) {
                screen.sendMessage("scdEnableVSF");
            }
            else if ("ovs".equalsIgnoreCase(screenMessage) && enableVoting) {
                openVoting();
                latch.lock();
            }

            else if ("cvs".equalsIgnoreCase(screenMessage) && !openVoting) {
                screen.sendMessage("scdOpenVSF");
            }

            else if ("cvs".equalsIgnoreCase(screenMessage) && openVoting) {
                closeVoting();
            }

            else if ("dvs".equalsIgnoreCase(screenMessage) && !closeVoting) {
                screen.sendMessage("scdCloseVSF");
            }

            else if ("dvs".equalsIgnoreCase(screenMessage) && closeVoting) {
                disableVotingSession();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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
//        System.out.println("ADMIN CHECK : " + screenMessage);
        message = true;
//        System.out.println("Admin Manager Check:"+ screenMessage);
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

    public boolean isEnableVoting() {
        return enableVoting;
    }

    public boolean isOpenVoting() {
        return openVoting;
    }

    public boolean isCloseVoting() {
        return closeVoting;
    }

    public boolean isDisableVoting() {
        return disableVoting;
    }

    public void sendMessage(String message) {
        screen.sendMessage("scd" + message);
    }

    public void getBoolean(boolean fromVM) {
        message = fromVM;
    }
}
