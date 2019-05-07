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

    /**
     * Gets the display name of the SwimmingDiscipline.
     * @return A String representing the name of the SwimmingDiscipline.
     */
    public String getDisciplineName() {
        return disciplineName;
    }

    /**
     * Gets a list of all the disciplines a member can be associated with.
     * @return A list of SwimmingDisciplines.
     */
    public static ArrayList<SwimmingDiscipline> getDisciplinesAsList() {
        ArrayList<SwimmingDiscipline> discipline = new ArrayList<>();
        discipline.add(SwimmingDiscipline.BUTTERFLY);
        discipline.add(SwimmingDiscipline.CRAWL);
        discipline.add(SwimmingDiscipline.BREASTSTROKE);
        discipline.add(SwimmingDiscipline.BACKSTROKE);
        return discipline;
    }

}
