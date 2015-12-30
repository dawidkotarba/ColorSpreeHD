package com.dk.colorgame.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dk.colorgame.model.tiles.Tile;
import com.dk.colorgame.model.tiles.impl.bonus.BombTile;
import com.dk.colorgame.model.tiles.impl.bonus.DynamiteBlueTile;
import com.dk.colorgame.model.tiles.impl.bonus.DynamiteGreenTile;
import com.dk.colorgame.model.tiles.impl.bonus.DynamiteTile;
import com.dk.colorgame.model.tiles.impl.bonus.ElectricTile;
import com.dk.colorgame.model.tiles.impl.bonus.MultiColorTile;
import com.dk.colorgame.model.tiles.impl.bonus.StarTile;
import com.dk.colorgame.model.tiles.impl.extra.BlueExtraTile;
import com.dk.colorgame.model.tiles.impl.extra.CyanExtraTile;
import com.dk.colorgame.model.tiles.impl.extra.GreenExtraTile;
import com.dk.colorgame.model.tiles.impl.extra.RedExtraTile;
import com.dk.colorgame.model.tiles.impl.extra.VioletExtraTile;
import com.dk.colorgame.model.tiles.impl.extra.YellowExtraTile;
import com.dk.colorgame.model.tiles.impl.regular.BlueTile;
import com.dk.colorgame.model.tiles.impl.regular.GreenTile;
import com.dk.colorgame.model.tiles.impl.regular.RedTile;
import com.dk.colorgame.model.tiles.impl.regular.VioletTile;
import com.dk.colorgame.model.tiles.impl.regular.YellowTile;
import com.dk.colorgame.service.board.MainBoard;

/**
 * Created by Dawid Kotarba on 2015-06-07.
 */
public class BoardUtils {

    public static Tile randomTile(SpriteBatch spriteBatch) {

        Tile tile = null;

        switch (Utils.getRandom().nextInt(5)) {
            case 0:
                tile = new RedTile(spriteBatch);
                break;
            case 1:
                tile = new BlueTile(spriteBatch);
                break;
            case 2:
                tile = new GreenTile(spriteBatch);
                break;
            case 3:
                tile = new YellowTile(spriteBatch);
                break;
            case 4:
                tile = new VioletTile(spriteBatch);
                break;
        }

        return tile;
    }

    public static Tile randomExtraBonusTile(SpriteBatch spriteBatch, MainBoard mainBoard) {

        Tile tile = null;

        int spawnedTile = Utils.getRandom().nextInt(13);

        switch (spawnedTile) {
            case 0:
                tile = new RedExtraTile(spriteBatch);
                break;
            case 1:
                tile = new BlueExtraTile(spriteBatch);
                break;
            case 2:
                tile = new YellowExtraTile(spriteBatch);
                break;
            case 3:
                tile = new StarTile(spriteBatch);
                break;
            case 4:
                tile = new GreenExtraTile(spriteBatch);
                break;
            case 5:
                tile = new VioletExtraTile(spriteBatch);
                break;
            case 6:
                tile = new DynamiteTile(spriteBatch, mainBoard);
                break;
            case 7:
                tile = new CyanExtraTile(spriteBatch);
                break;
            case 8:
                tile = new BombTile(spriteBatch, mainBoard);
                break;
            case 9:
                tile = new DynamiteBlueTile(spriteBatch, mainBoard);
                break;
            case 10:
                tile = new ElectricTile(spriteBatch, mainBoard);
                break;
            case 11:
                tile = new DynamiteGreenTile(spriteBatch, mainBoard);
                break;
            case 12:
                tile = new MultiColorTile(spriteBatch);
                break;
        }
        return tile;
    }

    public static Tile randomTestTiles(SpriteBatch spriteBatch, MainBoard mainBoard, Tile tile) {

        if (Utils.testLuck(1)) {
            return new DynamiteTile(spriteBatch, mainBoard);
        }
        if (Utils.testLuck(1)) {
            return new DynamiteBlueTile(spriteBatch, mainBoard);
        }
        if (Utils.testLuck(1)) {
            return new DynamiteGreenTile(spriteBatch, mainBoard);
        }
        if (Utils.testLuck(1)) {
            return new BombTile(spriteBatch, mainBoard);
        }
        if (Utils.testLuck(1)) {
            return new StarTile(spriteBatch);
        }
        if (Utils.testLuck(1)) {
            return new CyanExtraTile(spriteBatch);
        }
        if (Utils.testLuck(1)) {
            return new ElectricTile(spriteBatch, mainBoard);
        }
        if (Utils.testLuck(1)) {
            return new MultiColorTile(spriteBatch);
        }

        return randomTile(spriteBatch);
    }

    public static Tile randomMenuTile(SpriteBatch spriteBatch) {
        Tile tile = randomTile(spriteBatch);

        if (Utils.testLuck(5)) {
            tile = randomExtraBonusTile(spriteBatch, null);
        }

        return tile;
    }

    public static int calculateBoardOffset(Texture tileTexture, int tilesNumber) {
        int boardWidth = tilesNumber * tileTexture.getWidth();
        return (int) (GraphicUtils.getWorldWidth() - boardWidth) / 2;
    }
}
