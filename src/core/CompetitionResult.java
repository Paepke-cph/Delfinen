package core;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Alexander
 */
public class CompetitionResult extends TrainingResult implements Comparable<TrainingResult> {

    private String event;
    private int placement;

    public CompetitionResult(String event, int placement, SwimmingDiscipline swimmingDiscipline, LocalDate date, LocalTime time, int id) {
        super(swimmingDiscipline, date, time, id);
        this.event = event;
        this.placement = placement;
    }

    public String getEvent() {
        return event;
    }

    public int getPlacement() {
        return placement;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCompetitionResult{" + "event=" + event + ", placement=" + placement + '}';
    }

}
