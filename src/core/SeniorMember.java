package core;

import java.time.LocalDate;

/**
 *
 * @author Alexander
 */
public class SeniorMember extends Member {
    private double subscription;
    private boolean active;

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

    @Override
    public double calculatePrice() {
        double discount = 0.75;
        double subscription = 1600;
        double inactive = 500;
        if(!this.active)
            return inactive;
        else if (this.getAge() > 60)
            return discount * subscription;
        else
            return subscription;
    }

    @Override
    public String toString() {
        return "Senior medlem:\n" + super.toString();
    }

}
