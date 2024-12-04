package Server.Shared;

import Socket.InputHandler;

public class Latch {
    // Private field to store the state of the latch
    private boolean isLocked;
    private boolean latchError;
    private InputHandler socket = InputHandler.getInstance();


    public Latch() {
        isLocked = false;
        latchError = false;
    }

    // Method to lock the latch
    public void lock() {
        if (!latchError) {
            isLocked = true;
        }
    }
    private void readInput() {
                String idtype = socket.getInput().toLowerCase();
//                socket.clearInput();
    }
    public void unlock() {
        if (!latchError) {
            isLocked = false;
        }
    }
    public boolean isLocked() {
        return isLocked;
    }
    public boolean checkFail(){ return latchError; }
}
