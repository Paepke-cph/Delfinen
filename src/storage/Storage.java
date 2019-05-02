package storage;

import core.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alexander
 */
public interface Storage {
    
    public ArrayList<HashMap<String, String>> getMembers();
    public ArrayList<HashMap<String, String>> getMembersByName(String name);
    public ArrayList<HashMap<String, String>> getCompetitionResults(int member_id);
    public ArrayList<HashMap<String, String>> getTrainingResults(int member_id);
    public ArrayList<Integer> getSwimmingDisciplines(int member_id);
    public int getNextMemberID();
}
