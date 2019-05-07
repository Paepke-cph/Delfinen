package core;

import java.time.LocalDate;

/**
 * @author Alexander
 * @author Benjamin
 * @author Mads
 * @author Tobias
 */
public class Member {

    private CompetitionSwimmer competition;
    private String name;
    private int age;
    private int id;
    private double subscription;
    private boolean active;
    private LocalDate arrears;

    /**
     * Creates a generic type of member.
     * @param name The name of the swimmer/coach
     * @param age The age of the swimmer/coach
     * @param id The id of the swimmer/coach
     * @param active The membership status of the swimmer.
     */
    public Member(String name, int age, int id, boolean active) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.active = active;
    }

    /**
     * Creates a generic type of member.
     * @param active The membership of the swimmer.
     * @param name The name of the swimmer/coach.
     * @param age The age of the swimmer/coach
     * @param id The id of the swimmer/coach
     * @param arrears The date of the last paid membership subscription. Must be NULL if the member is a coach.
     * @param competition The associated competition information about the swimmer.
     */
    public Member(boolean active, String name, int age, int id, LocalDate arrears, CompetitionSwimmer competition) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.competition = competition;
        this.subscription = calculatePrice();
        this.active = active;
        this.arrears = arrears;
    }

    /**
     * Gets the competition information of the swimmer.
     * @return A CompetitionSwimmer object, with competition information.
     */
    public CompetitionSwimmer getCompetition() {
        return competition;
    }

    /**
     * Returns whether or not the swimmer's membership is active or inactive.
     * @return TRUE if membership is active and FALSE if membership is inactive.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the swimmer's membership status.
     * @param active TRUE if the membership should be active and FALSE if the membership should be inactive.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets the name of the swimmer.
     * @return The name of the swimmer.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the swimmer's name.
     * @param name The new name of the swimmer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the age of the swimmer.
     * @return The age of the swimmer.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the swimmer's age.
     * @param age The swimmer's new age.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets the last paid date of the swimmer
     * @param arrears The date of the last paid subscription fee.
     */
    public void setArrears(LocalDate arrears) {
        this.arrears = arrears;
    }

    /**
     * Gets the ID of the swimmer/coach.
     * @return The ID of the swimmer/coach.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the swimmer/coach.
     * @param id The new ID of the swimmer/coach, MUST be equal to the information on the database.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Calculates the price of the subscription of the coach. Due to the fact that only coaches should be created as the generic type Member it is always 0
     * @return The price of the subscription fee.
     */
    public double calculatePrice() {
        return 0;
    }

    /**
     * Gets the last date of the last paid subscription fee.
     * @return The date of the last paid subscription fee.
     */
    public LocalDate getArrears() {
        return arrears;
    }

    @Override
    public String toString() {
        String comp = competition != null ? "Kompetitive svømmer" : "";
        String medlemskab = active ? "Aktivt" : "Inaktivt";

        return "ID:\t\t\t\t" + id + "\nNavn:\t\t\t" + name + "\nAlder:\t\t\t" + age
                + "\nMedlemskab:\t\t" + medlemskab + "\nSidst betalt:\t" + arrears + "\n" + comp;
    }

}
