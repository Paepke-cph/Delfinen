package storage;

import core.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alexander
 */
public class MockStorage implements Storage {

    private ArrayList<HashMap<String, String>> members = new ArrayList<>();
    private ArrayList<HashMap<String, String>> competitionResults = new ArrayList<>();
    private ArrayList<HashMap<String, String>> trainingResults = new ArrayList<>();
    private int highestNumber;
    private ArrayList<Integer> swimmingDiscplines;

    public MockStorage() {
    }

    @Override
    public ArrayList<HashMap<String, String>> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<HashMap<String, String>> members) {
        this.members = members;
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
    public ArrayList<Integer> getSwimmingDisciplines(int member_id) {
        return swimmingDiscplines;
    }

    @Override
    public int getNextMemberID() {
        return highestNumber + 1;
    }

    @Override
    public ArrayList<HashMap<String, String>> getMembersByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public void setSwimmingDiscplines(ArrayList<Integer> swimmingDiscplines) {
        this.swimmingDiscplines = swimmingDiscplines;
    }

}
