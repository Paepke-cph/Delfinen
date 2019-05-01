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
    private int id;

    public TrainingResult(SwimmingDiscipline swimmingDiscipline, LocalDate date, LocalTime time, Member member, int id) {
        this.swimmingDiscipline = swimmingDiscipline;
        this.date = date;
        this.time = time;
        this.member = member;
        this.id = id;
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
