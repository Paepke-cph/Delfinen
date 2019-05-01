package core;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
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

    // TODO: Remove member
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

    private void loadMembersFromStorage() {
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
        String name = map.get("member_name");
        int age = Integer.parseInt(map.get("age"));
        int id = Integer.parseInt(map.get("member_id"));
        Member member = new Member(name, age, id, null);
        addMember("Coach", member);
    }

    private void createJuniorMember(HashMap<String, String> map) {
        String name = map.get("member_name");
        int age = Integer.parseInt(map.get("age"));
        int member_id = Integer.parseInt(map.get("member_id"));
        boolean active = map.get("active").equalsIgnoreCase("1");
        JuniorMember member;
        if (map.get("coach") == null) {
            member = new JuniorMember(active, name, age, member_id, null);
        } else {
            int coach_id = Integer.parseInt(map.get("coach"));
            CompetitionSwimmer compSwim = createCompetition(member_id, coach_id);
            member = new JuniorMember(active, name, age, member_id, compSwim);
        }
        addMember("JuniorMember", member);
    }

    private void createSeniorMember(HashMap<String, String> map) {
        String name = map.get("member_name");
        int age = Integer.parseInt(map.get("age"));
        int member_id = Integer.parseInt(map.get("member_id"));
        boolean active = map.get("active").equalsIgnoreCase("1");
        SeniorMember member;
        if (map.get("coach") == null) {
            member = new SeniorMember(active, name, age, member_id, null);
        } else {
            int coach_id = Integer.parseInt(map.get("coach"));
            CompetitionSwimmer compSwim = createCompetition(member_id, coach_id);
            member = new SeniorMember(active, name, age, member_id, compSwim);
        }
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
        ArrayList<String> juniors = new ArrayList<>();
        for (Member member : members.get("JuniorMember")) {
            juniors.add(member.toString());
        }
        return juniors;
    }

    public ArrayList<String> returnCoaches() {
        ArrayList<String> coaches = new ArrayList<>();
        for (Member member : members.get("Coach")) {
            coaches.add(member.toString());
        }
        return coaches;
    }

    public Member searchMemberById(int member_id) {
        if (members.containsKey("Coach")) {
            for (Member member : members.get("Coach")) {
                if (member.getId() == member_id) {
                    return member;
                }
            }
        }
        if (members.containsKey("JuniorMember")) {
            for (Member member : members.get("JuniorMember")) {
                if (member.getId() == member_id) {
                    return member;
                }
            }
        }
        if (members.containsKey("SeniorMember")) {
            for (Member member : members.get("SeniorMember")) {
                if (member.getId() == member_id) {
                    return member;
                }
            }
        }
        return null;
    }

    private CompetitionSwimmer createCompetition(int member_id, int coach_id) {
        Member coach = searchMemberById(coach_id);
        ArrayList<SwimmingDiscipline> disc = createDisciplines(member_id);
        CompetitionSwimmer compSwim = new CompetitionSwimmer(coach, disc);
        createCompetitionResultFromStorage(member_id, compSwim);
        createTrainingResultFromStorage(member_id, compSwim);
        return compSwim;
    }

    private void createTrainingResultFromStorage(int member_id, CompetitionSwimmer compSwim) {
        ArrayList<HashMap<String, String>> getResultsFromStorage = storage.getTrainingResults(member_id);
        if (!getResultsFromStorage.isEmpty()) {
            for (HashMap<String, String> map : getResultsFromStorage) {
                int swimDiscID = Integer.parseInt(map.get("discipline_id"));
                LocalDate date = LocalDate.parse(map.get("training_date"));
                SwimmingDiscipline disc = getSwimmingDisciplineByID(swimDiscID);
                LocalTime time = LocalTime.parse(map.get("best_time"));
                int trainingID = Integer.parseInt(map.get("training_id"));
                TrainingResult train = new TrainingResult(disc, date, time, trainingID);
                compSwim.addTrainingResult(train);
            }
        }
    }
    
    private void createCompetitionResultFromStorage(int member_id, CompetitionSwimmer compSwim) {
        ArrayList<HashMap<String, String>> getResultsFromStorage = storage.getCompetitionResults(member_id);
        if (!getResultsFromStorage.isEmpty()) {
            for (HashMap<String, String> map : getResultsFromStorage) {
                String event = map.get("event");
                int placement = Integer.parseInt(map.get("placement"));
                int swimDiscID = Integer.parseInt(map.get("discipline_id"));
                LocalDate date = LocalDate.parse(map.get("event_date"));
                SwimmingDiscipline disc = getSwimmingDisciplineByID(swimDiscID);
                LocalTime time = LocalTime.parse(map.get("best_time"));
                int competitionID = Integer.parseInt(map.get("competition_id"));
                CompetitionResult comp = new CompetitionResult(event, placement, disc, date, time, competitionID);
                compSwim.addCompetitionResult(comp);
            }
        }
    }

    private ArrayList<SwimmingDiscipline> createDisciplines(int member_id) {
        ArrayList<Integer> list = storage.getSwimmingDisciplines(member_id);
        ArrayList<SwimmingDiscipline> swimmingDisciplines = new ArrayList<>();
        for (Integer number : list) {
            swimmingDisciplines.add(getSwimmingDisciplineByID(number));
        }
        return swimmingDisciplines;
    }

    private SwimmingDiscipline getSwimmingDisciplineByID(int id) {
        switch (id) {
            case 1:
                return SwimmingDiscipline.BUTTERFLY;
            case 2:
                return SwimmingDiscipline.CRAWL;
            case 3:
                return SwimmingDiscipline.BACKSTROKE;
            default:
                return SwimmingDiscipline.BREASTSTROKE;
        }
    }
}
