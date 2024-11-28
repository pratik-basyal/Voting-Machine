package Server.Shared;

import Socket.Socket_Handler_Main;

import java.io.IOException;

public class SD_Card_R {
    private boolean isSDCardFailed;
    private Socket_Handler_Main socket;

    public SD_Card_R() throws IOException {
        this.isSDCardFailed = false;
        this.socket = Socket_Handler_Main.getInstance("localhost", 12345);
    }

    public boolean isSDCardFailed() {
        return isSDCardFailed;
    }


}
