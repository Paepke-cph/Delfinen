package core;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Alexander
 * @author Benjamin
 * @author Mads
 * @author Tobias
 */
public class CompetitionResult extends TrainingResult implements Comparable<TrainingResult> {

    private String event;
    private int placement;

    /**
     * Creates a competition result which holds data about the given competition.
     * @param event The name of the event.
     * @param placement The placement of the swimmer in the event.
     * @param swimmingDiscipline The discipline the swimmer par-took in, in this event.
     * @param date The date on which the swim-results was achieved on.
     * @param time The time it took the swimmer to complete a lap
     * @param id The id of this competition result.
     */
    public CompetitionResult(String event, int placement, SwimmingDiscipline swimmingDiscipline, LocalDate date, LocalTime time, int id) {
        super(swimmingDiscipline, date, time, id);
        this.event = event;
        this.placement = placement;
    }

    /**
     * Gets the name of the competition event.
     * @return the name of the event as a string.
     */
    public String getEvent() {
        return event;
    }

    /**
     * Gets the placement of the swimmer in the competition.
     * @return the placement of the swimmer in the competition as an int.
     */
    public int getPlacement() {
        return placement;
    }

    @Override
    public String toString() {
        return super.toString() + "\nStævne: " + event + ", Placering: " + placement;
    }

}
