package core;

/**
 *
 * @author Alexander
 */
public enum SwimmingDiscipline {
    BUTTERFLY ("BUTTERFLY"),
    CRAWL ("CRAWL"),
    BREASTSTROKE ("BREASTSTROKE"),
    BACKSTROKE ("BACKSTROKE");
    
    private String disciplineName;
    SwimmingDiscipline(String disciplineName){
        this.disciplineName = disciplineName;
    }

    public String getDisciplineName() {
        return disciplineName;
    }
    
}
