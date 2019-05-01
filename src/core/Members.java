package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import storage.Storage;
import ui.UI;

/**
 *
 * @author Alexander
 */
public class Members {

    private HashMap<String, ArrayList<Member>> members;
    private Storage storage;
    private UI ui;

    public Members(Storage storage) {
        this.storage = storage;
        this.members = new HashMap<>();
        loadMembersFromStorage();
    }

    public boolean setMember(String cat, Member member) {
        if (members.containsKey(cat)) {
            members.get(cat).add(member);
            return true;
        }
        return false;
    }

    public HashMap<String, ArrayList<Member>> getMembers() {
        return members;
    }

    public void loadMembersFromStorage() {
        ArrayList<HashMap<String, String>> list = storage.getMembers();
        for (HashMap<String, String> map : list) {
            int subscription = 0;
            if (map.containsKey("subscription") && map.get("subscription") != null) {
                subscription = Integer.parseInt(map.get("subscription"));
            }
            int age = Integer.parseInt(map.get("age"));
            if (map.get("subscription") == null || subscription < 500) {
                createMember(map);
            } else if (age < 18) {
                createJuniorMember(map);
            } else {
                createSeniorMember(map);
            }
        }
    }

    private void createMember(HashMap<String, String> map) {
        String name = map.get("name");
        int age = Integer.parseInt(map.get("age"));
        int id = Integer.parseInt(map.get("id"));
        Member member = new Member(name, age, id, null);
        addMember("Coach", member);
    }

    private void createJuniorMember(HashMap<String, String> map) {
        String name = map.get("name");
        int age = Integer.parseInt(map.get("age"));
        int id = Integer.parseInt(map.get("id"));
        boolean active = Boolean.parseBoolean(map.get("active"));
        JuniorMember member = new JuniorMember(active, name, age, id, null);
        addMember("JuniorMember", member);
    }

    private void createSeniorMember(HashMap<String, String> map) {
        String name = map.get("name");
        int age = Integer.parseInt(map.get("age"));
        int id = Integer.parseInt(map.get("id"));
        boolean active = Boolean.parseBoolean(map.get("active"));
        SeniorMember member = new SeniorMember(active, name, age, id, null);
        addMember("SeniorMember", member);
    }

    public void addMember(String cat, Member member) {
        String coach = "Coach";
        String seniorMember = "SeniorMember";
        String juniorMember = "JuniorMember";
        ArrayList<Member> list = new ArrayList<>();
        list.add(member);
        if (cat.equalsIgnoreCase(coach)) {
            if (members.containsKey(coach)) {
                members.get(coach).add(member);
            } else {
                members.put(coach, list);
            }
        } else if (cat.equalsIgnoreCase(seniorMember)) {
            if (members.containsKey(seniorMember)) {
                members.get(seniorMember).add(member);
            } else {
                members.put(seniorMember, list);
            }
        } else if (cat.equalsIgnoreCase(juniorMember)) {
            if (members.containsKey(juniorMember)) {
                members.get(juniorMember).add(member);
            } else {
                members.put(juniorMember, list);
            }
        }
    }

    public ArrayList<String> returnSeniorMembers() {
        ArrayList<String> seniors = new ArrayList<>();
        for (Member member : members.get("SeniorMember")) {
            seniors.add(member.toString());
        }
        return seniors;
    }

    public ArrayList<String> returnJuniorMembers() {
        ArrayList<String> seniors = new ArrayList<>();
        for (Member member : members.get("JuniorMember")) {
            seniors.add(member.toString());
        }
        return seniors;
    }

    public ArrayList<String> returnCoaches() {
        ArrayList<String> seniors = new ArrayList<>();
        for (Member member : members.get("Coach")) {
            seniors.add(member.toString());
        }
        return seniors;
    }

}
