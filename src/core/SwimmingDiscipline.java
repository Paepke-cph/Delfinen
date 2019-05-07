package core;

import java.util.ArrayList;

/**
 * @author Alexander
 * @author Benjamin
 * @author Mads
 * @author Tobias
 */
public enum SwimmingDiscipline {
    BUTTERFLY("BUTTERFLY"),
    CRAWL("CRAWL"),
    BREASTSTROKE("BREASTSTROKE"),
    BACKSTROKE("BACKSTROKE");

    private String disciplineName;

    SwimmingDiscipline(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public static ArrayList<SwimmingDiscipline> getDisciplinesAsList() {
        ArrayList<SwimmingDiscipline> discipline = new ArrayList<>();
        discipline.add(SwimmingDiscipline.BUTTERFLY);
        discipline.add(SwimmingDiscipline.CRAWL);
        discipline.add(SwimmingDiscipline.BREASTSTROKE);
        discipline.add(SwimmingDiscipline.BACKSTROKE);
        return discipline;
    }

}
