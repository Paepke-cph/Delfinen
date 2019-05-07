package core;

import java.time.LocalDate;

/**
 * @author Alexander
 * @author Benjamin
 * @author Mads
 * @author Tobias
 */
public class SeniorMember extends Member {

    private double subscription;
    private boolean active;

    /**
     * Creates a SeniorMember with core information about the senior member and their competition.
     * @param active The status of the membership status.
     * @param name The name of the swimmer.
     * @param age The age of the swimmer.
     * @param id The id of the swimmer.
     * @param arrears The date of the last paid subscription fee.
     * @param competition The competition information of the swimmer. NULL if the swimmer is not a competition swimmer.
     */
    public SeniorMember(boolean active, String name, int age, int id, LocalDate arrears, CompetitionSwimmer competition) {
        super(active, name, age, id, arrears, competition);
        this.active = active;
        this.subscription = calculatePrice();
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
        this.subscription = calculatePrice();
    }

    /**
     * Calculates the subscription fee of the SeniorMember.
     * @return The price of the subscription of a SeniorMember, based on membership status as well as age.
     */
    @Override
    public double calculatePrice() {
        double discount = 0.75;
        double subscription = 1600;
        double inactive = 500;
        if (!this.active) {
            return inactive;
        } else if (this.getAge() > 60) {
            return discount * subscription;
        } else {
            return subscription;
        }
    }

    @Override
    public String toString() {
        return "Senior medlem:\n" + super.toString();
    }

}
