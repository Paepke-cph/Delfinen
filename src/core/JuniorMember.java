package core;

import java.time.LocalDate;

/**
 * @author Alexander
 * @author Benjamin
 * @author Mads
 * @author Tobias
 */
public class JuniorMember extends Member {

    private double subscription;
    private boolean active;

    /**
     * Creates a junior member, holding all details of the swimmer along with whether or not they are a competition swimmer.
     * @param active The status of the swimmer's membership
     * @param name The name of the swimmer.
     * @param age The age of the swimmer.
     * @param id The ID of the swimmer.
     * @param arrears The date of the last paid membership subscription.
     * @param competition The information about what disciplines the swimmer par-takes in, and what coach is associated with the swimmer. Can be null if the swimmer is not a competitive swimmer.
     */
    public JuniorMember(boolean active, String name, int age, int id, LocalDate arrears, CompetitionSwimmer competition) {
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
     * Calculates the subscription fee of the JuniorMember.
     * @return The price of the subscription of a JuniorMember, based on membership status.
     */
    @Override
    public double calculatePrice() {
        double inactive = 500;
        double subscription = 1000;
        if (!this.active) {
            return inactive;
        } else {
            return subscription;
        }
    }

    @Override
    public String toString() {
        return "Junior medlem:\n" + super.toString();
    }
}
