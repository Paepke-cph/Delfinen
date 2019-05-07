package core;

import java.util.ArrayList;

/**
 * @author Alexander
 * @author Benjamin
 * @author Mads
 * @author Tobias
 */
public class CompetitionSwimmer {

    private Member coach;
    private ArrayList<SwimmingDiscipline> swimmingDiscipline;
    private ArrayList<TrainingResult> trainingResult = new ArrayList<>();
    private ArrayList<CompetitionResult> competitionResult = new ArrayList<>();

    public CompetitionSwimmer(Member coach, ArrayList<SwimmingDiscipline> swimmingDiscipline) {
        this.coach = coach;
        this.swimmingDiscipline = swimmingDiscipline;
    }

    public void addCompetitionResult(CompetitionResult result) {
        competitionResult.add(result);
    }

    public void addTrainingResult(TrainingResult result) {
        trainingResult.add(result);
    }

    public Member getCoach() {
        return coach;
    }

    public ArrayList<SwimmingDiscipline> getSwimmingDiscipline() {
        return swimmingDiscipline;
    }

    public ArrayList<TrainingResult> getTrainingResult() {
        return trainingResult;
    }

    public ArrayList<CompetitionResult> getCompetitionResult() {
        return competitionResult;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < swimmingDiscipline.size(); i++) {
            stringBuilder.append(swimmingDiscipline.get(i).getDisciplineName());
            if (i < swimmingDiscipline.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return coach + " " + stringBuilder.toString();
    }
}
