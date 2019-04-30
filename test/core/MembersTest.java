package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import storage.MockStorage;
import ui.MockUI;

/**
 *
 * @author Alexander
 */
public class MembersTest {

    Members members;
    MockStorage mockStorage;

    @Test
    public void testConstructor() {
        Member member1 = new Member("Alexander", 29, 1);
        Member member2 = new Member("Mads", 25, 2);
        SeniorMember seniorMember = new SeniorMember(true, "Benjamin", 26, 3);
        SeniorMember seniorMember2 = new SeniorMember(true, "Benjamin", 26, 6);
        JuniorMember juniorMember = new JuniorMember(true, "Tobias", 15, 4);
        JuniorMember juniorMember2 = new JuniorMember(true, "Henrik", 15, 5);
        mockStorage = new MockStorage();
        HashMap<String, String> map = new HashMap();
        HashMap<String, String> map2 = new HashMap();
        HashMap<String, String> map3 = new HashMap();
        HashMap<String, String> map4 = new HashMap();
        HashMap<String, String> map5 = new HashMap();
        HashMap<String, String> map6 = new HashMap();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        map.put("name", "Alexander");
        map.put("age", "29");
        map.put("id", "1");
        map.put("subscription", "0");
        list.add(map);
        map2.put("name", "Mads");
        map2.put("age", "25");
        map2.put("id", "2");
        map2.put("subscription", null);
        list.add(map2);
        map3.put("name", "Benjamin");
        map3.put("age", "26");
        map3.put("id", "3");
        map3.put("active", "true");
        map3.put("subscription", "1600");
        list.add(map3);
        map4.put("name", "Tobias");
        map4.put("age", "15");
        map4.put("id", "4");
        map4.put("active", "true");
        map4.put("subscription", "1000");
        list.add(map4);
        map5.put("name", "Henrik");
        map5.put("age", "15");
        map5.put("id", "5");
        map5.put("active", "true");
        map5.put("subscription", "1000");
        list.add(map5);
        map6.put("name", "Benjamin");
        map6.put("age", "26");
        map6.put("id", "6");
        map6.put("active", "true");
        map6.put("subscription", "1600");
        list.add(map6);
        mockStorage.setMembers(list);
        members = new Members(mockStorage);
        assertEquals(member1.getId(), members.getMembers().get("Coach").get(0).getId());
        assertEquals(member2.getId(), members.getMembers().get("Coach").get(1).getId());
        assertEquals(seniorMember.getId(), members.getMembers().get("SeniorMember").get(0).getId());
        assertEquals(seniorMember2.getId(), members.getMembers().get("SeniorMember").get(1).getId());
        assertEquals(juniorMember.getId(), members.getMembers().get("JuniorMember").get(0).getId());
        assertEquals(juniorMember2.getId(), members.getMembers().get("JuniorMember").get(1).getId());
    }

    /**
     * Test of setMember method, of class Members.
     */
    @Test
    public void testSetMember() {
        Member member1 = new Member("Alexander", 29, 1);
        mockStorage = new MockStorage();
        HashMap<String, String> map = new HashMap();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        map.put("name", "Alexander");
        map.put("age", "29");
        map.put("id", "1");
        map.put("subscription", "0");
        list.add(map);
        mockStorage.setMembers(list);
        members = new Members(mockStorage);
        assertTrue(members.setMember("Coach", member1));
        assertFalse(members.setMember("blabla", member1));
    }

}
