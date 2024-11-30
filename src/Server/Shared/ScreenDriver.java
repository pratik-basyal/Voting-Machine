package Server.Shared;
import Socket.InputHandler;
public class ScreenDriver {

    private InputHandler socket = InputHandler.getInstance();

    private String message = null;

    private boolean screenFailed;

    public ScreenDriver() {
        readInput();
        screenFailed = false;
    }

    private void readInput() {
        String message = socket.getInput().toLowerCase();
        socket.clearInput();

    }

    /*******
     * method to get message from socket
     * @return
     */
    public String getMessage() {
        return this.message;
    }

    public void sendMessage(Object message) {
        socket.sendToClient((String) message);
    }


    public boolean screenFailed () {
        return screenFailed;
    }
}
