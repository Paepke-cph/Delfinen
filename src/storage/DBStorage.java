package storage;

import core.Member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Alexander
 */
public class DBStorage implements Storage {

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
    public ArrayList<HashMap<String, String>> getMembersByName(String name) {
        String getMembersFromStorage = "SELECT * FROM MEMBERS WHERE member_name like \"" + name + "%\"";
        return sqlConnector.selectQuery(getMembersFromStorage);
    }

    @Override
    public int getNextMemberID() {
        String getNextID = "SELECT AUTO_INCREMENT as member_id FROM information_schema.TABLES "
                + "WHERE TABLE_SCHEMA = \"Delfinen\" "
                + "AND TABLE_NAME = \"MEMBERS\"";
        ArrayList<HashMap<String, String>> list = sqlConnector.selectQuery(getNextID);
        return Integer.parseInt(list.get(0).get("member_id"));
    }

    @Override
    public int getNextCompetitionID() {
        String getNextID = "SELECT AUTO_INCREMENT as competition_id FROM information_schema.TABLES "
                + "WHERE TABLE_SCHEMA = \"Delfinen\" "
                + "AND TABLE_NAME = \"COMPETITION_RESULTS\"";
        ArrayList<HashMap<String, String>> list = sqlConnector.selectQuery(getNextID);
        return Integer.parseInt(list.get(0).get("competition_id"));
    }

    @Override
    public int getNextTrainingID() {
        String getNextID = "SELECT AUTO_INCREMENT as trainning_id FROM information_schema.TABLES "
                + "WHERE TABLE_SCHEMA = \"Delfinen\" "
                + "AND TABLE_NAME = \"TRAINING_RESULTS\"";
        ArrayList<HashMap<String, String>> list = sqlConnector.selectQuery(getNextID);
        return Integer.parseInt(list.get(0).get("training_id"));
    }

    // TODO: Create new member
    // TODO: Remove Member
    @Override
    public boolean removeMember(int member_id) {
        String string = "SELECT * FROM MEMBERS WHERE MEMBER_ID LIKE " + member_id;
        ArrayList<HashMap<String, String>> memberExists = sqlConnector.selectQuery(string);
        if (!memberExists.isEmpty()) {
            String deleteTrainingResults = "DELETE FROM TRAINING_RESULTS WHERE MEMBER_ID LIKE " + member_id;
            sqlConnector.insertUpdateDeleteQuery(deleteTrainingResults);
            String deleteCompResults = "DELETE FROM COMPETITION_RESULTS WHERE MEMBER_ID LIKE " + member_id;
            sqlConnector.insertUpdateDeleteQuery(deleteCompResults);
            String deleteMember = "DELETE FROM MEMBERS WHERE MEMBER_ID LIKE " + member_id;
            sqlConnector.insertUpdateDeleteQuery(deleteMember);
            return true;
        }
        return false;
    }

    @Override
    public boolean createMember(Member member) {
        boolean active = member.isActive();
        String memberName = member.getName();
        int memberAge = member.getAge();
        double subscription = member.calculatePrice();
        boolean arrears = member.isArrears();

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO MEMBERS (MEMBER_NAME, AGE, SUBSCRIPTION, ACTIVE, ARREARS) VALUES (")
                .append("\""+memberName+"\"").append(", ")
                .append(memberAge).append(", ")
                .append(subscription).append(", ")
                .append(active).append(", ")
                .append(arrears).append(")");

        return sqlConnector.insertUpdateDeleteQuery(sb.toString());
    }

    // TODO: Change Sub
    @Override
    public ArrayList<HashMap<String, String>> getCompetitionResults(int member_id) {
        String getCompResults = "SELECT * FROM COMPETITION_RESULTS WHERE MEMBER_ID = " + member_id;
        return sqlConnector.selectQuery(getCompResults);
    }

    @Override
    public ArrayList<HashMap<String, String>> getTrainingResults(int member_id) {
        String getTrainingResults = "SELECT * FROM TRAINING_RESULTS WHERE MEMBER_ID = " + member_id;
        return sqlConnector.selectQuery(getTrainingResults);
    }

    @Override
    public ArrayList<Integer> getSwimmingDisciplines(int member_id) {
        String query = "SELECT * FROM discipline_member WHERE member_id = " + member_id;
        ArrayList<HashMap<String, String>> swimList = sqlConnector.selectQuery(query);
        ArrayList<Integer> swimDisc = new ArrayList<>();
        if (!swimList.isEmpty()) {
            for (HashMap<String, String> map : swimList) {
                swimDisc.add(Integer.parseInt(map.get("discipline_id")));
            }
            return swimDisc;
        }
        return null;
    }

}
