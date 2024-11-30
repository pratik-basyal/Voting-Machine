package Server.Shared;

import Socket.InputHandler;
import java.util.Scanner;

public class TamperSensor {
    private boolean tampered = false;
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


}
