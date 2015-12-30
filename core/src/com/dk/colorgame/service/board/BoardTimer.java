package com.dk.colorgame.service.board;

/**
 * Created by Dawid Kotarba on 2015-05-07.
 */
public interface BoardTimer {

    void reset();

    void start();

    void addSeconds(int seconds);

    String getBoardTimer();

    boolean isGameFinished();

    int getTotalPlayTimeSecs();

    void setCounterBoard(AwardBoard counterBoard);
}
