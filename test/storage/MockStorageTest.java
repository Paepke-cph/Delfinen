package storage;

import core.Member;
import java.util.ArrayList;
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
        Member member1 = new Member("Alexander", 29, 1);
        Member member2 = new Member("Mads", 25, 2);
        Member member3 = new Member("Benjamin", 26, 3);
        Member member4 = new Member("Tobias", 20, 4);
        Member member5 = new Member("Oscar", 25, 5);
        ArrayList<Member> members = new ArrayList<>();
        members.add(member1);
        members.add(member2);
        members.add(member3);
        members.add(member4);
        members.add(member5);
        mockStorage.setMembers(members);
        assertTrue(members.equals(mockStorage.getMembers()));
    }

}
