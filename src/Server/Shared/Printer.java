package Server.Shared;

import java.io.BufferedWriter;
import java.io.IOException;
import Socket.InputHandler;

public class Printer {
    private InputHandler handler = InputHandler.getInstance();

    private boolean isPrinted = false;
    private int count = 1;

    public static boolean printerError;
    public Printer(){
        printerError = false;
    }
    public String advance(){
        if(!printerError){
            return "\n\n\n\n";
        }
        return "";
    }
    public  synchronized boolean checkFail(){
        String input = handler.getInput().toLowerCase();
//        System.out.println("PRINTER IS LISTENING");
        if (input.equals("pfail")) {
            System.out.println("Checking Printer input");
            setFail();
        }
        return printerError;
    }

    private void setFail(){
        printerError = true;
    }

    public void printInput() throws InterruptedException {
        System.out.println("Printer is listening");
        String input = handler.getInput().toLowerCase();
                if(input.startsWith("pm")) {
                    System.out.println("Printing in Progress");
                    System.out.println("----------------------");
                    System.out.println(input.substring(2));
                    System.out.println("----------------------");
                    System.out.println("Printing Completed");
                    isPrinted = true;
                    handler.clearInput();
                }
    }

    public boolean printedConfirmed() {
        return isPrinted;
    }

}
