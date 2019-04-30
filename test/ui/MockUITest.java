/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rando
 */
public class MockUITest {
    
    @Test
    public void testPrintln(){
        // Arrange
        MockUI mock = new MockUI(null);
        // Act
        mock.println("testTest");
        // Assert
        assertEquals(1, mock.getOutput().size());
    }
    
    @Test
    public void testPrintf(){
        // Arrange
        MockUI mock = new MockUI(null);
        // Act
        mock.printf("test %s Test", "Hej");
        mock.printf("test %d Test", 23);
        // Assert
        assertEquals(2, mock.getOutput().size());
        assertEquals("test Hej Test", mock.getOutput().get(0));
        assertEquals("test 23 Test", mock.getOutput().get(1));
    }
    
    @Test
    public void testGetUserInput(){
        // Arrange
        MockUI mock = new MockUI(new String[] {"test", "Test", "Hej"});
        // Act
        String temp1 = mock.getUserInput();
        String temp2 = mock.getUserInput();
        String temp3 = mock.getUserInput();
        // Assert
        assertEquals("test", temp1);
        assertEquals("Test", temp2);
        assertEquals("Hej", temp3);
    }
    
    @Test
    public void testGetOutput(){
        // Arrange
        MockUI mock = new MockUI(null);
        // Act
        mock.println("test");
        mock.println("Test");
        mock.printf("Hej %f", 1.0f);
        // Assert
        assertNotNull(mock.getOutput());
        assertEquals(3, mock.getOutput().size());
    }
    
}
