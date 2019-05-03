package core;

import java.time.LocalDate;
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
public class StorageControllerTest {

    StorageController members;
    MockStorage mockStorage;

    @Test
    public void testConstructor() {
        LocalDate date = LocalDate.now();
        Member member1 = new Member("Alexander", 29, 1, true);
        Member member2 = new Member("Mads", 25, 2, true);
        SeniorMember seniorMember = new SeniorMember(true, "Benjamin", 26, 3, date, null);
        SeniorMember seniorMember2 = new SeniorMember(true, "Benjamin", 26, 6, date, null);
        JuniorMember juniorMember = new JuniorMember(true, "Tobias", 15, 4, date, null);
        JuniorMember juniorMember2 = new JuniorMember(true, "Henrik", 15, 5, date, null);
        mockStorage = new MockStorage();
        ArrayList<Integer> swimDisc = new ArrayList<>();
        swimDisc.add(1);
        swimDisc.add(2);
        mockStorage.setSwimmingDisciplines(swimDisc);
        HashMap<String, String> map = new HashMap();
        HashMap<String, String> map2 = new HashMap();
        HashMap<String, String> map3 = new HashMap();
        HashMap<String, String> map4 = new HashMap();
        HashMap<String, String> map5 = new HashMap();
        HashMap<String, String> map6 = new HashMap();
        HashMap<String, String> map7 = new HashMap();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        map.put("member_name", "Alexander");
        map.put("age", "29");
        map.put("member_id", "1");
        map.put("subscription", "0");
        map.put("arrears", "2019-04-05");
        list.add(map);
        map2.put("member_name", "Mads");
        map2.put("age", "25");
        map2.put("member_id", "2");
        map2.put("subscription", null);
        map2.put("arrears", "2019-04-05");
        list.add(map2);
        map3.put("member_name", "Benjamin");
        map3.put("age", "26");
        map3.put("member_id", "3");
        map3.put("active", "1");
        map3.put("subscription", "1600");
        map3.put("arrears", "2019-04-05");
        list.add(map3);
        map4.put("member_name", "Tobias");
        map4.put("age", "15");
        map4.put("member_id", "4");
        map4.put("active", "1");
        map4.put("subscription", "1000");
        map4.put("arrears", "2019-04-05");
        list.add(map4);
        map5.put("member_name", "Henrik");
        map5.put("age", "15");
        map5.put("member_id", "5");
        map5.put("active", "1");
        map5.put("subscription", "1000");
        map5.put("arrears", "2019-04-05");
        list.add(map5);
        map6.put("member_name", "Benjamin");
        map6.put("age", "26");
        map6.put("member_id", "6");
        map6.put("active", "1");
        map6.put("subscription", "1600");
        map6.put("arrears", "2019-04-05");
        list.add(map6);
        map7.put("member_name", "Nicklas");
        map7.put("age", "26");
        map7.put("member_id", "7");
        map7.put("active", "1");
        map7.put("subscription", "1600");
        map7.put("coach_id", "6");
        map7.put("arrears", "2019-04-05");
        list.add(map7);
        mockStorage.setMembers(list);
        members = new StorageController(mockStorage);
        assertEquals(member1.getId(), members.getMembers().get("Coach").get(0).getId());
        assertEquals(member2.getId(), members.getMembers().get("Coach").get(1).getId());
        assertEquals(seniorMember.getId(), members.getMembers().get("SeniorMember").get(0).getId());
        assertEquals(seniorMember2.getId(), members.getMembers().get("SeniorMember").get(1).getId());
        assertEquals(juniorMember.getId(), members.getMembers().get("JuniorMember").get(0).getId());
        assertEquals(juniorMember2.getId(), members.getMembers().get("JuniorMember").get(1).getId());
    }

    /**
     * Test of setMember method, of class StorageController.
     */
    @Test
    public void testSetMember() {
        LocalDate date = LocalDate.now();
        Member member1 = new Member(true, "Alexander", 29, 1, date, null);
        mockStorage = new MockStorage();
        HashMap<String, String> map = new HashMap();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        map.put("member_name", "Alexander");
        map.put("age", "29");
        map.put("member_id", "1");
        map.put("subscription", "0");
        map.put("arrears", "2019-04-05");
        list.add(map);
        mockStorage.setMembers(list);
        members = new StorageController(mockStorage);
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
        map.put("arrears", "2019-04-05");
        list.add(map);
        mockStorage.setMembers(list);
        members = new StorageController(mockStorage);
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
        map.put("arrears", "2019-04-05");
        list.add(map);
        mockStorage.setMembers(list);
        members = new StorageController(mockStorage);
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
        map.put("coach_id", null);
        map.put("arrears", "2019-04-05");
        list.add(map);
        mockStorage.setMembers(list);
        members = new StorageController(mockStorage);
        String expected = members.getMembers().get("JuniorMember").get(0).toString();
        String result = members.returnJuniorMembers().get(0);
        assertEquals(expected, result);
    }

