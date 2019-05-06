package storage;

import core.Member;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Alexander
 */
public class DBStorage implements Storage {

    SQLConnector sqlConnector;
    //private final String PREP_GET_NEXT_ID = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";

    public DBStorage() throws SQLException {
        this.sqlConnector = new SQLConnector();
    }

    @Override
    public ArrayList<HashMap<String, String>> getMembers() {
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement("SELECT * FROM MEMBERS");) {
            return sqlConnector.selectQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HashMap<String, String>> getMembersByName(String name) {
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement("SELECT * FROM MEMBERS WHERE member_name like \"" + name + "%\"");) {
            return sqlConnector.selectQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getNextMemberID() {
        String PREP_GET_NEXT_ID = "SELECT AUTO_INCREMENT as member_id FROM information_schema.TABLES WHERE TABLE_SCHEMA = \"Delfinen\" AND TABLE_NAME = ?";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(PREP_GET_NEXT_ID)) {
            preparedStatement.setString(1, "MEMBERS");
            ArrayList<HashMap<String, String>> list = sqlConnector.selectQuery(preparedStatement);
            return Integer.parseInt(list.get(0).get("member_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getNextCompetitionID() {
        String PREP_GET_NEXT_ID = "SELECT AUTO_INCREMENT as result_id FROM information_schema.TABLES WHERE TABLE_SCHEMA = \"Delfinen\" AND TABLE_NAME = ?";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(PREP_GET_NEXT_ID)) {
            preparedStatement.setString(1, "COMPETITION_RESULTS");
            ArrayList<HashMap<String, String>> list = sqlConnector.selectQuery(preparedStatement);
            return Integer.parseInt(list.get(0).get("result_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getNextTrainingID() {
        String PREP_GET_NEXT_ID = "SELECT AUTO_INCREMENT as result_id FROM information_schema.TABLES WHERE TABLE_SCHEMA = \"Delfinen\" AND TABLE_NAME = ?";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(PREP_GET_NEXT_ID)) {
            preparedStatement.setString(1, "TRAINING_RESULTS");
            ArrayList<HashMap<String, String>> list = sqlConnector.selectQuery(preparedStatement);
            return Integer.parseInt(list.get(0).get("result_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO: Remove Member
    @Override
    public boolean removeMember(int member_id) {
        String prepSelect = "SELECT * FROM MEMBERS WHERE MEMBER_ID LIKE ?";
        String prepDelete = "DELETE FROM MEMBERS WHERE MEMBER_ID LIKE ?";

        try (PreparedStatement preparedSelectStatement = sqlConnector.getConnection().prepareStatement(prepSelect)) {
            preparedSelectStatement.setInt(1, member_id);
            ArrayList<HashMap<String, String>> memberExists = sqlConnector.selectQuery(preparedSelectStatement);

            if (!memberExists.isEmpty()) {
                try (PreparedStatement preparedDeleteStatement = sqlConnector.getConnection().prepareStatement(prepDelete)) {
                    removeTrainingResult(member_id);
                    removeCompResult(member_id);
                    preparedDeleteStatement.setInt(1, member_id);
                    return sqlConnector.insertUpdateDeleteQuery(preparedDeleteStatement);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean removeTrainingResult(int member_id) {
        String prepDelete = "DELETE FROM TRAINING_RESULTS WHERE MEMBER_ID LIKE ?";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(prepDelete)) {
            preparedStatement.setInt(2, member_id);
            return sqlConnector.insertUpdateDeleteQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean removeCompResult(int member_id) {
        String prepDelete = "DELETE FROM COMPETITION_RESULTS WHERE MEMBER_ID LIKE ?";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(prepDelete)) {
            preparedStatement.setInt(1, member_id);
            return sqlConnector.insertUpdateDeleteQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // TODO: Create new member
    @Override
    public boolean createMember(Member member) {
        boolean active = member.isActive();
        String memberName = member.getName();
        int memberAge = member.getAge();
        double subscription = member.calculatePrice();
        String arrears = member.getArrears().toString();

        String insertMember = "INSERT INTO MEMBERS (MEMBER_NAME, AGE, SUBSCRIPTION, ACTIVE, ARREARS) VALUES (?. ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(insertMember)) {
            preparedStatement.setString(1, memberName);
            preparedStatement.setInt(2, memberAge);
            preparedStatement.setDouble(3, subscription);
            preparedStatement.setBoolean(4, active);
            preparedStatement.setString(5, arrears);
            return sqlConnector.insertUpdateDeleteQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // TODO: Update member
    @Override
    public boolean updateMember(Member member) {
        boolean active = member.isActive();
        String memberName = member.getName();
        int memberAge = member.getAge();
        double subscription = member.calculatePrice();
        String arrears = member.getArrears().toString();
        int memberID = member.getId();

        String updateMember = "UPDATE MEMBERS SET MEMBER_NAME = ? AGE = ? SUBSCRIPTION = ? ACTIVE = ? ARREARS = ? WHERE MEMBER_ID = ?";

        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(updateMember)) {
            preparedStatement.setString(1, memberName);
            preparedStatement.setInt(2, memberAge);
            preparedStatement.setDouble(3, subscription);
            preparedStatement.setBoolean(4, active);
            preparedStatement.setString(5, arrears);
            preparedStatement.setInt(6, memberID);
            return sqlConnector.insertUpdateDeleteQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<HashMap<String, String>> getCompetitionResults(int member_id) {
        String PREP_GET_RESULTS = "SELECT * FROM COMPETITION_RESULTS WHERE MEMBER_ID = ?";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(PREP_GET_RESULTS)) {
            preparedStatement.setInt(1, member_id);
            return sqlConnector.selectQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HashMap<String, String>> getTrainingResults(int member_id) {
        String PREP_GET_RESULTS = "SELECT * FROM TRAINING_RESULTS WHERE MEMBER_ID = ?";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(PREP_GET_RESULTS)) {
            preparedStatement.setInt(1, member_id);
            return sqlConnector.selectQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Integer> getSwimmingDisciplines(int member_id) {
        String PREP_GET_RESULTS = "SELECT * FROM DISCIPLINE_MEMBER WHERE MEMBER_ID = ?";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(PREP_GET_RESULTS)) {
            preparedStatement.setInt(1, member_id);
            ArrayList<HashMap<String, String>> swimList = sqlConnector.selectQuery(preparedStatement);
            ArrayList<Integer> swimDisc = new ArrayList<>();
            if (!swimList.isEmpty()) {
                for (HashMap<String, String> map : swimList) {
                    swimDisc.add(Integer.parseInt(map.get("discipline_id")));
                }
                return swimDisc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HashMap<String, String>> getTopFiveTrainingResultsByDiscipline(int discipline_id) {
        String get_Top_Results_By_Discipline = "SELECT * FROM TRAINING_RESULTS join MEMBERS on ? = MEMBERS.MEMBER_ID WHERE DISCIPLINE_ID = ? ORDER BY BEST_TIME LIMIT 5";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(get_Top_Results_By_Discipline)) {
            preparedStatement.setString(1, "TRAINING_RESULTS.MEMBER_ID");
            preparedStatement.setInt(2, discipline_id);
            return sqlConnector.selectQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HashMap<String, String>> getTopFiveCompetitionResultsByDiscipline(int discipline_id) {
        String get_Top_Results_By_Discipline = "SELECT * FROM COMPETITIONS_RESULTS join MEMBERS on ? = MEMBERS.MEMBER_ID WHERE DISCIPLINE_ID = ? ORDER BY BEST_TIME LIMIT 5";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(get_Top_Results_By_Discipline)) {
            preparedStatement.setString(1, "s");
            preparedStatement.setInt(2, discipline_id);
            return sqlConnector.selectQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
