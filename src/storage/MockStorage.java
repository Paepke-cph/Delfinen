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
    private int highestNumber;
    private ArrayList<Integer> swimmingDisciplines;

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

    @Override
    public Integer getNextMemberID() {
        return memberIDs[memberIDIndex++];
    }

    @Override
    public Integer getNextCompetitionID() {
        return 0;
    }

    @Override
    public Integer getNextTrainingID() {
        return 0;
    }


    @Override
    public boolean removeMember(int member_id) {
        return false;
    }

    @Override
    public boolean createMember(Member member) {
        return false;
    }

    public void setCompetitionResults(ArrayList<HashMap<String, String>> competitionResults) {
        this.competitionResults = competitionResults;
    }

    public void setTrainingResults(ArrayList<HashMap<String, String>> trainingResults) {
        this.trainingResults = trainingResults;
    }

    public void setSwimmingDisciplines(ArrayList<Integer> swimmingDisciplines) {
        this.swimmingDisciplines = swimmingDisciplines;
    }

    @Override
    public ArrayList<HashMap<String, String>> getMembersByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        return swimmingDisciplines;
    }

    @Override
    public ArrayList<HashMap<String, String>> getTopFiveTrainingResultsByDiscipline(int discipline_id) {
        return null;
    }

    @Override
    public ArrayList<HashMap<String, String>> getTopFiveCompetitionResultsByDiscipline(int discipline_id) {
        return null;
    }

    @Override
    public boolean updateMember(Member member) {
        return false;
    }

    @Override
    public ArrayList<HashMap<String, String>> getTopFiveTrainingResultsByDiscipline(int discipline_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<HashMap<String, String>> getTopFiveCompetitionResultsByDiscipline(int discipline_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
