package core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import storage.Storage;
import ui.UI;

/**
 *
 * @author Alexander
 */
public class StorageController {

    private static final String COACH_CAT = "Coach";
    private static final String JUNIOR_CAT = "JuniorMember";
    private static final String SENIOR_CAT = "SeniorMember";

    private HashMap<String, ArrayList<Member>> members;
    private Storage storage;
    private UI ui;

    public StorageController(Storage storage) {
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

    public ArrayList<Member> getMembersByName(String name) {
        ArrayList<HashMap<String, String>> collection = storage.getMembersByName(name);
        ArrayList<Member> result = new ArrayList<>();
        for (HashMap<String, String> hashMap : collection) {
            String mName = hashMap.get("member_name");
            int mAge = Integer.parseInt(hashMap.get("age"));
            int mID = Integer.parseInt(hashMap.get("member_id"));
            LocalDate mDate = LocalDate.parse(hashMap.get("arrears"));
            Member member = new Member(true, mName, mAge, mID, mDate, null);
            result.add(member);
        }
        return result;
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
                createCoach(map);
            } else if (age < 18) {
                createJuniorMember(map);
            } else {
                createSeniorMember(map);
            }
        }
    }

    private void createCoach(HashMap<String, String> map) {
        String name = map.get("member_name");
        int age = Integer.parseInt(map.get("age"));
        int id = Integer.parseInt(map.get("member_id"));
        LocalDate arrears = LocalDate.parse(map.get("arrears"));
        Member member = new Member(true, name, age, id, arrears, null);
        addMember(COACH_CAT, member);
    }


    private void createJuniorMember(HashMap<String, String> map) {
        String name = map.get("member_name");
        int age = Integer.parseInt(map.get("age"));
        int member_id = Integer.parseInt(map.get("member_id"));
        boolean active = map.get("active").equalsIgnoreCase("1");
        LocalDate arrears = LocalDate.parse(map.get("arrears"));
        JuniorMember member;
        if (map.get("coach_id") == null) {
            member = new JuniorMember(active, name, age, member_id, arrears, null);
        } else {
            int coach_id = Integer.parseInt(map.get("coach_id"));
            CompetitionSwimmer compSwim = createCompetition(member_id, coach_id);
            member = new JuniorMember(active, name, age, member_id, arrears, compSwim);
        }
        addMember(JUNIOR_CAT, member);
    }

    private void createSeniorMember(HashMap<String, String> map) {
        String name = map.get("member_name");
        int age = Integer.parseInt(map.get("age"));
        int member_id = Integer.parseInt(map.get("member_id"));
        boolean active = map.get("active").equalsIgnoreCase("1");
        LocalDate arrears = LocalDate.parse(map.get("arrears"));
        SeniorMember member;
        if (map.get("coach_id") == null) {
            member = new SeniorMember(active, name, age, member_id, arrears, null);
        } else {
            int coach_id = Integer.parseInt(map.get("coach_id"));
            CompetitionSwimmer compSwim = createCompetition(member_id, coach_id);
            member = new SeniorMember(active, name, age, member_id, arrears, compSwim);
        }
        addMember(SENIOR_CAT, member);
    }

    public void addMember(String cat, Member member) {
        ArrayList<Member> list = new ArrayList<>();
        list.add(member);
        if (cat.equalsIgnoreCase(COACH_CAT)) {
            if (members.containsKey(COACH_CAT)) {
                members.get(COACH_CAT).add(member);
            } else {
                members.put(COACH_CAT, list);
            }
        } else if (cat.equalsIgnoreCase(SENIOR_CAT)) {
            if (members.containsKey(SENIOR_CAT)) {
                members.get(SENIOR_CAT).add(member);
            } else {
                members.put(SENIOR_CAT, list);
            }
        } else if (cat.equalsIgnoreCase(JUNIOR_CAT)) {
            if (members.containsKey(JUNIOR_CAT)) {
                members.get(JUNIOR_CAT).add(member);
            } else {
                members.put(JUNIOR_CAT, list);
            }
        }
    }

    public void removeMember(String cat, Member member) {
        if(members.containsKey(cat)) {
            if(members.get(cat).remove(member)) {
                System.out.println("Old member removed");
            }
        }
    }

    public ArrayList<String> returnSeniorMembers() {
        ArrayList<String> seniors = new ArrayList<>();
        for (Member member : members.get(SENIOR_CAT)) {
            seniors.add(member.toString());
        }
        return seniors;
    }

    public ArrayList<String> returnJuniorMembers() {
        ArrayList<String> juniors = new ArrayList<>();
        for (Member member : members.get(JUNIOR_CAT)) {
            juniors.add(member.toString());
        }
        return juniors;
    }

    public ArrayList<String> returnCoaches() {
        ArrayList<String> coaches = new ArrayList<>();
        for (Member member : members.get(COACH_CAT)) {
            coaches.add(member.toString());
        }
        return coaches;
    }

    public Member searchMemberById(int member_id) {
        if (members.containsKey(COACH_CAT)) {
            for (Member member : members.get(COACH_CAT)) {
                if (member.getId() == member_id) {
                    return member;
                }
            }
        }
        if (members.containsKey(JUNIOR_CAT)) {
            for (Member member : members.get(JUNIOR_CAT)) {
                if (member.getId() == member_id) {
                    return member;
                }
            }
        }
        if (members.containsKey(SENIOR_CAT)) {
            for (Member member : members.get(SENIOR_CAT)) {
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

    public static String getCoachCat() {
        return COACH_CAT;
    }

    public static String getJuniorCat() {
        return JUNIOR_CAT;
    }

    public static String getSeniorCat() {
        return SENIOR_CAT;
    }

    public ArrayList<Member> getArrears() {
        ArrayList<Member> isArrears = new ArrayList<>();
        LocalDate date = LocalDate.now().minusYears(1);
        if (members.containsKey(SENIOR_CAT)) {
            for (Member member : members.get(SENIOR_CAT)) {
                if (member.getArrears().isBefore(date)) {
                    isArrears.add(member);
                }
            }
        }
        if (members.containsKey(JUNIOR_CAT)) {
            for (Member member : members.get(JUNIOR_CAT)) {
                if (member.getArrears().isBefore(date)) {
                    isArrears.add(member);
                }
            }
        }
        if (isArrears.isEmpty()) {
            return null;
        }
        return isArrears;
    }
}
