package core;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Alexander
 * @author Benjamin
 * @author Mads
 * @author Tobias
 */
public class TrainingResult implements Comparable<TrainingResult> {

    private SwimmingDiscipline swimmingDiscipline;
    private LocalDate date;
    private LocalTime time;
    private int id;

    /**
     * Creates a Training results object.
     * @param swimmingDiscipline The swimming discipline the result was achieved in.
     * @param date The date on which the result was achived on.
     * @param time The time of the swimmer.
     * @param id The id of the Training results.
     */
    public TrainingResult(SwimmingDiscipline swimmingDiscipline, LocalDate date, LocalTime time, int id) {
        this.swimmingDiscipline = swimmingDiscipline;
        this.date = date;
        this.time = time;
        this.id = id;
    }

    /**
     * Gets the swimming discipline of the result.
     * @return The SwimmingDiscipline of the result.
     */
    public SwimmingDiscipline getSwimmingDiscipline() {
        return swimmingDiscipline;
    }

    /**
     * The date on which the result was achived on.
     * @return The date of the result.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * The time of the lap of the result.
     * @return The time of the result.
     */
    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return id + "#\nResultat: " + swimmingDiscipline + "\nDato: " + date + ", Tid: " + time;
    }

    @Override
    public int compareTo(TrainingResult o) {
        return this.time.compareTo(o.getTime());
    }

}
