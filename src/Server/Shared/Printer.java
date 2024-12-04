package Server.Shared;

import java.io.BufferedWriter;
import java.io.IOException;
import Socket.InputHandler;

public class Printer {
    private InputHandler handler = InputHandler.getInstance();
<<<<<<< HEAD

    private boolean isPrinted = false;
    private int count = 1;

    public static boolean printerError;
    public Printer(){
        printerError = false;
=======
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
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    }
    public String advance(){
        if(!printerError){
            return "\n\n\n\n";
        }
        return "";
    }
<<<<<<< HEAD
    public  synchronized boolean checkFail(){
        String input = handler.getInput().toLowerCase();
//        System.out.println("PRINTER IS LISTENING");
        if (input.equals("pfail")) {
            System.out.println("Checking Printer input");
            setFail();
        }
=======
    public boolean checkFail(){
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
        return printerError;
    }

    private void setFail(){
        printerError = true;
    }

<<<<<<< HEAD
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
=======
    private void readInput() {
                String idtype = handler.getInput().toLowerCase();
                switch (idtype) {
                    case "print_fail":
                        setFail();
                        break;
                }
                handler.clearInput();
            }
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34

}
