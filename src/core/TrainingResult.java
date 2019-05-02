package core;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Alexander
 */
public class TrainingResult implements Comparable<TrainingResult> {

    private SwimmingDiscipline swimmingDiscipline;
    private LocalDate date;
    private LocalTime time;
    private int id;

    public TrainingResult(SwimmingDiscipline swimmingDiscipline, LocalDate date, LocalTime time, int id) {
        this.swimmingDiscipline = swimmingDiscipline;
        this.date = date;
        this.time = time;
        this.id = id;
    }

    public SwimmingDiscipline getSwimmingDiscipline() {
        return swimmingDiscipline;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "TrainingResult{" + "swimmingDiscipline=" + swimmingDiscipline + ", date=" + date + ", time=" + time + ", id=" + id + '}';
    }

    @Override
    public int compareTo(TrainingResult o) {
        return this.time.compareTo(o.getTime());
    }

}
