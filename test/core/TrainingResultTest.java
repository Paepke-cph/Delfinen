/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Alexander
 */
public class TrainingResultTest {
    Member member = new Member("Aleander", 29, 1, null);
    TrainingResult comp;
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.of(0, 10, 20, 43);

    @Before
    public void Setup() {
        comp = new TrainingResult(SwimmingDiscipline.BUTTERFLY, date, time, member);
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

    /**
     * Test of getMember method, of class TrainingResult.
     */
    @Test
    public void testGetMember() {
        assertEquals(member, comp.getMember());
    }

}
