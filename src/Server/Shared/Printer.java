package Server.Shared;

import java.io.BufferedWriter;
import java.io.IOException;
import Socket.InputHandler;

public class Printer {
    private InputHandler handler = InputHandler.getInstance();
    private BufferedWriter writer2;


    private int count = 1;

    boolean printerError;
    public Printer(){
        printerError = false;
        readInput();
    }
    public void print(String s) {
        if(!printerError){
            System.out.println(s);
        }
    }
    public String advance(){
        if(!printerError){
            return "\n\n\n\n";
        }
        return "";
    }
    public boolean checkFail(){
        return printerError;
    }

    private void setFail(){
        printerError = true;
    }

    private void readInput() {
                String idtype = handler.getInput().toLowerCase();
                switch (idtype) {
                    case "print_fail":
                        setFail();
                        break;
                }
                handler.clearInput();
            }

}
