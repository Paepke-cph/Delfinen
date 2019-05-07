package storage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import core.*;
import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * @author Alexander
 * @author Benjamin
 * @author Mads
 * @author Tobias
 */
public class DBStorageTest {

    ArrayList<String> createDatabase = scanFromFile("UnitTestCreate_Delfinen.sql");
    ArrayList<String> populateDatabase = scanFromFile("UnitTestPopulate_Delfinen.sql");
    private final static String user = "myuser";
    private final static String password = "Password123";
    private final static String IP = "127.0.0.1";
    private final static String PORT = "3306";
    private final static String DATABASE = "Delfinen";
    private final static String serverTime = "serverTimezone=UTC";
    private final static String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE + "?" + serverTime;

    DBStorage storage;

    public ArrayList<String> scanFromFile(String filename) {
        ArrayList<String> file = new ArrayList();
        try {
            Scanner scan = new Scanner(new File("scripts/" + filename));
            scan.useDelimiter(Pattern.compile(";"));
            while (scan.hasNext()) {
                file.add(scan.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Before
    public void setUp() {
        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();) {
            for (String sqlStatement : createDatabase) {
                stmt.executeUpdate(sqlStatement);
            }
            for (String sqlStatement : populateDatabase) {
                stmt.executeUpdate(sqlStatement);
            }
            storage = new DBStorage();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetMembers() {
        int actualSize = storage.getMembers().size();
        int expectedSize = 46;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testGetMembersByName() {
        int actualSize = storage.getMembersByName("Benjamin Paepke").size();
        int expectedSize = 1;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testGetNextMemberID() {
        int actual = storage.getNextMemberID();
        int expected = 47;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNextCompetitionID() {
        int actual = storage.getNextCompetitionID();
        int expected = 33;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNextTrainingID () {
        int actual = storage.getNextTrainingID();
        int expected = 33;
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveMember () {
        int sizeBefore = storage.getMembers().size();
        storage.removeMember(1);
        int sizeAfter = storage.getMembers().size();
        assertEquals(sizeBefore -1, sizeAfter);
    }

    @Test
    public void testRemoveNonExistingMember () {
        assertFalse(storage.removeMember(-1));
    }

    @Test
    public void testCreateMember () {
        // Meet John! John is created as a member object
        Member john = new Member(true, "John", 55, 200, LocalDate.now(), null);

        // We then fetch everyone called John from the DB. (No one - yet.)
        ArrayList<HashMap<String, String>> johnFromStorage;
        johnFromStorage = storage.getMembersByName("John");
        assertTrue(johnFromStorage.isEmpty());

        // John is stored in the DB
        storage.createMember(john);

        // Search for John again
        johnFromStorage = storage.getMembersByName("John");

        // John now exists in the DB with the correct member_id
        assertEquals("John", johnFromStorage.get(0).get("member_name"));
        assertEquals(200, Integer.parseInt(johnFromStorage.get(0).get("member_id")));
    }

    @Test
    public void testCreateMember_competitive () {
        Member coach = new Member("Coach", 55, 50, true);
        ArrayList<SwimmingDiscipline> swimmingDisciplines = new ArrayList<>();
        swimmingDisciplines.add(SwimmingDiscipline.BACKSTROKE);
        CompetitionSwimmer competitionSwimmer = new CompetitionSwimmer(coach, swimmingDisciplines);
        Member john = new Member(true, "John", 55, 200, LocalDate.now(), competitionSwimmer);
        storage.createMember(john);

        // Search for John again
        ArrayList<HashMap<String, String>> johnFromStorage = storage.getMembersByName("John");

        // John now exists in the DB with the correct member_id
        assertEquals("John", johnFromStorage.get(0).get("member_name"));
        assertEquals(200, Integer.parseInt(johnFromStorage.get(0).get("member_id")));
    }

    @Test
    public void testUpdateMember () {
        // Say hello to john again!
        Member john = new Member(true, "John", 55, 200, LocalDate.now(), null);
        storage.createMember(john);

        // Assert that John is created in the DB
        ArrayList<HashMap<String, String>> memberFromStorage;
        memberFromStorage = storage.getMembersByName("John");
        assertFalse(memberFromStorage.isEmpty());

        // John now wants to be called Michael
        john.setName("Michael");
        storage.updateMember(john);

        // Search the DB for members called Michael and assert their unique ID is the same as Johns
        memberFromStorage = storage.getMembersByName("Michael");
        assertEquals("Michael", memberFromStorage.get(0).get("member_name"));
        assertEquals(200, Integer.parseInt(memberFromStorage.get(0).get("member_id")));
    }

    @Test
    public void testGetCompetitionResults () {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(00, 04, 40, 00);

        CompetitionResult competitionResult = new CompetitionResult("Olympics", 1, SwimmingDiscipline.CRAWL, date, time, 5);

        int numberOfResultsBefore = storage.getCompetitionResults(5).size();
        storage.addCompResult(competitionResult, 5);

        int numberOfResultsAfter = storage.getCompetitionResults(5).size();

        assertEquals(numberOfResultsBefore + 1, numberOfResultsAfter);
    }

    @Test
    public void testGetCompetitiveResults_nonExistingMember () {
        assertTrue(storage.getCompetitionResults(400).isEmpty());
    }

    @Test
    public void testGetTrainingResults () {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(00, 04, 40, 00);


        TrainingResult trainingResult = new TrainingResult(SwimmingDiscipline.CRAWL, date, time, 5);

        int numberOfResultsBefore = storage.getTrainingResults(5).size();
        storage.addTrainingResult(trainingResult, 5);

        int numberOfResultsAfter = storage.getTrainingResults(5).size();

        assertEquals(numberOfResultsBefore + 1, numberOfResultsAfter);
    }

    @Test
    public void testGetTrainingResults_nonExistingMember () {
        assertTrue(storage.getTrainingResults(400).isEmpty());
    }

    @Test
    public void testGetSwimmingDisciplines () {
        assertEquals(1, (int)storage.getSwimmingDisciplines(5).get(0));
        assertEquals(4, (int)storage.getSwimmingDisciplines(5).get(1));
    }

    @Test
    public void testGetSwimmingDisciplines_NonExistingMember () {
        assertNull(storage.getSwimmingDisciplines(400));
    }

    @Test
    public void testGetTopFiveCompetitionResultsByDiscipline () {
        // Do we get 5 results back?
        assertEquals(5, storage.getTopFiveCompetitionResultsByDiscipline(1).size());
        LocalTime result1 = LocalTime.parse(storage.getTopFiveCompetitionResultsByDiscipline(1).get(0).get("best_time"));
        LocalTime result2 = LocalTime.parse(storage.getTopFiveCompetitionResultsByDiscipline(1).get(1).get("best_time"));
        LocalTime result3 = LocalTime.parse(storage.getTopFiveCompetitionResultsByDiscipline(1).get(2).get("best_time"));
        LocalTime result4 = LocalTime.parse(storage.getTopFiveCompetitionResultsByDiscipline(1).get(3).get("best_time"));
        LocalTime result5 = LocalTime.parse(storage.getTopFiveCompetitionResultsByDiscipline(1).get(4).get("best_time"));

        // Right order?
        assertTrue(result1.compareTo(result2) <= 0);
        assertTrue(result2.compareTo(result3) <= 0);
        assertTrue(result3.compareTo(result4) <= 0);
        assertTrue(result4.compareTo(result5) <= 0);
    }

    @Test
    public void testGetTopFiveTrainingResultsByDiscipline () {
        // Do we get 5 results back?
        assertEquals(5, storage.getTopFiveTrainingResultsByDiscipline(1).size());
        LocalTime result1 = LocalTime.parse(storage.getTopFiveTrainingResultsByDiscipline(1).get(0).get("best_time"));
        LocalTime result2 = LocalTime.parse(storage.getTopFiveTrainingResultsByDiscipline(1).get(1).get("best_time"));
        LocalTime result3 = LocalTime.parse(storage.getTopFiveTrainingResultsByDiscipline(1).get(2).get("best_time"));
        LocalTime result4 = LocalTime.parse(storage.getTopFiveTrainingResultsByDiscipline(1).get(3).get("best_time"));
        LocalTime result5 = LocalTime.parse(storage.getTopFiveTrainingResultsByDiscipline(1).get(4).get("best_time"));

        // Right order?
        assertTrue(result1.compareTo(result2) <= 0);
        assertTrue(result2.compareTo(result3) <= 0);
        assertTrue(result3.compareTo(result4) <= 0);
        assertTrue(result4.compareTo(result5) <= 0);
    }





}
