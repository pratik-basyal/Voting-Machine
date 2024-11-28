package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Socket_Handler_Main {

    private Socket socket;

    private static Socket_Handler_Main instance; //single instance
    private PrintWriter out;
    private BufferedReader in;

    //Constructor to establish the socket connection
    public Socket_Handler_Main(String host, int port) throws IOException {
        this.socket = new Socket(host, port); //Connect to the server
        this.out = new PrintWriter(socket.getOutputStream(), true); //To send the data
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    //static method to provide shared instance
    public static Socket_Handler_Main getInstance(String host, int port) throws IOException {
        if (instance != null) {
            instance = new Socket_Handler_Main(host, port);
        }
        return instance;
    }

    //Method to send a command to the server
    public void sendCommand(String command) {
        out.println(command);
    }

    //Method to receive the server's response
    public String receiveResponse() throws IOException {
        return in.readLine();
    }

    //Method to close the socket connection
    public void close() throws Exception {
        in.close();
        out.close();
        socket.close();
    }
}
