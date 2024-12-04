package Server.VotingManager.AdminManager;
import Server.Shared.ScreenDriver;
import Server.Shared.Latch;

/***********
 * This contains instance of :
 * Monitor, AdminManager, Voting, CardHolder
 */
public class AdminManager_Main implements Runnable{

<<<<<<< HEAD
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
=======
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
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    }




    @Override
<<<<<<< HEAD
    public synchronized void run() {
        while(!adminDone()) {
//            System.out.println("ADMINCHECK ");
//            screen.readInput();
            screenMessage= screen.getMessage();
=======
    public void run() {
        while(!adminDone()) {
            String screenMessage = screen.getMessage();
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34

            if("evs".equalsIgnoreCase(screenMessage)) {
                enableVotingSession();
            }
<<<<<<< HEAD

            else if ("ovs".equalsIgnoreCase(screenMessage) && !enableVoting) {
                screen.sendMessage("scdEnableVSF");
            }
            else if ("ovs".equalsIgnoreCase(screenMessage) && enableVoting) {
=======
            else if ("ov".equalsIgnoreCase(screenMessage) && !enableVoting) {
                System.out.println("ENABLE THE VOTING SESSION FIRST");
            }
            else if ("ov".equalsIgnoreCase(screenMessage) && enableVoting) {
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
                openVoting();
                latch.lock();
            }

<<<<<<< HEAD
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

=======
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
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
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
<<<<<<< HEAD
//        System.out.println("ADMIN CHECK : " + screenMessage);
        message = true;
//        System.out.println("Admin Manager Check:"+ screenMessage);
=======
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
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
<<<<<<< HEAD
        enableVoting = true;
        disableVoting = false;
=======
            enableVoting = true;
            disableVoting = false;
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
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
<<<<<<< HEAD

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
=======
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
}
