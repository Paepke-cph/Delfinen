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
//        UIController UIC = new UIController(new ConsoleUI(), new MockStorage());
//        UIC.startProgram();

        DBStorage storage;
        try {
            storage = new DBStorage();
            Members members = new Members(storage);
            for (String string : members.returnSeniorMembers())
                System.out.println(string);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}