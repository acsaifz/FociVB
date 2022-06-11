package hu.progmatic.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Match implements Comparable<Match>{
    public final static String SEPARATOR = ";";
    public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private WorldCup worldCup;
    private Stage stage;
    private LocalDate date;
    private String teamA;
    private String teamB;
    private int goalsA;
    private int goalsB;

    public Match(){

    }

    public Match(WorldCup worldCup, String[] parameters) {
        this.worldCup = worldCup;
        this.stage = Stage.getByString(parameters[1]);
        this.date = LocalDate.parse(parameters[2],DATE_FORMATTER);
        this.teamA = parameters[3];
        this.teamB = parameters[4];
        this.goalsA = Integer.parseInt(parameters[5]);
        this.goalsB = Integer.parseInt(parameters[6]);

    }

    public int getGoalDifference(){
        return Math.abs(goalsA-goalsB);
    }

    public int sumGoals(){
        return goalsA + goalsB;
    }

    public WorldCup getWorldCup() {
        return worldCup;
    }

    public void setWorldCup(WorldCup worldCup) {
        this.worldCup = worldCup;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public int getGoalsA() {
        return goalsA;
    }

    public void setGoalsA(int goalsA) {
        this.goalsA = goalsA;
    }

    public int getGoalsB() {
        return goalsB;
    }

    public void setGoalsB(int goalsB) {
        this.goalsB = goalsB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return goalsA == match.goalsA && goalsB == match.goalsB && Objects.equals(worldCup, match.worldCup) && stage == match.stage && Objects.equals(date, match.date) && Objects.equals(teamA, match.teamA) && Objects.equals(teamB, match.teamB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(worldCup, stage, date, teamA, teamB, goalsA, goalsB);
    }

    @Override
    public int compareTo(Match o) {
        if (date.isBefore(o.getDate())){
            return -1;
        }else{
            return 1;
        }
    }
}
