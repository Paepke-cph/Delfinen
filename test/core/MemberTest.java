package core;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Alexander
 */
public class MemberTest {
    Member member;

    @Before
    public void setUp(){
        member = new Member("Alexander", 29, 1);
    }
    @Test
    public void testGetName() {
        String expected = "Alexander";
        String result = member.getName();
        assertTrue(expected.equals(result));
    }
    
    @Test
    public void testGetAge() {
        int expected = 29;
        int result = member.getAge();
        assertEquals(expected, result);
    }
    
    @Test
    public void testGetID(){
        int expected = 1;
        int result = member.getId();
        assertEquals(expected, result);
    }
    
    @Test
    public void testSetName() {
        String expected = "Alex";
        member.setName(expected);
        String result = member.getName();
        assertTrue(expected.equals(result));
    }
    
    @Test
    public void testSetAge() {
        int expected = 20;
        member.setAge(expected);
        int result = member.getAge();
        assertEquals(expected, result);
    }
    
    @Test
    public void testSetID(){
        int expected = 5;
        member.setId(expected);
        int result = member.getId();
        assertEquals(expected, result);
    }

    /**
     * Test of calculatePrice method, of class Member.
     */
    @Test
    public void testCalculatePrice() {
        assertEquals(0, member.calculatePrice(),0);
    }

    /**
     * Test of toString method, of class Member.
     */
    @Test
    public void testToString() {
    }
      
    
}
