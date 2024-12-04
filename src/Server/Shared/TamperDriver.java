package Server.Shared;

import Socket.InputHandler;


import java.io.IOException;

public class TamperDriver {
    private InputHandler socket;
    protected boolean tampered;


    private String message = null;

<<<<<<< HEAD
    public static boolean tamperedFailed;
=======
    protected boolean tamperedFailed;
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34

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

    public boolean checkFail() {
        String str = socket.getInput();
        if(str.equalsIgnoreCase("tfail"))
        {
            tampered = true;
        }
        return tampered;
    }
}
