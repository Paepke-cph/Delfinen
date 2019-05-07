package core;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Alexander
 * @author Benjamin
 * @author Mads
 * @author Tobias
 */
public class SwimmingDisciplineTest {

    SwimmingDiscipline disc;

    /**
     * Test of Butterfly method, of class SwimmingDiscipline.
     */
    @Test
    public void testButterfly() {
        String result = disc.BUTTERFLY.name();
        assertEquals("BUTTERFLY", result);
    }

    /**
     * Test of Butterfly method, of class SwimmingDiscipline.
     */
    @Test
    public void testBackStroke() {
        String result = disc.BACKSTROKE.name();
        assertEquals("BACKSTROKE", result);
    }

    /**
     * Test of Butterfly method, of class SwimmingDiscipline.
     */
    @Test
    public void testCrawl() {
        String result = disc.CRAWL.name();
        assertEquals("CRAWL", result);
    }

    /**
     * Test of Butterfly method, of class SwimmingDiscipline.
     */
    @Test
    public void testBreastStroke() {
        String result = disc.BREASTSTROKE.name();
        assertEquals("BREASTSTROKE", result);
    }

}
