package storage;

import core.Member;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander
 */
public class MockStorageTest {

    MockStorage mockStorage;

    /**
     * Test of get and set Members method, of class MockStorage.
     */
    @Test
    public void testGetterSetterMembers() {
        mockStorage = new MockStorage();
        HashMap<String, String> map = new HashMap();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        map.put("member_name", "Alexander");
        map.put("age", "29");
        map.put("member_id", "1");
        map.put("subscription", "0");
        list.add(map);
        map.put("member_name", "Mads");
        map.put("age", "25");
        map.put("member_id", "2");
        map.put("subscription", null);
        list.add(map);
        map.put("member_name", "Benjamin");
        map.put("age", "26");
        map.put("member_id", "3");
        map.put("active", "1");
        list.add(map);
        map.put("member_name", "Tobias");
        map.put("age", "15");
        map.put("member_id", "4");
        map.put("active", "1");
        list.add(map);
        mockStorage.setMembers(list);
        assertTrue(list.equals(mockStorage.getMembers()));
    }



}
