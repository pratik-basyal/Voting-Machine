package Server.Shared;
import Socket.InputHandler;
public class ScreenDriver {

    private InputHandler socket = InputHandler.getInstance();

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

    }

    /*******
     * method to get message from socket
     * @return
     */
    public String getMessage() {
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
    }


    public boolean screenFailed () {
        return screenFailed;
    }
}
