package core;

/**
 *
 * @author rando
 */
public class JuniorMember extends Member {
    private double subscription;
    private boolean active;
    
    public JuniorMember(boolean active, String name, int age, int id) {
        super(name, age, id);
        this.active = active;
        this.subscription = calculatePrice();
    }

    public void setSubscription(double subscription) {
        this.subscription = subscription;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
