package storage;

import core.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alexander
 */
public class MockStorage implements Storage{

    private ArrayList<HashMap<String, String>> members = new ArrayList<>();

    public MockStorage() {
    }
    
    @Override
    public ArrayList<HashMap<String, String>> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<HashMap<String, String>> members) {
        this.members = members;
    }
    
    

}
