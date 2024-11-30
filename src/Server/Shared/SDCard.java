//package Server.Shared;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//
//import Socket.InputHandler;
//public class SDCard {
//    TamperSensor tamper = new TamperSensor();
//    InputHandler handler = InputHandler.getInstance();
//
//    private String fileName;
//    private BufferedReader reader;
//    private BufferedWriter writer;
//    private String fileContents;
//    private char type;
//    private boolean fail = false;
//
//    public SDCard(String fileName, char type) {
//        this.fileName = fileName;
//        this.type = type;
//    }
//
//
//    public void openFileForWriting() throws IOException {
//        if(type == 'w'&&(!fail)) {
//            writer = new BufferedWriter(new FileWriter(fileName, true));
//        }
//    }
//    public void openFileForReading() throws IOException {
//        if(type == 'R'&&(!fail)) {
//            reader = new BufferedReader(new FileReader(fileName));
//            fileContents = readFile();
//        }
//    }
//
//    public String readFile() throws IOException {
//        if(type == 'R'&&(!fail)) {
//
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line).append("\n");
//            }
//            return sb.toString();
//        }
//        return null;
//    }
//    public void writeFile(String content) throws IOException {
//        if (type == 'W'&&(!fail)) {
//            if (!tamper.isTampered()) {
//                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
//                    // Write the content to the file
//                    writer.write(content);
//                    writer.write("=================================================================");
//                    writer.newLine();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//
//    public void closeFile() throws IOException {
//        if (reader != null) {
//            reader.close();
//        }
//        if (writer != null) {
//            writer.close();
//        }
//    }
//
//    public boolean checkFail(){
//        return fail;
//    }
//
//    private void readInput() {
//        new Thread(() -> {
//            while (true) {
//                String idtype = handler.getInput().toLowerCase();
//                switch (idtype) {
//                    case "sd_fail":
//                        setFail();
//                        break;
//                    default:
//                        continue;
//                }
//                handler.clearInput();
//
//            }
//        }).start();
//    }
//    private String getFileContents() {
//        return fileContents;
//    }
//
//
//    // Method to write a string to the file
//
//    private void setFail(){
//        fail = true;
//    }
//}
