package core;

import java.util.ArrayList;
import storage.Storage;
import ui.UI;

/**
 *
 * @author Alexander
 */
public class Members {
    private ArrayList<Member> members;
    private Storage storage;
    private UI ui;

    public Members(Storage storage) {
        this.storage = storage;
        this.members = storage.getMembers();
    }

    public ArrayList<Member> getMembers() {
        return members;
    }
    
    
    
}
