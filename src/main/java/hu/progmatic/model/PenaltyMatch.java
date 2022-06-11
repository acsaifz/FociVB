package hu.progmatic.model;

import java.time.LocalDate;

public class PenaltyMatch extends Match{
    private int penaltyA;
    private int penaltyB;

    public PenaltyMatch(WorldCup worldCup,String[] parameters){
        setWorldCup(worldCup);
        setStage(Stage.getByString(parameters[1]));
        setDate(LocalDate.parse(parameters[2],DATE_FORMATTER));
        setTeamA(parameters[3]);
        setTeamB(parameters[4]);
        setGoalsA(Integer.parseInt(parameters[5]));
        setGoalsB(Integer.parseInt(parameters[6]));
        this.penaltyA = Integer.parseInt(parameters[7]);
        this.penaltyB = Integer.parseInt(parameters[8]);
    }

    @Override
    public int getGoalDifference(){
        return(Math.abs(penaltyA-penaltyB));
    }

    public int getPenaltyA() {
        return penaltyA;
    }

    public void setPenaltyA(int penaltyA) {
        this.penaltyA = penaltyA;
    }

    public int getPenaltyB() {
        return penaltyB;
    }

    public void setPenaltyB(int penaltyB) {
        this.penaltyB = penaltyB;
    }
}
