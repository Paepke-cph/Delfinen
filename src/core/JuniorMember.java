package core;

import java.time.LocalDate;

/**
 *
 * @author rando
 */
public class JuniorMember extends Member {
    private double subscription;
    private boolean active;

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
    
    @Override
    public double calculatePrice(){
        double inactive = 500;
        double subscription = 1000;
        if(!this.active)
            return inactive;
        else
            return subscription;
    }

    @Override
    public String toString() {
        return super.toString() + " " + subscription + ", " + active;
    }
    
    
}
