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
public class MockStorage implements Storage {

    private ArrayList<HashMap<String, String>> members = new ArrayList<>();
    private ArrayList<HashMap<String, String>> competitionResults = new ArrayList<>();
    private ArrayList<HashMap<String, String>> trainingResults = new ArrayList<>();
    private ArrayList<String> getSwimmingDiscplines;
    private int highestNumber;
    private ArrayList<Integer> swimmingDiscplines;

    private int[] memberIDs;
    private int memberIDIndex = 0;

    public MockStorage() {
    }

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
    public ArrayList<Integer> getSwimmingDisciplines(int member_id) {
        return swimmingDiscplines;
    }

    @Override
    public boolean removeMember(int member_id) {
        return false;
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

    public void setGetSwimmingDiscplines(ArrayList<String> getSwimmingDiscplines) {
        this.getSwimmingDiscplines = getSwimmingDiscplines;
    }

    public void setSwimmingDiscplines(ArrayList<Integer> swimmingDiscplines) {
        this.swimmingDiscplines = swimmingDiscplines;
    }

    @Override
    public boolean createMember(Member member) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
