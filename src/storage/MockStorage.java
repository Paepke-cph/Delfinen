package storage;

import core.Member;
import java.util.ArrayList;

/**
 *
 * @author Alexander
 */
public class MockStorage implements Storage{

    private ArrayList<Member> members;

    public MockStorage() {
    }
    
    @Override
    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }
    
    

}
