package Socket;

<<<<<<< HEAD
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InputHandler {
    private static InputHandler instance;
    private String inputty = ""; // Current message being processed
    private final Object lock = new Object(); // Lock for synchronization
    private ServerSocket serverSocket;
=======
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

>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    private ObjectOutputStream clientOut;

    private InputHandler() {
        try {
<<<<<<< HEAD
            serverSocket = new ServerSocket(1234);
            System.out.println("Server is listening on port 1234");

            // Start a thread for terminal input
            new Thread(this::listenToTerminal).start();
        } catch (IOException e) {
=======
            serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
        } catch (IOException e) {
            System.err.println("Failed to open server on port " + port);
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    // Singleton pattern to ensure a single instance
=======
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    public static synchronized InputHandler getInstance() {
        if (instance == null) {
            instance = new InputHandler();
            new Thread(instance::start).start();
        }
        return instance;
    }

<<<<<<< HEAD
    // Start listening for client connections
=======
    public synchronized String getInput() {
        return inputty;
    }

    public synchronized void clearInput() {
        inputty = "";
    }

    public synchronized boolean getStatus() {
        return status.get();
    }

>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    public void start() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
<<<<<<< HEAD
                new Thread(() -> handleClient(socket)).start();
            }
        } catch (IOException e) {
=======

                new Thread(() -> handleClient(socket)).start();
            }
        } catch (IOException e) {
            System.err.println("Error while accepting client connection");
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    // Handle a connected client
=======
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
    private void handleClient(Socket socket) {
        try (
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())
        ) {
            this.clientOut = out;
<<<<<<< HEAD
            Object message;

            while ((message = in.readObject()) != null) {
                if (message instanceof String msg) {
                    processMessage(msg); // Process incoming message
                } else {
                    processMessage("template_done");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
=======

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
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
    // Process a message from the client
    private void processMessage(String message) {
        synchronized (lock) {
            // Wait until inputty is cleared
            while (!inputty.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // Set inputty to the new message
            inputty = message;
            System.out.println("Processing message: " + inputty);

            // Simulate processing
            simulateProcessing();
        }
    }

    // Simulate processing logic
    private void simulateProcessing() {
        try {
            // Simulate some processing time (e.g., interacting with drivers)
            Thread.sleep(1000); // Adjust the delay as needed
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Get the current inputty value
    public synchronized String getInput() {
        return inputty;
    }

    // Clear the inputty value
    public synchronized void clearInput() {
        synchronized (lock) {
            inputty = "";
            lock.notifyAll(); // Notify threads waiting for inputty to clear
            System.out.println("INPUTTTY cleared manually");
        }
    }

    // Send a message to the client
    public synchronized void sendToClient(Object message) {
=======

    public synchronized void sendToClient(Object message) {

>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
        if (clientOut != null) {
            try {
                clientOut.writeObject(message);
                clientOut.flush();
                System.out.println("Sent to client: " + message);
            } catch (IOException e) {
<<<<<<< HEAD
                e.printStackTrace();
            }
        }
    }

    // Get the status of the handler (dummy implementation to retain method)
    public synchronized boolean getStatus() {
        return inputty.isEmpty();
    }

    // Listen to input from the terminal
    private void listenToTerminal() {
        Scanner scanner = new Scanner(System.in);
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();  // Executor for scheduled tasks

        while (true) {
            System.out.println("Enter command: ");
            String command = scanner.nextLine();

            // Process terminal input (e.g., update inputty, send to client, etc.)
            synchronized (lock) {
                while (!inputty.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                inputty = command;
                System.out.println("Terminal input processed: " + inputty);

                // Schedule a task to clear inputty after 5 seconds if it hasn't been cleared
                scheduler.schedule(() -> {
                    synchronized (lock) {
                        if (!inputty.isEmpty()) {
                            System.out.println("Clearing inputty after 5 seconds...");
                            inputty = ""; // Clear inputty after timeout
                            lock.notifyAll(); // Notify any waiting threads
                        }
                    }
                }, 5, TimeUnit.SECONDS); // Delay of 5 seconds
            }
        }
    }
    }
=======
                System.err.println("Error sending object to client");
                e.printStackTrace();
            }
        }

    }
}
>>>>>>> c5eb49cdbebf3b97603439cba225034757d86d34
