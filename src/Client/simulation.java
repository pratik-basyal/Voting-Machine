package Client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class simulation extends Application {

    // Assuming you have a list to store selected options
    List<String> selectedOptions = new ArrayList<>(Arrays.asList("Option 1", "Option 2", "Option 3", "Option4", "Option 5"));
    private Socket socket;
    private Template template2;
    private int counter;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Stage primaryStage;


    private boolean ScreenStatus = false;
    private BorderPane screen = new BorderPane();
    private VBox stuff = new VBox();


    public void ScreenOn(){
        ScreenStatus = true;
    }
    public int constraint;
    public int count = 0;
    public int color = 0;

    public String background = "#0c605d";
    public String select = "#0c605d";

    public String blueBack = "#cad6e8";
    public String blueSelect = "#aebcd6";

    public String pinkBack = "#f4ebf7";
    public String pinkSelect = "#c4aad1";

    public String greenBack = "#d9fadc";
    public String greenSelect = "#9EDF9C";
    BorderPane root = new BorderPane();
    BorderPane empty = new BorderPane();

    BorderPane rooty = new BorderPane();



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        connectToServer();
        mainUI();
        // Send initialization signal to server
        sendObjectToServer("template_done");

        Scene scene = new Scene(rooty, 1200, 1000);
        primaryStage.setTitle("Voting machine demo");
        primaryStage.setScene(scene);
        primaryStage.show();
        this.primaryStage = primaryStage;
    }

    public void mainUI(){
        // Main UI container
        empty.setMinSize(1000, 1000);
        empty.setMaxSize(1000, 1000);
        empty.setStyle("-fx-border-color: black; -fx-border-width: 5; -fx-background-color: white;");
        root.setCenter(empty);

        screen.setMaxSize(900, 800);
        screen.setMinSize(900, 800);
        screen.setStyle("-fx-border-color: black; -fx-border-width: 5;");
        root.setStyle("-fx-background-color: #0c605d;");
        root.setMaxSize(1200, 1200);
        root.setMinSize(1200, 1200);
        rooty.setCenter(root);
        TextField readerField = new TextField ();
        readerField.clear();
        // Create the card reader (text box)
        readerField.setPromptText("Insert card...");
        readerField.setFont(Font.font("Verdana", 18));
        readerField.setMinHeight(40);
        readerField.setMaxWidth(150);
        readerField.setOnAction(e -> {
            String cardData = readerField.getText();
            if (!cardData.isEmpty()) {
                sendObjectToServer("reader:" + cardData); // Send card data to the server
                readerField.clear(); // Clear the field after sending
            }
        });

        // Add the text box to the screen's top
        VBox headerBox = new VBox();
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(15));

        // "Welcome Voting 2024" Header
        Label headerLabel = new Label("Welcome Voting 2024");
        headerLabel.setFont(Font.font("Verdana", 48));
        headerLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #ddddf6;");

        // USA Flag Image (ensure the image file is available on your system)
        ImageView usaFlag = new ImageView(new Image("Client/f.png"));  // Add your image path here
        usaFlag.setFitWidth(300); // Resize the flag to a reasonable size
        usaFlag.setPreserveRatio(true);

        // "Please scan your ID" label
        Label scanLabel = new Label("Please scan your ID");
        scanLabel.setFont(Font.font("Verdana", 24));
        scanLabel.setStyle("-fx-text-fill: #000000;");

        // Add the header components
        headerBox.getChildren().addAll(headerLabel, usaFlag, scanLabel, readerField);

        // Attach the header box to the top of the screen
        screen.setTop(headerBox);
        root.setCenter(screen);
    }
    public void showTemplate(Template template){
        root.setCenter(screen);
        clear();
        if(color==0){
            background = blueBack;
            select = blueSelect;
            color++;
        } else if(color==1){
            background = pinkBack;
            select = pinkSelect;
            color++;
        } else if(color==2){
            background = greenBack;
            select = greenSelect;
            color = 0;
        }
        screen.setCenter(stuff);
        constraint = template.getConstraint();

        stuff.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        stuff.setPadding(new Insets(25, 25, 10, 25));
        stuff.setStyle("-fx-background-color: "+ background);
        stuff.setAlignment(Pos.CENTER);
        VBox heads = new VBox();
        heads.setStyle("-fx-background-color: "+background);
        // Ensure parent layout stretches
        screen.setCenter(stuff);
        BorderPane.setAlignment(stuff, Pos.CENTER);

        heads.setAlignment(Pos.CENTER);
        screen.setTop(heads);

        Label head = new Label(template.getHeader());
        head.setWrapText(true);
        head.setAlignment(Pos.CENTER);
        head.setFont(Font.font("Verdana", 48));
        head.setMinWidth(800);
        head.setMaxWidth(800);
        head.setStyle(" -fx-background-color:" + select);

        Label desc = new Label(template.getDescription());
        desc.setWrapText(true);

        desc.setAlignment(Pos.CENTER);
        desc.setFont(Font.font("Verdana", 18));
        desc.setMinWidth(800);
        desc.setMaxWidth(800);
        desc.setStyle(" -fx-background-color: " + select);



        Label cons = new Label(template.getConsdesc());
        cons.setAlignment(Pos.CENTER);
        cons.setFont(Font.font("Verdana", 18));
        cons.setMinSize(800,50);
        cons.setStyle(" -fx-background-color: " + select);
        heads.getChildren().addAll(head,desc,cons);

        for(int i = 0; i < 5;i++){
            String s =template.getOptions(i).getFirst().toString();
            if(!s.equals("") && !(s == null)) {
                Label option = new Label(s);
                option.setAlignment(Pos.CENTER);
                option.setFont(Font.font("Verdana", 24));
                option.setMinSize(800,100);
                option.setMaxSize(800,100);
                option.setStyle("-fx-background-radius: 20;");
                boolean check = (boolean) template.getOptions(i).getSecond();
                if(check){
                    option.setStyle(" -fx-background-color: " + select +"; -fx-background-radius: 20;");
                }
                int finalI = i;
                option.setOnMouseClicked(event -> {
                    int county = 0;
                    for(int j = 0;j < 5; j++){
                        if((boolean) template.getOptions(j).getSecond() ==true){
                            county++;
                        }
                    }
                    boolean checks = (boolean) template.getOptions(finalI).getSecond();
                    if(checks){
                        template.getOptions(finalI).setSecond(false);
                        option.setStyle(" -fx-background-color: " + background +"; -fx-background-radius: 20;");
                    } else if (county < constraint){
                        template.getOptions(finalI).setSecond(true);
                        option.setStyle(" -fx-background-color: " + select +"; -fx-background-radius: 20;");

                    }
                });
                stuff.getChildren().add(option);
            }
        }


        HBox buttonField = new HBox();
        buttonField.setStyle("-fx-background-color:  #1d457c#1f477e#20477e#21477e#22477e#23477e#0f0d41"+ background);

        buttonField.setPrefHeight(100);
        buttonField.setAlignment(Pos.CENTER);
        buttonField.setSpacing(70);

        for(int i = 0; i < 3;i++){
            String s =template.getButtons(i).getFirst().toString();
            if(!s.equals("") && !(s == null)) {
                Label button1 = new Label(s);
                button1.setAlignment(Pos.CENTER);
                button1.setMinSize(200, 100);
                button1.setFont(Font.font("Verdana", 36));
                button1.setStyle("-fx-background-radius: 20; -fx-background-color:  #6983b4#348a8c"+ select);
                buttonField.getChildren().add(button1);
                int finalI = i;
                button1.setOnMouseClicked(event -> {

                    if(finalI==2){
                        for (int j = 0; j < 5; j++) {
                            if ((Boolean) template.getOptions(j).getSecond()) {
                                    selectedOptions.set(count, template.getOptions(j).getFirst().toString());
                                    break;
                            }
                        }
                        count++;
                        System.out.println(count);
                        if (count == 1) {
                            template.votingTemplateII(template);
                            sendObjectToServer(template);
                            template2 = new Template(); // Create or get another template instance
                            template2.votingTemplateII(template2);
                            prevTemplate(template2);
                        }
                        else if (count == 2) {
                            template.votingTemplateIII(template);
                            sendObjectToServer(template);
                            template2 = new Template(); // Create or get another template instance
                            template2.votingTemplateIII(template2);
                            prevTemplate(template2);
                        }
                        else if (count == 3) {
                            template.votingTemplateIV(template);
                            sendObjectToServer(template);
                            template2 = new Template(); // Create or get another template instance
                            template2.votingTemplateIV(template2);
                            prevTemplate(template2);
                        }
                        else if (count == 4) {
                            template.votingTemplateV(template);
                            sendObjectToServer(template);
                            template2 = new Template(); // Create or get another template instance
                            template2.votingTemplateV(template2);
                            prevTemplate(template2);
                        }

                    }
                    if(finalI==1){
                        for (int j = 0; j < 5; j++) {
                            if ((Boolean) template.getOptions(j).getSecond()) {
                                selectedOptions.set(count,template.getOptions(j).getFirst().toString());
                                break;
                            }
                        }
                        sendObjectToServer(selectedOptions);
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Confirmation");
                            alert.setHeaderText(null);
                            alert.setContentText("Thanks for Voting!");
                            alert.showAndWait();
                            count = 0;
                            // Set the background for the main container (e.g., VBox or BorderPane)
                            stuff.setStyle("-fx-background-color: #0c605d;");
                            screen.setStyle("-fx-background-color: #0c605d;");
                            buttonField.setStyle("-fx-background-color:#0c605d");
                            clear();
                            selectedOptions = new ArrayList<>(Arrays.asList("Option 1", "Option 2", "Option 3", "Option4", "Option 5"));
                            mainUI();
                        });

                    }
                    if (finalI == 0) {
                        count--;
                        System.out.println(selectedOptions);

                        if (count == 0) {
                            template.votingTemplate(template);
                            sendObjectToServer(template);
                            template2 = new Template(); // Create or get another template instance
                            template2.votingTemplate(template2);
                            prevTemplate(template2);
                        }
                        else if (count == 1) {
                            template.votingTemplateII(template);
                            sendObjectToServer(template);
                            template2 = new Template(); // Create or get another template instance
                            template2.votingTemplateII(template2);
                            prevTemplate(template2);
                        }
                        else if (count == 2) {
                            template.votingTemplateIII(template);
                            sendObjectToServer(template);
                            template2 = new Template(); // Create or get another template instance
                            template2.votingTemplateIII(template2);
                            prevTemplate(template2);
                        }
                        else if (count == 3) {
                            template.votingTemplateIV(template);
                            sendObjectToServer(template);
                            template2 = new Template(); // Create or get another template instance
                            template2.votingTemplateIV(template2);
                            prevTemplate(template2);

                        }
                    }
                });
            } else{
                Region blankSpace = new Region();
                blankSpace.setMinSize(200, 100);
                blankSpace.setMaxSize(200, 100);
                buttonField.getChildren().add(blankSpace);

            }
        }
        screen.setBottom(buttonField);
    }
    private void prevTemplate(Template template2){
        // Automatically select the previously selected option
        if (!selectedOptions.isEmpty()) {
            String previouslySelected = selectedOptions.get(count);
            for (int j = 0; j < 5; j++) {
                if (template2.getOptions(j).getFirst().toString().equals(previouslySelected)) {
                    template2.getOptions(j).setSecond(true); // Select the option in the template
                    break;
                }
            }
        }
        showTemplate(template2);  // Display the new template
    }
    private void clear(){
        stuff.getChildren().clear();
        if (screen.getTop() instanceof Pane) {
            ((Pane) screen.getTop()).getChildren().clear();
        }
        if (screen.getBottom() instanceof Pane) {
            ((Pane) screen.getBottom()).getChildren().clear();
        }

    }
    private void connectToServer() {
        try {
            socket = new Socket("localhost", 1234);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            // Start a thread to listen for server messages
            new Thread(this::listenForServerMessages).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendObjectToServer(Object object) {
        try {
            out.writeObject(object);
            out.flush();
            System.out.println("Sent object to server: " + object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*=======================================
    /TODO: Use these whenever you want to force a failure (for testing purposes in the demo)
    sendObjectToServer("print_fail");
    sendObjectToServer("read_fail");
    sendObjectToServer("trip_sensor");

     ========================================*/

    private void listenForServerMessages() {
        try {
            Object message;
            while ((message = in.readObject()) != null) {
                if (message instanceof String msg) {
                    switch (msg.split(":")[0].toLowerCase()) {
                        case "reader" -> {
                            String[] parts = msg.split(":");

                        }
                        case "ejected" -> {

                        }
                        case "printer" -> {
                            /*================================================
                            /TODO: However you want to react to the printers message goes here
                             ================================================*/

                        }
                        default -> System.out.println("Unhandled message: " + msg);
                    }
                } else if (message instanceof Template template) {
                    Platform.runLater(() -> showTemplate(template));
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}