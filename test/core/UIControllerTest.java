/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import org.junit.Test;
import static org.junit.Assert.*;
import storage.MockStorage;
import ui.MockUI;

/**
 *
 * @author rando
 */
public class UIControllerTest {

    private MockStorage mockStorage;
    public UIControllerTest() {
        mockStorage = new MockStorage(new int[] {1});
    }

    @Test
    public void testStartProgram() {
        // Arrange
        MockUI mockUI = new MockUI(new String[]{"9"});
        UIController UIC = new UIController(mockUI, mockStorage);
        // Act
        UIC.startProgram();
        // Assert
        assertEquals(6, mockUI.getOutput().size());
    }

    @Test
    public void testStartProgram_WithInvalidChoice() {
        // Arrange
        MockUI mockUI = new MockUI(new String[]{"k", "9"});
        UIController UIC = new UIController(mockUI, mockStorage);
        // Act
        UIC.startProgram();
        // Assert
        assertEquals(7, mockUI.getOutput().size());
    }

    @Test
    public void testAdmMemberMenu() {
        // Arrange
        MockUI mockUI = new MockUI(new String[]{"1", "9", "9"});
        UIController UIC = new UIController(mockUI, mockStorage);
        // Act
        UIC.startProgram();
        // Assert
        assertEquals(18, mockUI.getOutput().size());
    }

    @Test
    public void testAdmKontingenterMenu() {
        // Arrange
        MockUI mockUI = new MockUI(new String[]{"2", "9", "9"});
        UIController UIC = new UIController(mockUI, mockStorage);
        // Act
        UIC.startProgram();
        // Assert
        assertEquals(17, mockUI.getOutput().size());
    }

    @Test
    public void testResultaterMenu() {
        // Arrange
        MockUI mockUI = new MockUI(new String[]{"3", "9", "9"});
        UIController UIC = new UIController(mockUI, mockStorage);
        // Act
        UIC.startProgram();
        // Assert
        assertEquals(17, mockUI.getOutput().size());
    }

    @Test
    public void testAddMember_AddJunior() {
        // Arrange
        MockUI mockUI = new MockUI(new String[]{"1", "1", "Peter Larsen", "17", "nej", "nej", "ja", "9", "9"});
        UIController UIC = new UIController(mockUI, mockStorage);
        // Act
        UIC.startProgram();
        // Assert
        assertEquals(1, UIC.getAllMembers().get("JuniorMember").size());
    }

    @Test
    public void testAddMember_AddSenior() {
        // Arrange
        MockUI mockUI = new MockUI(new String[]{"1", "1", "Peter Larsen", "22", "nej", "nej", "ja", "9", "9"});
        UIController UIC = new UIController(mockUI, mockStorage);
        // Act
        UIC.startProgram();
        // Assert
        assertEquals(1, UIC.getAllMembers().get("SeniorMember").size());
    }

    @Test
    public void testAddMember_AddCompJunior() {
        // Arrange
        MockUI mockUI = new MockUI(new String[]{"1", "1", "Peter Larsen", "17", "nej", "ja","2", "9", "ja", "9", "9"});
        UIController UIC = new UIController(mockUI, mockStorage);
        // Act
        UIC.startProgram();
        // Assert
        assertEquals(1, UIC.getAllMembers().get("JuniorMember").size());
    }

    @Test
    public void testAddMember_AddCompSenior() {
        // Arrange
        MockUI mockUI = new MockUI(new String[]{"1", "1", "Peter Larsen", "80", "nej", "ja", "2", "9", "ja", "9", "9"});
        UIController UIC = new UIController(mockUI, mockStorage);
        // Act
        UIC.startProgram();
        // Assert
        assertEquals(1, UIC.getAllMembers().get("SeniorMember").size());
    }

    @Test
    public void testAddMember_AddCoach() {
        // Arrange
        MockUI mockUI = new MockUI(new String[]{"1", "1", "Coach Nielsen", "80", "ja", "9", "9"});
        UIController UIC = new UIController(mockUI, mockStorage);
        // Act
        UIC.startProgram();
        // Assert
        assertEquals(1, UIC.getAllMembers().get("Coach").size());
    }
}
