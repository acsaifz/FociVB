package hu.progmatic;

import hu.progmatic.model.Stage;
import hu.progmatic.model.WorldCup;

import static hu.progmatic.util.Services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        List<WorldCup> worldCups = new ArrayList<>();
        if (readWorldCups(worldCups, WORLDCUPS_PATH) && readMatches(worldCups,MATCHES_PATH)) {
            System.out.printf("Number of matches: %d%n", countMatches(worldCups));
            WorldCup selectedWorldCup;
            do {
                selectedWorldCup = findWorldCup(worldCups, askHostName());
                if (selectedWorldCup == null) {
                    System.out.println("Host not found!");
                }
            }
            while (selectedWorldCup == null);
            writeSelectedWorldCupToFile(selectedWorldCup);

            System.out.printf("2. Maximum goal difference %d%n", maxGoalDifference(selectedWorldCup));
            System.out.printf("3. The player has won %d bets.%n", countWinnerBets(selectedWorldCup));
            System.out.println("4. Total goals by stage:");
            for(Map.Entry<Stage,Integer> totalGoalsInStage: getTotalGoalsByStage(selectedWorldCup).entrySet()){
                System.out.printf("%s: %d%n",Stage.getStringFromEnum(totalGoalsInStage.getKey()),totalGoalsInStage.getValue());
            }
        }
    }
}
