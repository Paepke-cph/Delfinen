package core;

import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * @author Alexander
 * @author Benjamin
 * @author Mads
 * @author Tobias
 */
public class SeniorMemberTest {

    SeniorMember member;

    @Before
    public void setUp() {
        LocalDate date = LocalDate.now();
        member = new SeniorMember(true, "Alexander", 29, 1, date, null);
    }

    /**
     * Test of calculatePrice method, of class SeniorMember.
     */
    @Test
    public void testCalculatePrice() {
        double expected = 1600;
        double result = member.calculatePrice();
        assertEquals(expected, result, 0);
        expected = 1200;
        member.setAge(61);
        result = member.calculatePrice();
        assertEquals(expected, result, 0);
        expected = 500;
        member.setActive(false);
        result = member.calculatePrice();
        assertEquals(expected, result, 0);

    }

}
