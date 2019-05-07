package core;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * @author Alexander
 * @author Benjamin
 * @author Mads
 * @author Tobias
 */
public class CompetitionResultTest {

    CompetitionResult comp;
    String event = "DM";
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.of(0, 10, 20, 43);
    int placement = 5;
    int id = 2;

    @Before
    public void setUp() {
        comp = new CompetitionResult(event, placement, SwimmingDiscipline.BUTTERFLY, date, time, id);
    }

    /**
     * Test of getSwimmingDiscipline method, of class CompetitionResult.
     */
    @Test
    public void testGetSwmmingDiscipline() {
        assertEquals("BUTTERFLY", comp.getSwimmingDiscipline().getDisciplineName());
    }

    /**
     * Test of getEvent method, of class CompetitionResult.
     */
    @Test
    public void testGetEvent() {
        assertEquals(event, comp.getEvent());
    }

    /**
     * Test of getDate method, of class CompetitionResult.
     */
    @Test
    public void testGetDate() {
        assertEquals(date, comp.getDate());
    }

    /**
     * Test of getTime method, of class CompetitionResult.
     */
    @Test
    public void testGetTime() {
        assertEquals(time, comp.getTime());
    }

    /**
     * Test of getPlacement method, of class CompetitionResult.
     */
    @Test
    public void testGetPlacement() {
        assertEquals(placement, comp.getPlacement());
    }

}
