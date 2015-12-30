package com.dk.colorgame.service.board;

/**
 * Created by Dawid Kotarba on 2015-05-10.
 */
public interface AwardBoard {
    void draw();

    void add(String text, int points);

    void addPoints(int points);

    boolean withdrawAwardPoint();
}
