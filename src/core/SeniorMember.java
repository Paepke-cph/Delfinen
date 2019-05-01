package core;

/**
 *
 * @author Alexander
 */
public class SeniorMember extends JuniorMember{

    public SeniorMember(boolean active, String name, int age, int id, CompetitionSwimmer competition) {
        super(active, name, age, id, competition);
    }

    @Override
    public double calculatePrice() {
        double discount = 0.75;
        double subscription = 1600;
        double inactive = 500;
        if(!this.isActive())
            return inactive;
        else if (this.getAge() > 60)
            return discount * subscription;
        else
            return subscription;
    }    

}
