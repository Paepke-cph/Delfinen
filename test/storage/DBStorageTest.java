package storage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.junit.Test;
import org.junit.Before;

/**
 * @author Alexander
 * @author Benjamin
 * @author Mads
 * @author Tobias
 */
public class DBStorageTest {

    ArrayList<String> createDatabase = scanFromFile("UnitTestCreate_Delfinen.sql");
    ArrayList<String> populateDatabase = scanFromFile("UnitTestPopulate_Delfinen.sql");
    private final static String user = "root";
    private final static String password = "Nu66ets";
    private final static String IP = "127.0.0.1";
    private final static String PORT = "3306";
    private final static String DATABASE = "Delfinen";
    private final static String serverTime = "serverTimezone=UTC";
    private final static String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE + "?" + serverTime;

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test of getMembers method, of class DBStorage.
     */
    @Test
    public void testGetMembers() {
    }

    public ArrayList<String> scanFromFile(String filename) {
        ArrayList<String> file = new ArrayList();
        try {
            Scanner scan = new Scanner(new File("scripts\\" + filename));
            scan.useDelimiter(Pattern.compile(";"));
            while (scan.hasNext()) {
                file.add(scan.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}
