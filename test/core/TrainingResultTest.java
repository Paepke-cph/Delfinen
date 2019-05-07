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
public class TrainingResultTest {

    TrainingResult comp;
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.of(0, 10, 20, 43);
    int id = 2;

    @Before
    public void Setup() {
        comp = new TrainingResult(SwimmingDiscipline.BUTTERFLY, date, time, id);
    }

    /**
     * Test of getSwimmingDiscipline method, of class TrainingResult.
     */
    @Test
    public void testGetSwimmingDiscipline() {
        assertEquals("BUTTERFLY", comp.getSwimmingDiscipline().getDisciplineName());
    }

    /**
     * Test of getDate method, of class TrainingResult.
     */
    @Test
    public void testGetDate() {
        assertEquals(date, comp.getDate());
    }

    /**
     * Test of getTime method, of class TrainingResult.
     */
    @Test
    public void testGetTime() {
        assertEquals(time, comp.getTime());
    }

}
