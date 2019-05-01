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

    public int getNextMemberID () {
        String getMaxID = "SELECT MAX(MEMBER_ID) AS 'MEMBER_ID' FROM MEMBERS";
        ArrayList<HashMap<String, String>> list = sqlConnector.selectQuery(getMaxID);
        return Integer.parseInt(list.get(0).get("member_id")) + 1;
    }

    // TODO: Remove Member
    // TODO: Create new member
    // TODO: Change Sub
}
