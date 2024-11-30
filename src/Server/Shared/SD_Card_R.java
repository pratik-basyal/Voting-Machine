package Server.Shared;

import Socket.InputHandler;

import java.io.IOException;

import java.io.*;

public class SD_Card_R {
    private boolean isSDCardFailed;
    private InputHandler socket;

    private File blankBallotFile;

    public SD_Card_R() throws IOException {
        this.isSDCardFailed = false;
        this.socket = InputHandler.getInstance();
        this.blankBallotFile = new File("/Users/pratik/Desktop/Fall-2024/" +
                "460/Voting Machine/Voting_Machine/Voting_Machine/src/Server/Shared/blankBallot.txt");
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
        sendBlankBallotToClient();
        return text.toString();
    }

    public void sendBlankBallotToClient() {
        socket.sendToClient(String.valueOf(blankBallotFile));
    }

    public boolean isSDCardFailed() {
        return isSDCardFailed;
    }


}
