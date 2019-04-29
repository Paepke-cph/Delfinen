package core;

import java.util.ArrayList;
import java.util.Arrays;
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
        Member member3 = new Member("Benjamin", 26, 3);
        Member member4 = new Member("Tobias", 20, 4);
        Member member5 = new Member("Oscar", 25, 5);
        Member[] memberArray = {member1, member2, member3, member4, member5};
        mockStorage = new MockStorage();
        ArrayList<Member> memberList = new ArrayList<>(Arrays.asList(memberArray));
        mockStorage.setMembers(memberList);
        members = new Members(mockStorage);
        assertTrue(memberList.equals(members.getMembers()));
    }


}
