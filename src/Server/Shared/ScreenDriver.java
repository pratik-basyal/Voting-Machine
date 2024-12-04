package Server.Shared;
import Socket.InputHandler;
public class ScreenDriver {

    private InputHandler socket = InputHandler.getInstance();

<<<<<<< HEAD
    private String message="";

    public static boolean screenFailed;

    public ScreenDriver() {
        screenFailed = false;
    }

    public void readInput() {
        if(socket.getInput().toLowerCase().startsWith("scd")) {
            message = socket.getInput().toLowerCase().substring(3);
            socket.clearInput();
        }
=======
    private String message = null;

    private boolean screenFailed;

    public ScreenDriver() {
        readInput();
        screenFailed = false;
    }

    private void readInput() {
        String message = socket.getInput().toLowerCase();
        socket.clearInput();
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34

    }

    /*******
     * method to get message from socket
     * @return
     */
    public String getMessage() {
<<<<<<< HEAD
        readInput();
        return message;
    }

    public void sendMessage(Object message) {
        socket.sendToClient(message);
    }

    public boolean checkFail() {
        String str = socket.getInput();
        if(str.equalsIgnoreCase("scfail"))
        {
            screenFailed = true;
        }
        return screenFailed;
=======
        return this.message;
    }

    public void sendMessage(Object message) {
        socket.sendToClient((String) message);
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    }


    public boolean screenFailed () {
        return screenFailed;
    }
}
