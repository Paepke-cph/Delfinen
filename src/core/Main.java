package core;

import java.sql.SQLException;
import storage.DBStorage;
import storage.MockStorage;
import ui.ConsoleUI;

/**
 *
 * @author rando
 */
public class Main {

    public static void main(String[] args) {
        // UIController UIC = new UIController(new ConsoleUI(), new MockStorage());
        // UIC.startProgram();

        try {
            DBStorage storage = new DBStorage();
            Members members = new Members(storage);
            System.out.println(members.searchMemberById(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
    }
}