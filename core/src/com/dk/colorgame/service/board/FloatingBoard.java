package com.dk.colorgame.service.board;

import com.dk.colorgame.model.tiles.Tile;

import java.util.List;

/**
 * Created by Dawid Kotarba on 2015-06-21.
 */
public interface FloatingBoard {
    void draw(Tile firstTouched, Tile lastTouched, List<Tile> activeTiles);
}
