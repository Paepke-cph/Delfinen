package storage;

import core.Member;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Alexander
 */
public class MockStorage implements Storage{

    private ArrayList<Map<String, String>> members;

    public MockStorage() {
    }
    
    @Override
    public ArrayList<Map<String, String>> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Map<String, String>> members) {
        this.members = members;
    }
    
    

}