    @Test
    public void testJuniorCompSwimmer () {
        mockStorage = new MockStorage();
        HashMap<String, String> map = new HashMap();
        HashMap<String, String> map2 = new HashMap();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        ArrayList<Integer> swimDisc = new ArrayList<>();
        swimDisc.add(1);
        swimDisc.add(2);
        map2.put("member_name", "Benjamin");
        map2.put("age", "26");
        map2.put("member_id", "6");
        map2.put("active", "1");
        map2.put("subscription", "0");
        map2.put("arrears", "2019-04-05");
        list.add(map2);
        map.put("member_name", "Alexander");
        map.put("age", "15");
        map.put("member_id", "1");
        map.put("active", "1");
        map.put("subscription", "1000");
        map.put("coach_id", "3");
        map.put("arrears", "2019-04-05");
        list.add(map);
        
        mockStorage.setSwimmingDisciplines(swimDisc);
        mockStorage.setMembers(list);
        members = new StorageController(mockStorage);

        assertNotNull(members.searchMemberById(1).getCompetition());
    }

    @Test
    public void testSearchMemberByID () {
        mockStorage = new MockStorage();
        HashMap<String, String> map = new HashMap();
        HashMap<String, String> map2 = new HashMap();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        map.put("member_name", "Alexander");
        map.put("age", "15");
        map.put("member_id", "1");
        map.put("active", "1");
        map.put("subscription", "1000");
        map.put("coach_id", null);
        map.put("arrears", "2019-04-05");
        list.add(map);
        map2.put("member_name", "Alexander");
        map2.put("age", "40");
        map2.put("member_id", "2");
        map2.put("active", "1");
        map2.put("subscription", "1600");
        map2.put("coach_id", null);
        map2.put("arrears", "2019-04-05");
        list.add(map2);
        mockStorage.setMembers(list);
        members = new StorageController(mockStorage);
        Member actual = members.searchMemberById(1);
        Member actual2 = members.searchMemberById(2);
        int expected = 1;
        int expected2 = 2;
        assertEquals(expected, actual.getId());
        assertEquals(expected2, actual2.getId());
    }

    @Test
    public void testCreateCompetition(){
        mockStorage = new MockStorage();
        HashMap<String, String> map = new HashMap();
        HashMap<String, String> map2 = new HashMap();
        HashMap<String, String> comp = new HashMap();
        HashMap<String, String> train = new HashMap();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        ArrayList<HashMap<String, String>> competition = new ArrayList<>();
        ArrayList<HashMap<String, String>> training = new ArrayList<>();
        ArrayList<Integer> swimDisc = new ArrayList<>();
        swimDisc.add(3);
        swimDisc.add(4);
        map2.put("member_name", "Hans");
        map2.put("age", "30");
        map2.put("member_id", "2");
        map2.put("active", "1");
        map2.put("subscription", "0");
        map2.put("coach_id", null);
        map2.put("arrears", "2019-04-05");
        list.add(map2);
        map.put("member_name", "Alexander");
        map.put("age", "15");
        map.put("member_id", "1");
        map.put("active", "1");
        map.put("subscription", "1000");
        map.put("coach_id", "2");
        map.put("arrears", "2019-04-05");
        list.add(map);
        comp.put("event", "dm");
        comp.put("placement", "3");
        comp.put("discipline_id", "1");
        comp.put("event_date", "2019-04-03");
        comp.put("best_time", "00:20:35");
        comp.put("competition_id", "1");
        competition.add(comp);
        train.put("discipline_id", "1");
        train.put("training_date", "2019-04-03");
        train.put("best_time", "00:20:35");
        train.put("training_id", "1");
        training.add(train);
        mockStorage.setCompetitionResults(competition);
        mockStorage.setTrainingResults(training);
        mockStorage.setSwimmingDisciplines(swimDisc);
        mockStorage.setMembers(list);
        members = new StorageController(mockStorage);
        Member member = members.searchMemberById(1);
        
        ArrayList<SwimmingDiscipline> expected = new ArrayList<>();
        expected.add(SwimmingDiscipline.BACKSTROKE);
        expected.add(SwimmingDiscipline.BREASTSTROKE);
        ArrayList<SwimmingDiscipline> result = member.getCompetition().getSwimmingDiscipline();
        
        assertTrue(expected.equals(result));
    }



}
