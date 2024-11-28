package Server.Shared;

import Socket.Socket_Handler_Main;

import java.io.IOException;

public class TamperDriver {
    private Socket_Handler_Main socket;
    protected boolean tampered;

    protected boolean tamperedFailed;

    public TamperDriver () throws IOException {
        tampered = false;
        tamperedFailed = false;
        socket = Socket_Handler_Main.getInstance("localhost", 1234);
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

    /****
     * method to check if tamper driver failed
     */
    public boolean isTamperedDriverFailed() {
        return tamperedFailed;
    }
}
