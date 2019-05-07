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

    /**
     * @param coach The coach which this swimmer is associated with.
     * @param swimmingDiscipline All the swimming disciplines the swimmer is par-taking in.
     */
    public CompetitionSwimmer(Member coach, ArrayList<SwimmingDiscipline> swimmingDiscipline) {
        this.coach = coach;
        this.swimmingDiscipline = swimmingDiscipline;
    }

    /**
     * Adds a new competition result to the swimmer
     * @param result The competition result
     */
    public void addCompetitionResult(CompetitionResult result) {
        competitionResult.add(result);
    }

    /**
     * Adds a new training result to the swimmer.
     * @param result The training result.
     */
    public void addTrainingResult(TrainingResult result) {
        trainingResult.add(result);
    }

    /**
     * Retrieves the swimmer's associated coach.
     * @return The coach.
     */
    public Member getCoach() {
        return coach;
    }

    /**
     * Gets the list of swimming disciplines the swimmer is par-taking in.
     * @return A list of all the swimming disciplines.
     */
    public ArrayList<SwimmingDiscipline> getSwimmingDiscipline() {
        return swimmingDiscipline;
    }

    /**
     * Gets the training results of the swimmer.
     * @return A list of all the training results.
     */
    public ArrayList<TrainingResult> getTrainingResult() {
        return trainingResult;
    }

    /**
     * Gets the competition results of the swimmer.
     * @return A list of all the competition results.
     */
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
