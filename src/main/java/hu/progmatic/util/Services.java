package hu.progmatic.util;

import hu.progmatic.model.Match;
import hu.progmatic.model.PenaltyMatch;
import hu.progmatic.model.Stage;
import hu.progmatic.model.WorldCup;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Services {
    public final static String WORLDCUPS_PATH = "src/main/resources/worldcups.csv";
    public final static String MATCHES_PATH = "src/main/resources/matches_all.csv";


    public static boolean readWorldCups(List<WorldCup> worldCups,String path){
        try(Scanner fileScanner = new Scanner(new File(path))){
            fileScanner.nextLine();
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                worldCups.add(new WorldCup(line));
            }
        }catch (IOException e){
            return false;
        }

        return true;
    }

    public static boolean readMatches(List<WorldCup> worldCups, String path){
        try(Scanner fileScanner = new Scanner(new File(path))){
            fileScanner.nextLine();
            while(fileScanner.hasNextLine()){
                String[] parameters = fileScanner.nextLine().split(Match.SEPARATOR);
                WorldCup worldCup = findWorldCup(worldCups,Integer.parseInt(parameters[0]));
                if (parameters.length == 7){
                    worldCup.getMatches().add(new Match(worldCup,parameters));
                } else {
                    worldCup.getMatches().add(new PenaltyMatch(worldCup,parameters));
                }
            }
        }catch (IOException e){
            return false;
        }
        return true;
    }

    public static WorldCup findWorldCup(List<WorldCup> worldCups,int year){
        for (WorldCup worldCup: worldCups){
            if(worldCup.getYear() == year){
                return worldCup;
            }
        }
        return null;
    }

    public static WorldCup findWorldCup(List<WorldCup> worldCups, String host){
        for (WorldCup worldCup: worldCups){
            if(worldCup.getHost().equals(host)){
                return worldCup;
            }
        }
        return null;
    }

    public static int countMatches(List<WorldCup> worldCups){
        int sum = 0;
        for (WorldCup worldCup: worldCups){
            sum += worldCup.getMatches().size();
        }
        return sum;
    }

    public static String askHostName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name of host: ");
        return scanner.nextLine();
    }

    public static int maxGoalDifference(WorldCup worldCup){
        int maxDifference = 0;
        for (Match match: worldCup.getMatches()){
            if(match instanceof PenaltyMatch penaltyMatch){
                if (maxDifference < penaltyMatch.getGoalDifference()){
                    maxDifference = penaltyMatch.getGoalDifference();
                }
            } else {
                if(maxDifference < match.getGoalDifference()){
                    maxDifference = match.getGoalDifference();
                }
            }
        }
        return maxDifference;
    }

    public static void writeSelectedWorldCupToFile(WorldCup worldCup){
        try(PrintWriter writer = new PrintWriter("selected.csv")){
            writer.println("year;stage;date;team_a;team_b;goals_a;goals_b;penalties_a;penalties_b");
            for (Match match: worldCup.getMatches()){
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append(worldCup.getYear());
                stringBuilder.append(Match.SEPARATOR);
                stringBuilder.append(Stage.getStringFromEnum(match.getStage()));
                stringBuilder.append(Match.SEPARATOR);
                stringBuilder.append(match.getDate().format(Match.DATE_FORMATTER));
                stringBuilder.append(Match.SEPARATOR);
                stringBuilder.append(match.getTeamA());
                stringBuilder.append(Match.SEPARATOR);
                stringBuilder.append(match.getTeamB());
                stringBuilder.append(Match.SEPARATOR);
                stringBuilder.append(match.getGoalsA());
                stringBuilder.append(Match.SEPARATOR);
                stringBuilder.append(match.getGoalsB());
                if (match instanceof PenaltyMatch penaltyMatch){
                    stringBuilder.append(Match.SEPARATOR);
                    stringBuilder.append(penaltyMatch.getPenaltyA());
                    stringBuilder.append(Match.SEPARATOR);
                    stringBuilder.append(penaltyMatch.getPenaltyB());
                }

                writer.println(stringBuilder);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static int countWinnerBets(WorldCup worldCup){
        int countWinnerBets = 0;

        for (Match match: worldCup.getMatches()){
            if(match instanceof PenaltyMatch penaltyMatch){
                if (isTeamAWinner(penaltyMatch.getPenaltyA(), penaltyMatch.getPenaltyB())){
                    countWinnerBets++;
                }
            }else{
                if (isTeamAWinner(match.getGoalsA(),match.getGoalsB())){
                    countWinnerBets++;
                }
            }
        }

        return countWinnerBets;
    }

    public static boolean isTeamAWinner(int goalsA, int goalsB){
        return goalsA > goalsB;
    }

    public static Map<Stage,Integer> getTotalGoalsByStage(WorldCup worldCup){
        Map<Stage, Integer> sumGoalsByStage = new TreeMap<>();
        for (Match match: worldCup.getMatches()){
            sumGoalsByStage.put(
                    match.getStage(),
                    sumGoalsByStage.getOrDefault(match.getStage(),0) + match.sumGoals()
            );
        }
        return sumGoalsByStage;
    }
}
