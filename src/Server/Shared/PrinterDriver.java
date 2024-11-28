package Server.Shared;

import Socket.Socket_Handler_Main;

import java.io.IOException;

public class PrinterDriver {
    private boolean printerFailed;
    private Socket_Handler_Main socket;

    public PrinterDriver () throws IOException {
        this.socket = Socket_Handler_Main.getInstance("localhost", 12345);
        this.printerFailed = false;
    }
    public boolean isPrinterFailed() {
        return printerFailed;
    }
}
