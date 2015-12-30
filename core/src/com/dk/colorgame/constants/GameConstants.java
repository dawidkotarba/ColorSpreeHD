package com.dk.colorgame.constants;

/**
 * Created by Dawid Kotarba on 2015-05-02.
 */
public class GameConstants {

    // externalized properties
    public static final String PROPERTY_SCORE_BOARD_HIGH_SCORE = "highScore";
    public static final String PROPERTY_HIGH_SWIPE_SCORE = "highSwipeScore";
    public static final String PROPERTY_HIGH_SWIPE_TILES = "highSwipeTiles";
    public static final String PROPERTY_HIGH_PLAY_TIME = "highPlayTime";

    // time
    public static final int GAME_INITIAL_TIME_SECS = 120;
    public static final int GAME_MAX_SECONDS_GAIN = 20;
    public static final int GAME_SECS_GAIN_DIVIDER = 12;
    public static final int GAME_START_DELAY = 1;

    // extra tiles multipliers
    public static final int GAME_SCORE_MULTIPLIER_STAR = 3;
    public static final int GAME_SCORE_MULTIPLIER_EXTRA = 2;
    public static final int GAME_TILE_ELECTRIC_ACTIVATE = 2;

    // awards
    public static final int GAME_MIN_TILES_FOR_BONUS = 5;
    public static final int GAME_AWARDS_MIN_TILES = 8;

    private GameConstants() {
        // intentionally left blank
    }
}
