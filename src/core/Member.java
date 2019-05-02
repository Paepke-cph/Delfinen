package core;

/**
 *
 * @author Alexander
 */
public class Member {
    
    private CompetitionSwimmer competition;
    private String name;
    private int age;
    private int id;
    private double subscription;
    private boolean active;
    private boolean arrears;

    public Member(boolean active, String name, int age, int id, boolean arrears, CompetitionSwimmer competition) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.competition = competition;
        this.subscription = calculatePrice();
        this.active = active;
        this.arrears = arrears;
    }

    public CompetitionSwimmer getCompetition() {
        return competition;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double calculatePrice() {
        return 0;
    }

    public boolean isArrears() {
        return arrears;
    }

    @Override
    public String toString() {
        return name + ", " + age + ", " + id + ", " + competition;
    }

}