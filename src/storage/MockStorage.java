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

}
