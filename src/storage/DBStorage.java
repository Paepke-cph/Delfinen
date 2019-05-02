package storage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Alexander
 */
public class DBStorage implements Storage{

    SQLConnector sqlConnector;

    public DBStorage() throws SQLException {
        this.sqlConnector = new SQLConnector();
    }

    @Override
    public ArrayList<HashMap<String, String>> getMembers() {
        String getMembersFromStorage = "SELECT * FROM MEMBERS";
        ArrayList<HashMap<String, String>> list = sqlConnector.selectQuery(getMembersFromStorage);
        return list;
    }

    // TODO: getNextMemberID
    @Override
    public int getNextMemberID () {
        // TODO(Benjamin): This won't work correctly if you remove the latest added member and then add a new member
        String getMaxID = "SELECT MAX(MEMBER_ID) AS 'MEMBER_ID' FROM MEMBERS";
        ArrayList<HashMap<String, String>> list = sqlConnector.selectQuery(getMaxID);
        return Integer.parseInt(list.get(0).get("member_id")) + 1;
    }

    // TODO: Remove Member
    // TODO: Create new member
    // TODO: Change Sub

    @Override
    public ArrayList<HashMap<String, String>> getCompetitionResults(int member_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<HashMap<String, String>> getTrainingResults(int member_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getSwimmingDiscplines(int member_id) {
        return null;
    }

    @Override
    public ArrayList<Integer> getSwimmingDisciplines(int member_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
