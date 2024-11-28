package Server.Shared;

import Socket.Socket_Handler_Main;

import java.io.IOException;

public class LatchDriver {
    private Socket_Handler_Main socket;
    private String latchStatus;

    public LatchDriver() throws IOException {
        this.socket = Socket_Handler_Main.getInstance("localhost", 1234);
        this.latchStatus = "opened";
    }


    /******
     * method to open latches
     */
    public void openLatches() {
       latchStatus = "opened";
    }

    /*******
     * method to close latches
     */
    public void closeLatches() {
       latchStatus = "closed";
    }
}
