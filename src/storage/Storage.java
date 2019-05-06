package storage;

import core.CompetitionResult;
import core.Member;
import core.TrainingResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alexander
 */
public interface Storage {

    public Integer getNextMemberID();

    public Integer getNextCompetitionID();

    public Integer getNextTrainingID();
    public boolean removeMember(int member_id);
    public boolean createMember(Member member);

    public boolean updateMember(Member member);

    public ArrayList<HashMap<String, String>> getMembers();
    public ArrayList<HashMap<String, String>> getMembersByName(String name);
    public ArrayList<HashMap<String, String>> getCompetitionResults(int member_id);
    public ArrayList<HashMap<String, String>> getTrainingResults(int member_id);
    public ArrayList<Integer> getSwimmingDisciplines(int member_id);
    public ArrayList<HashMap<String, String>> getTopFiveTrainingResultsByDiscipline(int discipline_id);
    public ArrayList<HashMap<String, String>> getTopFiveCompetitionResultsByDiscipline(int discipline_id);

    public boolean addCompResult(CompetitionResult result, int member_id);
    public boolean addTrainingResult(TrainingResult result, int member_id);
}
