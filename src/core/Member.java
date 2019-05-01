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

    public Member(String name, int age, int id, CompetitionSwimmer competition) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.competition = competition;
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

    @Override
    public String toString() {
        return name + ", " + age + ", " + id;
    }

}