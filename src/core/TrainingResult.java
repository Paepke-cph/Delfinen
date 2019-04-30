package core;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Alexander
 */
public class TrainingResult {

    private SwimmingDiscipline swimmingDiscipline;
    private LocalDate date;
    private LocalTime time;
    private Member member;

    public TrainingResult(SwimmingDiscipline swimmingDiscipline, LocalDate date, LocalTime time, Member member) {
        this.swimmingDiscipline = swimmingDiscipline;
        this.date = date;
        this.time = time;
        this.member = member;
    }

    public Member getMember() {
        return member;
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
