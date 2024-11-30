package Socket;

import javafx.application.Platform;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class InputHandler {
    private static InputHandler instance;
    private AtomicBoolean status = new AtomicBoolean(false);
    private String inputty = "";
    int port = 1234;
    private ServerSocket serverSocket;

    private ObjectOutputStream clientOut;

    private InputHandler() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
        } catch (IOException e) {
            System.err.println("Failed to open server on port " + port);
            e.printStackTrace();
        }
    }

    public static synchronized InputHandler getInstance() {
        if (instance == null) {
            instance = new InputHandler();
            new Thread(instance::start).start();
        }
        return instance;
    }

    public synchronized String getInput() {
        return inputty;
    }

    public synchronized void clearInput() {
        inputty = "";
    }

    public synchronized boolean getStatus() {
        return status.get();
    }

    public void start() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                new Thread(() -> handleClient(socket)).start();
            }
        } catch (IOException e) {
            System.err.println("Error while accepting client connection");
            e.printStackTrace();
        }
    }

    private void handleClient(Socket socket) {
        try (
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())
        ) {
            this.clientOut = out;

            Object message;

            while ((message = in.readObject()) != null) {
                if(message  instanceof String){

                    String msg = (String) message;
                    inputty = msg;

                } else {
                    inputty = "template_done";
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error handling client communication");
            e.printStackTrace();
        }
    }


    public synchronized void sendToClient(Object message) {

        if (clientOut != null) {
            try {
                clientOut.writeObject(message);
                clientOut.flush();
                System.out.println("Sent to client: " + message);
            } catch (IOException e) {
                System.err.println("Error sending object to client");
                e.printStackTrace();
            }
        }

    }
}
