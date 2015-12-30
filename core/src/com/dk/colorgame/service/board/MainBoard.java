package com.dk.colorgame.service.board;

import com.dk.colorgame.model.tiles.Tile;

/**
 * Created by Dawid Kotarba on 2015-04-28.
 */
public interface MainBoard {

    void create();

    void draw();

    void reset();

    void activateNeighbours(Tile tile);

    void activateRandomNeighbour(Tile tile);

    void activateDiagonalNeighbours(Tile tile);

    void activateRandomTiles(int number);

    Tile getRandomTile();

    public ScoreBoard getScoreBoard();
}
