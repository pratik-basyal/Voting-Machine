package Socket;

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
    private ObjectOutputStream clientOut;

    private InputHandler() {
        try {
            serverSocket = new ServerSocket(1234);
            System.out.println("Server is listening on port 1234");

            // Start a thread for terminal input
            new Thread(this::listenToTerminal).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Singleton pattern to ensure a single instance
    public static synchronized InputHandler getInstance() {
        if (instance == null) {
            instance = new InputHandler();
            new Thread(instance::start).start();
        }
        return instance;
    }

    // Start listening for client connections
    public void start() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                new Thread(() -> handleClient(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Handle a connected client
    private void handleClient(Socket socket) {
        try (
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())
        ) {
            this.clientOut = out;
            Object message;

            while ((message = in.readObject()) != null) {
                if (message instanceof String msg) {
                    processMessage(msg); // Process incoming message
                } else {
                    processMessage("template_done");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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
        if (clientOut != null) {
            try {
                clientOut.writeObject(message);
                clientOut.flush();
                System.out.println("Sent to client: " + message);
            } catch (IOException e) {
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
