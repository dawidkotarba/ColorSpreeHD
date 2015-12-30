package com.dk.colorgame.service;

import com.dk.colorgame.enums.TileFeatureEnum;
import com.dk.colorgame.enums.TileTypesEnum;
import com.dk.colorgame.model.tiles.Tile;

import java.util.List;

import static com.dk.colorgame.constants.GameConstants.GAME_MAX_SECONDS_GAIN;
import static com.dk.colorgame.constants.GameConstants.GAME_SECS_GAIN_DIVIDER;

/**
 * Created by Dawid Kotarba on 2015-08-22.
 */
public class PointsCalculator {

    public static int calculateAddedPoints(List<Tile> activeTiles, Tile firstTile) {
        if (firstTile == null || activeTiles == null) {
            return 0;
        }

        int selectedByType = getSelectedByType(activeTiles, firstTile);
        int selectedOthers = activeTiles.size() - selectedByType;

        // just in case if there is any null tile in activeTiles
        if (selectedOthers < 0) {
            selectedOthers = 0;
        }

        int selectionPoints = selectedByType * selectedByType - selectedByType;
        float othersToSelectedRatio = (float) selectedOthers / selectionPoints;

        int totalPoints = (int) (selectionPoints + (selectionPoints * othersToSelectedRatio));

        return multiplyPoints(activeTiles, totalPoints);
    }

    public static int calculateAddedSeconds(List<Tile> activeTiles, Tile firstTile) {
        int addedSecs = calculateAddedPoints(activeTiles, firstTile) / GAME_SECS_GAIN_DIVIDER;
        return addedSecs < GAME_MAX_SECONDS_GAIN ? addedSecs : GAME_MAX_SECONDS_GAIN;
    }

    private static int multiplyPoints(List<Tile> activeTiles, int currentPoints) {

        int multiplier = 1;

        if (activeTiles == null) {
            return multiplier;
        }

        for (Tile tile : activeTiles) {
            if (tile != null) {
                multiplier *= tile.getPointsMultiplier();
            }
        }

        return multiplier * currentPoints;
    }

    private static int getSelectedByType(List<Tile> activeTiles, Tile firstTile) {
        int counter = 0;

        if (firstTile == null || activeTiles == null) {
            return counter;
        }

        TileTypesEnum firstTileType = firstTile.getTileType();

        for (Tile tile : activeTiles) {
            // if the same color or bomb, dynamite, star (bonus feature)
            if (tile != null && (tile.getTileType() == firstTileType || tile.getTileFeature() == TileFeatureEnum.BONUS)) {
                counter++;
            }
        }

        return counter;
    }
}
