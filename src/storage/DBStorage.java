package storage;

import core.CompetitionResult;
import core.CompetitionSwimmer;
import core.Member;
import core.SwimmingDiscipline;
import core.TrainingResult;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
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
        String PREP_GET_NEXT_ID = "SELECT MAX(MEMBER_ID) as member_id FROM MEMBERS";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(PREP_GET_NEXT_ID)) {
            ArrayList<HashMap<String, String>> list = sqlConnector.selectQuery(preparedStatement);
            return Integer.parseInt(list.get(0).get("member_id")) + 1;
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
        String prepDelete = "DELETE FROM MEMBERS WHERE MEMBER_ID = ?";
        try (PreparedStatement preparedDeleteStatement = sqlConnector.getConnection().prepareStatement(prepDelete)) {
            removeTrainingResult(member_id);
            removeCompResult(member_id);
            removeDisciplineAssociation(member_id);
            preparedDeleteStatement.setInt(1, member_id);
            return sqlConnector.insertUpdateDeleteQuery(preparedDeleteStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean removeTrainingResult(int member_id) {
        String prepDelete = "DELETE FROM TRAINING_RESULTS WHERE MEMBER_ID = ?";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(prepDelete)) {
            preparedStatement.setInt(1, member_id);
            return sqlConnector.insertUpdateDeleteQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean removeDisciplineAssociation(int member_id) {
        String prepDelete = "DELETE FROM DISCIPLINE_MEMBER WHERE MEMBER_ID = ?";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(prepDelete)) {
            preparedStatement.setInt(1, member_id);
            return sqlConnector.insertUpdateDeleteQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean removeCompResult(int member_id) {
        String prepDelete = "DELETE FROM COMPETITION_RESULTS WHERE MEMBER_ID = ?";
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
        String memberName = member.getName();
        int memberAge = member.getAge();
        double subscription = member.calculatePrice();
        boolean active = member.isActive();
        LocalDate arrears = member.getArrears();
        CompetitionSwimmer compSwim = member.getCompetition();
        int member_id = member.getId();
        String insertMember = "INSERT INTO MEMBERS (MEMBER_NAME, AGE, SUBSCRIPTION, ACTIVE, ARREARS, MEMBER_ID) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(insertMember)) {
            preparedStatement.setString(1, memberName);
            preparedStatement.setInt(2, memberAge);
            preparedStatement.setDouble(3, subscription);
            preparedStatement.setBoolean(4, active);
            preparedStatement.setDate(5, java.sql.Date.valueOf(arrears));
            preparedStatement.setInt(6, member_id);
            boolean success = sqlConnector.insertUpdateDeleteQuery(preparedStatement);
            if(compSwim != null){
                return addSwimmingDisciplineToMember(compSwim, member_id);
            } else {
                return success;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean addSwimmingDisciplineToMember(CompetitionSwimmer compSwim, int member_id){
        int member = member_id;
        String insertDisciplineMember = "INSERT INTO DISCIPLINE_MEMBER (MEMBER_ID, DISCIPLINE_ID) VALUES (?, ?)";
        boolean success = false;
        for(SwimmingDiscipline swimDisc : compSwim.getSwimmingDiscipline()){
            int disciplineNumber = getDisciplineIDFromName(swimDisc);
            try(PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(insertDisciplineMember)){
                preparedStatement.setInt(1, member_id);
                preparedStatement.setInt(2, disciplineNumber);
                success = sqlConnector.insertUpdateDeleteQuery(preparedStatement);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return success;
    }
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

    public ArrayList<HashMap<String, String>> getTopFiveTrainingResultsByDiscipline(int discipline_id) {
        String get_Top_Results_By_Discipline = "SELECT * FROM training_results join members on training_results.MEMBER_ID = members.MEMBER_ID WHERE discipline_id = ? ORDER BY best_time LIMIT 5";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(get_Top_Results_By_Discipline)) {
            preparedStatement.setInt(1, discipline_id);
            return sqlConnector.selectQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<HashMap<String, String>> getTopFiveCompetitionResultsByDiscipline(int discipline_id) {
        String get_Top_Results_By_Discipline = "SELECT * FROM competition_results join members on competition_results.MEMBER_ID = members.MEMBER_ID WHERE discipline_id = ? ORDER BY best_time LIMIT 5";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(get_Top_Results_By_Discipline)) {
            preparedStatement.setInt(1, discipline_id);
            return sqlConnector.selectQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addTrainingResult(TrainingResult result, int member_id) {
        LocalDate date = result.getDate();
        LocalTime time = result.getTime();
        int discID = getDisciplineIDFromName(result.getSwimmingDiscipline());
        String addResult = "INSERT INTO TRAINING_RESULTS (DISCIPLINE_ID, TRAINING_DATE, BEST_TIME, MEMBER_ID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(addResult)) {
            preparedStatement.setInt(1, discID);
            preparedStatement.setString(2, date.toString());
            preparedStatement.setString(3, time.toString());
            preparedStatement.setInt(4, member_id);
            return sqlConnector.insertUpdateDeleteQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int getDisciplineIDFromName(SwimmingDiscipline swimDisc) {
        switch (swimDisc) {
            case BUTTERFLY:
                return 1;
            case CRAWL:
                return 2;
            case BACKSTROKE:
                return 3;
            default:
                return 4;
        }
    }

    public boolean addCompResult(CompetitionResult result, int member_id) {
        String name = result.getEvent();
        int discID = getDisciplineIDFromName(result.getSwimmingDiscipline());
        LocalDate date = result.getDate();
        LocalTime time = result.getTime();
        int placement = result.getPlacement();

        String addResult = "INSERT INTO COMPETITION_RESULTS (EVENT_NAME, DISCIPLINE_ID, EVENT_DATE, BEST_TIME, PLACEMENT, MEMBER_ID) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = sqlConnector.getConnection().prepareStatement(addResult)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, discID);
            preparedStatement.setString(3, date.toString());
            preparedStatement.setString(4, time.toString());
            preparedStatement.setInt(5, placement);
            preparedStatement.setInt(6, member_id);
            return sqlConnector.insertUpdateDeleteQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
