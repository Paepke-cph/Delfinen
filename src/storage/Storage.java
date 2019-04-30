package storage;

import core.Member;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Alexander
 */
public interface Storage {
    
    public ArrayList<Map<String, String>> getMembers();
}
