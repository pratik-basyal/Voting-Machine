package Server.Shared;

import Socket.InputHandler;

import java.io.IOException;

import java.io.*;

public class SD_Card_R {
    public static boolean isSDCardFailed;
    private InputHandler socket;

    private File blankBallotFile;

    public SD_Card_R() throws IOException {
        this.isSDCardFailed = false;
        this.socket = InputHandler.getInstance();
        this.blankBallotFile = new File("/Users/pratik/Desktop/Fall-2024/460/Voting_Machine/Voting_Machine/Voting_Machine_Final/src/Server/Shared/blankBallot.txt");
    }

    // Reads the content of the blank ballot file
    public String readBlankBallot() {
        if (isSDCardFailed) {
            throw new IllegalStateException("SD Card has failed. Cannot read the blank ballot.");
        }
        return readFile(blankBallotFile);
    }

    /*****
     * Method to read the contents of any file
     * @param file
     * @return
     */
    public String readFile(File file) {
        boolean firstLine = true;
        StringBuilder text = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(firstLine) {
                    firstLine = false;
                    continue;
                }
                text.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }


    public boolean checkFail() {
        String str = socket.getInput();
        if(str.equalsIgnoreCase("sdrfail"))
        {
            isSDCardFailed = true;
        }
        return isSDCardFailed;
    }

    public void sendBallot() {
        socket.sendToClient("BB:" + readFile(blankBallotFile));
    }

}
