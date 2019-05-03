/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Alexander
 */
public class JuniorMemberTest {
    JuniorMember member;

    @Before
    public void setUp(){
        LocalDate date = LocalDate.now();
        member = new JuniorMember(true,"Alexander", 12, 1, date, null);
    }

    /**
     * Test of calculatePrice method, of class JuniorMember.
     */
    @Test
    public void testCalculatePrice() {
        double expected = 1000;
        double result = member.calculatePrice();
        assertEquals(expected, result,0);
        expected = 500;
        member.setActive(false);
        result = member.calculatePrice();
        assertEquals(expected, result,0);
    }

    /**
     * Test of isActive method, of class JuniorMember.
     */
    @Test
    public void testIsActive() {
        assertTrue(member.isActive());
    }
    
}
