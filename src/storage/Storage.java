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
    public int getNextMemberID();

}
