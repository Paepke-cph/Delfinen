package storage;

import core.Member;
import ui.MockUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alexander
 */
public class MockStorage implements Storage{

    private ArrayList<HashMap<String, String>> members = new ArrayList<>();
    private ArrayList<HashMap<String, String>> competitionResults = new ArrayList<>();
    private ArrayList<HashMap<String, String>> trainingResults = new ArrayList<>();
    private ArrayList<String> getSwimmingDiscplines;

    private int[] memberIDs;
    private int memberIDIndex = 0;

    public MockStorage() {}

    public MockStorage(int[] memberIDs) {
        this.memberIDs = memberIDs;
    }

    @Override
    public ArrayList<HashMap<String, String>> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<HashMap<String, String>> members) {
        this.members = members;
    }
    public int getNextMemberID() {
        return memberIDs[memberIDIndex++];
    }

    @Override
    public ArrayList<HashMap<String, String>> getCompetitionResults(int member_id) {
        return competitionResults;
    }

    @Override
    public ArrayList<HashMap<String, String>> getTrainingResults(int member_id) {
        return trainingResults;
    }

    @Override
    public ArrayList<String> getSwimmingDiscplines(int member_id) {
        return getSwimmingDiscplines;
    }

    public void setCompetitionResults(ArrayList<HashMap<String, String>> competitionResults) {
        this.competitionResults = competitionResults;
    }

    public void setTrainingResults(ArrayList<HashMap<String, String>> trainingResults) {
        this.trainingResults = trainingResults;
    }

    public void setHighestNumber(int highestNumber) {
        this.highestNumber = highestNumber;
    }

    public void setGetSwimmingDiscplines(ArrayList<String> getSwimmingDiscplines) {
        this.getSwimmingDiscplines = getSwimmingDiscplines;
    }
    
    
    
>>>>>>> ee9bb576649b61b7e422afb7899ad04e3a8758a3

}
