/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;

/**
 *
 * @author rando
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
    
    public void addCompetitionResult(CompetitionResult result){
        competitionResult.add(result);
    }
    
    public void addTrainingResult(TrainingResult result){
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
    
    
    
    
}
