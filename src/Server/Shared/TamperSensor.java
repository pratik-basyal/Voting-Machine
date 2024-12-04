package Server.Shared;

import Socket.InputHandler;
import java.util.Scanner;

public class TamperSensor {
    private boolean tampered = false;
    private InputHandler socket = InputHandler.getInstance();


    public boolean checkFail() {
        String str = socket.getInput();
        if(str.equalsIgnoreCase("tmp"))
        {
            tampered = true;
        }
        return tampered;
    }
}
