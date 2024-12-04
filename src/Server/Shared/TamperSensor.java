package Server.Shared;

import Socket.InputHandler;
import java.util.Scanner;

public class TamperSensor {
    private boolean tampered = false;
<<<<<<< HEAD
    private InputHandler socket = InputHandler.getInstance();


    public boolean checkFail() {
        String str = socket.getInput();
        if(str.equalsIgnoreCase("tmp"))
        {
            tampered = true;
        }
        return tampered;
    }
=======
    private InputHandler handler = InputHandler.getInstance();


    public TamperSensor() {
        readInput();
    }




    public boolean isTampered() {
        return tampered;
    }
    private void tamperedMachine() {
        tampered = true;}

    private void readInput() {
        new Thread(() -> {
            while (true) {
                String idtype = handler.getInput().toLowerCase();
                switch (idtype) {
                    case "tamper":
                        break;
                    case "trip_sensor":
                        tamperedMachine();
                        break;
                    default:
                        continue;
                }
                handler.clearInput();

            }
        }).start();
    }


>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
}
