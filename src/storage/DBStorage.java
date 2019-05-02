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

    @Override
    public int getNextMemberID () {
        String getMaxID = "SELECT MAX(MEMBER_ID) AS 'MEMBER_ID' FROM MEMBERS";
        ArrayList<HashMap<String, String>> list = sqlConnector.selectQuery(getMaxID);
        return Integer.parseInt(list.get(0).get("member_id")) + 1;
    }

    // TODO: Remove Member
    @Override
    public boolean removeMember(int member_id) {
        String deleteTrainingResults = "DELETE FROM TRAINING_RESULTS WHERE MEMBER_ID LIKE " + member_id;
        sqlConnector.insertUpdateDeleteQuery(deleteTrainingResults);
        String deleteCompResults = "DELETE FROM COMPETITION_RESULTS WHERE MEMBER_ID LIKE " + member_id;
        sqlConnector.insertUpdateDeleteQuery(deleteCompResults);
        String deleteMember = "DELETE FROM MEMBERS WHERE MEMBER_ID LIKE " + member_id;
        sqlConnector.insertUpdateDeleteQuery(deleteMember);
        return true;
    }

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
    public ArrayList<Integer> getSwimmingDisciplines(int member_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
