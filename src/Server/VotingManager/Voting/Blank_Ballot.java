package Server.VotingManager.Voting;

import Server.Shared.SD_Card_R;

import java.io.IOException;

public class Blank_Ballot {
    private SD_Card_R sdCardReader; // Reference to the SD card reader
    private String blankBallotContents; // Stores the retrieved blank ballot

    public Blank_Ballot() throws IOException {
        this.sdCardReader = new SD_Card_R();
        this.blankBallotContents = null;
    }

    // Retrieves the blank ballot from the SD card
    public void fetchBlankBallot() {
        try {
            this.blankBallotContents = sdCardReader.readBlankBallot();
            System.out.println("Blank ballot successfully fetched:");
        } catch (Exception e) {
            System.out.println("Failed to fetch blank ballot: " + e.getMessage());
        }
    }

    // Returns the blank ballot contents
    public String getBlankBallot() {
        if (blankBallotContents == null) {
            throw new IllegalStateException("Blank ballot has not been fetched yet.");
        }
        return blankBallotContents;
    }

    
}
