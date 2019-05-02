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

        try {
            DBStorage storage = new DBStorage();
            UIController UIC = new UIController(new ConsoleUI(), storage);
            UIC.startProgram();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
    }
}