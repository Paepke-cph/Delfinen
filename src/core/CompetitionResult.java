package core;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Alexander
 */
public class CompetitionResult extends TrainingResult{

    private String event;
    private int placement;

    public CompetitionResult(String event, int placement, SwimmingDiscipline swimmingDiscipline, LocalDate date, LocalTime time, Member member) {
        super(swimmingDiscipline, date, time, member);
        this.event = event;
        this.placement = placement;
    }

    public String getEvent() {
        return event;
    }

    public int getPlacement() {
        return placement;
    }
    
    
}
