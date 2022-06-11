package hu.progmatic.model;

public enum Stage {
    GROUP_A,
    GROUP_B,
    GROUP_C,
    GROUP_D,
    GROUP_E,
    GROUP_F,
    GROUP_G,
    GROUP_H,
    ROUND_OF_16,
    QUARTER_FINALS,
    SEMI_FINALS,
    THIRD_PLACE_PLAY_OFF,
    FINAL;

    public static Stage getByString(String text){
        return switch(text){
            case "Group A" -> GROUP_A;
            case "Group B" -> GROUP_B;
            case "Group C" -> GROUP_C;
            case "Group D" -> GROUP_D;
            case "Group E" -> GROUP_E;
            case "Group F" -> GROUP_F;
            case "Group G" -> GROUP_G;
            case "Group H" -> GROUP_H;
            case "Round of 16" -> ROUND_OF_16;
            case "Quarter-finals" -> QUARTER_FINALS;
            case "Semi-finals" -> SEMI_FINALS;
            case "Third place play-off" ->THIRD_PLACE_PLAY_OFF;
            case "Final" -> FINAL;
            default -> throw new IllegalStateException("Unexpected value: " + text);
        };
    }

    public static String getStringFromEnum(Stage stage){
        return switch (stage){
            case GROUP_A -> "Group A";
            case GROUP_B -> "Group B";
            case GROUP_C -> "Group C";
            case GROUP_D -> "Group D";
            case GROUP_E -> "Group E";
            case GROUP_F -> "Group F";
            case GROUP_G -> "Group G";
            case GROUP_H -> "Group H";
            case ROUND_OF_16 -> "Round of 16";
            case QUARTER_FINALS -> "Quarter-finals";
            case SEMI_FINALS -> "Semi-finals";
            case THIRD_PLACE_PLAY_OFF -> "Third place play-off";
            case FINAL -> "Final";
        };
    }
}
