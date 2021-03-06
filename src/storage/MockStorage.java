package storage;

import core.CompetitionResult;
import core.Member;
import core.TrainingResult;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Alexander
 * @author Benjamin
 * @author Mads
 * @author Tobias
 */
public class MockStorage implements Storage {

    private ArrayList<HashMap<String, String>> members = new ArrayList<>();
    private ArrayList<HashMap<String, String>> competitionResults = new ArrayList<>();
    private ArrayList<HashMap<String, String>> trainingResults = new ArrayList<>();
    private int highestNumber;
    private ArrayList<Integer> swimmingDisciplines;

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
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        for (HashMap<String, String> map : members) {
            if (map.get("member_name").contentEquals(name)) {
                result.add(map);
            }
        }
        return result;
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

    @Override
    public boolean addCompResult(CompetitionResult result, int member_id) {
        return false;
    }

    @Override
    public boolean addTrainingResult(TrainingResult result, int member_id) {
        return false;
    }
}
