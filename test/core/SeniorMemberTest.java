/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Alexander
 */
public class SeniorMemberTest {

    SeniorMember member;

    @Before
    public void setUp() {
        member = new SeniorMember(true, "Alexander", 29, 1, false, null);
    }

    /**
     * Test of calculatePrice method, of class SeniorMember.
     */
    @Test
    public void testCalculatePrice() {
        double expected = 1600;
        double result = member.calculatePrice();
        assertEquals(expected, result,0);
        expected = 1200;
        member.setAge(61);
        result = member.calculatePrice();
        assertEquals(expected, result,0);
        expected = 500;
        member.setActive(false);
        result = member.calculatePrice();
        assertEquals(expected, result,0);
        
    }

}
