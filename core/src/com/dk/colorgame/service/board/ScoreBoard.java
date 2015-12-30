package com.dk.colorgame.service.board;

/**
 * Created by Dawid Kotarba on 2015-05-03.
 */
public interface ScoreBoard {

    void reset();

    void draw();

    int fetchHighScore();

    int fetchHighSwipeScore();

    int fetchHighSwipeTiles();

    int fetchHighPlayTime();

    boolean updateHighSwipeScore(int score);

    boolean updateHighSwipeTiles(int score);

    void saveIfHighScore();

    void resetRecords();

    int getCurrentScore();

    void addScore(int score);

    void addSeconds(int seconds);

    boolean isGameFinished();

    void setCounterBoard(AwardBoard counterBoard);

    int getGameplayTime();

    int getCurrentSwipeTiles();
}
