package storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Alexander
 */
public class SQLConnector implements AutoCloseable {

    private final static String user = "myuser";
    private final static String password = "Nu66ets";
    private final static String IP = "127.0.0.1";
    private final static String PORT = "3306";
    private final static String DATABASE = "Delfinen";
    private final static String serverTime = "serverTimezone=UTC";
    private final static String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE + "?" + serverTime;
    private Connection connection;
    private Statement statement;

    public SQLConnector() throws Exception{
        this.connection = DriverManager.getConnection(url,user,password);
        this.statement = connection.prepareStatement(url);
    }

    public ArrayList<HashMap<String, String>> selectQuery(String query) {
        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsdm = rs.getMetaData();
            while (rs.next()) {
                HashMap<String, String> column = new HashMap<>();
                for (int i = 1; i <= rsdm.getColumnCount(); i++) {
                    String name = rsdm.getColumnLabel(i).toLowerCase();
                    column.put(name, rs.getString(name));
                }
                result.add(column);
            }
            return result;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    
    
    @Override
    public void close() throws Exception {
        if (!connection.isClosed()) {
            connection.close();
        }
    }

}