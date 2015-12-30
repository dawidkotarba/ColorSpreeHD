package com.dk.colorgame.model.tiles.impl.regular;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dk.colorgame.constants.Colors;
import com.dk.colorgame.enums.TileFeatureEnum;
import com.dk.colorgame.enums.TileTypesEnum;
import com.dk.colorgame.model.tiles.AbstractTile;
import com.dk.colorgame.resources.Textures;

/**
 * Created by Dawid Kotarba on 2015-04-28.
 */
public class RedTile extends AbstractTile {

    public RedTile(SpriteBatch spriteBatch) {
        super(spriteBatch, Textures.IMG_RED_TILE, Textures.IMG_RED_ACTIVE_TILE);
        setTileType(TileTypesEnum.RED);
        setTileFeature(TileFeatureEnum.REGULAR);
        color = Colors.RED;
    }
}