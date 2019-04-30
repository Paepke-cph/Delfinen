package core;

import java.util.ArrayList;
import java.util.Map;
import storage.Storage;
import ui.UI;

/**
 *
 * @author Alexander
 */
public class Members {
    private Map<String,ArrayList<Member>> members;
    private Storage storage;
    private UI ui;

    public Members(Storage storage) {
        this.storage = storage;
        loadMembersFromStorage();
    }

    public boolean setMember(String cat, Member member) {
        if(members.containsKey(cat)) {
            members.get(cat).add(member);
            return true;
        }
        return false;
    }

    public Map<String,ArrayList<Member>> getMembers() {
        return members;
    }
    
    public void loadMembersFromStorage(){
        
    }
    
}
