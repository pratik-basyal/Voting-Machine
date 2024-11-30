package Server.Shared;

import Socket.InputHandler;

public class SD_Card_W1 {

    InputHandler socket;

    private String message;

    public SD_Card_W1() {
        this.socket = InputHandler.getInstance();
        this.message = null;
    }

    boolean cardFailed = false;

    public void readInput() {
        message = socket.getInput();
    }

    public String getMessage() {
        return message;
    }

    public boolean isCardFailed() {
        return cardFailed;
    }


}
