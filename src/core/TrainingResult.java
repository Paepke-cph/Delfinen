package core;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Alexander
 */
public class TrainingResult {

    SwimmingDiscipline swimmingDiscipline;
    LocalDate date;
    LocalTime time;

    public TrainingResult(SwimmingDiscipline swimmingDiscipline, LocalDate date, LocalTime time) {
        this.swimmingDiscipline = swimmingDiscipline;
        this.date = date;
        this.time = time;
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
    
    
}
