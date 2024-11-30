package Client;

import java.util.List;
import Socket.InputHandler;
public class Screen {
    private boolean userDone = false;


    public void ScreenOn(){

    }
    public int constraint;
    public int count = 0;

    public String background = "#cad6e8";
    public String select = "#aebcd6";

    public String blueBack = "#cad6e8";
    public String blueSelect = "#aebcd6";

    public String pinkBack = "#f4ebf7";
    public String pinkSelect = "#c4aad1";

    public String greenBack = "#d9fadc";
    public String greenSelect = "#9EDF9C";
    private InputHandler handler = InputHandler.getInstance();



    public Screen(){
        readInput();
    }
    public void showTemplate(Template template){
        userDone = false;
        handler.sendToClient(template);
    }
    public boolean exitReady(){
        return userDone;
    }

    private void readInput() {
        new Thread(() -> {
            while (true) {
                String idtype = handler.getInput().toLowerCase();
                switch (idtype) {
                    case "template_done":
                        userDone = true;
                        break;
                    default:
                        continue;
                }
                handler.clearInput();

            }
        }).start();
    }
}

