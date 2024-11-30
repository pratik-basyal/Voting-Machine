package Server.Shared;

import Socket.InputHandler;


import java.io.IOException;

public class TamperDriver {
    private InputHandler socket;
    protected boolean tampered;


    private String message = null;

    protected boolean tamperedFailed;

    public TamperDriver () throws IOException {
        tampered = false;
        tamperedFailed = false;
        socket = InputHandler.getInstance();
    }

    /******
     * method to set tampered true
     */
    public void isTampered() {
        tampered = true;
    }

    /********
     *
     */
    public void setTamperedFailed() {
        tamperedFailed = true;
    }

    public void readInput(){
        message = socket.getInput();
    }

    /****
     * method to check if tamper driver failed
     */
    public boolean isTamperedDriverFailed() {
        return tamperedFailed;
    }
}
