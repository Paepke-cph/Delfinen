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
        Member member1 = new Member("Alexander", 29, 1, null);
        Member member2 = new Member("Mads", 25, 2, null);
        SeniorMember seniorMember = new SeniorMember(true, "Benjamin", 26, 3, null);
        SeniorMember seniorMember2 = new SeniorMember(true, "Benjamin", 26, 6, null);
        JuniorMember juniorMember = new JuniorMember(true, "Tobias", 15, 4, null);
        JuniorMember juniorMember2 = new JuniorMember(true, "Henrik", 15, 5, null);
        mockStorage = new MockStorage();
        HashMap<String, String> map = new HashMap();
        HashMap<String, String> map2 = new HashMap();
        HashMap<String, String> map3 = new HashMap();
        HashMap<String, String> map4 = new HashMap();
        HashMap<String, String> map5 = new HashMap();
        HashMap<String, String> map6 = new HashMap();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        map.put("member_name", "Alexander");
        map.put("age", "29");
        map.put("member_id", "1");
        map.put("subscription", "0");
        list.add(map);
        map2.put("member_name", "Mads");
        map2.put("age", "25");
        map2.put("member_id", "2");
        map2.put("subscription", null);
        list.add(map2);
        map3.put("member_name", "Benjamin");
        map3.put("age", "26");
        map3.put("member_id", "3");
        map3.put("active", "1");
        map3.put("subscription", "1600");
        list.add(map3);
        map4.put("member_name", "Tobias");
        map4.put("age", "15");
        map4.put("member_id", "4");
        map4.put("active", "1");
        map4.put("subscription", "1000");
        list.add(map4);
        map5.put("member_name", "Henrik");
        map5.put("age", "15");
        map5.put("member_id", "5");
        map5.put("active", "1");
        map5.put("subscription", "1000");
        list.add(map5);
        map6.put("member_name", "Benjamin");
        map6.put("age", "26");
        map6.put("member_id", "6");
        map6.put("active", "1");
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
        Member member1 = new Member("Alexander", 29, 1, null);
        mockStorage = new MockStorage();
        HashMap<String, String> map = new HashMap();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        map.put("member_name", "Alexander");
        map.put("age", "29");
        map.put("member_id", "1");
        map.put("subscription", "0");
        list.add(map);
        mockStorage.setMembers(list);
        members = new Members(mockStorage);
        assertTrue(members.setMember("Coach", member1));
        assertFalse(members.setMember("blabla", member1));
    }

    @Test
    public void testReturnSeniorMembers() {
        mockStorage = new MockStorage();
        HashMap<String, String> map = new HashMap();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        map.put("member_name", "Alexander");
        map.put("age", "29");
        map.put("member_id", "1");
        map.put("active", "1");
        map.put("subscription", "1600");
        list.add(map);
        mockStorage.setMembers(list);
        members = new Members(mockStorage);
        String expected = members.getMembers().get("SeniorMember").get(0).toString();
        String result = members.returnSeniorMembers().get(0);
        assertEquals(expected, result);
    }

    @Test
    public void testReturnCoaches() {
        mockStorage = new MockStorage();
        HashMap<String, String> map = new HashMap();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        map.put("member_name", "Alexander");
        map.put("age", "29");
        map.put("member_id", "1");
        map.put("subscription", "0");
        map.put("active", "1");
        list.add(map);
        mockStorage.setMembers(list);
        members = new Members(mockStorage);
        String expected = members.getMembers().get("Coach").get(0).toString();
        String result = members.returnCoaches().get(0);
        assertEquals(expected, result);
    }

    @Test
    public void testReturnJuniorMembers() {
        mockStorage = new MockStorage();
        HashMap<String, String> map = new HashMap();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        map.put("member_name", "Alexander");
        map.put("age", "15");
        map.put("member_id", "1");
        map.put("active", "1");
        map.put("subscription", "1000");
        map.put("coach", null);
        list.add(map);
        mockStorage.setMembers(list);
        members = new Members(mockStorage);
        String expected = members.getMembers().get("JuniorMember").get(0).toString();
        String result = members.returnJuniorMembers().get(0);
        assertEquals(expected, result);
    }

    @Test
    public void testJuniorCompSwimmer () {
        mockStorage = new MockStorage();
        HashMap<String, String> map = new HashMap();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        map.put("member_name", "Alexander");
        map.put("age", "15");
        map.put("member_id", "1");
        map.put("active", "1");
        map.put("subscription", "1000");
        map.put("coach", "3");
        list.add(map);
        mockStorage.setMembers(list);
        members = new Members(mockStorage);

        assertNotNull(members.searchMemberById(1).getCompetition());
    }

    @Test
    public void testSearchMemberByID () {
        mockStorage = new MockStorage();
        HashMap<String, String> map = new HashMap();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        map.put("member_name", "Alexander");
        map.put("age", "15");
        map.put("member_id", "1");
        map.put("active", "1");
        map.put("subscription", "1000");
        map.put("coach", null);
        list.add(map);
        Member actual = members.searchMemberById(1);
        int expected = 1;
        assertEquals(expected, actual.getId());
    }





}
